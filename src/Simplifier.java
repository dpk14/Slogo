import java.util.ArrayList;

public class Simplifier {

        private int myIndex=0;

    public String[] simplifyCommandArguments(ArrayList<String> currentLine, int currentIndex){
        int ArgumentsSimplified=0;
        int reducedValue=0;
        currentIndex++;
        while(ArgumentsSimplified<myNumberOfArguments){
            if(isImmediateOrVariable(currentLine.get(currentIndex))){
                currentIndex++;
            }
            else{
                reducedValue=checkExpressionTypeAndSimplify(currentLine, currentIndex);
                currentIndex=myIndex;
            }
            myArguments[ArgumentsSimplified]=reducedValue;
            ArgumentsSimplified++;
            reducedValue=0;

        }
        return commandArguments;
    }

    public int checkExpressionTypeAndSimplify(ArrayList<String> currentLine, int currentIndex){
        String expressionType=currentLine.get(currentIndex);
        Expression currentExpression;
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

        double simplifiedValue=simplifyExpression(myCurrentLine, currentIndex, currentExpression.getArgumentNumber());
        currentIndex=Simplifier.getCurrentIndex();
        return simplifiedValue;

    }
}
