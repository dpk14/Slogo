import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewParser {

    ArrayList<String> currentLine;
    Simplifier simplifier=new Simplifier(); //must be reinitialized after every line, so must also reinitialize parser

    public void evaluateInput(String currentLine) {
        String[] currentLineWithoutSpaces = currentLine.split(" ");
        currentLine = new ArrayList<String>(Arrays.asList(currentLineWithoutSpaces));
        int currentIndex=0;
        boolean isCommentOrBlank=currentLine.size()==0 || currentLine.get(currentIndex).equals("#");
        if (isCommentOrBlank) return;
        //checkControlTags (like for, ifelse, etc). If they exist, initialize the type and make a queue
        //if no controlTags:
        evaluateIndependentLine(currentIndex);
        }

    private void evaluateIndependentLine(int currentIndex){
        boolean isList=currentLine.get(currentIndex).equals("[");
        if(isList) currentIndex++; //skips over bracket
        if (!currentLine.contains("]")); //throw error
        int commandCount=0;
        while(!currentLine.get(currentIndex).equals("]")) {
            if(!isList && commandCount==1); //throw error, bc if it isn't a list, shouldn't be more than one command
            currentIndex=parseCommand(currentIndex);
            commandCount++;
        }
    }

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
        double[] simplifedArguments=simplifier.simplifyCommandArguments(currentLine, currentIndex, currentCommand.getArgumentNumber());
        currentIndex=simplifier.getLargestIndexReached();
        currentIndex++; //go to next unvisited piece of code
        currentCommand.setArguments(simplifedArguments);
        currentCommand.execute();
        return currentIndex;
    }
