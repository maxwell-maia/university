#! /bin/bash

#If there were no parameters passed, display an error.
if [ -z "$1" ]; then
	echo "Usage $0 mutex-name" >&1
	exit 1
else
	#Create a lock if there is none and exit. If there is a lock then sit and wait.
	#If an operation involves a client then a lock is be made for the client. The lock is only released when the operation is complete. 
	#If, before this command is completed, another command (by the same client or another client) that involves this client is run, then an attempt to acquire this client's lock is made. The lock would already exist and the second command's execution would stay within the loop in this script until the first command is complete and the lock is released.
	while ! ln "$0" "$1" 2>/dev/null; do
		sleep 1
	done
	
	exit 0
fi

