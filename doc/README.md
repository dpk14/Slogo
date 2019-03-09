# SLogo

* names of all people who worked on the project:
    * Irene Qiao
    * Daniel Kingsbury
    * Diego Chamorro
    * Amanda Madden
    
* date started: 2/14
* date finished: 3/9
* estimate of the number of hours worked on the project:
    * Irene: 20 hours
    
* each person's role in developing the project:
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
        
* any books, papers, online, or human resources that you used in developing the project:
    * Javafx documentation
    
* files used to start the project (the class(es) containing main): 
    * Main class in module frontEnd, package mainpackage

* files used to test the project:
    * none
    
* errors you expect your program to handle without crashing:
    * syntactical errors
    
* any data or resource files required by the project (including format of non-standard files)
    * resources root: data, under module slogo_team03
    * includes examples, properties files for:
        * default menu colors
        * default menu shapes
        * operations
        * UI button names
        * error messages
       
* any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs)
    * n/a
    
* any decisions, assumptions, or simplifications you made to handle vague, ambiguous, or conflicting requirements
    * assume variables are set to a constant value (rather than linked to a state, such as PenUp)
    
* any known bugs, crashes, or problems with the project's functionality
    * lack of error handling throughout project
    
* any extra features included in the project: none

* your impressions of the assignment to help improve it in the future:
    * Irene: Fun project, but it's hard to split up work. The parsing was a big task for one person
    to handle, but there was no reasonable way to split up the actual coding of the parser. This assignment
    also required extensive communication among team members. For me, I relied on my team members to implement the methods
    that were called by Operations, so I needed to communicate the methods I needed to members working in 
    all other parts of the project. I wish we could have spent more time learning/practicing how to use the different
    types of ways to separate the frontend and backend, such as binding properties. I think that the binding
    is very interesting but since we were not comfortable using it, we did not use that design method in our project.