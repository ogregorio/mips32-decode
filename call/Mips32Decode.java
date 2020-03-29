package call;

import java.util.Scanner;

import decoders.DecodeOpcode;
import decoders.DecodeRegister;
import identifier.RegisterIdentifier;
import treatment.InstructionTreatment;

public class Mips32Decode {

	static Scanner scanner = new Scanner(System.in);
	static String instructionType;
	public static String mips32Decode(String instruction) {
		DecodeRegister decode = new DecodeRegister();
		DecodeOpcode decodeOP = new DecodeOpcode();
		if (!instruction.equals ("nop")) {
			instruction = InstructionTreatment.instructionTreatment(instruction);
			instructionType = RegisterIdentifier.instructionIdentifierType(instruction);
			String opcode = RegisterIdentifier.opcodeIdentifier(instruction);
			System.out.println(instruction+" "+opcode+" "+instructionType);
			switch(instructionType) {
			case "r":
				instruction = decode.decodeRegistersTypeR(instruction)+decodeOP.decodeOpcode(opcode);
				break;
			case "j":
				instruction = decode.decodeRegistersTypeJ(instruction)+decodeOP.decodeOpcode(opcode);
				break;
			case "i":
				instruction = decode.decodeRegistersTypeI(instruction)+decodeOP.decodeOpcode(opcode);
				break;
			}
			System.out.println(instruction);
			System.out.println("Instruction decoded with no exception! Proceeding...\n");
			return instruction;
		}
		else {
			instruction = " ";
			return instruction;
		}
		
	}

}
