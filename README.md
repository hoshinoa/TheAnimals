# TheAnimals
INF 122 Final Project Group

####Branches
Name | Contributors | Purpose
---   | ---   | ---
Master | all | For production edition of the code.
View | View Subteam | For the view subteam's project development.
hoshinoa | hoshinoa (Alexis) | For project code in development by Alexis.
integration | Erick being a cat
- | - | feel free to add your branch in if it helps you stay organized!

####Server & Client README####

**March 8,  3:10AM hoshinoa**

To run the code from command line:

`cd` until you're in the `src` directory of AnimalsProj.

run `javac Client.java` to compile the Client.

run `javac Server.java` to compile the Server.

run `java Server` to get the Server up and running. It will automatically accept the first Client to join.

run `java Client` to get the Client up and running. It will automatically connect to the Server.

Note that you can also run both from Eclipse, I just thought it was easier to see the separate consoles in dedicated command line windows for each.

The Client only echoes itself, but the Server is able to echo the input stream from the Client. Enter a message in the Client and then a message in Server, and you should see both of them echo respective messages.
Right now the Server only supports echoing one Client at a time (other Clients can still connect, but the Server only constructs a listener socket for the first one in the code). Getting the Server to support multiple Clients (multi-threading) will likely be a next step.
Of the examples I saw, none of them involved the Client echoing messages from the Server so finding an example of that to study will also be a next step.

How to add a new game
-1) Add to client side list of games
-2) New class that extends from Game
- 3) New Logic class that implements GameLogic
-4) Add to Simple Game Factory

Known Bugs (Breaking)
-can't start game until all players are present (will break)
-(an blank room name will break)

Other Bugs (Not breaking, but more missing features)
-technically not allowed to put pieces everywhere in othello (should only place pieces/valid moves to adjacent pieces)
-checkers, doesn't allow for multi-jumps