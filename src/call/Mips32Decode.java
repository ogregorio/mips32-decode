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
				instruction = decodeOP.decodeOpcode(opcode)+decode.decodeRegistersTypeR(instruction);
				break;
			case "j":
				instruction = decodeOP.decodeOpcode(opcode)+decode.decodeRegistersTypeJ(instruction);
				break;
			case "i":
				instruction = decodeOP.decodeOpcode(opcode)+decode.decodeRegistersTypeI(instruction);
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
