#! /bin/bash

#If there were no parameters passed, display an error.
if [ -z "$1" ]; then
	echo "Usage $0 mutex-name" >&1
	exit 1
else
	#Release the lock
	rm "$1"
	exit 0
fi
