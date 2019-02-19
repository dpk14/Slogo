public class NewParser {

    private int parseCommand(int currentIndex){
        Command currentCommand;
        String currentString=currentLine.get(currentIndex);
        if (IsAlphabetic(currentString)) {
            currentString=currentString.toLowerCase();
        }
        if (currentString.equals("home")) {
            currentCommand = new movementCommand(currentString, 0);
        }
        else if (currentString.equals("forward") || currentString.equals("fd") || currentString.equals("backward") || currentString.equals("bd")){
            currentCommand=new movementCommand(currentString, 1);
        }
        else if (currentString.equals("setxy") || currentString.equals("goto")) {
            currentCommand = new movementCommand(currentString, 2);
        }
        else if (currentString.equals("right") || currentString.equals("rt") || currentString.equals("left") || currentString.equals("lt") || currentString.equals("setheading") || currentString.equals("seth")) {
            currentCommand = new rotationCommand(currentString, 1);
        }
        else if (currentString.equals("towards")) {
            currentCommand = new rotationCommand(currentString, 2);
        }
        else if (currentString.equals("penup") || currentString.equals("pu") || currentString.equals("pendown") || currentString.equals("pd")) {
            currentCommand = new drawCommand(currentString, 1);
        }
        else if (currentString.equals("showturtle") || currentString.equals("st") || currentString.equals("hideturtle") || currentString.equals("ht")) {
            currentCommand = new turtleStateCommand(currentString, 1);
        }
        else if (currentString.equals("make") || currentString.equals("set")){
            currentCommand = new setterCommand(currentString, 2);
        }
        else return currentIndex;
        String[] simplifedArguments=Simplifier.simplifyCommandArguments(myCurrentLine, currentIndex, currentCommand.getArgumentNumber());
        currentIndex=Simplifier.getCurrentIndex();
        currentCommand.setArguments(simplifedArguments);
        currentCommand.execute();
        return currentIndex;
    }
