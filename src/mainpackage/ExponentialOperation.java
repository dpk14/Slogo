package mainpackage;

public class ExponentialOperation extends Operation {
    public ExponentialOperation (String myType, int numArgs, SystemStorage storage){
        super(myType, numArgs, storage);
    }

    @Override
    public double evaluate() {
        ret = -1;
        if (myType.equals("pow")){
            ret = Math.pow(myArgs.get(0), myArgs.get(1));
        }
        else if (myType.equals("log")){
            ret = Math.log(myArgs.get(0));
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new ExponentialOperation(myType, myNumArgs, mySystemStorage);
        return copy;
    }
}
