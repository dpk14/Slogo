package operations.turtle_queries;

public class XCorQuery extends TurtleQuery {

    public XCorQuery(){
        super();
    }

    @Override
    public double evaluate() {
        return getTurtle().getCoordinates()[0];
    }
}
