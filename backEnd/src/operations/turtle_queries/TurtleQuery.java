package operations.turtle_queries;

import operations.TurtleOperation;

abstract public class TurtleQuery extends TurtleOperation {
    private final int QUERY_NUM_ARGS = 0;

    public TurtleQuery () {
        super();
        setNumArgs(QUERY_NUM_ARGS);
    }

    @Override
    abstract public double evaluate();

}
