package pippin;

//Gabriel Ochoa
public class _001001 extends Instruction {

	
	public _001001(Processor cpu, Memory memory)
	{
		super(cpu, memory);
	}

	@Override
    public void execute(int argument, boolean indirect)
            throws DataAccessException {
        
        cpu.setAccumulator(cpu.getAccumulator() - (argument));
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
        return "SUBI";
    } 
}
