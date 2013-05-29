package pippin;

//Gabriel Ochoa
public class _011111 extends Instruction {

	
	public _011111(Processor cpu, Memory memory)
	{
		super(cpu, memory);
	}

	@Override
    public void execute(int argument, boolean indirect)
            throws DataAccessException {
		
			//HALT?
		
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
        return "HALT";
    } 
}
