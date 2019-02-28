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
    /**
     * Create an empty parser.
     */
    public ProgramParser() {
        mySymbols = new ArrayList<>();
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

    public HashMap makeControlMap(){
        return new HashMap<String, ControlStructure>();
    }

    public HashMap makeOperationsMap() {

        myOperationsMap.put("Forward", new MovementCommand("forward", 1, null, null));
        myOperationsMap.put("Backward", new MovementCommand("backward", 1, null, null));
        myOperationsMap.put("Left", new RotateCommand("left", 1, null, null));
        myOperationsMap.put("Right", new RotateCommand("right", 1, null, null));
        myOperationsMap.put("SetHeading", new SetAbsoluteCommand("heading", 1, null, null));
        myOperationsMap.put("SetTowards", new SetAbsoluteCommand("towards", 2, null, null));
        myOperationsMap.put("SetPosition", new SetAbsoluteCommand("position", 2, null, null));
        myOperationsMap.put("PenDown", new SetPen("down", 0, null, null));
        myOperationsMap.put("PenUp", new SetPen("up", 0, null, null));
        myOperationsMap.put("ShowTurtle", new VisibilityCommand("show", 0, null, null));
        myOperationsMap.put("HideTurtle", new VisibilityCommand("hide", 0, null, null));
        myOperationsMap.put("Home", new HomeCommand("home", 0, null, null));
        myOperationsMap.put("ClearScreen", new HomeCommand("clear", 0, null, null));

        myOperationsMap.put("XCoordinate", new TurtleQuery("xcor", 0, null, null));
        myOperationsMap.put("YCoordinate", new TurtleQuery("ycor", 0, null, null));
        myOperationsMap.put("Heading", new TurtleQuery("heading", 0, null, null));
        myOperationsMap.put("IsPenDown", new TurtleQuery("pen", 0, null, null));
        myOperationsMap.put("IsShowing", new TurtleQuery("showing", 0, null, null));

        myOperationsMap.put("Sum", new BasicMathOperation("sum", 2, null));
        myOperationsMap.put("Difference", new BasicMathOperation("difference", 2, null));
        myOperationsMap.put("Product", new BasicMathOperation("product", 2, null));
        myOperationsMap.put("Quotient", new BasicMathOperation("quotient", 2, null));
        myOperationsMap.put("Remainder", new BasicMathOperation("remainder", 2, null));
        myOperationsMap.put("Minus", new BasicMathOperation("minus", 1, null));
        myOperationsMap.put("Random", new RandomGenerator("random", 1, null));
        myOperationsMap.put("Sine", new TrigonometricOperation("sin", 1, null));
        myOperationsMap.put("Cosine", new TrigonometricOperation("cos", 1, null));
        myOperationsMap.put("Tangent", new TrigonometricOperation("tan", 1, null));
        myOperationsMap.put("ArcTangent", new TrigonometricOperation("atan", 1, null));
        myOperationsMap.put("NaturalLog", new ExponentialOperation("log", 1, null));
        myOperationsMap.put("Power", new ExponentialOperation("pow", 2, null));


        // continue

        return new HashMap<String, Operation>();
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

    public ControlStructure getControlStructure(String controlTag){
        if (!myControlMap.containsKey(controlTag)) return new NoControlStructure();
        else return (myControlMap.get(controlTag));
    }

    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private boolean match (String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }
}
