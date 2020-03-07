package decoders;

import java.util.*;

public class DecodeRegister {
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
			regValue.add(i,decodeRegisterByIndex(regValueFinal.get(i)));
		}
			return regValue.get(2)+" "+regValue.get(1)+" "+regValue.get(0)+" ";
			
	}
	public String decodeRegistersTypeJ(String instruction) {
		int initialPosition = instruction.indexOf("$");
		int finalPosition = instruction.indexOf("$")+3;
		String regValueFinal = "";
		String regVariable = "";
		String regValue = "";
		for (int i=0;i<=9;i++) {
			if ( (instruction.substring(instruction.indexOf("$")+3)).equals( Integer.toString(i) )) {
				regVariable = instruction.substring(initialPosition+1,finalPosition-1);
				regValue = instruction.substring(initialPosition+2,finalPosition+1);
				break;
			} else {
				regVariable = instruction.substring(initialPosition+1,finalPosition-1);
				regValue = instruction.substring(initialPosition+2,finalPosition);
			}
		}
		
		if ( (regVariable.equals("t")) || (regVariable.equals("r")) ) {
			regValueFinal = regValue;
		} else if ( regVariable.equals("s") ) {
			regValueFinal = regValue;
		} else if( regVariable.equals("z") ) {
			regValueFinal = "0";
		}
		
		int convBinary = Integer.parseInt(regValueFinal);
		int[] convBinaryFinal = new int[5];
		for (int i = 0; i<=4; i++) {
			convBinaryFinal[i] = convBinary%2;
			convBinary /= 2;
		}
		regValueFinal = "";
		for (int i = 4; i>=0; i--) 
			regValueFinal += Integer.toString(convBinaryFinal[i]);
		return regValueFinal+" ";
	}
	public String decodeRegistersTypeI(String instruction) {return "teste";}
	
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
	// Converte a instrução do registrador através do index para binário
	public String decodeRegisterByIndex(String regValueFinal) {
		switch (regValueFinal) {
		case "0":return "00000";
		case "1":return "00001";
		case "2":return "00010";
		case "3":return "00011";
		case "4":return "00100";
		case "5":return "00101";
		case "6":return "00110";
		case "7":return "00111";
		case "8":return "01000";
		case "9":return "01001";
		case "10":return "01010";
		case "11":return "01011";
		case "12":return "01100";
		case "13":return "01101";
		case "14":return "01110";
		case "15":return "01111";
		case "16":return "10000";
		case "17":return "10001";
		case "18":return "10010";
		case "19":return "10011";
		case "20":return "10100";
		case "21":return "10101";
		case "22":return "10110";
		case "23":return "10111";
		case "24":return "11000";
		case "25":return "11001";
		case "26":return "11010";
		case "27":return "11011";
		case "28":return "11100";
		case "29":return "11101";
		case "30":return "11110";
		case "31":return "11111";
		}
		return regValueFinal;
	}
}
