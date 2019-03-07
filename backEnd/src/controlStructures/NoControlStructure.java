package controlStructures;

import mainpackage.ProgramParser;
import mainpackage.SystemStorage;

import java.util.ArrayList;

public class NoControlStructure extends ControlStructure {

        public NoControlStructure(int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage){
            super(numOfExpressionArguments, numOfListArguments, parser, storage);
        }

        @Override
        public ControlStructure copy() {
            return new NoControlStructure(myNumOfExpressionArguments, myNumOfListArguments, myParser, myStorage);
        }

        @Override
        public double executeCode(){
            simplifyAndExecuteStructure();
            return 0;
        }

        @Override
        protected void simplifyAndExecuteStructure(){
            simplifyAndEvaluate(mySimplifiableLine, myStartingIndex, myAnimal);
        }

        @Override
        protected ArrayList<String> replaceCodeWithReturnValue(double returnValue, ArrayList<String> mySimplifiableLine){
            return mySimplifiableLine;
        }
    }

