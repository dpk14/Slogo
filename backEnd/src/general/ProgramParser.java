package general;

import controlStructures.*;
import operations.*;

import java.lang.reflect.InvocationTargetException;
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
    private Map<String, ControlStructure> myControlMap;
    private SystemStorage myStorage;
    /**
     * Create an empty parser.
     */
    public ProgramParser(SystemStorage storage) {
        mySymbols = new ArrayList<>();
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
        myControlMap.put("AskWith ", new AskWith(0, 2, this, myStorage));
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

    public ControlStructure getControlStructure(String controlType){
        if (!myControlMap.containsKey(controlType)) return new NoControlStructure(0, 0, this, myStorage);
        else return (myControlMap.get(controlType));
    }

    public Operation getOperation(String operationType){
        var resource = ResourceBundle.getBundle("resources/operation_defaults/Operations");
        if (resource.containsKey(operationType)){
            String className = resource.getString(operationType);
            try {
                Operation operation = (Operation) Class.forName(className).getConstructor().newInstance();
                return operation;
            }
            catch (ClassNotFoundException e){
                System.out.println("Operation not found");
            }
            catch (NoSuchMethodException e){
                System.out.println("Operation constructor not found");
            }
            catch (InstantiationException e){
                System.out.println("Operation instantiation exception");
            }
            catch (IllegalAccessException e){
                System.out.println("Illegal access exception");
            }
            catch (InvocationTargetException e){
                System.out.println("Invocation target exception");
            }
        }
        else {
            //throw new Error("Illegal Operation");
        }
        return null;
    }

    public boolean isControl(String candidate){
        return myControlMap.containsKey(candidate);
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
