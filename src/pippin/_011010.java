package pippin;

//Gabriel Ochoa
public class _011010 extends Instruction {

	
	public _011010(Processor cpu, Memory memory)
	{
		super(cpu, memory);
	}

	@Override
    public void execute(int argument, boolean indirect)
            throws DataAccessException {
		 
			if(argument < 0 || argument >= 256)
				throw new DataAccessException("Negative Argument");
			
		   if(indirect)
		   {
			   int location = memory.getData(argument);
			   cpu.setProgramCounter(location);
		   }else
		   {
			   cpu.setProgramCounter(argument);
		   }
        
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
        return "JUMP";
    } 
}
