package treatment;

public class InstructionTreatment {
	public static String instructionTreatment(String instruction) {
		String aux = instruction.replace(" ", "");
		instruction = aux.replace(",", "");
		System.out.println(instruction);
		return instruction;
	}
}
