import call.Mips32Decode;
public class MIPS32 {
	public static void main(String[] args) {
		String instruction = "add $s1, $s2, $s3";
		Mips32Decode.mips32Decode(instruction);
	}
}
