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
    private HashMap<String, Operation> myOperations;

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

    private void makeOperationsMap() {
        myOperations.put("Forward", new movementCommand(FORWARD_DIRECTION, DEFAULT_MOVEMENT));
        myOperations.put("Backward", new movementCommand(BACKWARD_DIRECTION, DEFAULT_MOVEMENT));
        // continue
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
    */
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

    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private boolean match (String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }
}
