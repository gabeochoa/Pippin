package pippin;

//Gabriel Ochoa
public class _000110 extends Instruction {

	
	public _000110(Processor cpu, Memory memory)
	{
		super(cpu, memory);
	}

	@Override
    public void execute(int argument, boolean indirect)
            throws DataAccessException {
		 
		   int address = argument;
	        
	        if(indirect){
	            address = memory.getData(argument);
	            cpu.setAccumulator(cpu.getAccumulator() * memory.getData(address));
	        }
	        else
	        {
	        	address = memory.getData(argument);
	            cpu.setAccumulator(cpu.getAccumulator() * (address));
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
        return "MUL";
    } 
}
