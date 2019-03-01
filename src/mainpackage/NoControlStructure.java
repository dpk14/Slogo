package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class NoControlStructure extends ControlStructure {

        public NoControlStructure(int numOfListArguments, ProgramParser parser, SystemStorage storage){
            super(numOfListArguments, parser, storage);
        }

        @Override
        protected void convertCodeToCommands(){
            simplifyLineSection(myStartingIndex);
        }

        @Override
        protected ArrayList<String> replaceCodeWithReturnValue(double returnValue){
            return myUserInput;
        }
    }

