package mainpackage;

public class ID extends Operation {
    public ID(String operationType, int numArgs, SystemStorage systemStorage) {
        super(operationType, numArgs, systemStorage);
    }

    @Override
    public double evaluate() {
        return 0;
    }

    @Override
    public Operation copy() {
        return null;
    }
}
