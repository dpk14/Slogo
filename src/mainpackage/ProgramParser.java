package mainpackage;

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
        myOperationsMap.put("Pi", new TrigonometricOperation("pi", 0, null));

        myOperationsMap.put("LessThan", new BooleanExpression("less", 2, null));
        myOperationsMap.put("GreaterThan", new BooleanExpression("greater", 2, null));
        myOperationsMap.put("Equal", new BooleanExpression("equal", 2, null));
        myOperationsMap.put("NotEqual", new BooleanExpression("notequal", 2, null));
        myOperationsMap.put("And", new BooleanOperator("and", 2, null));
        myOperationsMap.put("Or", new BooleanOperator("or", 2, null));
        myOperationsMap.put("Not", new BooleanOperator("not", 1, null));
    }

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
