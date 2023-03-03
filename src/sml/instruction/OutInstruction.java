package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents an Out Instruction which prints the value of a
 * register to the console
 * @author Marcus Burns
 */

public class OutInstruction extends Instruction {
	private final RegisterName result;

	public static final String OP_CODE = "out";

	/**
	 * Constructor: an Out instruction with a label, and a result register,
	 *
	 * @param label optional label (can be null)
	 * @param result a register where the value to be printed is stored
	 */
	public OutInstruction(String label, RegisterName result) {
		super(label, OP_CODE);
		this.result = result;
	}

	/**
	 * Executes the Out instruction in the given machine
	 * which prints the value of the result register to the console
	 *
	 * @param m the machine the instruction runs on
	 * @return the address of the next instruction to run
	 */
	@Override
	public int execute(Machine m) {
		System.out.println(m.getRegisters().get(result));
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * A string representation of the Out instruction
	 *
	 * @return returns the OPP_CODE and result register in a formatted string
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result;
	}

	/**
	 * Compares this object to the specified object for equality.
	 *
	 * @param o the object to compare this instance to.
	 * @return true if the specified object is an instance of OutInstruction and
	 * the result registers are equal.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof OutInstruction) {
			OutInstruction other = (OutInstruction) o;
			return result.equals(other.result);
		}
		return false;
	}

	/**
	 * Returns a hash code value for this object based on the result register
	 * and the OP_CODE.
	 * @return a hash code value for this object
	 */
	@Override
	public int hashCode() {return Objects.hash(result, OP_CODE);}
}
