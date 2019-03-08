package operations.boolean_operations;

public class GreaterThan extends BooleanOperation {

    public GreaterThan(){
        super();
    }

    @Override
    public double evaluate() {
        boolean ret = getArgIndex(0) > getArgIndex(1);
        if (ret){
            return 1;
        }
        else {
            return 0;
        }
    }
}
