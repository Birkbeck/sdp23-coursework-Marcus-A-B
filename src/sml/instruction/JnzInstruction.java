package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents a Jnz Instruction
 * @author Marcus Burns
 */

public class JnzInstruction extends Instruction {
	private final RegisterName result;
	private final String destinationLabel;

	public static final String OP_CODE = "jnz";

	/**
	 * Constructor: a Jnz (Jump if Not Zero) instruction with a label, a result register,
	 * and a destionationLabel String.
	 *
	 * @param label optional label (can be null)
	 * @param result a register where the value to be evaluated is stored
	 * @param destinationLabel a string which is a label which is used to determine
	 * the address of the next instruction to be executed
	 */
	public JnzInstruction(String label, RegisterName result, String destinationLabel) {
		super(label, OP_CODE);
		this.result = result;
		this.destinationLabel = destinationLabel;
	}

	/**
	 * Executes the Jnz instruction in the given machine
	 * which returns an integer value. If this value is not zero
	 * the address of the next instruction in the program to be executed
	 * will be fetched from the machine and returned
	 *
	 * @param m the machine the instruction runs on
	 * @return the address of the next instruction to run
	 */
	@Override
	public int execute(Machine m) {
		if(m.getRegisters().get(result) != 0)
			return m.getLabels().getAddress(destinationLabel);

		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * A string representation of the Jnz instruction
	 *
	 * @return returns the OPP_CODE and result register and destinationLabel String in a formatted string
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + destinationLabel;
	}

	/**
	 * Compares this object to the specified object for equality.
	 *
	 * @param o the object to compare this instance to.
	 * @return true if the specified object is an instance of JnzInstruction and
	 * the result register and destinationLabel strings are equal.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof JnzInstruction) {
			JnzInstruction other = (JnzInstruction) o;
			return result.equals(other.result) && destinationLabel.equals(other.destinationLabel);
		}
		return false;
	}

	/**
	 * Returns a hash code value for this object based on the result register,
	 * the destinationLabel string, and the OP_CODE.
	 * @return a hash code value for this object
	 */
	@Override
	public int hashCode() {return Objects.hash(result, destinationLabel, OP_CODE);}
}
