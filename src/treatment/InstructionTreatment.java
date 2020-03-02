package treatment;

public class InstructionTreatment {
	public static String instructionTreatment(String instruction) {
		String aux = instruction.replace(" ", "");
		instruction = aux.replace(",", "");
		aux = instruction.replace("(zero)", "z0");
		instruction = aux;
		System.out.println(instruction);
		return instruction;
	}
}
