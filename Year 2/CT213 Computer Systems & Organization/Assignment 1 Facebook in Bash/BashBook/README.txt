Run the server.sh script. This script will allow you to enter requests into the prompt. A request follows a particular format, see below. Also see below the syntax for using each command. The requests will make use of the other scripts in this program: create.sh, add_friend.sh, post_messages.sh, display_wall.sh.



Format for a request

req id [args]
req: the accepted commands {create|add|post|display}
id: user id
args: all arguments required for the chosen command. Note, if the id is a neccesary argument for a command, the id still needs to be provided in args as it is an argument.

NOTE: In the assignment instructions it says: Every request follows the structure req id [args]. I don't know why we need the id because the args contains the id anyway. The server script will receive the id from the prompt and assign it to a variable called id just as it is in the structure however, it will not be used for anything because if a command needs id as an id it will be given as part of its argument.


====================

Create a user
syntax:
create id username

example:
create id albin

result:
directory "albin" will be created with "wall" and "friends" files

--------------------

Add a friend
syntax:
add id user newFriend

example:
add id albin maxwell

result:
"maxwell" will be added to the friend file of albin

--------------------

Post a message
syntax:
post id sender receiver message

example:
post id maxwell albin hi you stink like shit today
maxwell puts a message in albin's wall that reads : hi you look great today

result:
line added to albin/wall
the line reads as follows: 
maxwell: hi you look great today

--------------------

Display a wall
syntax:
display id username

example:
display id albin

result:
writes albin's wall to the terminal

--------------------


