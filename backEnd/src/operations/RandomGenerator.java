package operations;

import mainpackage.SystemStorage;

public class RandomGenerator extends Operation {
    public RandomGenerator (String myType, int numArgs){
        super(myType, numArgs);
    }

    @Override
    public double evaluate() {
        if (myType.equals("random")){
            setReturnValue(Math.random() * getArgIndex(0));
        }
        return getReturnValue();
    }

    @Override
    public Operation copy() {
        Operation copy = new RandomGenerator(myType, getNumArgs());
        return copy;
    }
}
