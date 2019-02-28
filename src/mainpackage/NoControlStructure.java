package mainpackage;

import java.util.ArrayList;

public class NoControlStructure extends ControlStructure {

        public NoControlStructure(int numOfListArguments, ProgramParser parser, SystemStorage storage){
            super(numOfListArguments, parser, storage);
        }

        @Override
        public double executeCode(){
            ArrayList<String> simplifiedLine=evaluateLineSection(myStartingIndex, myUserInput);
            myUserInput=simplifiedLine;
            return 0;
        }
    }

