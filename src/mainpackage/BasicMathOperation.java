package mainpackage;

public class BasicMathOperation extends Operation {
    private final int MINUS_MULTIPLIER = -1;

    public BasicMathOperation(String type, int numArgs){
        super(type, numArgs);
    }

    @Override
    public double evaluate() {
        if (myType.equals("sum")){
            double sum = getArgIndex(0) + getArgIndex(1);
            setReturnValue(sum);
        }
        else if (myType.equals("difference")){
            double difference = getArgIndex(0) - getArgIndex(1);
            setReturnValue(difference);
        }
        else if (myType.equals("product")){
            double product = getArgIndex(0) * getArgIndex(1);
            setReturnValue(product);
        }
        else if (myType.equals("quotient")){
            double quotient = getArgIndex(0) / getArgIndex(1);
            setReturnValue(quotient);
        }
        else if (myType.equals("minus")){
            setReturnValue(getArgIndex(0) * MINUS_MULTIPLIER);
        }
        else if (myType.equals("remainder")){
            double remainder = getArgIndex(0) % getArgIndex(1);
            setReturnValue(remainder);
        }
        return getReturnValue();
    }

    @Override
    public Operation copy() {
        Operation copy = new BasicMathOperation(myType, getNumArgs());
        return copy;
    }
}
