package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static sml.Registers.Register;

/**
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author ...
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    private HashMap<String, InstructionDescriptor> instructionMap;

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Stores an Instruction subclass along with its Constructor parameters
     */
    private class InstructionDescriptor{
        private Class<? extends Instruction> instructionClass;
        private Class[] constructorParameters;

        public InstructionDescriptor(Class<? extends Instruction> instructionClass,Class... constructorParameters){
            this.instructionClass = instructionClass;
            this.constructorParameters = constructorParameters;
        }

        public Class<? extends Instruction> getInstructionClass(){
            return instructionClass;
        }

        public Class[] getConstructionParameters(){
            return constructorParameters;
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {

        instructionMap = new HashMap<>();

        instructionMap.put(AddInstruction.OP_CODE, new InstructionDescriptor(AddInstruction.class, String.class, RegisterName.class, RegisterName.class));
        instructionMap.put(SubInstruction.OP_CODE, new InstructionDescriptor(SubInstruction.class, String.class, RegisterName.class, RegisterName.class));
        instructionMap.put(MulInstruction.OP_CODE, new InstructionDescriptor(MulInstruction.class, String.class, RegisterName.class, RegisterName.class));
        instructionMap.put(DivInstruction.OP_CODE, new InstructionDescriptor(DivInstruction.class, String.class, RegisterName.class, RegisterName.class));
        instructionMap.put(MovInstruction.OP_CODE, new InstructionDescriptor(MovInstruction.class, String.class, RegisterName.class, int.class));
        instructionMap.put(JnzInstruction.OP_CODE, new InstructionDescriptor(JnzInstruction.class, String.class, RegisterName.class, String.class));
        instructionMap.put(OutInstruction.OP_CODE, new InstructionDescriptor(OutInstruction.class, String.class, RegisterName.class));

        if (line.isEmpty()) {
            return null;
        }

        String opcode = scan();

        InstructionDescriptor instructionData = instructionMap.get(opcode);
        if (instructionData == null) {
            System.out.println("Unknown instruction: " + opcode);
            return null;
        }

        Class<? extends Instruction> instructionClass = instructionData.getInstructionClass();
        Class[] constructorParams = instructionData.getConstructionParameters();

        System.out.println(Arrays.stream(constructorParams).toList());
        try {
            Constructor<? extends Instruction> constructor = instructionClass.getConstructor(constructorParams);

            String r = scan();
            String s = scan();

            Instruction instruction = null;

            if( constructorParams.length < 3)

                instruction = constructor.newInstance(label, Register.valueOf(r));

            else if( constructorParams[2].toString().equals("int"))

                instruction = constructor.newInstance(label, Register.valueOf(r), Integer.valueOf(s));

            else if( constructorParams[2].toString().equals("interface sml.RegisterName"))

                instruction = constructor.newInstance(label, Register.valueOf(r), Register.valueOf(s));

            else if( constructorParams[2].toString().equals("class java.lang.String"))

                instruction = constructor.newInstance(label, Register.valueOf(r), s);

            else{
                System.out.println("Invalid parameter type");
                System.exit(-1);
            }

            return instruction;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}