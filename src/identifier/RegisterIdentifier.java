package identifier;

public class RegisterIdentifier {
	public static String opcodeIdentifier(String instruction) {
		if (instruction.substring(0, 1).equals ("j") && instruction.indexOf("$") == -1) {
			int indexFinal = 0;
			int i = 1;
			do {
				indexFinal = instruction.indexOf(Integer.toString(i));
				i++;
				if (i>9) {
					if (instruction.indexOf("jal")==0) {
						return "jal";
					} else if (instruction.indexOf("jr")==0) {
						return "jr";
					} else if (instruction.indexOf("j")==0) {
						return "j";
					}
				}
			} while (indexFinal == -1);
			return instruction.substring(0,indexFinal);
		} else {
			int indexFinal = instruction.indexOf("$");
			return instruction.substring(0,indexFinal);
		}
	}
	public static String instructionIdentifierType(String instruction) {
		switch(RegisterIdentifier.opcodeIdentifier(instruction)) {
		//type R
		case "add":return "r";
		case "sub":return "r";
		case "mult":return "r";
		case "div":return "r";
		case "xor":return "r";
		case "nor":return "r";
		case "slt":return "r";
		case "and":return "r";
		case "sll":return "i";
		case "srl":return "r";
		case "jr":return "j";
		case "or":return "r";
		case "neg":return "r";
		//type J
		case "j":return "j";
		case "jal":return "j";
		//type I
		case "addi":return "i";
		case "lw":return "i";
		case "sw":return "i";
		case "beq":return "i";
		case "bne":return "i";
		case "slti":return "i";
		case "andi":return "i";
		case "ori":return "i";
		}
		return null;
	}
}
