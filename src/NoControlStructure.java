import java.util.ArrayList;

    public class NoControlStructure extends ControlStructure {

        public NoControlStructure(int numOfListArguments, ProgramParser parser, SystemStorage storage){
            super(numOfListArguments, parser, storage);
        }

        @Override
        public double executeCode(){
            evaluateLineSection(myStartingIndex+1, myUserInput);
            return 0;
        }
    }

