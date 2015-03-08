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
