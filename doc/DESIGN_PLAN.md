# Design Plan

## Introduction

- Our team is trying to make programming accesible to children by creating a friendly programming language. Our goal is to make this project visually appealing and create an interface with the ability to execute complex instructions. Primarily, we want the backend to be flexible so we can add the most functionality to our program. 
- Our program has three main parts: visualization, Parser, and the Commands. The visualization portion communicates directly with the Parser (the inputted text), and then the Parser calls upon the commands depending on the input. The Parser is basically what links the front-end with the back-end.


## Design Overview 

#### UI class
* Behavior:
    * Display Turtle, command box (with past commands displayed), "interpret" button, draw lines
    * Take in lines of input from user, store in a Collection until user clicks "interpret"
    * When "interpret" is clicked, feed in each string of commands to the Parser by calling Parser read() method
    * Also add each string of commands to a Collection of past commands within SystemStorage by calling SystemStorage.recordCommand()
    * Draw a line given parameters passed in from Turtle class when called by appropriate Command subclass
    * Display errors from Parser to user in a friendly format
* Public methods:
    * drawLine()
    * setLineColor()
    * displayError()

#### Turtle Class
* Behavior: 
    * Move by a specified number of pixels
    * Rotate
    * Leave a trail when moving
    * Become visible/invisible
* Public methods:
    * drawLine() //needs to call UI class method
    * move()
    * rotate()
    * setVisibility()
* States:
    * direction vector/heading
    * x and y location
    * penDown boolean
    * isVisible

#### Command superclass
* Behavior:
    * execute a type of command, overriden by each subclass implementation
    * constructor assigns parameter values to variables relevant to execution of command
* Public methods:
    * constructor
    * execute()

* Subclasses:
    * TurtleCommand - access to Turtle objects in SystemStorage
        * TurtleMovement
        * TurtleQuery
        * TurtleShow - access to UI
    * DrawCommand - access to UI and Turtle
        * DrawLine
        * LineAppearance
    * VisualCommand
        * BackgroundColor
        * TurtleImage
        * ChooseLanguage
    * VariableSetter - access to variables map in SystemStorage
    
#### Expressions superclass - for inputs that return values
* MathOperation - access to variables map in SystemStorage
* BooleanOperation - access to variables map in SystemStorage

#### Other input structures
* ConditionalStructure
    * IfStructure
    * IfElseStructure
* LoopStructure
    * Repeat
    * DoTimes
    * For
* ToCommand

#### System Storage
* Behavior:
    * record past commands in Collection of String commands
* Public methods:
    * recordCommand() called by UI after UI calls Parser to read command
* States:
    * Map of variable name to variable value
    * Collection of String commands
    * Collection of Turtle objects
    * Collection of UI's

#### Parser
* Behavior:
    * reads String input passed from UI and checks for key words
    * instantiates correct Command objects for each user command
    * processes each string command to determine variable values, perform math/boolean operations, general structure, etc. before feeding in values into Command constructor as parameter values
    * Throws errors if no key words are detected/incorrect syntax - call on UI to display errors to user
* Public methods:
    * read()

## User Interface

- https://imgur.com/a/HPXfSPW <- image of GUI
- The user will write commands in the text box, and when he is ready to run them will press interpret. 
- There will be an option where the user can change the animal on the screen. 
- If the command is not correctly written, or the command does not exist then an error message will print on the screen. 


## API Details 

Internal:
The internal API interacts with the external API via the Parser class. The parser takes a command string and identifies key words or characters in the string that distinguish the kind of command it is using a body of conditional statements. The parser is easily extensible, because once new command types are introduced, the conditionals merely need to be extended for those cases. If no key word is identified (the else case) an error is thrown. This will remain the same regardless of whether there is an extension. If a key word is identified, a new instance of the Command subclass specific to the command type is made using the original command line String or parts of the String as a constructor input. Command is a superclass with an abstract execute method and an operation method. The operation method determines if a basic mathematical operation is being performed, then simplifies it into a variable that can be directly stored by one of the subclass-specific variables. There are multiple subclasses of Command, each defined by the command type. The constructor of these subclasses uses the string input to define unique instance variables, and throws errors if there is a command-specific syntactical error in the string input. The command.execute() method performs the unique subclass operations directly on the turtle using the uniquely defined variable set. The command set is therefore easily extensible, because new subclasses for those commands will just need to be made in the grain of the others. All of the commands will access the SystemStorage class, which stores all user variabes, commands, and the turtles. It will contain methods for getting, setting, and clearing variables in memory. It is easily extensible, because new data types simply need to be added as needed, while the actual methods will be retained. Also, we will use a Turtle HashMap containing a set of turtle names mapped to turtle objects, as we anticipate that the extensions will involve multiple turtles. This map will be stored in memory along with other data sets.  

External: 
* UI and Parser
    * The user inputs text, and this text is sent to the parser for processing when the user hits the "interpret" button - this is done by calling a public parser method with the input text as a method parameter 
    *  Then the parser decides which command to execute, and then the command affects the turtle or other variables in storage. Each command is executed by constructing a command subclass object. 
    * The Turtle  displayed in the UI changes visual appearance/location when the Turtle state is changed by the execution of some relevant command
    * Likewise, lines drawn in the UI are the result of the parser contructing a Command that calls a public Turtle class method that has access to the UI canvas
    * UI will have access to objects stored in System Storage class, which can be modified by relevant Command subclass methods. For example, the Turtle will be stored in the System Storage and Turtle methods such as move() can be called by Command movement subclass. The move method would alter the Turtle state, which is tracked by the UI and the location of the Turtle would be appropriately changed.
    * Turtle instance variables will be encapsulated, but there will be specific public methods accessible to relevant Turtle commands that will allow modification of these variables.
    * Errors thrown: 
        * Lack of key word
        * Incorrect syntax
        * Variable/object does not exist

## API Example Code
* fd 50
    * UI: takes 'fd 50' string and sends it to parser. parser.read(string)
    * UI sends "fd 50" string to be stored in Command History Collection in SystemStorage by calling SystemStorage method addCommand() and inputing string as method parameter
    * Parser determines which command to run depending on the string passed in to it. 
    * Command subclass Movement calls on SystemStorage to access the Turtle (or whatever else is being commanded to move)
    * Movement calls public move() method from Turtle object stored in SystemStorage, inputs "forward" and "50" as method parameters
    * (assuming pen is already down) Turtle move() method contains boolean if(penDown) that calls private Turtle drawTrail() method

* sum 10 12
    * UI: takes in "sum 10 12" string and sends to Parser
    * Sends "sum 10 12" to SystemStorage HistoryCollection by method addCommand()
    * Parser detects key word "sum" and passes "sum," 10" and "12" as parameters to mathematical operation
    * Mathematical operation returns value - nothing else happens


* x = 10, y = 20, fd sum x y
    * UI stores each separate line of command as a string until "interpret" button is clicked
    * Then, each string is sent to the parser
    * x = 10 and y = 20 are recognized as variable setting commands by Parser, which then calls the Command MakeVariable subclass which adds the entries ("x", 10) and ("y", 20) to the variables Map in the SystemStorage class
    * The fd keyword is recognized by Parser, which creates the Command Movement subclass that takes in "fd" and "x+y" as parameters
    * "sum x y" is then recognized to contain variables x and y (using Command superclass private method scanVariables()), and the respective values are retrieved
    * Then, the sum  is recognized by the Command superclass private method scanOperation() that checks for operations and performs the math
    * After sum x y is calculated, the numerical value 30 is passed into the parameter for public method move() belonging to the relevant Turtle object that is accessed by the Command Movement subclass from SystemStorage
    * The Turtle then changes location, which is visible in the UI


## Design Considerations 
This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. Include any design decisions that the group discussed at length (include pros and cons from all sides of the discussion) as well as any ambiguities, assumptions, or dependencies regarding the program that impact the overall design.

* Splitting the UI into separate classes for each component - console, errors, canvas
    * More freedom to manipulate and extend specific features of the UI
* Expressions class containing mathematical operations and boolean operations that extends Commands superclass vs. not extending Commands superclass
    * Expressions operations have return values while Commands return void, hence execute() in Command superclass can not be overriden
* Ambiguity: keeping track of the original string command input by user or adding the Command object to the HistoricalCommands collection
    * Pros of storing string: contains full information to re-display if necessary - ex: fd vs. forward are the same command but different strings
    * Pros of storing command itself: storing method call can be called by Parser or Command itself - in other words, the UI would not need to call SystemStorage method to store the command
* visualization to Parser handling info - consider using queues vs. lists to keep track of past Commands and order of interpretation for each line
    * How best to preserve order of commands in the execution? Deferred to later discussion

## Team Responsibilities
* Daniel: Parser, Commands
* Irene: Parser, Commands
* Diego: visualization
* Amanda: Parser, Commands