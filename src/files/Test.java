package files;

public class Test{
   public static void main(String[] args){
      String wayInput = args[0];
      String wayOutput = args[1];
      ReadArchive.archiveWasConnected(wayInput);
      String [] allContentThatWasOnTextArchive = ReadArchive.readingArchive(wayInput).split(" ");
      if(WriteArchive.writingOnArchive(wayOutput, allContentThatWasOnTextArchive))
         System.out.println("The operation of write on archive was finalize with sucess !!!");
         else
            System.out.println("Error, the operation of write on archive was finalize without sucess !!!");
   }
}