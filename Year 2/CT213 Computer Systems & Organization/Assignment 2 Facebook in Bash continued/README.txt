FOR THE SERVER

Steps to ensure the program runs properly.
1. Please delete all locks and pipes that may remain.
(These are automatically removed during 
normal server operation but ensuring that 
these are removed prevents programs 
hanging for the client and the server).

2. Please empty the contents of server.pipe (if it exists yet).

3. Run "./server.sh" in one terminal.

4. Run "./client.sh [YourClientID]" in a different terminal (optional).


FOR THE CLIENT

Run "./client.sh [YourClientID]" in the terminal.

--help
All client requests.
===========================================================
create
Creates a user with your client name.
Will not create a user if the user name already exists.
===========================================================
add [newFriendID]
Add a friend.
Once you have added a user as a friend, 
they can type in your wall.
Another user needs to add you as a friend 
so that you can type in their wall.
===========================================================
post [receiverID] [message]
Post a message in another user's wall.
Only works if that user has added you as a friend.
The message can include spaces.
===========================================================
display [userID]
Displays a user's wall.
===========================================================
