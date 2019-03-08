package operations.turtle_queries;

public class YCorQuery extends TurtleQuery {

    public YCorQuery(){
        super();
    }

    @Override
    public double evaluate() {
        return getTurtle().getCoordinates()[1];
    }
}
