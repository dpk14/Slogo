import javax.management.Query;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Parser {

    //we need an Expression superClass with a BooleanExpression and QueryExpression subclass. Superclass contains basic mathematical operations.
    //All contain a .evaluate() method. These classes evaluate expressions according to their type, calling from memory as needed
    //to grab variables

    ArrayList<String> currentLineSection;
    final double NOT_DETECTED=Math.random();

    /*
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

*/
    private double parseNext(){
        HashMap<Boolean >
        if (isImmediateOrVariable(current)) return value or variable
        else if (isCommand(currentIndex)) return parseCommand(currentIndex);
        else if (isQuery) return parseQuery();
        else if (isBoolean) return parseBoolean();
        else; //throw error
        return 0;
        }


    private double parseCommand(){
        String currentString=currentLineSection.get(0);
        // if (currentString.equals(value or variable)) return value or variable
        //check if currentString is fully alphabetic. If not, throw error
        currentString=currentString.toLowerCase();
        if (currentString.equals("home")) {
            currentCommand = new movementCommand(currentString);
            advanceParser(1);
        }
        else if (currentString.equals("forward") || currentString.equals("fd") || currentString.equals("backward") || currentString.equals("bd")){
            String movementAmount=parseNext();
            currentCommand=new movementCommand(currentString, movementAmount);
            advanceParser(2);
        }
        else if (currentString.equals("setxy") || currentString.equals("goto")) {
            String x=parseNext();
            String y=parseNext();
            currentCommand = new movementCommand(currentString, x, y);
            advanceParser(3);
        }
        else if (currentString.equals("right") || currentString.equals("rt") || currentString.equals("left") || currentString.equals("lt") || currentString.equals("setheading") || currentString.equals("seth")) {
            String rotationAmount=parseNext();
            currentCommand = new rotationCommand(currentString, rotationAmount);
            advanceParser(2);
        }
        else if (currentString.equals("towards")) {
            String rotatex=parseNext();
            String rotatey=parseNext();
            currentCommand = new rotationCommand(currentString, rotatex, rotatey);
            advanceParser(3);
        }
        else if (currentString.equals("penup") || currentString.equals("pu") || currentString.equals("pendown") || currentString.equals("pd")) {
            String penState=parseNext();
            currentCommand = new drawCommand(currentString, penState);
            advanceParser(2);
        }
        else if (currentString.equals("showturtle") || currentString.equals("st") || currentString.equals("hideturtle") || currentString.equals("ht")) {
            String turtleState=parseNext();
            currentCommand = new turtleStateCommand(currentString, turtleState);
            advanceParser(2);
        }
        else if (currentString.equals("make") || currentString.equals("set")){
            String variable=currentLineSection.get(1);
            String expression=currentLineSection.get(2);
            currentCommand = new setterCommand(currentString, variable, expression);
            advanceParser(2);
        }
        else return NOT_DETECTED; //indicates command is not found
        return 0;

       // if(!isList && currentLineSection.size()!=0); //throw error. If it isn't a list, there shouldn't be multiple commands in one line
        //if(isList && currentLineSection.get(0).equals("]")) currentLineSection.remove(0);

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

    private double parseAndEvaluateAndAdvanceIfBoolean(){
        String booleanType=currentLineSection.get(0);
        double returnValue=NO_BOOLEAN_PRESENT;
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
