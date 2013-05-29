package pippin;

//Gabriel Ochoa
public class _010001 extends Instruction {

	
	public _010001(Processor cpu, Memory memory)
	{
		super(cpu, memory);
	}
	

	//if the contents of the accumulator and Z are both non-zero, 
	//put 1 into the accumulator; otherwise, put 0 into the accumulator (ignore indirect)

	@Override
    public void execute(int argument, boolean indirect)
            throws DataAccessException {
		
		if(cpu.getAccumulator() != 0 &&  argument != 0)
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
       return true;
    }
    @Override
    public boolean requiresArgument( ) {
       return true;
    }
    @Override
    public String toString() {
        return "ANDI";
    } 
}