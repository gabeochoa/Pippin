package pippin;

public class CodeLine {
	private Instruction inst;
	private int arg;
	private long binary;
	private boolean indirect;
		
	public CodeLine(Instruction inst, int arg, long binary, boolean indirect) {
		super();
		this.inst = inst;
		this.arg = arg;
		this.binary = binary;
		this.indirect = indirect;
	}
	
	public CodeLine(Machine m, long ln) {
		binary = ln;
		indirect = false;
		if (ln < 0) {
			indirect = true;
			ln = -ln;
		}
		int high = (int)(ln >> 32);
		arg = (int)(-1 & ln);
		inst = m.INSTRUCTION_SET[high];
	}

	public Instruction getInst() {
		return inst;
	}

	public int getArg() {
		return arg;
	}

	public long getBinary() {
		return binary;
	}

	public boolean isIndirect() {
		return indirect;
	}

	@Override
	public String toString() {
		return inst + (indirect?" N":" ") + Integer.toString(arg,16);
	}

}