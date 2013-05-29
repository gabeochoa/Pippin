package pippin;

//Gabriel Ochoa
public class _010100 extends Instruction {

	
	public _010100(Processor cpu, Memory memory)
	{
		super(cpu, memory);
	}

	@Override
    public void execute(int argument, boolean indirect)
            throws DataAccessException {
		 
			int address = argument;
			int operand = memory.getData(address);
		
			if(operand < 0)
				cpu.setAccumulator(1);
			else
				cpu.setAccumulator(0);
			
	        cpu.incrementCounter();
    }

    @Override
    public boolean isImmediate( ) {
       return false;
    }
    @Override
    public boolean requiresArgument( ) {
       return true;
    }
    @Override
    public String toString() {
        return "CMPL";
    } 
}
