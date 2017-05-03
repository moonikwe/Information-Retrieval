package irframe;


import java.util.*;
import java.lang.*;
import java.io.*;

 
public class Tokenizer {
  ArrayList <Token> tokens = new ArrayList <Token>(); 
  ArrayList <String> sortedTokens = new ArrayList <String>(); 
  String Docs [] = {"CEB-Leyte.txt", "CEB-Lucban.txt", "CEB-Bataan.txt", "CEB-Batanes.txt", "CEB-Puerto-Princesa.txt", "CEB-Samal-Island.txt"};
  ArrayList <Token> tokinzzz = new ArrayList <Token>(); 
  
  public ArrayList<String> storeTokens(String [] Docs){
    Set<String> data = new HashSet<String>();
    ArrayList <String> sort = new ArrayList <String>();
    Tokenizer tokenizer = new Tokenizer();

    for(int x = 0; x < Docs.length; x++){
      try{
        File f = new File(Docs[x]);
        BufferedReader br = new BufferedReader(new FileReader(f));
        try{
          String strLine = " ";
          String fData = "";

          while((strLine = br.readLine()) != null){
            fData = fData + strLine + " ";
          }

          StringTokenizer stk = new StringTokenizer(fData, " \"'.,;{}?()*:+=/!_&^%$#@~<>[]|");

          while(stk.hasMoreTokens()) {
            boolean exist = false;
            String token=stk.nextToken();   
            // System.out.println("token: "+token);
            if(token.charAt(token.length() - 1) == '"' || token.charAt(token.length() - 1) == '?') {
              token = token.substring(0, token.length() - 1);
            }

            if(!(Character.isDigit(token.charAt(0)) && Character.isLetter(token.charAt(0)) )){
              token = token.substring(0, token.length());
            }

            //Checks if string is already stored Ex. Mga, mga
            for (String s : data) {
              exist = token.equalsIgnoreCase(s);
              if(exist)
                break;
            }
            
            if(!exist){
              data.add(token);
            }
          }
          br.close();
        }catch (FileNotFoundException e) {  
          System.err.println(e);   
        }
      }catch (Exception e){
        System.err.println("Error: " + e.getMessage());
      } 
    }  
    sort.addAll(data);
    Collections.sort(sort, String.CASE_INSENSITIVE_ORDER);
    return sort;
  }

  public void tokenize(ArrayList<String> sortedTokens, String [] Docs){
    String currentToken;
    int position;
    int occurences;
    ArrayList <String> currPosition;
    ArrayList <Document> currDocument;
    ArrayList <Token> tokies = new ArrayList <Token> ();
    Token insertToken = new Token();
    Document docu;

    for(int i = 0; i < this.sortedTokens.size(); i++){
      currentToken = this.sortedTokens.get(i).toLowerCase();
      currDocument = new ArrayList <Document> ();
      occurences = 0;

      for(int j = 0; j < this.Docs.length; j++){
        currPosition = new ArrayList <String> ();
        position = 0;
        docu = new Document();
        try{
          File f = new File(Docs[j]);
          BufferedReader br = new BufferedReader(new FileReader(f));
          try{
            String strLine = " ";
            String fData = "";

            while((strLine = br.readLine()) != null){
              fData = fData + strLine + " ";
            }

            StringTokenizer stk = new StringTokenizer(fData, " \"'.,;{}?()*:+=/!_&^%$#@~<>[]|");
            

            while(stk.hasMoreTokens()) {
              boolean exist = false;
              String token=stk.nextToken();
              position++;   
         
              if(currentToken.equalsIgnoreCase(token)){
                currPosition.add(Integer.toString(position));

              }
            }

            if(!(currPosition.isEmpty())){
              docu.docId = Integer.toString(j+1);
              docu.position = currPosition;
              currDocument.add(docu);
            }
             occurences+=currPosition.size();
            br.close();
          }catch (FileNotFoundException e) {  
            System.err.println(e);   
          }
        }catch (Exception e){
          System.err.println("Error: " + e.getMessage());
        } 
      }
      insertToken.token = currentToken;
      insertToken.occurences = occurences;
      insertToken.document = currDocument;
      //System.out.println("Token: "+insertToken.token);
      tokies.add(insertToken);
      insertToken = new Token();
    }
    this.tokinzzz = tokies;
    this.tokens = tokies;
    writeFiles();
  }

  void writeFiles () {
    Token tok;
    Document docu;
    int posSize;
    int getCurrDocSize;
    int getCurrPositionSize;
    String currDocId;
    String currPosition;
    ArrayList <Document> getCurrDoc = new ArrayList <Document>();
    ArrayList <String> getCurrPosition = new ArrayList <String> ();
    int occurences;
    String leToken;
    try{     
      BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
      for(int i = 0; i < tokinzzz.size(); i++) {
        tok = new Token();
        // System.out.println("Token: "+tokinzzz.get(i).token);
        leToken = tokinzzz.get(i).token;
        occurences = tokinzzz.get(i).occurences;
        writer.write(leToken +":");
        writer.newLine();
        getCurrDoc = tokinzzz.get(i).document;
        getCurrDocSize = getCurrDoc.size();
        // System.out.println("docSize: "+docSize+" getCurrDocSize: "+getCurrDocSize);
        for(int j = 0; j < getCurrDocSize; j++) {
          docu = new Document();
          docu = getCurrDoc.get(j);
          currDocId = docu.docId;
          // System.out.print("ID: "+currDocId + " ");
          // System.out.print(" ");
          // writer.write(currDocId);
          writer.write(currDocId + "<");          
          getCurrPosition = docu.position;
          getCurrPositionSize = getCurrPosition.size();
          for(int k = 0; k < getCurrPositionSize; k++){
            currPosition = getCurrPosition.get(k);
            writer.write("  "+currPosition);
            // writer.write(currPosition);
            // System.out.print("   "+currPosition);
          }
          // System.out.println("J NI SA IF "+j);
          writer.write(">");
          // System.out.println();
          writer.newLine();
        }
        writer.write("\n$");
        writer.newLine();
      }      
      writer.close(); 
      System.out.println("\tFinish");     
    }
    catch(Exception e){
       e.printStackTrace();
    }
      // for(int i = 0; i < tokinzzz.size(); i++) {
      //   tokies = tokinzzz.get(i).token;
      //   writer.write(tokies+ " ");
      //   // for(String s : sort) {
      //   //   writer.write(s+ " ");
      //   //   writer.newLine();
      //   // }  
      //   writer.close();       
      // }

    // // tokinzzz.size()
    // for(int i = 0; i < tokinzzz.size(); i++) {
    //   tok = new Token();
    //   System.out.println("Token: "+tokinzzz.get(i).token);
    //   getCurrDoc = tokinzzz.get(i).document;
    //   getCurrDocSize = getCurrDoc.size();
    //   // System.out.println("docSize: "+docSize+" getCurrDocSize: "+getCurrDocSize);
    //   for(int j = 0; j < getCurrDocSize; j++) {
    //     docu = new Document();
    //     docu = getCurrDoc.get(j);
    //     currDocId = docu.docId;
    //     System.out.print("ID: "+currDocId + " ");
    //     System.out.print(" ");
    //     getCurrPosition = docu.position;
    //     getCurrPositionSize = getCurrPosition.size();
    //     for(int k = 0; k < getCurrPositionSize; k++){
    //       currPosition = getCurrPosition.get(k);
    //       System.out.print("   "+currPosition);
    //     }
    //     System.out.println();
    //   }
    //   System.out.println("\n"); 
    // }
  }

  public static void main(String[] args) throws IOException{
    Tokenizer tokenizer = new Tokenizer();
    tokenizer.sortedTokens = tokenizer.storeTokens(tokenizer.Docs);
    tokenizer.tokenize(tokenizer.sortedTokens, tokenizer.Docs);
  }
}

class Token{
  String token;
  int occurences;
  ArrayList <Document> document = new ArrayList <Document>();

}  

class Document{
  String docId;
  ArrayList <String> position = new ArrayList <String>();
}
