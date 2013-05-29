package pippin;

//Gabriel Ochoa
public class _000001 extends Instruction {

	
	public _000001(Processor cpu, Memory memory)
	{
		super(cpu, memory);
	}

	@Override
    public void execute(int argument, boolean indirect)
            throws DataAccessException {
		 
		int address = argument;
		int operand = memory.getData(address);
		
		if(indirect){
			//use the data value stored at data
			//memory location X as the location 
			//of the data and load that data into 
			//the accumulator
			cpu.setAccumulator(memory.getData(operand));
			
	        }
		else
		{
			//put the data value stored at data 
			//memory location X into the accumulator. 
			cpu.setAccumulator(operand);
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
        return "LOD";
    } 
}
