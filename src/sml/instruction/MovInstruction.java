package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents a Mov Instruction which loads an integer into a register
 * @author Marcus Burns
 */

public class MovInstruction extends Instruction {
	private final RegisterName result;
	private final int source;

	public static final String OP_CODE = "mov";

	/**
	 * Constructor: a Mov instruction with a label, a result register,
	 * and a source register. (The register is enumerated).
	 *
	 * @param label optional label (can be null)
	 * @param result a register where the source integer is stored
	 * @param source an integer that is loaded into the result register
	 */
	public MovInstruction(String label, RegisterName result, int source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	/**
	 * Executes the Mov instruction in the given machine
	 *
	 * @param m the machine the instruction runs on
	 * @return the address of the next instruction to run
	 */
	@Override
	public int execute(Machine m) {
		// TODO: replace 1 with x
		m.getRegisters().set(result, source);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * A string representation of the Mov instruction
	 *
	 * @return returns the OPP_CODE and result register and source integer in a formatted string
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}

	/**
	 * Compares this object to the specified object for equality.
	 *
	 * @param o the object to compare this instance to.
	 * @return true if the specified object is an instance of MovInstruction and
	 * the result register and source integers are equal.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof MovInstruction) {
			MovInstruction other = (MovInstruction) o;
			return result.equals(other.result) && source == source;
		}
		return false;
	}

	/**
	 * Returns a hash code value for this object based on the result register,
	 * the source integer, and the OP_CODE.
	 * @return a hash code value for this object
	 */
	@Override
	public int hashCode() {return Objects.hash(result, source, OP_CODE);}
}
