package mainpackage;

public class BasicMathOperation extends Operation {
    private final int MINUS_MULTIPLIER = -1;

    public BasicMathOperation(String myType, int numArgs, SystemStorage storage){
        super(myType, numArgs, storage);
    }

    @Override
    public double evaluate() {
        if (myType.equals("sum")){
            double sum = myArgs.get(0) + myArgs.get(1);
            ret = sum;
        }
        else if (myType.equals("difference")){
            double difference = myArgs.get(0) - myArgs.get(1);
            ret = difference;
        }
        else if (myType.equals("product")){
            double product = myArgs.get(0) * myArgs.get(1);
            ret = product;
        }
        else if (myType.equals("quotient")){
            double quotient = myArgs.get(0) / myArgs.get(1);
            ret = quotient;
        }
        else if (myType.equals("minus")){
            ret = myArgs.get(0) * MINUS_MULTIPLIER;
        }
        else if (myType.equals("remainder")){
            double remainder = myArgs.get(0) % myArgs.get(1);
            ret = remainder;
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new BasicMathOperation(myType, myNumArgs, mySystemStorage);
        return copy;
    }
}
