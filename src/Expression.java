import java.util.ArrayList;

public class Expression {
    String myExpressionType;
    ArrayList<String> myArguments=new ArrayList<String>();
    int myNumberOfArguments;
    
    Expression(String commandType, int numberOfArguments){
        myExpressionType=commandType;
        myNumberOfArguments=numberOfArguments;
    }

    public int getNumberOfArguments(){
        return myNumberOfArguments;
    }

    public ArrayList<String> getArguments(){
        return myArguments;
    }

}
