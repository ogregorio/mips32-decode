package files;
import call.Mips32Decode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadArchive {
	private static String content = "";
	private static BufferedReader readFileRef;
	public static boolean archiveWasConnected(String way) {
      content = "";
		try{	
			FileReader file = new FileReader(way);
			BufferedReader readFile = new BufferedReader(file);
			readFileRef = readFile;
			return true;
		} catch(FileNotFoundException ex){
         System.out.println(ex.getMessage());
			return false; 
		}
	}

	public static String isPossibleReadArchive() throws IOException{
			return readFileRef.readLine();
	}

	public static String readingArchive(String way){
		String line = "";
      String out = "";
      try{
         line = ReadArchive.isPossibleReadArchive();
         if(line != null){
            out = Mips32Decode.mips32Decode(line);
            content += out;
            content += " ";
            ReadArchive.readingArchive(way);
         }
			return content;
      } catch(IOException e){
         System.out.println(e.getMessage());
         	content = "Error : Don't was possible read archive!!!";	
		      return content;
		}
	}
}
