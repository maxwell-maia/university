#!/bin/bash

request_is_valid=false
prev_input="zzzzzzzzzzzzzzz"
#input="wasssssup"

request="no request stored"
prev_request="no previous request stored"

flag=0

#checking that a parameter has been given
if [ $# -eq 1 ]; then
	id=$1
	echo "client id: $id (not neccesarily an existing user id)"
	
	#I am a client with id=$id. I want to recieve information from the server in the pipe called $id.pipe
	#Create pipe to receive data on
	mkfifo $id.pipe
	
	#Bonus mark question
	## deletes the pipe when ctrl+c is pressed
	trap "rm $id.pipe" EXIT
	

	#endless loop
	while true; do
		#read a request
		read req args
		
		#Requests. $req $id $args
		# $req is a command e.g. create/add/post/display
		# $id is obtained when starting the client.sh script and is automatically added when sending the request to the server. so the user does not have to type it.
		
		
		#Example requests that a user can type
		
		#create
		#add friendname
		#post friendname message
		#display userId
		
		request_is_valid=false #We assume the request is inherently wrong 


		case "$req" in 	
		[Cc]reate)
			request_is_valid=true	
			;;
		[Aa]dd)	
			if [ -n "$args" ]; then
				request_is_valid=true
			fi		
			;;
		[Dd]isplay)
			if [ -n "$args" ]; then
				request_is_valid=true
			fi		
			;;
		[Pp]ost)
			if [ -n "$args" ]; then
				request_is_valid=true
			fi		
			;;
		*)
			echo "Not a valid request"
			;;
		esac
		
		#if the request is well formed...
		if [ "$request_is_valid" = true ]; then
			
			#Send the request to the server. echo into server.pipe
			
			request="$req $id $args"
			
			#Code to prevent the program from hanging when the same request is made to the server twice in a row. (Since the server ignores the same request as the one before[so that it doesn't spam commands in a loop]. we have to send a different request, clear the $id.pipe and then our intended request can be sent)
			if [ "$prev_request" == "$request" ]; then

		 	 echo "the requests are the same, $request/$prev_request"

		 # update server pipe with refresh message
		 	 echo "refresh $id" > server.pipe # refresh command because I want the server to update it's prev_input so it wont skip my next command"
		 	 echo "sent to server: refresh $id"
		 	 echo "before reading refresh, input:$input"

		 	 read input < $id.pipe

		 	 echo "should be refresh success response from server:$input"

		 	 #Hello reader. Below is the line of code I needed. 
			 #I far too many hours trying to find this line. There was an empty character in the $id.pipe for some reason. One of those moments.
		 	 
			 #remove invisible character from pipe
		 	 read input < $id.pipe
		 	


	 		else
	 	 	 echo "the requests are the different, $request/$prev_request"
	
	 		fi
	 
	 
	 		
	 
	 		
			echo $req $id $args > server.pipe #this works
			prev_request="$req $id $args"
		else
			echo "Request not well formed. (requests have the form: req args)"
		fi  #end of if [ isValidRequest ]
		
		
		#SERVER REPLY
		
		#Noted issue: when the user enters the same command as before (and no other user has entered a command), the user updates the server.pipe (as its supposed to)
	    #but this update didn't change what was in the pipe (because the last command was the exact same).

   		#Instead we refresh the pipe and run the command again
		
		read input < $id.pipe


	#If the input is a signal that a wall (file) is being sent over the pipe, read and print the pipe until it says end of the file.
	if [ "$input" == "start_of_the_file" ]; then

	echo "$args's wall:"

	 while [ "$input" != "end_of_the_file" ]; do
		 read input < $id.pipe #reading again skips start of file notification
		 
		 #checking for error statement from the wall 
		 if [[ "$input" == "nok: user id does not exist" || "$input" == "nok: problem with the number of identifiers" ]]; then
		 	echo "User ID does not exist or incorrect number of identifiers. Please check and retry."
		
		#Otherwise we print out the wall until we hit the end of the file message
		 elif [ "$input" != "end_of_the_file" ]; then
		 	 echo "$input"
		 fi
	done
	
	else
	
	#Checking for error cases or if none found we declare success
	case "$input" in 	
		'nok: no identifier provided')
			echo "No input provided, please provide an ID."
			;;
		'1')	
			echo "Incorrect amount of arguments provided, please provide correct amount of arguments for the requested function."
			echo "add - friendname, post - friendname & message, display - userId"
			;;
		'nok: user id already exists')
			echo "The user already exists."	
			;;
		'nok: user friend does not exist')
			echo "The second user id does not exist."	
			;;
		'nok: user id does not  exist')
			echo "The user id does not exist."
			;;			
		'nok: could not add friend as friend')
			echo "Couldn't add second user id as a friend, please check if friends already."	
			;;
		'nok: user sender does not  exist')
			echo "The user id does not exist."
			;;
		'nok: user receiver does not exist')
			echo "The second user id does not exist."
			;;
		'nok: user sender is not a friend of receiver')
			echo "The sender user is not a friend of the recievers, user must be friends to post."
			;;



		*)
			echo "Success"
			;;
		esac

	 
	fi
	
	
		
	#main while loop end		
	done
	
	
fi

echo "nok: no client identifier provided or too many parameters provided"
	exit 1

