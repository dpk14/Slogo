 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interpreter {
    ProgramParser myParser;
    Builder myBuilder;

    public Interpreter(ProgramParser parser) {
    myParser=parser;
    }

  //must be reinitialized after every line, so must also reinitialize parser

    public void evaluateInput(String currentLine) {
        myBuilder=new Builder(myParser);
        String[] currentLineWithoutSpaces = currentLine.split(" ");
        currentLine = new ArrayList<String>(Arrays.asList(currentLineWithoutSpaces));

        int currentIndex=0;
        boolean isCommentOrBlank=currentLine.size()==0 || currentLine.get(currentIndex).equals("#");
        if (isCommentOrBlank) return;
        //checkControlTags (like for, ifelse, etc). If they exist, initialize the type and make a queue
        //if no controlTags:
        evaluateIndependentLine(currentIndex);
        }

    private void evaluateIndependentLine(){
        /*
        boolean isList=currentLine.get(currentIndex).equals("[");
        if(isList) currentIndex++; //skips over bracket
        if (!currentLine.contains("]")); //throw error
        int commandCount=0;
        while(!currentLine.get(currentIndex).equals("]")) {
            if(!isList && commandCount==1); //throw error, bc if it isn't a list, shouldn't be more than one command
            currentIndex=parseCommand(currentIndex);
            commandCount++;
        }
        */
    }

    private double parseOperation(){
        String currentWord=myCurrentLine.get(myCurrentIndex);
        String currentSymbol=myParser.getSymbol(currentWord); //will automatically throw error if doesn't work
        if(currentSymbol.equals("Variable")) {
            return parseVariable(currentWord);
        }
        if(currentSymbol.equals("Constant")){
            return Double.parseDouble(currentWord);
        }
        Operation defaultOperation=myOperationsMap.get(operationTag); //will automatically throw error if doesn't work
        Operation operation=myBuilder.build(defaultOperation, myCurrentLine, myCurrentIndex);
        double returnValue=operation.execute();
        if(operation instanceof Command) myPastCommands.add(operation);
        return returnValue;
    }


        double[] simplifedArguments=simplifier.simplifyCommandArguments(currentLine, currentIndex, currentCommand.getArgumentNumber());

        currentIndex=simplifier.getLargestIndexReached();
        currentIndex++; //go to next unvisited piece of code
        return currentIndex;
    }
