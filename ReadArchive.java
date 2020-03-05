package trabalhandoComArquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadArchive {
	private static String content = "";
	private static BufferedReader readFileRef;
	public static boolean archiveWasConnected(String way) {
		try{	
			FileReader file = new FileReader(way);
			BufferedReader readFile = new BufferedReader(file);
			readFileRef = readFile;
			return true;
		} catch(FileNotFoundException ex){
			return false; 
		}
	}

	public static boolean isPossibleReadArchive(){
		try{
			content = "";
			String line = readFileRef.readLine();
			content += line;
			return true;
		} catch(IOException ex){
			return false;
		}
	}

	public static String readingArchive() throws IOException{
		String line = "";
		if(ReadArchive.isPossibleReadArchive()){
			line = readFileRef.readLine();
			if(line != null){
				content += line;
				ReadArchive.readingArchive();
			}
			return content;
		}
		content = "Error : Don't was possible read archive!!!";	
		return content;
	}
}
