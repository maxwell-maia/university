#!/bin/bash

id=$1
friend=$2


# exit program if directory for id doesn't exist
if ! [ -d $id ]; then
	echo "nok: user '$id' does not exist" > /dev/null # >&2
	exit 1
fi


# exit program if directory for friend doesn't exist
if ! [ -d $friend ]; then
	echo "nok: user '$friend' does not exist" > /dev/null #>&2
	exit 1
fi

# check that the user $friend is not already in the list of friends of the user $id before adding them
# if are already friends
if grep "^$friend" "$id"/friends  > /dev/null; then
	#echo "$friend is in $id's friend file"
	exit 0
fi

# else: "$friend is not in $id's friend file"
# add them
echo "$friend" > $(pwd)/$id/friends
echo "friend added!" > /dev/null # >&2
exit 1

# are the error messages redirected to the correct place. it says so on page 2.



