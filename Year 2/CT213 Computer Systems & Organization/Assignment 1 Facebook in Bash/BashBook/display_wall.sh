#!/bin/bash


# get user id input.
id=$1


# REQUIRES 1 PARAMETER
# exit program if directory for $id doesn't exist
if ! [ -d $id ]; then
	echo "nok: user '$id' does not exist"
	exit 1
fi

# print the file
echo "start_of_file"
cat $(pwd)/$id/wall
echo "end_of_file"

exit 0
