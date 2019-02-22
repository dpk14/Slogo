 import java.util.ArrayList;
import java.util.Arrays;
 import java.util.List;
 import java.util.Map;
 import java.util.regex.Pattern;
 import java.util.Stack;

 public class Interpreter {
    ProgramParser myParser;
    OperationBuilder myBuilder;
     private List<Map.Entry<String, Pattern>> mySymbols;

    public Interpreter(ProgramParser parser) {
    myParser=parser;
    }

  //must be reinitialized after every line, so must also reinitialize parser

    public void evaluateInput(String currentLine) {
        myBuilder=new OperationBuilder(myParser);
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

    private int parseOperationAndUpdateIndex(int currentIndex) {
        String operationTag = myCurrentLine.get(currentIndex);
        operationTag=mySymbols.get(operationTag);
        Operation defaultOperation = myOperationsMap.get(operationTag); //will automatically throw error if doesn't work
        Stack<OperationBuilder> builderStack=new Stack<OperationBuilder>();
        OperationBuilder builder=new OperationBuilder(defaultOperation, myCurrentLine, currentIndex, operationTag);
        stack.push(builder);
        while (builderStack.size() != 0) {
            builder = builderStack.peek();
            if (builder.getMyNumOfArgsFilled() == builder.getMyNumOfArgsNeeded()) {
                builder.performOperationAndSimplifyLine(builderStack, currentIndex);
            } else currentIndex=builder.continueBuildingOperation(builderStack);
        }
        return currentIndex;
    }
