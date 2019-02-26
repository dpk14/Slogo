import java.util.List;

public class Expression extends Operation {

    public Expression(String expressionType, List<String> arguments, SystemStorage storage){
        super(expressionType, arguments, storage);
    }

    @Override
    public double execute() {
        return 0;
    }

    @Override
    public int getNumArgs() {
        return 0;
    }

    @Override
    public Operation copy() {
        return null;
    }
}
