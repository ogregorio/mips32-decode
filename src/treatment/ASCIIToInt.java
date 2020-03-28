package treatment;

public class ASCIIToInt {

	public String ConvASCII (String value) {
		
		String finalASCII = "";
		int[] finalC = new int[8];
		for (int i=0;i<value.length();i++) {
			int c = value.charAt(i);
			for (int j = 0; j<=7; j++) {
				finalC[j] = c%2;
				c /= 2;
			}
			for (int j = 7; j>=0; j--) {
				finalASCII += finalC[j];
			}
		}
		return finalASCII;
	}
	
}
