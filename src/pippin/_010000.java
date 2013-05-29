package pippin;

//Gabriel Ochoa
public class _010000 extends Instruction {

	
	public _010000(Processor cpu, Memory memory)
	{
		super(cpu, memory);
	}
	


// 	if the contents of the accumulator and the data value stored at data memory location X 
//are both non-zero, put 1 into the accumulator; otherwise, put 0 into the accumulator (ignore indirect)

	@Override
    public void execute(int argument, boolean indirect)
            throws DataAccessException {

        int address = argument;
        int operand = memory.getData(address);
		
		if(cpu.getAccumulator() != 0 &&  operand != 0)
        {
			cpu.setAccumulator (1);
        }
		else
		{
			cpu.setAccumulator(0);
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
        return "AND";
    } 
}