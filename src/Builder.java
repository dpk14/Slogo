import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class Builder {
    ProgramParser myParser;
    ArrayList<String> myCurrentLine;
    int myCurrentIndex;


    public Builder(ProgramParser parser){
        myParser=parser;
    }

        // private int largestIndexReached=0;

    public Operation build(Operation defaultOperation, ArrayList<String> currentLine, int currentIndex){
        Operation operation=defaultOperation.copy();
        ArrayList<String> arguments=createArguments(operation.getNumOfArgs(), currentLine, currentIndex); // make operation class throw error if num of args doesnt match
        operation.setArguments(arguments);
        return operation;
    }

    private ArrayList<String> createArguments(int numOfArgs, ArrayList<String> currentLine, int currentIndex){
        Stack<String> stack=new Stack<String>();
        stack.push(currentLine.get(currentIndex+1));
        while(stack.size()!=0){
            String current=stack.pop();
            parseWord()
        }

    }







    public double[] simplifyCommandArguments(ArrayList<String> currentLine, int currentIndex, int numberOfArguments){
        double[] commandArguments=new double[numberOfArguments];
        int ArgumentsSimplified=0;
        double reducedValue=0;
        currentIndex++;
        while(ArgumentsSimplified<numberOfArguments){
            if(isNumericOrVariable(currentLine.get(currentIndex))){
                currentIndex++;
            }
            else{
                reducedValue=checkExpressionTypeAndSimplify(currentLine, currentIndex);
                currentIndex=largestIndexReached;
            }
            commandArguments[ArgumentsSimplified]=reducedValue;
            ArgumentsSimplified++;
            reducedValue=0;

        }
        return commandArguments;
    }

    public double checkExpressionTypeAndSimplify(ArrayList<String> currentLine, int currentIndex){
        String expressionType=currentLine.get(currentIndex);
        Expression currentExpression=new Expression();
        if (expressionType.indexOf("?")==expressionType.length()-1){
            expressionType=expressionType.substring(0, expressionType.length()-1);
            expressionType+="P";
        }
        if (expressionType.equals("+") || expressionType.equals("-") || expressionType.equals("*") || expressionType.equals("/") || expressionType.equals("~")) {
            currentExpression= new ArithmeticExpression(expressionType, 2);
        }
        else if (isAlphabetic(expressionType)) expressionType=expressionType.toLowerCase();
        else; //throw error

        if (expressionType.equals("xcor") || expressionType.equals("ycor") || expressionType.equals("heading") || expressionType.equals("pendownp") || expressionType.equals("showingp")) {
            currentExpression=new QueryExpression(expressionType, 0);
        }
        else if (expressionType.equals("lessp") || expressionType.equals("greaterp") || expressionType.equals("equalp") || expressionType.equals("notequalp")) {
            currentExpression= new BooleanExpression(expressionType, 2);
        }
        else if (expressionType.equals("pi")){
            currentExpression= new ArithmeticExpression(expressionType, 0);
        }
        else if (expressionType.equals("minus") || expressionType.equals("random") || expressionType.equals("log")){
            currentExpression= new ArithmeticExpression(expressionType, 1);
        }
        else if (expressionType.equals("sum") || expressionType.equals("product") || expressionType.equals("quotient") || expressionType.equals("remainder") || expressionType.equals("pow")){
            currentExpression= new ArithmeticExpression(expressionType, 2);
        }
        else if (expressionType.equals("sin") || expressionType.equals("cos") || expressionType.equals("tan") || expressionType.equals("atan")){
            currentExpression= new TrigExpression(expressionType, 2);
        }
        else if (!(expressionType.equals("+") || expressionType.equals("-") || expressionType.equals("*") || expressionType.equals("/") || expressionType.equals("~"))); //throw error

        double simplifiedValue=simplifyExpression(currentLine, currentIndex+1, currentExpression);
        return simplifiedValue;
    }

    private double simplifyExpression(ArrayList<String> currentLine, int currentIndex, Expression currentExpression){

        if (currentIndex>largestIndexReached) largestIndexReached=currentIndex; //makes sure largestIndexReached is the highest one visited in this recursion, so that when the parser gets this index, it will pass this entire command. Careful here. This may be flawed.

        String currentEntry=currentLine.get(currentIndex);

        if(isNumericOrVariable(currentEntry)){
            currentExpression.getArguments().add(currentEntry);
            if(currentExpression.getArguments().size()==currentExpression.getNumberOfArguments()){
                return currentExpression.evaluate();
            }
            return simplifyExpression(currentLine, currentIndex+1, currentExpression);
        }
        else {
            String argument=Double.toString(checkExpressionTypeAndSimplify(currentLine, currentIndex));
            currentExpression.getArguments().add(argument);
            return simplifyExpression(currentLine, largestIndexReached+1, currentExpression);
        }
        //^else, there must be another nested expression, so find the value of this expression and store it as the first argument of the current Expression
    }

    public int getLargestIndexReached(){
        return largestIndexReached;
    }

    private boolean isNumericOrVariable(String str){
        boolean isNumeric=true;
        boolean isVariable=false;

        try
        {
            double number = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            isNumeric=false;
        }

        if(str.indexOf(":")==0) isVariable=true;
        return isNumeric || isVariable;
    }
}
