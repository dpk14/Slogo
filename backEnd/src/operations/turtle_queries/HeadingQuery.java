package operations.turtle_queries;

public class HeadingQuery extends TurtleQuery {
    public HeadingQuery(){
        super();
    }

    @Override
    public double evaluate() {
        return getTurtle().getHeading();
    }
}
