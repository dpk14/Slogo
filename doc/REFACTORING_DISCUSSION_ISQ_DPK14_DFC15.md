# Refactoring Discussion
NetIDs: isq, dpk14, dfc15

### Organizing Classes into Modules and Packages
* Previously, we had all classes in one module - mainpackage
* We realized that this was bad design because all classes could access each other, even when access was not necessary
* Modules:
    * backend
    * visualization
    * controller
* backend content:
    * Animal class
    * controlStructures package
    * operations package
* visualization content:
    * visualization package
* controller content:
    * ProgramParser class
    * mainpackage.Main class
    * OperationBuilder class
* controller module requires:
    * all backend packages
    * all visualization packages

### Splitting up Commands
* One of the longest methods is .evaluate within MathOperation class because it contained multiple conditional statements
* Previously, we used if statements to determine type of command in order to group together different commands in one class
* This was a bad idea because some classes contain long methods with many conditionals
* This also violated the open/closed principle because adding a command required adding code to a conditional statement within an existing class

### Encapsulation of instance variables
* Previously, we used protected and public variables in superclasses
* This was bad design because it allowed subclasses to change variables in the superclass
* We implemented set and get methods instead for necessary access of private instance variables in the superclass

### Using List/Map instead of specific ArrayList/Hashmap
* Previously, we used ArrayList/Hashmap in method headers, which is bad design because it unncessarily limits the possible implementations that can be used with the methods
 


