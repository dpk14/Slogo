package mainpackage;

public class RandomGenerator extends Operation {
    public RandomGenerator (String myType, int numArgs, SystemStorage storage){
        super(myType, numArgs, storage);
    }

    @Override
    public double evaluate() {
        ret = -1;
        if (myType.equals("random")){
            ret = Math.random() * myArgs[0];
        }
        return ret;
    }

    @Override
    public Operation copy() {
        Operation copy = new RandomGenerator(myType, myNumArgs, mySystemStorage);
        return copy;
    }
}
