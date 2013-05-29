package pippin;

//Gabriel Ochoa
public class _001000 extends Instruction {

	
	public _001000(Processor cpu, Memory memory)
	{
		super(cpu, memory);
	}

	@Override
    public void execute(int argument, boolean indirect)
            throws DataAccessException {

        int operand = (argument);
        cpu.setAccumulator (cpu.getAccumulator() + operand);
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
        return "ADDI";
    } 
}
