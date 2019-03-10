package operations.boolean_operations;

public class LessThan extends BooleanOperation {

    public LessThan(){
        super();
        setUnlimitedArgs();
    }

    @Override
    public double evaluate() {
        boolean ret = getArgIndex(0) < getArgIndex(1);
        return getReturnValue(ret);
    }
}
