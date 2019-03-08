package operations.turtle_queries;

public class PenQuery extends TurtleQuery {
    private final double PEN_UP_VAL = 0;
    private final double PEN_DOWN_VAL = 1;

    public PenQuery(){
        super();
    }

    @Override
    public double evaluate() {
        boolean ret = getTurtle().getPenStatus();
        if (ret){
            return PEN_DOWN_VAL;
        }
        else {
            return PEN_UP_VAL;
        }
    }
}
