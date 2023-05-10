#!/bin/bash



dir_name_user_name=$1

# REQUIRES ONLY 1 PARAMETER
# exit program if any other than 1 param is given
if [ $# -ne 1 ]; then 
	echo "nok: no identifier provided" >&2
	exit 1 #exit saying something bad happened
fi

# exit program if director already exists
if [ -d $dir_name_user_name ]; then
	echo "nok: user already exists" >&2
	exit 1
fi

# user doesn't exist
# create directory
mkdir $dir_name_user_name

# create wall.txt
touch $(pwd)/$dir_name_user_name/wall

# create friends.txt
touch $(pwd)/$dir_name_user_name/friends

echo "ok: user created!" >&2
exit 0
