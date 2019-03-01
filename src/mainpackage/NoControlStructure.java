package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class NoControlStructure extends ControlStructure {

        public NoControlStructure(int numOfListArguments, ProgramParser parser, SystemStorage storage){
            super(numOfListArguments, parser, storage);
        }

        @Override
        public double executeCode(){
            simplifyAndExecuteStructure();
            return 0;
        }

        @Override
        protected void simplifyAndExecuteStructure(){
            simplifyAndEvaluate(mySimplifiableLine, myStartingIndex);
        }

        @Override
        protected ArrayList<String> replaceCodeWithReturnValue(double returnValue, ArrayList<String> mySimplifiableLine){
            return mySimplifiableLine;
        }
    }

