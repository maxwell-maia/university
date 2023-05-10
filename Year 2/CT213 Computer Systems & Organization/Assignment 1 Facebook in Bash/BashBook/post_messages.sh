#!/bin/bash

sender=$1
receiver=$2

noArgs=$#

# exit program if directory for $sender doesn't exist
if ! [ -d $sender ]; then
	echo "nok: user '$sender' does not exist"
	exit 1
fi

# exit program if directory for $receiver doesn't exist
if ! [ -d $receiver ]; then
	echo "nok: user '$receiver' does not exist"
	exit 1
fi

# check that the user $sender is in the list of friends of the user $receiver before posting to the wall

# if sender isn't in receivers friends. then we show an error and exit 1
if grep "^$sender" "$receiver"/friends > /dev/null; then
	# put $sender: message in wall.txt of receiver
	echo "$sender: $3" >> $(pwd)/$receiver/wall

	#success message
	echo "ok: message posted!"
	exit 0
fi

# error message. sender not in friend list of receiver
echo "nok: user '$sender' is not a friend of '$receiver'"
exit 1


