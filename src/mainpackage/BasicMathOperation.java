package mainpackage;

public class BasicMathOperation extends Operation {
    private final int MINUS_MULTIPLIER = -1;

    public BasicMathOperation(String myType, int numArgs, SystemStorage storage){
        super(myType, numArgs, storage);
    }

    @Override
    public double evaluate() {
        if (myType.equals("sum")){
            double sum = Double.parseDouble(myArgs.get(0)) + Double.parseDouble(myArgs.get(1));
            ret = sum;
        }
        else if (myType.equals("difference")){
            double difference = parseString(myArgs.get(0)) - parseString(myArgs.get(1));
            ret = difference;
        }
        else if (myType.equals("product")){
            double product = parseString(myArgs.get(0)) * parseString(myArgs.get(1));
            ret = product;
        }
        else if (myType.equals("quotient")){
            double quotient = parseString(myArgs.get(0)) / parseString(myArgs.get(1));
            ret = quotient;
        }
        else if (myType.equals("minus")){
            ret = parseString(myArgs.get(0)) * MINUS_MULTIPLIER;
        }
        else if (myType.equals("remainder")){
            double remainder = parseString(myArgs.get(0)) % parseString(myArgs.get(1));
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
