package mainpackage;

import java.util.ArrayList;

public class NoControlStructure extends ControlStructure {

        public NoControlStructure(int numOfListArguments, ProgramParser parser, SystemStorage storage){
            super(numOfListArguments, parser, storage);
        }

        @Override
        public double executeCode(){
            evaluateSimplifiableCopy(myStartingIndex);
            myUserInput=mySimplifiableCopy;
            return 0;
        }

        @Override
        protected ArrayList<String> replaceCodeWithReturnValue(double returnValue){
            return myUserInput;
        }
    }

