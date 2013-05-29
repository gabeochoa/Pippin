package pippin;

//Gabriel Ochoa
public class _000011 extends Instruction {

	
	public _000011(Processor cpu, Memory memory)
	{
		super(cpu, memory);
	}

	@Override
    public void execute(int argument, boolean indirect)
            throws DataAccessException {
		 
		int address = argument;
		int operand = memory.getData(address);
		int value = cpu.getAccumulator();
		
		if(indirect){
				memory.setData((operand), value);
	        }
		else
		{
				memory.setData(argument, value);
		}
	        
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
        return "STO";
    } 
}
