#!/bin/bash

#This script will loop, at every loop it will: take in text from the prompt; devide the text into req, id and args variables; check req using the case command; run the script associated with the req using the args from the input.
#In the assignment instructions it says: Every request follows the structure req id [args].
# req id [args]
# I don't know why we need the id because the args contains the id anyway.
# This script will receive the id from the prompt and assign it to a variable called id just as it is in the structure however, it will not be used because the args are all that is necessary.

## id won't be needed but it is included because it is in the specification


#loop
while true; do	
	# get the request, id and args from prompt
	read request id args
	#echo request: $request
	#echo id: $id
	#echo args: $args
	
	# Check if the request has the right format
	if [[ -z $request || -z $id || -z $args ]]; then
		echo "nok: bad request"
		exit 1
	fi
	
	
	case "$request" in	
		create)
			./create.sh $args
			;;
		add)
			./add_friend.sh $args
			;;
		post)
			# dividing args
			#args = maxwell albin Hello bro, how you doing?
			#args = sender receiver message
			
			IFS=" " read sender receiver message <<< "$args"
			#echo $sender
			#echo $receiver
			#echo $message
			#maxwell
			#albin
			#Hello bro, how you doing?
			
			./post_messages.sh $sender $receiver "$message"
			;;
		display)
			./display_wall.sh $args
			;;
		*)
			echo "Accepted Commands: {create|add|post|display}"
			exit 1
	esac
done


