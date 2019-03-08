package operations.boolean_operations;

public class AndOperation extends BooleanOperation {
    public AndOperation(){
        super();
    }
    @Override
    public double evaluate() {
        boolean ret = (getArgIndex(0) != 0) && (getArgIndex(1) != 0);
        return getReturnValue(ret);
    }
}
