package treatment;

import java.util.List;

public class BinaryConversor {
	
	public static String convBinary(String regValueFinal) {
		int convBinary = Integer.parseInt(regValueFinal);
		int[] convBinaryFinal = new int[5];
		for (int i = 0; i<=4; i++) {
			convBinaryFinal[i] = convBinary%2;
			convBinary /= 2;
		}
		String endConv = "";
		for (int i = 4; i >= 0; i--) {
			endConv += ((Integer.toString(convBinaryFinal[i])));
		}
		return endConv;
	}
	
	public static String convBinaryList(List<String> regValueFinal, int get, int quant) {
		int convBinary = Integer.parseInt(regValueFinal.get(get));
		int[] convBinaryFinal = new int[quant];
		for (int i = 0; i<=(quant-1); i++) {
			convBinaryFinal[i] = convBinary%2;
			convBinary /= 2;
		}
		String endConv = "";
		for (int i = (quant-1); i >= 0; i--) {
				endConv += ((Integer.toString(convBinaryFinal[i])));
		}
		return endConv;
	}

}
