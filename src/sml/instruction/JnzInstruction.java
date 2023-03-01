package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class JnzInstruction extends Instruction {
	private final RegisterName result;
	private final String l;

	public static final String OP_CODE = "jnz";

	public JnzInstruction(String label, RegisterName result, String l) {
		super(label, OP_CODE);
		this.result = result;
		this.l = l;
	}

	@Override
	public int execute(Machine m) {
		if(m.getRegisters().get(result) != 0)
			return m.getLabels().getAddress(l);

		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + l;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof JnzInstruction) {
			JnzInstruction other = (JnzInstruction) o;
			return result.equals(other.result) && l.equals(other.l);
		}
		return false;
	}

	@Override
	public int hashCode() {return Objects.hash(result, l, OP_CODE);}
}
