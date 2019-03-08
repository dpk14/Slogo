package operations.turtle_queries;

public class ShowingQuery extends TurtleQuery {
    private final double SHOWING_VAL = 1;
    private final double NOT_SHOWING_VAL = 0;

    public ShowingQuery(){
        super();
    }

    @Override
    public double evaluate() {
        boolean ret = getTurtle().isVisible();
        if (ret){
            return SHOWING_VAL;
        }
        else {
            return NOT_SHOWING_VAL;
        }
    }
}
