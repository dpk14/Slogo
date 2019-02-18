import javax.management.Query;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Parser {

    //we need an Expression superClass with a BooleanExpression and QueryExpression subclass. Superclass contains basic mathematical operations.
    //All contain a .evaluate() method. These classes evaluate expressions according to their type, calling from memory as needed
    //to grab variables

    ArrayList<String> currentLineSection;
    final double NO_QUERY_PRESENT=Math.random();
    final int NO_BOOLEAN_PRESENT=-1;

    public void evaluateInput(String currentLine) {
        String[] currentLineWithoutSpaces = currentLine.split(" ");
        currentLineSection = new ArrayList<String>(Arrays.asList(currentLineWithoutSpaces));
        boolean isCommentOrBlank=currentLineSection.size()==0 || currentLineSection.get(0).equals("#");
        if (isCommentOrBlank) return;
        boolean hasTag=checkAndParseIfControlTag(); //checks for tags like REPEAT, DOTIMES, FOR, and IF, etc
        if(!hasTag) evaluateIndependentLine();
        return;
        }

    private void evaluateIndependentLine(){
        boolean isList=currentLineSection.contains("[");
        if(isList) currentLineSection.remove(0);
        if (!currentLineSection.contains("]")) //throw error
        while(currentLineSection.size()!=0) {
            parseCurrentCommandAndAdvance(isList);
        }
        return;
    }

    private void parseCurrentCommandAndAdvance(boolean isList){
        String commandtype=currentLineSection.get(0);
        //check if commandtype is fully alphabetic. If not, throw error
        commandtype=commandtype.toLowerCase();
        if (commandtype.equals("home")) {
            currentCommand = new movementCommand(commandtype);
            advanceParser(1);
        }
        else if (commandtype.equals("forward") || commandtype.equals("fd") || commandtype.equals("backward") || commandtype.equals("bd")){
            String movementAmount=currentLineSection.get(1);
            currentCommand=new movementCommand(commandtype, movementAmount);
            advanceParser(2);
        }
        else if (commandtype.equals("setxy") || commandtype.equals("goto")) {
            String x=currentLineSection.get(1);
            String y=currentLineSection.get(2);
            currentCommand = new movementCommand(commandtype, x, y);
            advanceParser(3);
        }
        else if (commandtype.equals("right") || commandtype.equals("rt") || commandtype.equals("left") || commandtype.equals("lt") || commandtype.equals("setheading") || commandtype.equals("seth")) {
            String rotationAmount=currentLineSection.get(1);
            currentCommand = new rotationCommand(commandtype, rotationAmount);
            advanceParser(2);
        }
        else if (commandtype.equals("towards")) {
            String rotatex=currentLineSection.get(1);
            String rotatey=currentLineSection.get(2);
            currentCommand = new rotationCommand(commandtype, rotatex, rotatey);
            advanceParser(3);
        }
        else if (commandtype.equals("penup") || commandtype.equals("pu") || commandtype.equals("pendown") || commandtype.equals("pd")) {
            String penState=currentLineSection.get(1);
            currentCommand = new drawCommand(commandtype, penState);
            advanceParser(2);
        }
        else if (commandtype.equals("showturtle") || commandtype.equals("st") || commandtype.equals("hideturtle") || commandtype.equals("ht")) {
            String turtleState=currentLineSection.get(1);
            currentCommand = new turtleStateCommand(commandtype, turtleState);
            advanceParser(2);
        }
        else if (commandtype.equals("make") || commandtype.equals("set")){
            String variable=currentLineSection.get(1);
            String expression=currentLineSection.get(2);
            currentCommand = new setterCommand(commandtype, variable, expression);
            advanceParser(2);
        }

        if(!isList && currentLineSection.size()!=0); //throw error. If it isn't a list, there shouldn't be multiple commands in one line
        if(isList && currentLineSection.get(0).equals("]")) currentLineSection.remove(0);
    }

    private boolean checkAndParseIfControlTag(){
        String ControlTag=currentLineSection.get(0);
        if(ControlTag.equals("REPEAT")){
            String expression=currentLineSection.get(1);

            String commands=currentLineSection.get(2);


        }

            else return false;
            return true;
    }

    private double parseAndEvaluateAndAdvanceIfQuery(){
        String queryType=currentLineSection.get(0);
        double returnValue=NO_QUERY_PRESENT;
        if (queryType.indexOf("?")==queryType.length()-1){
            queryType=queryType.substring(0, queryType.length()-1);
            queryType+="P";
        }
        //check if string is fully alphabetic. If not, throw error
        queryType=queryType.toLowerCase();
        if (queryType.equals("xcor") || queryType.equals("ycor") || queryType.equals("heading") || queryType.equals("pendownp") || queryType.equals("showingp")) {
            currentQuery = new QueryExpression(queryType);
            returnValue= currentQuery.evaluate();
            advanceParser(1);
        }
        return returnValue;
    }

    private int parseAndEvaluateAndAdvanceIfBoolean(){
        String booleanType=currentLineSection.get(0);
        int returnValue=NO_BOOLEAN_PRESENT;
        if (booleanType.indexOf("?")==booleanType.length()-1){
            booleanType=booleanType.substring(0, booleanType.length()-1);
            booleanType+="P";
        }
        //check if string is fully alphabetic. If not, throw error
        booleanType=booleanType.toLowerCase();
        if (booleanType.equals("lessp") || booleanType.equals("greaterp") || booleanType.equals("equalp") || booleanType.equals("notequalp")) {
            String expression1=currentLineSection.get(1);
            String expression2=currentLineSection.get(2);
            currentBoolean= new BooleanExpression(booleanType, expression1, expression2);
            returnValue=currentBoolean.evaluate();
            advanceParser(3);
        }
        // still need to do one for AND, OR, and NOT
        return returnValue;
    }

    private void advanceParser(int advanceBy){
            for(int k=1; k<advanceBy; k++) currentLineSection.remove(0);
    }

    }
