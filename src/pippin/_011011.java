package pippin;

//Gabriel Ochoa
public class _011011 extends Instruction {

	
	public _011011(Processor cpu, Memory memory)
	{
		super(cpu, memory);
	}

	@Override
    public void execute(int argument, boolean indirect)
            throws DataAccessException {
		 
		int address = argument;
		int operand = (address);
		int value = cpu.getAccumulator();
		
		if(indirect){
				if(value == 0)
				{
					cpu.setProgramCounter(memory.getData(operand));
				}
				else
				{
					cpu.incrementCounter();
				}
	        }
		else
		{
				if(value == 0)
				{
					cpu.setProgramCounter(operand);
				}
				else
				{
					cpu.incrementCounter();
				}
		}
	        
	        
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
        return "JMPZ";
    } 
}
