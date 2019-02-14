# SLogo Architecture Design

When does parsing need to take place and what does it need to start properly?
- Parsing needs to take place after the enter key, whatever the user has inputted in that line is 'parsed' and acted upon
- This input is taken from the UI class and handed to the Parser class

What is the result of parsing and who receives it?
- If the input is valid, then the parser creates a Command object that is executed by the Execution class
- The Command object is also stored in the Execution

When are errors detected and how are they reported?
Errors are detected in the parser?

- Errors are detected in the parser
- The parser breaks down the string from the command line into its component parts
- contains a bunch of conditional statements that decide what kind of commandment it is, then calls that subclass commandment
- if none of those conditions are satisfied, then return error, because it is a type of commandment not registered
- If there is an error, then the command is not called
- Return an error alert to the user

What do commands know, when do they know it, and how do they get it?

- Commands are interpreted by the Parser, which calls upon the appropriate subclass of command
- Each subclass has different access to methods and would execute only the methods available to them
- examples of command subclasses: movement, rotation, drawing
- these classes break apart the string sent by the parser and uses them to define specific variables
- commands operate on turtle according to these variables
How is the GUI updated after a command has completed execution?

- Each command would change some type of state - of the Turtle, of variables present, canvas etc. so the change in each state is reflected in the GUI
- 
Notes:
- UI->parser
- Parser->
- Turtle->commands
- Pen