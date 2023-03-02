package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class JnzInstructionTest {
  private Machine machine;
  private Registers registers;
  private Labels labels;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    labels = new Labels();
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    machine.getLabels().addLabel("f3", 2);
    registers.set(EAX, 0);
    Instruction instruction = new JnzInstruction(null, EAX, "f3");
    Assertions.assertEquals(-1, instruction.execute(machine));
  }

  @Test
  void executeValidTwo() {
    machine.getLabels().addLabel("f3", 2);
    registers.set(EAX, 5);
    Instruction instruction = new JnzInstruction(null, EAX, "f3");
    Assertions.assertEquals(2, instruction.execute(machine));
  }
}