package etl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author arnaud
 */
public class loadCSV {


   private Data data;

   public loadCSV(String filePath) throws IOException {


      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      data = new Data();

      StringTokenizer st;
      //Separator to the data Input
      String separator = ","; // Notre separateur ,

     

      // first line: header
      String line = reader.readLine();
      int i = 0;
      
      /**
       * Missing values are identified by "SeparatorSeparator". We replace by "SeparatorNANSeparator"
       * Notice that it should never happen. If it is the case, it is better
       * to send data to trash :)
       */ 
      while (line.contains(separator + separator)) 
      {
         line = line.replaceAll(separator + separator, separator +" UNKNOWN_ATTRIBUTE_"+ i + separator);
         i++;
      }
      
      st = new StringTokenizer(line,separator);

      int currentToken = 0;
      int start = 1;//Global.getColumnStart();
      int end = 10;
      //Todo Global.getColumnEnd();
      int id   = 1;
      // Todo Global.getColumnId();

      int columnId = 0;
      
      
      reader.close();
   }
   
   public Data getData() {
	   return this.data;
   }

}
