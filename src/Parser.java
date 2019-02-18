import java.util.ArrayList;


public class Parser {
    boolean isList;

    public void evaluateLine(String commandLine) {
        if(commandLine.split(" ").length==0 || commandLine.contains("#")) return;
        commandLine = removeBrackets(commandLine);
        String[] splittedLine = commandLine.split(" ");
        ArrayList<String> commandLineWithoutSpaces = new ArrayList<String>();
        for (int k = 0; k < splittedLine.length; k++) {
            if (!splittedLine[k].equals(" ")) commandLineWithoutSpaces.add(splittedLine[k]);
        }
        parse(commandLineWithoutSpaces);
    }

    private ArrayList<String> parse(ArrayList<String> currentLineSection){
        String commandtype=currentLineSection.get(0).toLowerCase();

        if (commandtype.equals("home")) {
            currentCommand = new movementCommand(commandtype);
            currentLineSection=advanceParser(1, currentLineSection);
        }
        else if (commandtype.equals("forward") || commandtype.equals("fd") || commandtype.equals("backward") || commandtype.equals("bd")){
            String movementAmount=currentLineSection.get(1);
            currentCommand=new movementCommand(commandtype, movementAmount);
            currentLineSection=advanceParser(2, currentLineSection);
        }
        else if (commandtype.equals("setxy") || commandtype.equals("goto")) {
            String x=currentLineSection.get(1);
            String y=currentLineSection.get(2);
            currentCommand = new movementCommand(commandtype, x, y);
            currentLineSection=advanceParser(3, currentLineSection);
        }
        else if (commandtype.equals("right") || commandtype.equals("rt") || commandtype.equals("left") || commandtype.equals("lt") || commandtype.equals("setheading") || commandtype.equals("seth")) {
            String rotationAmount=currentLineSection.get(1);
            currentCommand = new rotationCommand(commandtype, rotationAmount);
            currentLineSection=advanceParser(2, currentLineSection);
        }
        else if (commandtype.equals("towards")) {
            String rotatex=currentLineSection.get(1);
            String rotatey=currentLineSection.get(2);
            currentCommand = new rotationCommand(commandtype, rotatex, rotatey);
            currentLineSection=advanceParser(3, currentLineSection);
        }
        else if (commandtype.equals("penup") || commandtype.equals("pu") || commandtype.equals("pendown") || commandtype.equals("pd")) {
            String penState=currentLineSection.get(1);
            currentCommand = new drawCommand(commandtype, penState);
            currentLineSection=advanceParser(2, currentLineSection);
        }
        else if (commandtype.equals("showturtle") || commandtype.equals("st") || commandtype.equals("hideturtle") || commandtype.equals("ht")) {
            String turtleState=currentLineSection.get(1);
            currentCommand = new turtleStateCommand(commandtype, turtleState);
            currentLineSection=advanceParser(2, currentLineSection);
        }

        if(!isList && currentLineSection.size()!=0); //throw error. If it isn't a list, there shouldn't be multiple commands in one line
        else parse(currentLineSection);
    }

    private String removeBrackets(String commandLine) {
        int openBrack = commandLine.indexOf("[");
        int closeBrack = commandLine.indexOf("]");
        if (openBrack > 0) {
            if (commandLine.indexOf("]") < 0) ; //throw error
            String bracketsRemoved = commandLine.substring(openBrack, closeBrack);
            isList=true;
            return bracketsRemoved;
        }
        isList=false;
        return commandLine;
    }

    private ArrayList<String> advanceParser(int advanceBy, ArrayList<String> currentLineSection){
            for(int k=1; k<advanceBy; k++) currentLineSection.remove(0);
            return currentLineSection;
    }
    }
