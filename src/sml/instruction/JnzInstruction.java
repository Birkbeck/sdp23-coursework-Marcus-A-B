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
	private final RegisterName source;

	public static final String OP_CODE = "jnz";

	public JnzInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	@Override
	public int execute(Machine m) {
		if(m.getRegisters().get(source) != 0)
			//return counter value of statement labelled L
			;
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof JnzInstruction) {
			JnzInstruction other = (JnzInstruction) o;
			return result.equals(other.result) && source.equals(other.source);
		}
		return false;
	}

	@Override
	public int hashCode() {return Objects.hash(result, source, OP_CODE);}
}
