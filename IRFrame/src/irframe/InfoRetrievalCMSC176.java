package irframe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author TRISHA NICOLE
 */
public class InfoRetrievalCMSC176 {

  /**
   * @param args the command line arguments
   */
    

  public static void main(String[] args) {
    /*************************************************************************/
    // HASHMAP map stores a key String(Token), and a value of 
    // HashMap whose key is the Document ID and a value of
    // an ArrayList<Integer> of positions.
    /*************************************************************************/
    
  }
  
  public String search(String input){
    HashMap < String, HashMap < Integer, ArrayList < Integer >>> map = new HashMap < String, HashMap < Integer, ArrayList < Integer >>> ();
    ArrayList < String > query = new ArrayList < String > ();
    
    String output = "";
    StringTokenizer st = new StringTokenizer(input, " \"'.,;{}?()*:+=/!_&^%$#@~<>[]|");
    String a;
    while(st.hasMoreElements()) {
      a = st.nextElement().toString();
      query.add(a.toLowerCase());
    }
     
    try {
      /*************************************************************************/
      // THIS TRY CATCH OPENS THE TOKENIZED TERMS
      /*************************************************************************/
      File f = new File("output.txt");
      Scanner sc = new Scanner(f);
      String word = "";
      int docId = 0;
      int pos;
      ArrayList < Integer > posi = new ArrayList < Integer > ();
      String token = "";
      
      /*************************************************************************/
      // IDPos Hashmap stores the document as a key, and a value of an
      // ArrayList of positions
      /*************************************************************************/
      HashMap < Integer, ArrayList < Integer >> IDPos = new HashMap < Integer, ArrayList < Integer >> ();
      
       /*************************************************************************/
       // THIS WHILE LOOP IS FOR STORING THE TOKENS FOUND IN THE OUTPUT.TXT
       /*************************************************************************/
      while (sc.hasNext()) {
        word = sc.next();
        if (word.contains(":")) {
          token = word.substring(0, word.length() - 1);
          posi = new ArrayList < Integer > ();
          IDPos = new HashMap < Integer, ArrayList < Integer >> ();
        } else if (word.equals("$")) {
          map.put(token, IDPos);
        } else if (word.equals("*")) {
          break;
        } else if (word.contains("<")) {
          docId = Integer.parseInt(word.substring(0, word.length() - 1));
          posi = new ArrayList < Integer > ();
        } else if (word.contains(">")) {
          pos = Integer.parseInt(word.substring(0, word.length() - 1));
          posi.add(pos);
          IDPos.put(docId, posi);

        } else {
          pos = Integer.parseInt(word);
          posi.add(pos);
        }

      }

      boolean proceed = true;
      ArrayList < Integer > query_found = new ArrayList < Integer > ();
      for (int i = 0; i < query.size(); i++) {
        if (!(map.containsKey(query.get(i)))) {
          proceed = false;
        }
      }

      if (proceed && query.size() > 1) {
        Set keys = map.get(query.get(0)).keySet();
        ArrayList < Integer > ids = new ArrayList < Integer > ();
         ArrayList < Integer > common  = new ArrayList < Integer > ();
         
        for (Iterator i = keys.iterator(); i.hasNext();) {
          int key = (int) i.next();
          common.add(key);

        }

        
        for (int i = 1; i < query.size(); i++) {
          
          Set query2Keys = map.get(query.get(i)).keySet();

          ids = new ArrayList < Integer > ();

          ArrayList < Integer > key1 = common;
          ids = common;
          ArrayList < Integer > key2 = new ArrayList < Integer > ();

          common = new ArrayList < Integer > ();
          
          for (Iterator q2 = query2Keys.iterator(); q2.hasNext();) {
            int key = (int) q2.next();
            key2.add(key);
  
          }

          int i1 = 0;
          int i2 = 0;

          while (i1 < key1.size() && i2 < key2.size()) {
            if (key1.get(i1) == key2.get(i2)) {
              common.add(key1.get(i1));
              i1++;
              i2++;
            } else {
              if (key1.get(i1) < key2.get(i2)) {
                i1++;
              } else {
                i2++;
              }
            }
          }

        }

        boolean flag = false;
        ids = common;
        for (int id = 0; id < ids.size(); id++) {
          Stack < Integer > s = new Stack();
          ArrayList < Integer > p1 = map.get(query.get(0)).get(ids.get(id));

          flag = false;

          for (int iP1 = 0; iP1 < p1.size(); iP1++) {
            s = new Stack();
            s.push(p1.get(iP1));

            ArrayList < Integer > p2 = map.get(query.get(1)).get(ids.get(id));
            int iQuery = 1;

            
            for (int iP2 = 0; iP2 < p2.size(); iP2++) {
              if (( p2.get(iP2) - 1 > s.peek() - 1 )  && (p2.get(iP2) <= s.peek() + 2) && (p2.get(iP2) != s.peek())){
                s.push(p2.get(iP2));
                iQuery++;
                if (iQuery != query.size()) {
                  p2 = map.get(query.get(iQuery)).get(ids.get(id));
                  iP2 = -1;
                } else {
                  query_found.add(ids.get(id));
                  flag = true;
                  break;
                }
              }
            }

            if (flag) {
              break;
            }

          }

        }

      } else if (proceed && query.size() == 1) {
        query_found = new ArrayList < Integer > ();

        Set keys = map.get(query.get(0)).keySet();

        for (Iterator i = keys.iterator(); i.hasNext();) {
          int key = (int) i.next();
          query_found.add(key);

        }

      }

      if (!query_found.isEmpty()) {
        for (int i = 0; i < query_found.size(); i++) {
            
            String doc = "";
            
            if(query_found.get(i) == 1){
                doc = "CEB-Leyte";
            }
            else if(query_found.get(i) == 2){
                doc = "CEB-Lucban";
            }
            else if(query_found.get(i) == 3){
                doc = "CEB-Bataan";
            }
            else if(query_found.get(i) == 4){
                doc = "CEB-Batanes";
            }
            else if(query_found.get(i) == 5){
                doc = "CEB-Puerto-Princesa";
            }
            else{
                doc = "CEB-Samal-Island";
            }
            
            output = output + "query found in Doc: " + doc + "\n";
//            System.out.println("query found in Doc ID: " + query_found.get(i) + " ");
        }
      } else {
        output = "no results found for '" + input + "'";
//        System.out.println("");
      }

    } catch (Exception e) {
      System.out.println(e.getMessage() + " error");
    }

    return output;
  }

}