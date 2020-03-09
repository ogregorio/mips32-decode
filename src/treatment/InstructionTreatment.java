package treatment;

public class InstructionTreatment {
	public static String instructionTreatment(String instruction) {
		String aux = instruction.replace(" ", "");
		instruction = aux.replace(",", "");
		aux = instruction.replace("zero", "z0");
		instruction = aux.replace(")", "");
		aux = instruction.replace("(", "");
		System.out.println(aux);
		return aux;
	}
}
