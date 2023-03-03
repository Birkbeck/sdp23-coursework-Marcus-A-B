package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents and Add Instruction which sums the contents of two registers
 * and stores the result in the first register.
 * @author
 */

public class AddInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "add";

	/**
	 * Constructor: an Add instruction with a label, a result register,
	 * and a source register. (The registers are enumerated).
	 *
	 * @param label optional label (can be null)
	 * @param result a register where the result of the sum operation is stored
	 * @param source a register which contains an integer that is added to the result register
	 */
	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	/**
	 * Executes the Add instruction in the given machine
	 *
	 * @param m the machine the instruction runs on
	 * @return the address of the next instruction to run
	 */
	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 + value2);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * A string representation of the Add instruction
	 *
	 * @return returns the OPP_CODE and result and source registers in a formatted string
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}

	/**
	 * Compares this object to the specified object for equality.
	 *
	 * @param o the object to compare this instance to.
	 * @return true if the specified object is an instance of AddInstruction and
	 * the result and source registers are equal.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof AddInstruction) {
			AddInstruction other = (AddInstruction) o;
			return result.equals(other.result) && source.equals(other.source);
		}
		return false;
	}

	/**
	 * Returns a hash code value for this object based on the result register,
	 * the source register, and the OP_CODE.
	 * @return a hash code value for this object
	 */
	@Override
	public int hashCode() {return Objects.hash(result, source, OP_CODE);}
}
