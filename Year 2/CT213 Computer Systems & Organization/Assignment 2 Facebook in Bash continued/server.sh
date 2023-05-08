#!/bin/bash


while true; do
	# read a new command 
	# It does not matter if the user gives less or more arguments at this point
	read command id id2 message
	
	# check if $id is not empty
	if [ -n $id ]; then
		case $command in
			create)
				#execute the command create and send the standard output in the user $id pipe
				
				./create.sh $ids 
				
				;;
			add)
				./add.sh $id $id2
				;;
			post)
				./post.sh $id $id2 "$message"
				;;
			display)
				# inform the client that we will start senting the content of the wall
				echo "start_of_the_file"
				#execute the display command and save the result in a variable
				clientWall=`./display.sh $id`
				# send the content of the wall stored in clientWall to the client line by line
				IFS=$'\n' # changing the delimiter to a new line
				for line in $clientWall; do
					echo $line 
				done
				IFS=$' ' # bring back the delimiter into its original state (i.e., space)
				echo "end_of_the_file"
				;;
			*)
				echo "These are the only valid commands: {create|add|post|display}"
				#exit 1
		esac
	fi
done
exit 0
