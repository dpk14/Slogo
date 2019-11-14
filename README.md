Design Review
===
Author: Daniel Kingsbury

## Summary
A user-friendly, graphically-driven IDE allowing juvenile coders to develop programs in SLogo: a simplified version of the Logo language. Users can dynamically code a turtle to draw complex geometrical patterns by using existing control structures or developing their own.  

## General Structure:

The front end is dominated by the Main class, which initializes and holds instances of all the other visualization elements (such as the root), recognizing user interaction with these elements, updating them as necessary, primarily when the user runs the code he or she has written.

Main compiles the code and then executes it using appropriate combinations of back end classes, which parse the code and perform appropriate alterations on the variables of models representing the turtles and other user-modifiable aspects of the display. The front end then interprets these models, performing the appropriate parallel operations on the ImageView represented by the model. Through these models and front end model interpreter classes, the front end is able to communicate with the back end.

The back end breaks apart code into a combination of nested or separated Control Structures, which are essentially instructions for visiting the following code and performing operations on the turtle. For instance, "repeat" is a control structure. The control tag "repeat" indicates that the following code is to be visited a certain amount of times. Parsing methods in the Control Structure superclass simplify the code by modifying a copy of it, reducing everything into a return value. This way, nested control structures are simplified first, replacing themselves with a return value, so that the outer control structures can be executed next.

"Operations," which are any command or expression with arguments, simplify themselves into return values in a similar fashion. Every time an operation name is read, an "OperationBuilder" is created and added to a stack. Operation builders simplify following code into fully reduced arguments according to the number of arguments expected, then create the appropriate operation, giving
it the simplified arguments. The code then performs .evaluate or .execute (according to whether the code is in compilation or execution stage) on the newly initialized Operation, which modifies the corresponding turtle model by performing its unique action using
its arguments.

## Dependencies:

The back end API is very concise. The Main controller only interacts with the back end by instantiating a starting Control Structure to evaluate the code, then calls a single public .executeCode method to simplify this control structure. The Control Structure recursively parses the rest of the code internally.

The controller also grabs the updated list of back end models stored in the backend SystemStorage, then updates the front end according to these models. So essentially, there are two dependencies. One is .executeCode, which links the frontEnd to control structures via Main. The other is copy of SystemStorage accessible to Main and the Interpreters, which update the front end using the models of the front end objects contained in the back end.

The Operations package is easy to use, because the subclasses classes are all very short and simple, divided up as much as possible,
with very few methods that need to be overridden (pretty much only .execute or .evaluate, or both). In .execute or .evaluate,
you just have to ask yourself what objects the operations will be manipulating, correspond them to an appropriate backEnd model, then modfiy the model or just find the return value of the operation.

The implementation of Operations can be easily changed, because Operations are practically only accessible to Control Structures (which simplify them by very general criteria) and to the ProgramParser, which contains a map of default Operations that the Control Structure can use to find out what Operation the code is referring to. Operations don't have access to very many variables at all, and are more tools than manipulable objects, hence their strong encapsulation.

## ProgramParser:

The ProgramParser has methods to read a text file and reflectively produce maps of default operations and control structures. These defaults can be copied over and their arguments can be filled from the surroundings. 

## Main:

The main class initializes the ProgramParser's definition maps, then initializes and owns instances of other Visualization components. When the user runs the code, the code is sent to the method evaluateInput, which compiles and executes it by identifying the outer Control Structure type, executing it, then moving to the next one, etc. 


## ControlStructures:

ControlStructures have many subclasses containing the rules defining how the surrounding code should be visited and interpreted. ControlStructures use the large number of superclass parsing methods to visit surrounding code, and recursively simplify code and replace it with return values where necessary. In the process of evaluating lines, Operations and nested ControlStructures are parsed from the text and initialized appropriately. parseOperation finds the appropriate operation and adds it to a stack of operationBuilders, which simplify the following arguments until the expected number is found, at which time the operation is initialized with the arguments and evaluated or executed and then replaced with a return value. Loops store saved copies of unsimplified code to be restated every new iteration of the loop.


## Operations: 

Operations in code modify turtle models and display models, which are JavaFX-less back end representations of visual elements.
Once Main processes user input, it uses Interpreters to reflect the changes in the models on corresponding front end elements.
Interpreters use the instance variables of each model object to set the corresponding state in the corresponding visual object.

Depending on the Operation type, it may require a display model or active turtle model to manipulate, or various classes for math operations. It is extremely independent, and requires almost no resources. It is simply a translation of the actual actions in the code, containing corresponding Java actions. Everything is encapsulated, and extensibility is maximized because of the large variety in the subgroupings of Operations, and because hardly any Operation superclass methods need to be overridden. Operations have arguments given to them by the Builder, and simply manipulate them directly or model javafx actions by changing the instance variables of the active model turtle. .Evaluate just calculates the return value, and .execute performs actual necessary actions.


## OperationBuilders:

OperationBuilders model the recursive behavior of code simulation through the use of readable stack loops, filling arguments of nest Operations by simplifying and replacing with return values. They contain methods which determine whether an Operation's arguments have been found, after which the Operation is constructed with its arguments, after which the Operation is ready to be popped from the stack and evaluated or executed. This prevents unformed, incomplete Operation objects from floating around visibly. Operations are created only when nested operations in their arguments have been found by the Builder.

Builders are a useful and versatile interpretive tool that is able to both construct Operations and modify surrounding user input text
to reflect the evaluated returns of Operations. There are no evident dependencies in this code. OperationBuilders are initialized
and used by outer Control Structures the modify code and generate instructions from it. It is a tool rather than a fixed entity, so
its contents or functionality are not dependent on the surrounding code that called it.


## Extensibility: 

The code is structured in such a way that it is easy to add new commands to the language.

If the command is a control structure (a way of visiting code: for, repeat, ifelse, and multiple turtle commands) a Control Structure subclass is created, and the appropriate .simplifyAndExecuteStructure() method is called, which uses an appropriate combination of superclass parsing methods to interpret the surrounding lines according to the "rules" of the Control Structure type.

If the command is an operation (anything which has solely double arguments and modifies numbers or existing objects accessible to the user), then a subclass of the appropriate operation subclass is created, containing a unique overridden .execute or .evaluate method
determining how the operation uses its arguments to modify numbers or elements visible to the user. Also, the operation must be added
to the reflection files so that the Program Parser can make a default instance of it, and add it to a map keyed by the operation name, so that copies can be made (and their arguments filled) any time the operation is seen. 

The .evaluate and .execute methods of Operations simply have to be overridden with usually short code storing appropriate arguments in appropriate instance variables and acting on the appropriate model. ControlStructure subclasses usually just need to override one method, which interprets and uses the contents of the reduced format of the following code to
define and use the instance variables of the ControlStructure.


## ControlStructures:

We were originally debating having ControlStructures as a subclass of Operations, as they both have some number and variety of
expected arguments, and both have one or two main overridden methods that simplify code and produce return values, and would both be
constructed by stacks of Builders. If we were able to implement this, the code could be reduced significantly in complexity, and
the similar parsing code that handles Operations and ControlStructures could be reduced into one model. However, unification
would have restricted extension capability as commands became more and more complex. The inherent conceptual difference between
commands that deal with logic, math, or manipulating the screen (Operations) are inherently different than ControlStructures
(commands like For and Repeat) which handle how code is visited. These conceptual differences lead me to believe that
the code is better off as it is, with ControlStructures as a separate entity. It is important not over-implement one class so much
that extensibility is stifled.

## Main:

Originally we planned on storing important Maps and states of various models in Main. Instead, we ended up preferring
a straightforward and minimally cluttered main, storing all such variables in SystemStorage. I think it would have been a much
better idea to take out SystemStorage and put these variables in Main. SystemStorage is a passive, holder class, which
doesn't really have any real utility other than keeping track of global instance variables. If any kind of ControlStructure or
Visualization class needs a SystemStorage object, whatever method that created the class and is handling the class
must give SystemStorage to the constructor of the class, even if the class only needs one element from Storage.
Alternatively, Main could just store all the SystemStorage variables directly and give out each one as needed in the constructors
of any classes it creates. This way, a passive class with usually superfluous data is not passed around aimlessly from class to class.


## Conclusions:

I think the best feature of the code's design are the maps in the ProgramParser, namely the Operation Maps. Using a
properties file, methods within the Parser reflectively create Maps with default operations/Control Structures and their associated
keys. If an Operation name is detected in the code, parsers get the default, copy it, and initialize it with the appropriate
variables. This exemplifies good code because it minimizes conditionals, and takes away responsibility from the programmer,
allowing a game designer to add operational variety to the program without needing to alter any code at all. Furthermore, the use
of a map of default cases is more efficient, because repeatedly found operations will
not have to be called and created from the file directly every single time.

I think the worst feature in the design is the SystemStorage class. It is a passive holder for key universal variables, dominated
by setter and getter methods, and has no legitimate functionality. It allows variables to be concisely translated between classes.
When a class needs something from SystemStorage, SystemStorage is given to the class in its constructor, even though
the class may need only one variable from it. All variables in SystemStorage should just be set in Main and given individually
as they are needed to ControlStructures or visual elements that need them. This allows for greater conciseness and removes lots of
dependencies (some classes will inevitably have to import the entire package SystemStorage is contained in just
to get one variable from it).

In future projects, I should start placing a higher reliability on data files to drive extension. I want to be able to use elementary logic in these files to enhance their functionality, reducing the actual code modification necessary upon each extension. I want to plan out concise and efficient language to use in these files to enhance the logical capabilities of these files, then design a parser to interpret this information.

I think I should definitely make the code modular earlier in the project, preferably even before we do anything at all.
Our original philosophy was to plan minimizing dependencies as we coded by keeping in mind a general modular separation all along,
giving us greater freedom to mutate the dependency layout as necessary later on. However, not everyone was fully on the same page
with what dependencies were allowable and which weren't, so we ended up having to spend a lot more modularizing than we
anticipated, and for a temporary period of time on a night planned for debugging the code was not functional at all (because modulizing had not been fully completed). It's much better to have somewhat restrictive code than build up to a moment where
functioning code must be broken and completely dysfunctional in order to work again.
