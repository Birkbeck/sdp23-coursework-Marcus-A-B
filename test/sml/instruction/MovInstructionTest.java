package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class MovInstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    Instruction instruction = new MovInstruction(null, EDX, 131);
    instruction.execute(machine);
    Assertions.assertEquals(131, machine.getRegisters().get(EDX));
  }

  @Test
  void executeValidTwo() {
    Instruction instruction = new MovInstruction(null, ECX, -255);
    instruction.execute(machine);
    Assertions.assertEquals(-255, machine.getRegisters().get(ECX));
  }
}