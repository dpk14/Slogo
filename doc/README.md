# SLogo

##### Names of all people who worked on the project:
    * Irene Qiao *
    * Daniel Kingsbury *
    * Diego Chamorro
    * Amanda Madden
    
##### Date started: 2/14
##### Date finished: 3/9
##### Estimate of the number of hours worked on the project:
    * Irene: 25 hours
    * Daniel: 65 hours
    * Amanda: ???
    * Diego: ???

##### Important note: 
Amanda and Diego did not contribute significantly to the second sprint of the project.
Amanda did not push any changes in the second half of the project, even though she was responsible
for working with Diego on the frontend extensions and implementing error handling.

Diego helped set up the modular layout of the project and implemented some of the new visuals in the second
sprint, but did not help implement methods in the Animal class or ScreenOptions that were needed
by the new display Operations and did not separate the Animal backend from the frontend, which was
expected to be done by the night before the deadline. 
The rest of the team has been unable to contact him since the evening before the 
deadline. 

Irene and Daniel did not realize that Amanda and Diego had not completed their tasks until a few hours
before the deadline, and both worked to complete the remaining tasks but could not complete the project
by 3:08am. 

Please consider these circumstances when reviewing this project. Thank you for your understanding.
    
##### Each person's role in developing the project:
    * Irene: 
        * Planning for Command structure
        * Planning for parsing algorithm
        * Creation of all Operations
        * Creation of operations properties file, reading file into operations map
        * Creation of Display Commands and implementation in ScreenOptions class (frontend)
        * Creation of extended Pen commands and implementation in Animal class
        * Creation of ErrorMessage class and error messages properties file
        * Implementation of showing error Alerts in frontend
        * Creation of DisplayModel class
    
        
##### Any books, papers, online, or human resources that you used in developing the project:
    * Javafx documentation
    
##### Files used to start the project (the class(es) containing main): 
    * Main class in module frontEnd, package mainpackage

##### Files used to test the project: none
    
##### Errors you expect your program to handle without crashing:
    * syntactical errors
    
##### Any data or resource files required by the project (including format of non-standard files)
    * resources root: data, under module slogo_team03
    * includes examples, properties files for:
        * default menu colors
        * default menu shapes
        * operations
        * UI button names
        * error messages
       
##### Any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs): none

    
##### Any decisions, assumptions, or simplifications you made to handle vague, ambiguous, or conflicting requirements
    * assume variables are set to a constant value (rather than linked to a state, such as PenUp)
    
##### Any known bugs, crashes, or problems with the project's functionality
    * lack of error handling throughout project
    
##### Any extra features included in the project: none

##### Impressions of the assignment to help improve it in the future:
    * Irene: Fun project, but it's hard to split up work. The parsing was a big task for one person
    to handle, but there was no reasonable way to split up the actual coding of the parser. This assignment
    also required extensive communication among team members. For me, I relied on my team members to implement the methods
    that were called by Operations, so I needed to communicate the methods I needed to members working in 
    all other parts of the project. I wish we could have spent more time learning/practicing how to use the different
    types of ways to separate the frontend and backend, such as binding properties. I think that the binding
    is very interesting but since we were not comfortable using it, we did not use that design method in our project.