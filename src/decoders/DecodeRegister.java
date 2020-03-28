package decoders;

import java.util.*;

import treatment.ASCIIToInt;
import treatment.BinaryConversor;

public class DecodeRegister {
	BinaryConversor conv = new BinaryConversor();
	ASCIIToInt ascii = new ASCIIToInt();
	// Decodifica instrucoes do tipo R
	public String decodeRegistersTypeR(String instruction) {

		List<String> regVariable = new ArrayList<String>();
		List<String> regValue = new ArrayList<String>();
		List<String> regValueFinal = new ArrayList<String>();

		//Desfragementa a instrucao em registradores
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
		//Decodifica em binario buscando o index na tabela
		for (int i = 0; i <= 2; i++) {
			regValue.add(i,conv.convBinaryList(regValueFinal, i, 5));
		}
			return regValue.get(2)+regValue.get(1)+regValue.get(0);
			
	} // Decodifica instrucoes do tipo J
	public String decodeRegistersTypeJ(String instruction) {
		int initialPosition = instruction.indexOf("$");
		int finalPosition = instruction.indexOf("$")+3;
		List<String> regVariable = new ArrayList<String>();
		List<String> regValue = new ArrayList<String>();
		List<String> regValueFinal = new ArrayList<String>();

		if (initialPosition != -1) { // Sessao de conversao com registradores
			if ( (instruction.substring(instruction.indexOf("$")+3)).equals( Integer.toString(0) )) {
				regVariable.add(0, instruction.substring(initialPosition+1,finalPosition-1));
				regValue.add(0, instruction.substring(initialPosition+2,finalPosition+1));
			} else {
				regVariable.add(0, instruction.substring(initialPosition+1,finalPosition-1));
				regValue.add(0, instruction.substring(initialPosition+2,finalPosition));
			}
			regValueFinal.add(0, Integer.toString(decodeRegVariable(regVariable.get(0),regValue.get(0))));
			regValue.add(0,conv.convBinaryList(regValueFinal, 0, 5));
			return regValue.get(0); // FIM DA SESSAO COM REGISTRADORES
		} else { // Sessao de conversao sem registradores
			int indexFinal = 0;
			int i = 1;
			do { // Subsessao de conversao para ASCII
				indexFinal = instruction.indexOf(Integer.toString(i));
				i++;
				if (i>9) {
					if (instruction.indexOf("jal")==0) {
						regValue.add(0, instruction.substring(3, instruction.length()));
						regVariable.add(0, "null");
						String end = ascii.ConvASCII(regValue.get(0));
						return end;
						
					} else if (instruction.indexOf("jr")==0) {
						regValue.add(0, instruction.substring(2, instruction.length()));
						regVariable.add(0, "null");
						String end = ascii.ConvASCII(regValue.get(0));
						return end;
						
					} else if (instruction.indexOf("j")==0) {
						regValue.add(0, instruction.substring(1, instruction.length()));
						regVariable.add(0, "null");
						String end = ascii.ConvASCII(regValue.get(0));
						return end;
						
					}
				} // FIM DA SUBSESsaO DE CONVERsaO PARA ASCII
			} while (indexFinal == -1);
			regValue.add(0, instruction.substring(indexFinal, instruction.length()));
			regVariable.add(0, "null");
			
			regValueFinal.add(0, Integer.toString(decodeRegVariable(regVariable.get(0),regValue.get(0))));
			regValue.add(0,conv.convBinaryList(regValueFinal, 0, 15));
			return regValue.get(0);
		} // FIM DA SESsaO DE CONVERsaO SEM REGISTRADORES
			
	} // Decodifica instrucoes do tipo I
	public String decodeRegistersTypeI(String instruction) {
		//A princípio são criadas listas que receberão as variáveis e valores, além de um String
		//que guarda qual tipo de instrução é recebida após ser subdividida
		List<String> regVariable = new ArrayList<String>();
		List<String> regValue = new ArrayList<String>();
		List<String> regValueFinal = new ArrayList<String>();
		String OgIns = instruction;	//guarda instrução original
		//são cirados INTs que descobrirão onde "cortar" a instrução para separar seus valores
		//e variáveis (ex. separando o "s" e o "0" de $s0)
		int initialPosition = 0;
		int finalPosition = 0;
			
			//a instrução passa por dois tipos diferentes de tratamento dependendo de sua instrução.
			//caso seja lw ou sw, segue as instruções abaixo
			if (OgIns.substring(0, 2).equals("lw") || OgIns.substring(0, 2).equals("sw")) {
				//as posições final e inicial são definidas para capturar os valores e variáveis
				initialPosition = 0;
				finalPosition = instruction.indexOf("$")+3;
				//a partir das posições, adicionamos o valor e variável descobertos a
				//regVariable e regValue usando substrings
				regVariable.add(0, instruction.substring(initialPosition+3,finalPosition-1));
				regValue.add(0, instruction.substring(initialPosition+4,finalPosition));
				//após isso, "cortamos" a instrução original para que esta seja
				//processada novamente abaixo.
				instruction = instruction.substring(finalPosition,instruction.length());
				
				//a instrução, que foi cortada acima, passa por um novo processamento para
				//descobrir o próximo valor e variável. aqui, caso a instrução seja lw ou sw,
				//tratamos a segunda variável como um imediato
				initialPosition = 0;
				finalPosition = (instruction.indexOf("$"));
				regValue.add(1, instruction.substring(initialPosition,finalPosition-1));
				regVariable.add(1,"s");
				instruction = instruction.substring(finalPosition,instruction.length());
				
				initialPosition = (instruction.indexOf("$"));
				finalPosition = instruction.length();
				regVariable.add(2,instruction.substring(initialPosition+1,finalPosition-1));
				regValue.add(2, instruction.substring(initialPosition+1,finalPosition-1));
				
				//após adquirirmos os 3 valores distintos, iremos os adicionar a regValueFinal
				for (int i = 0; i <= 2; i++) {
					regValueFinal.add(i, Integer.toString(decodeRegVariable(regVariable.get(i),regValue.get(i))));
				}

				//finalmente convertemos cada valor para um valor binário de acordo com
				//o número de casas necessárias na conversão. binaryA e binaryC irão armazenar
				//os valores nos registradores, que irão até 11111 no máximo. binaryB irá converter
				//um número de 16 bits, que pode ter até 15 numerais. após a conversão, os strings
				//são concatenados e retornados.
				String binaryA = conv.convBinaryList(regValueFinal, 0, 5);
				String binaryB = conv.convBinaryList(regValueFinal, 1, 15);
				String binaryC = conv.convBinaryList(regValueFinal, 2, 5);
				return binaryC+binaryB+binaryA;
				
			} else {
				//caso a instrução recebida não seja lw ou sw, usaremos um tratamento diferente
				//que irá receber o imediato em binaryC (a terceira instrução) ao invés de binaryB.
				initialPosition = (instruction.indexOf("$")+1);
				finalPosition = (instruction.indexOf("$")+3);
				regVariable.add(0, instruction.substring(initialPosition,finalPosition-1));
				regValue.add(0, instruction.substring(initialPosition+1,finalPosition));
				instruction = instruction.substring(finalPosition,instruction.length());
				
				initialPosition = 1;
				finalPosition = 3;
				regVariable.add(1, instruction.substring(initialPosition,finalPosition-1));
				regValue.add(1, instruction.substring(initialPosition+1,finalPosition));
				instruction = instruction.substring(finalPosition,instruction.length());
				
				//vale ressaltar que aqui, como não temos um registrador, devolvemos a variável
				//do tipo "null" para que esta seja tratada de forma especial abaixo, em decodeRegVariable
				initialPosition = 0;
				finalPosition = instruction.length();
				regVariable.add(2, "null");
				regValue.add(2, instruction.substring(initialPosition,finalPosition));
				
				for (int i = 0; i <= 2; i++) {
					regValueFinal.add(i, Integer.toString(decodeRegVariable(regVariable.get(i),regValue.get(i))));
				}

				String binaryA = conv.convBinaryList(regValueFinal, 0, 5);
				String binaryB = conv.convBinaryList(regValueFinal, 1, 5);
				String binaryC = conv.convBinaryList(regValueFinal, 2, 15);
				return binaryC+binaryB+binaryA;
			}
			

		
	}
	
	// Decodifica a "letra do registrador" e devolve sua posicao no index de registradores
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
		case "null": return Integer.parseInt(regValue);
		}
		return 0;
	}
}
