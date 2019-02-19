import java.util.ArrayList;
import java.util.Stack;

public class Command {
    String myCommandType;
    String[] myArguments;
    int myNumberOfArguments;
    Command(String commandType, int numberOfArguments){
        myCommandType=commandType;
        myNumberOfArguments=numberOfArguments;
    }

    public int getArgumentNumber(){
        return myNumberOfArguments;
    }

    public void setArguments(String[] arguments){
        myArguments=arguments;
    }

}