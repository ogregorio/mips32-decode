package treatment;

import java.util.List;

public class BinaryConversor {
	
	public String convBinaryList(List<String> regValueFinal, int get, int quant) {
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
