package pippin;

//Gabriel Ochoa
public class _000000 extends Instruction {

	
	public _000000(Processor cpu, Memory memory)
	{
		super(cpu, memory);
	}

	@Override
    public void execute(int argument, boolean indirect)
            throws DataAccessException {
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
        return "NOP";
    } 
}
