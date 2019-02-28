import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;


/**
 * Simple parser based on regular expressions that matches program strings to 
 * kinds of language features.
 * 
 * @author Robert C. Duvall
 */
public class ProgramParser {
    // "types" and the regular expression patterns that recognize those types
    // note, it is a list because order matters (some patterns may be more generic)
    private List<Entry<String, Pattern>> mySymbols;
    private HashMap<String, Operation> myOperationsMap;
    private HashMap<String, ControlStructure> myControlMap;
    private SystemStorage myStorage;
    /**
     * Create an empty parser.
     */
    public ProgramParser(SystemStorage storage) {
        mySymbols = new ArrayList<>();
        myOperationsMap=new HashMap<>();
        myControlMap=new HashMap<>();
        myStorage=storage;
        //initialize mySymbols and myOperations with the given values
    }

    /**
     * Adds the given resource file to this language's recognized types
     */
    public void addPatterns (String syntax) {
        var resources = ResourceBundle.getBundle(syntax);
        for (var key : Collections.list(resources.getKeys())) {
            var regex = resources.getString(key);
            mySymbols.add(new SimpleEntry<>(key,
                           // THIS IS THE IMPORTANT LINE
                           Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    public void makeControlMap(){
        myControlMap.put("MakeVariable", new MakeVariable(0, this, myStorage));
        myControlMap.put("Repeat", new Repeat(1, this, myStorage));
        myControlMap.put("DoTimes", new DoTimes(2, this, myStorage));
        myControlMap.put("For", new For(2, this, myStorage));
        myControlMap.put("If", new If(1, this, myStorage));
        myControlMap.put("IfElse", new IfElse(1, this, myStorage));
    }

    public void makeOperationsMap() {
        myOperationsMap.put("Forward", new MovementCommand(FORWARD_DIRECTION, DEFAULT_MOVEMENT));
        myOperationsMap.put("Backward", new MovementCommand(BACKWARD_DIRECTION, DEFAULT_MOVEMENT));
        // continue

    }
    /*
        if (currentString.equals("home")) {
            new movementCommand(currentString, 0);
        } else if (currentString.equals("Forward") || currentString.equals("Backward")) {
            currentCommand = new movementCommand(currentString, 1);
        } else if (currentString.equals("setxy") || currentString.equals("goto")) {
            currentCommand = new movementCommand(currentString, 2);
        } else if (currentString.equals("Right") || currentString.equals("Left") || currentString.equals("lt") || currentString.equals("setheading") || currentString.equals("seth")) {
            currentCommand = new rotationCommand(currentString, 1);
        } else if (currentString.equals("towards")) {
            currentCommand = new rotationCommand(currentString, 2);
        } else if (currentString.equals("penup") || currentString.equals("pu") || currentString.equals("pendown") || currentString.equals("pd")) {
            currentCommand = new drawCommand(currentString, 1);
        } else if (currentString.equals("showturtle") || currentString.equals("st") || currentString.equals("hideturtle") || currentString.equals("ht")) {
            currentCommand = new turtleStateCommand(currentString, 1);
        } else if (currentString.equals("make") || currentString.equals("set")) {
            currentCommand = new setterCommand(currentString, 2);
        }



                String expressionType=currentLine.get(currentIndex);
        Expression currentExpression=new Expression();
        if (expressionType.indexOf("?")==expressionType.length()-1){
            expressionType=expressionType.substring(0, expressionType.length()-1);
            expressionType+="P";
        }
        if (expressionType.equals("+") || expressionType.equals("-") || expressionType.equals("*") || expressionType.equals("/") || expressionType.equals("~")) {
            currentExpression= new ArithmeticExpression(expressionType, 2);
        }
        else if (isAlphabetic(expressionType)) expressionType=expressionType.toLowerCase();
        else; //throw error

        if (expressionType.equals("xcor") || expressionType.equals("ycor") || expressionType.equals("heading") || expressionType.equals("pendownp") || expressionType.equals("showingp")) {
            currentExpression=new QueryExpression(expressionType, 0);
        }
        else if (expressionType.equals("lessp") || expressionType.equals("greaterp") || expressionType.equals("equalp") || expressionType.equals("notequalp")) {
            currentExpression= new BooleanExpression(expressionType, 2);
        }
        else if (expressionType.equals("pi")){
            currentExpression= new ArithmeticExpression(expressionType, 0);
        }
        else if (expressionType.equals("minus") || expressionType.equals("random") || expressionType.equals("log")){
            currentExpression= new ArithmeticExpression(expressionType, 1);
        }
        else if (expressionType.equals("sum") || expressionType.equals("product") || expressionType.equals("quotient") || expressionType.equals("remainder") || expressionType.equals("pow")){
            currentExpression= new ArithmeticExpression(expressionType, 2);
        }
        else if (expressionType.equals("sin") || expressionType.equals("cos") || expressionType.equals("tan") || expressionType.equals("atan")){
            currentExpression= new TrigExpression(expressionType, 2);
        }
        else if (!(expressionType.equals("+") || expressionType.equals("-") || expressionType.equals("*") || expressionType.equals("/") || expressionType.equals("~"))); //throw error

        double simplifiedValue=simplifyExpression(currentLine, currentIndex+1, currentExpression);
        return simplifiedValue;
    }

    */

    /**
     * Returns language's type associated with the given text if one exists 
     */
    public String getSymbol (String text) {
        final var ERROR = "NO MATCH";
        for (var e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        // FIXME: perhaps throw an exception instead
        return ERROR;
    }

    public HashMap<String, Operation> getOperationsMap() {
        return myOperationsMap;
    }

    public ControlStructure getControlStructure(String controlType){
        if (!myControlMap.containsKey(controlType)) return new NoControlStructure(0, this, myStorage);
        else return (myControlMap.get(controlType));
    }

    public Operation getOperation(String operationType){
        if (!myOperationsMap.containsKey(operationType)); //throw error
        return (myOperationsMap.get(operationType));
    }

    public boolean isControl(String candidate){
        return myControlMap.containsKey(candidate);
    }

    public boolean isOperation(String candidate){
        return myOperationsMap.containsKey(candidate);
    }

    private boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    public String parseVariable(String variable){
        variable=removeColon(variable);
        return Double.toString(myStorage.getVariableValue(variable));
    }

    public String removeColon(String variable){
        return variable.substring(1);
    }


}
