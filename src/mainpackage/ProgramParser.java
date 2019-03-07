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
        myControlMap.put("MakeVariable", new MakeVariable(2, 0, this, myStorage));
        myControlMap.put("Repeat", new Repeat(1, 1, this, myStorage));
        myControlMap.put("DoTimes", new DoTimes(0,2, this, myStorage));
        myControlMap.put("For", new For(0,2, this, myStorage));
        myControlMap.put("If", new If(1,1, this, myStorage));
        myControlMap.put("IfElse", new IfElse(1, 2, this, myStorage));
        myControlMap.put("MakeUserInstruction", new MakeUserInstruction(1, 2, this, myStorage));
        myControlMap.put("Tell", new Tell(0, 1, this, myStorage));
        myControlMap.put("Ask", new Ask(0, 2, this, myStorage));
        myControlMap.put("AskWith", new AskWith(0, 2, this, myStorage));
    }

    public void makeOperationsMap() {
        myOperationsMap.put("Forward", new MovementCommand("forward", 1, myStorage));
        myOperationsMap.put("Backward", new MovementCommand("backward", 1, myStorage));
        myOperationsMap.put("Left", new RotateCommand("left", 1, myStorage));
        myOperationsMap.put("Right", new RotateCommand("right", 1, myStorage));
        myOperationsMap.put("SetHeading", new SetAbsoluteCommand("heading", 1, myStorage));
        myOperationsMap.put("SetTowards", new SetAbsoluteCommand("towards", 2, myStorage));
        myOperationsMap.put("SetPosition", new SetAbsoluteCommand("position", 2, myStorage));
        myOperationsMap.put("PenDown", new SetPen("down", 0, myStorage));
        myOperationsMap.put("PenUp", new SetPen("up", 0, myStorage));
        myOperationsMap.put("ShowTurtle", new VisibilityCommand("show", 0, myStorage));
        myOperationsMap.put("HideTurtle", new VisibilityCommand("hide", 0, myStorage));
        myOperationsMap.put("Home", new HomeCommand("home", 0, myStorage));
        myOperationsMap.put("ClearScreen", new HomeCommand("clear", 0, myStorage));

        myOperationsMap.put("XCoordinate", new TurtleQuery("xcor", 0, myStorage, null));
        myOperationsMap.put("YCoordinate", new TurtleQuery("ycor", 0, myStorage, null));
        myOperationsMap.put("Heading", new TurtleQuery("heading", 0, myStorage, null));
        myOperationsMap.put("IsPenDown", new TurtleQuery("pen", 0, myStorage, null));
        myOperationsMap.put("IsShowing", new TurtleQuery("showing", 0, myStorage, null));

        myOperationsMap.put("Sum", new BasicMathOperation("sum", 2, myStorage));
        myOperationsMap.put("Difference", new BasicMathOperation("difference", 2, myStorage));
        myOperationsMap.put("Product", new BasicMathOperation("product", 2, myStorage));
        myOperationsMap.put("Quotient", new BasicMathOperation("quotient", 2, myStorage));
        myOperationsMap.put("Remainder", new BasicMathOperation("remainder", 2, myStorage));
        myOperationsMap.put("Minus", new BasicMathOperation("minus", 1, myStorage));
        myOperationsMap.put("Random", new RandomGenerator("random", 1, myStorage));
        myOperationsMap.put("Sine", new TrigonometricOperation("sin", 1, myStorage));
        myOperationsMap.put("Cosine", new TrigonometricOperation("cos", 1, myStorage));
        myOperationsMap.put("Tangent", new TrigonometricOperation("tan", 1, myStorage));
        myOperationsMap.put("ArcTangent", new TrigonometricOperation("atan", 1, myStorage));
        myOperationsMap.put("NaturalLog", new ExponentialOperation("log", 1, myStorage));
        myOperationsMap.put("Power", new ExponentialOperation("pow", 2, myStorage));
        myOperationsMap.put("Pi", new TrigonometricOperation("pi", 0, myStorage));

        myOperationsMap.put("LessThan", new BooleanExpression("less", 2, myStorage));
        myOperationsMap.put("GreaterThan", new BooleanExpression("greater", 2, myStorage));
        myOperationsMap.put("Equal", new BooleanExpression("equal", 2, myStorage));
        myOperationsMap.put("NotEqual", new BooleanExpression("notequal", 2, myStorage));
        myOperationsMap.put("And", new BooleanOperator("and", 2, myStorage));
        myOperationsMap.put("Or", new BooleanOperator("or", 2, myStorage));
        myOperationsMap.put("Not", new BooleanOperator("not", 1, myStorage));

        myOperationsMap.put("ID", new MultipleTurtleOperation("id", 0, myStorage));
        myOperationsMap.put("Turtles", new MultipleTurtleOperation("turtles", 0, myStorage));
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
        return text;
    }

    public HashMap<String, Operation> getOperationsMap() {
        return myOperationsMap;
    }

    public ControlStructure getControlStructure(String controlType){
        if (!myControlMap.containsKey(controlType)) return new NoControlStructure(0, 0, this, myStorage);
        else return (myControlMap.get(controlType));
    }

    public Operation getOperation(String operationType){
        if (!myOperationsMap.containsKey(operationType)) {
            //throw new Error("Illegal Operation");
        }
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

    public double parseVariable(String variable){
        variable=removeColon(variable);
        return myStorage.getVariableValue(variable);
    }

    public String removeColon(String variable){
        return variable.substring(1);
    }

    public Map getControlMap(){
        return myControlMap;
    }
}
