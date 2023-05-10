#!/bin/bash

echo "Initilizing server..."
echo ""

#set up the server.pipe if it hasn't been set up already
mkfifo server.pipe

#On exit delete server pipe to clear it
trap "rm $id.pipe" EXIT 

echo ""



echo "1. Make sure to *REMOVE CONTENTS OF server.pipe* manually before starting the server."
echo "2. AND *REMOVE ANY LOCKS AND "'$'"id.pipes* from the directory."
echo "[Locks and "'$'"id.pipes are removed automatically during the normal operation of the server (including stopping the server) however your server's computer system crashing may cause locks and pipes to be left over. These could cause incorrect commands to be run and could cause the program to hang.]"
echo ""

#placeholder previous input
prev_input="zzzzzzzzzzzzzzz"

while true; do
	
	# read a new command 
	# It does not matter if the user gives less or more arguments at this point
	# read command id id2 message
	
	#constantly read from the server pipe.
	
	read input < server.pipe
	
	
	
	# if input is different, understand the request *AND execute it. *This is assuming the request is well formed, which it is (done in the client script).
	
	if [ "$prev_input" != "$input" ]; then
		#Need to read "command id id2 message" variables from what comes in the pipe
		#echo $input | (read command id id2 message)
		read command id id2 message <<< $input
		
	##Server status messages
	echo "==========================================="
	echo "Request recieved from server.pipe. Interpretting request."
	echo "Request='$input'"
	echo "VARIABLES FROM REQUEST:"
	echo "command: $command"
	echo "id: $id"
	#IS id in the client script different from the id of the user that the client uses?
	#I guess thats a design decision. 

	#The client id will become the user id. when the client requests to create a user, 
	#the server creates a user with the client's id, so they kind of become the same.

	echo "id2: $id2"
	echo "message: $message"
	

	#Run the command accociated with the request.
	echo "Executing command with parameters..."
	#SYNCRONISATION AND COMMANDS

	#We are sorry about the commands all being on one line, this is for syncronisation with the following reasoning;
	
	#the & means that the program will continue running, it will not wait for this line to finish.
	#the line will finish on it's own and return the input to the user on its own.
	#This means that the server can take requests from multiple people at once. (This is why we need syncronisation)!

		case $command in
			[Cc]reate)
				#execute the command create and send the standard output in the user $id pipe
				
				
				#The echos can be removed in server implementation for better performance.
				#*The echos in the line below are part of the critical section because another request might change the $id*,
				#the echos are there for debugging and demonstration purposes.

				./acquire.sh "$id"_lock; echo "Executing create command for user: '$id'"; ./create.sh $id > $id.pipe; echo "Create command completed, returning output to user: '$id'"; ./release.sh "$id"_lock &
				;;
			[Aa]dd)
						
				#Echos can be removed for performance.

				#Only lock one client
				./acquire.sh "$id"_lock; echo "Executing add command for user: '$id'"; ./add.sh $id $id2 > $id.pipe; echo "Add command completed, returning output to user: '$id'"; ./release.sh "$id"_lock &

				;;
			[Pp]ost)
			#Echos can be removed for performance.
			
				#Cannot lock the same id twice
				#Check that the id's are different
				if [ "$id" != "$id2" ]; then
					#Lock 2 clients
					./acquire.sh "$id"_lock; ./acquire.sh "$id2"_lock; echo "Executing post command for user: '$id'"; ./post.sh $id $id2 "$message" > $id.pipe; echo "Post command completed, returning output to user: '$id'"; ./release.sh "$id2"_lock; ./release.sh "$id"_lock &
				else
					#Only lock one client
					./acquire.sh "$id"_lock; echo "Executing post command for user: '$id'"; ./post.sh $id $id2 "$message" > $id.pipe; echo "Post command completed, returning output to user: '$id'"; ./release.sh "$id"_lock &
				fi
				
				
				;;
			[Dd]isplay)
			
				
				
				#DISPLAY WALL and send output to client that requested it (id)
				#The echo lines are used to notify the client when to start and when to stop reading lines from the pipe

				#The "echo "result: returning wall to '$id.pipe'";" bit can be removed for performance reasons.
				
				##Note id2 is the user we want to see the wall of
				
				#Cannot lock the same id twice
				#Check that the id's are different
				if [ "$id" != "$id2" ]; then
					#Lock 2 clients
					./acquire.sh "$id"_lock; ./acquire.sh "$id2"_lock; echo "start_of_the_file" > $id.pipe; ./display.sh $id2 > $id.pipe; echo "end_of_the_file" > $id.pipe; echo "result: returning wall to '$id.pipe'"; ./release.sh "$id2"_lock; ./release.sh "$id"_lock &
				else
					#Only lock one client
					./acquire.sh "$id"_lock; echo "start_of_the_file" > $id.pipe; ./display.sh $id2 > $id.pipe; echo "end_of_the_file" > $id.pipe; echo "result: returning wall to '$id.pipe'"; ./release.sh "$id"_lock &
				fi
				
				
				
				
				
				
								
				
				;;
			refresh)
				#Special command to resfresh the item in the server.pipe.
				#This allows the same user to send the same command twice in a row.
				#All it needs to do is tell the user that the refresh is successful and complete. 
				#This will change the prev_input variable too so that the next command will run. 
				#(the same command doesn't run twice because otherwise the server would coninously spam the most recent command as it is in a loop.)
				echo "result: refresh success. Sending response to user."
				echo "refresh success" > $id.pipe
				
				;;
			*)
				echo "result: invalid command. Sending response to user."
				echo "These are the only valid commands: {create|add|post|display}" > $id.pipe
				
		esac
		
		prev_input=$input
		
		
	fi
	
	
done
exit 0

