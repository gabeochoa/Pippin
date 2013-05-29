package pippin;

//Gabriel Ochoa
public class _010010 extends Instruction {

	
	public _010010(Processor cpu, Memory memory)
	{
		super(cpu, memory);
	}
	


	//if the accumulator contains 0, put 1 into the accumulator; otherwise put 0 into the accumulator  (ignore indirect)
	
	@Override
    public void execute(int argument, boolean indirect)
            throws DataAccessException {
		
		if(cpu.getAccumulator() == 0)
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
       return false;
    }
    @Override
    public String toString() {
        return "NOT";
    } 
}