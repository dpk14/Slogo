package mainpackage;

public class ExponentialOperation extends Operation {
    public ExponentialOperation (String myType, int numArgs){
        super(myType, numArgs);
    }

    @Override
    public double evaluate() {
        ret = -1;
        if (myType.equals("pow")){
            ret = Math.pow(myArgs[0], myArgs[1]);
        }
        else if (myType.equals("log")){
            ret = Math.log(myArgs[0]);
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new ExponentialOperation(myType, myNumArgs);
        return copy;
    }
}
