package files;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteArchive{
   private static PrintWriter toRecordArchive;
   public static boolean isPossibleWriteOnArchive(String way){
      try{
         FileWriter archive = new FileWriter(way);
         toRecordArchive = new PrintWriter(archive);
         return true;
         
      } catch(IOException e){
         System.out.println(e.getMessage());
         return false;
      }
   }
   
   public static boolean writingOnArchive(String way ,String [] phrases){
      if(WriteArchive.isPossibleWriteOnArchive(way)){
         for(String phrase : phrases)
            toRecordArchive.print(phrase+" \n");
         toRecordArchive.close();
         return true;
      }
      return false;
   }
}
