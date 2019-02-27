import java.util.List;

abstract public class Expression extends Operation {

    public Expression(String expressionType, List<String> arguments, SystemStorage storage){
        super(expressionType, arguments, storage);
    }

    @Override
    abstract public double execute();

    @Override
    abstract public Operation copy();
}
