import javax.management.Query;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Parser {
    boolean needsNextLine;
    boolean isList;
    boolean queryExpected;
    boolean booleanExpected;
    boolean expressionExpected;
    ArrayList<String> currentLineSection;

    public void evaluateInput(String currentLine) {
        String[] currentLineWithoutSpaces = currentLine.split(" ");
        ArrayList<String> currentLineSection = new ArrayList<String>(Arrays.asList(currentLineWithoutSpaces));
        boolean isCommentOrBlank=currentLineSection.size()==0 || currentLineSection.get(0).equals("#");
        if (isCommentOrBlank) return;
        boolean hasTag=checkAndParseIfControlTag(currentLineSection); //checks for tags like REPEAT, DOTIMES, FOR, and IF, etc
        if(!hasTag) evaluateLineIndependently(currentLineSection);
        return;
        }

    private void evaluateLineIndependently(ArrayList<String> currentLineSection){
        currentLineSection=parseAndAdvanceIfQuery(currentLineSection);
        currentLineSection=parseAndAdvanceIfBoolean(currentLineSection);
        if ((isQuery || isBoolean) && splittedLine.length>1 && conditional) throw error. Queries and booleans should be alone on their line if not prefaced by conditional
        isList=currentLine.contains("[");
        if(isList) currentLine = removeBrackets(currentLine);
        while(currentLineSection.size()!=0) {
            currentLineSection=parseCurrentCommandAndAdvance(currentLineSection, isList);
        }
        return;
    }

    private ArrayList<String> parseCurrentCommandAndAdvance(ArrayList<String> currentLineSection, boolean isList){
        String commandtype=currentLineSection.get(0);
        //check if commandtype is fully alphabetic. If not, throw error
        commandtype=commandtype.toLowerCase();
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
        else if (commandtype.equals("make") || commandtype.equals("set")){
            String variable=currentLineSection.get(1);
            String expression=currentLineSection.get(2);
            currentCommand = new setterCommand(commandtype, variable, expression);
            currentLineSection=advanceParser(2, currentLineSection);
        }

        if(!isList && currentLineSection.size()!=0); //throw error. If it isn't a list, there shouldn't be multiple commands in one line
        return currentLineSection;
    }

    private String removeBrackets(String currentLine) {
        int openBrack = currentLine.indexOf("[");
        int closeBrack = currentLine.indexOf("]");
        if (openBrack > 0) {
            if (currentLine.indexOf("]") < 0) ; //throw error
            String bracketsRemoved = currentLine.substring(openBrack, closeBrack);
            return bracketsRemoved;
        }
        return currentLine;
    }

    private boolean checkAndParseIfControlStructure(ArrayList<String> currentLineSection){
        return false;
    }

    private ArrayList<String> parseAndAdvanceIfQuery(ArrayList<String> currentLineSection){
        String queryType=currentLineSection.get(0);
        if (queryType.indexOf("?")==queryType.length()-1){
            queryType=queryType.substring(0, queryType.length()-1);
            queryType+="P";
        }
        //check if string is fully alphabetic. If not, throw error
        queryType=queryType.toLowerCase();
        if (queryType.equals("xcor") || queryType.equals("ycor") || queryType.equals("heading") || queryType.equals("pendownp") || queryType.equals("showingp")) {
            currentCommand = new QueryCommand(queryType);
            currentLineSection=advanceParser(1, currentLineSection);
        }
        return currentLineSection;
    }

    private ArrayList<String> parseAndAdvanceIfBoolean(ArrayList<String> currentLineSection){
        String booleanType=currentLineSection.get(0);
        if (booleanType.indexOf("?")==booleanType.length()-1){
            booleanType=booleanType.substring(0, booleanType.length()-1);
            booleanType+="P";
        }
        //check if string is fully alphabetic. If not, throw error
        booleanType=booleanType.toLowerCase();
        if (booleanType.equals("lessp") || booleanType.equals("greaterp") || booleanType.equals("equalp") || booleanType.equals("notequalp")) {
            String expression1=currentLineSection.get(1);
            String expression2=currentLineSection.get(2);
            currentCommand = new QueryCommand(booleanType, expression1, expression2);
            currentLineSection=advanceParser(3, currentLineSection);
        }
        // still need to do one for AND, OR, and NOT
        return currentLineSection;
    }

    private ArrayList<String> advanceParser(int advanceBy, ArrayList<String> currentLineSection){
            for(int k=1; k<advanceBy; k++) currentLineSection.remove(0);
            return currentLineSection;
    }
    }
