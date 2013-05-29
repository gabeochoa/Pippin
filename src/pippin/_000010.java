package pippin;

//Gabriel Ochoa
public class _000010 extends Instruction {

	
	public _000010(Processor cpu, Memory memory)
	{
		super(cpu, memory);
	}

	@Override
    public void execute(int argument, boolean indirect)
            throws DataAccessException {
		 
		int address = argument;
		
		cpu.setAccumulator(address);	        
	    cpu.incrementCounter();
    }

    @Override
    public boolean isImmediate( ) {
       return true;
    }
    @Override
    public boolean requiresArgument( ) {
       return true;
    }
    @Override
    public String toString() {
        return "LODI";
    } 
}
