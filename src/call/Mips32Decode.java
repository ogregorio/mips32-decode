package call;

import java.util.Scanner;

import decoders.DecodeOpcode;
import decoders.DecodeRegister;
import identifier.RegisterIdentifier;
import treatment.InstructionTreatment;

public class Mips32Decode {

	static Scanner scanner = new Scanner(System.in);
	static String instructionType;
	public static void main(String[] args) {
		String teste = "";
		DecodeRegister decode = new DecodeRegister();
		DecodeOpcode decodeOP = new DecodeOpcode();
		String instruction = scanner.nextLine();
		instruction = InstructionTreatment.instructionTreatment(instruction);
		instructionType = RegisterIdentifier.instructionIdentifierType(instruction);
		String opcode = RegisterIdentifier.opcodeIdentifier(instruction);
		System.out.println(instruction+" "+opcode+" "+instructionType);
		switch(instructionType) {
		case "r":
			teste = decode.decodeRegistersTypeR(instruction)+decodeOP.decodeOpcode(opcode);
			break;
		case "j":
			teste = decode.decodeRegistersTypeJ(instruction)+decodeOP.decodeOpcode(opcode);
			break;
		case "i":
			teste = decode.decodeRegistersTypeI(instruction)+decodeOP.decodeOpcode(opcode);
			break;	
		}
		System.out.println(teste);
	}

}
