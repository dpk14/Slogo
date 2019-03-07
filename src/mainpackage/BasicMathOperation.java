package mainpackage;

public class BasicMathOperation extends Operation {
    private final int MINUS_MULTIPLIER = -1;

    public BasicMathOperation(String myType, int numArgs){
        super(myType, numArgs);
    }

    @Override
    public double evaluate() {
        if (myType.equals("sum")){
            double sum = myArgs[0] + myArgs[1];
            ret = sum;
        }
        else if (myType.equals("difference")){
            double difference = myArgs[0] - myArgs[1];
            ret = difference;
        }
        else if (myType.equals("product")){
            double product = myArgs[0] * myArgs[1];
            ret = product;
        }
        else if (myType.equals("quotient")){
            double quotient = myArgs[0] / myArgs[1];
            ret = quotient;
        }
        else if (myType.equals("minus")){
            ret = myArgs[0] * MINUS_MULTIPLIER;
        }
        else if (myType.equals("remainder")){
            double remainder = myArgs[0] % myArgs[1];
            ret = remainder;
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new BasicMathOperation(myType, myNumArgs);
        return copy;
    }
}
