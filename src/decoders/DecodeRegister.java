package decoders;

import java.util.*;

import treatment.BinaryConversor;

public class DecodeRegister {
	BinaryConversor conv = new BinaryConversor();
	// Decodifica instruções do tipo R
	public String decodeRegistersTypeR(String instruction) {

		List<String> regVariable = new ArrayList<String>();
		List<String> regValue = new ArrayList<String>();
		List<String> regValueFinal = new ArrayList<String>();

		//Desfragementa a instrução em registradores
		for (int i = 0; i <= 2; i++) {
			int initialPosition = instruction.indexOf("$");
			int finalPosition = instruction.indexOf("$")+3;
			regVariable.add(i, instruction.substring(initialPosition+1,finalPosition-1));
			regValue.add(i, instruction.substring(initialPosition+2,finalPosition));
			instruction = instruction.substring(finalPosition,instruction.length());
		}
		//Decodifica o regitrador e devolve seu index na tabela
		for (int i = 0; i <= 2; i++) {
			regValueFinal.add(i, Integer.toString(decodeRegVariable(regVariable.get(i),regValue.get(i))));
		}
		//Decodifica em binário buscando o index na tabela
		for (int i = 0; i <= 2; i++) {
			regValue.add(i,conv.convBinaryList(regValueFinal, i, 5));
		}
			return regValue.get(2)+" "+regValue.get(1)+" "+regValue.get(0)+" ";
			
	}
	public String decodeRegistersTypeJ(String instruction) {
		int initialPosition = instruction.indexOf("$");
		int finalPosition = instruction.indexOf("$")+3;
		List<String> regVariable = new ArrayList<String>();
		List<String> regValue = new ArrayList<String>();
		List<String> regValueFinal = new ArrayList<String>();

			if ( (instruction.substring(instruction.indexOf("$")+3)).equals( Integer.toString(0) )) {
				regVariable.add(0, instruction.substring(initialPosition+1,finalPosition-1));
				regValue.add(0, instruction.substring(initialPosition+2,finalPosition+1));
			} else {
				regVariable.add(0, instruction.substring(initialPosition+1,finalPosition-1));
				regValue.add(0, instruction.substring(initialPosition+2,finalPosition));
			}

		

			regValueFinal.add(0, Integer.toString(decodeRegVariable(regVariable.get(0),regValue.get(0))));

		
			regValue.add(0,conv.convBinaryList(regValueFinal, 0, 5));
		return regValue.get(0)+" ";
	}
	public String decodeRegistersTypeI(String instruction) {
		List<String> regVariable = new ArrayList<String>();
		List<String> regValue = new ArrayList<String>();
		List<String> regValueFinal = new ArrayList<String>();

		
			int initialPosition = 0;
			int finalPosition = instruction.indexOf("$")+3;
			regVariable.add(0, instruction.substring(initialPosition+3,finalPosition-1));
			regValue.add(0, instruction.substring(initialPosition+4,finalPosition));
			instruction = instruction.substring(finalPosition,instruction.length());

			initialPosition = 0;
			finalPosition = (instruction.indexOf("$"));
			regValue.add(1, instruction.substring(initialPosition,finalPosition-1));
			regVariable.add(1,"s");
			instruction = instruction.substring(finalPosition,instruction.length());
			
			initialPosition = (instruction.indexOf("$"));
			finalPosition = instruction.length();
			regVariable.add(2,instruction.substring(initialPosition+1,finalPosition-1));
			regValue.add(2, instruction.substring(initialPosition+1,finalPosition-1));

		for (int i = 0; i <= 2; i++) {
			regValueFinal.add(i, Integer.toString(decodeRegVariable(regVariable.get(i),regValue.get(i))));
		}

		String binaryA = conv.convBinaryList(regValueFinal, 0, 5);
		String binaryB = conv.convBinaryList(regValueFinal, 1, 15);
		String binaryC = conv.convBinaryList(regValueFinal, 2, 5);
		return binaryC+" "+binaryB+" "+binaryA+" ";
	}
	
	// Decodifica a "letra do registrador" e devolve sua posição no index de registradores
	public int decodeRegVariable(String regVariable, String regValue) {
		regVariable = (regVariable.equals("s") && regValue.equals("p") == true)?"sp":regVariable;
		regVariable = (regVariable.equals("t") && regValue.equals("8") == true)?"t8":regVariable;
		regVariable = (regVariable.equals("t") && regValue.equals("9") == true)?"t9":regVariable;
		regVariable = (regVariable.equals("a") && regValue.equals("t") == true)?"at":regVariable;
		switch(regVariable) {
		case "s": return Integer.parseInt(regValue) + 15;
		case "t": return Integer.parseInt(regValue)+ 7;
		case "a": return Integer.parseInt(regValue) + 3;
		case "z": return 0;
		case "v": return Integer.parseInt(regValue) + 1;
		case "sp": return 29;
		case "t8": return 24;
		case "t9": return 25;
		case "k": return Integer.parseInt(regValue) + 24;
		case "g": return 28;
		case "f": return 30;
		case "r": return 31;
		case "at": return 1;
		}
		return 0;
	}
}
