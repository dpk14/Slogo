package mainpackage;

import java.util.ArrayList;
import java.util.Arrays;
 import java.util.List;
 import java.util.Map;
 import java.util.regex.Pattern;


//This class needs to be removed. Let's put evaluate input in main, and evaluateIndependentLine and parseCommand in their respective Control subclasses.

 public class Interpreter {
     ProgramParser myParser;
     OperationBuilder myBuilder;
     ArrayList<String> myCurrentLine;
     private List<Map.Entry<String, Pattern>> mySymbols;

     public Interpreter(ProgramParser parser) {
         myParser = parser;
     }

     //must be reinitialized after every line, so must also reinitialize parser

     public void evaluateInput(ArrayList<String> textBlock) {
         for(int currentLineNumber=0; currentLineNumber<textBlock.size(); currentLineNumber++) {
             String[] currentLineWithoutSpaces = textBlock.get(currentLineNumber).split(" ");
             myCurrentLine = new ArrayList<String>(Arrays.asList(currentLineWithoutSpaces));
             int currentIndex = 0;
             // else check for a control tag. Call Control.execute, and evaluateIndependentLine will be called accordingly
             //I'm thinking that if no control tag, create base control, which simply calls evaluateIndependentLine until textBlock runs out
             //if List, call parseOperationAndUpdate Index until close bracket is d etected
         }
     }


 }
