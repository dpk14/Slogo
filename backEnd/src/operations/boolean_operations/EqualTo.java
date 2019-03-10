package operations.boolean_operations;

public class EqualTo extends BooleanOperation {

    public EqualTo(){
        super();
        setUnlimitedArgs();
    }

    @Override
    public double evaluate() {
        boolean ret = getArgIndex(0) == getArgIndex(1);
        return getReturnValue(ret);
    }
}
