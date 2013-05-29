package pippin;

import java.util.Observable;
public class Processor extends Observable {

	private int accumulator, programCounter;

    public void incrementCounter() {
        programCounter++;
    }
    
    public int getAccumulator()
    {
    	return accumulator;
    }
    public int getProgramCounter()
    {
    	return programCounter;
    }
    
    public void setAccumulator(int sa)
    {
    	accumulator = sa;
    	setChanged();
    	notifyObservers();
    }
    public void setProgramCounter(int pc)
    {
    	programCounter = pc;
    	setChanged();
    	notifyObservers();
    }
 
    
}