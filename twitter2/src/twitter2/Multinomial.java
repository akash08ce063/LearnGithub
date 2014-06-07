package twitter2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author Ricardo KAKA
 */
public class Multinomial {
    int classwords[] = new int[5];
    int totalvocab;
    String FinalString[] = new String[5];
    HashMap positive = new HashMap();
        HashMap negative = new HashMap();
        
    Multinomial() throws FileNotFoundException, IOException{
     //   calculateprobability();
       // calculatefinalresult("exceptionally well movie");
        FileReader fr = new FileReader(new File("positive-words.txt"));
        BufferedReader br = new BufferedReader(fr);
        System.out.println("I am here");
        String newLine= "";
        while((newLine= br.readLine())!= null){
            
            StringTokenizer stt = new StringTokenizer(newLine," ");
            String tokens = (String)stt.nextToken();
            int rate =Integer.parseInt(stt.nextToken());
            positive.put(tokens, rate);
         //   System.out.println("Token"+tokens+"rate"+rate);
            
        }
        
      //  System.out.println(positive.toString());
        
        FileReader fri = new FileReader(new File("negative-words.txt"));
        BufferedReader bri = new BufferedReader(fri);
         while((newLine= bri.readLine())!= null){
            
            //StringTokenizer stt = new StringTokenizer(newLine," ");
            //String tokens = (String)stt.nextToken();
           
            negative.put(newLine,2);
         //   System.out.println("Token"+tokens+"rate"+rate);
            
        }
        
    //    System.out.println(negative.toString());
        
        
    }
    
   public void calculateprobability() throws FileNotFoundException, IOException{
        File a[] = new File[]{new File("class 1.txt"),new File("class 2.txt"),new File("class 3.txt"),new File("class 4.txt"),new File("class 5.txt")};
        
        
        for(int i = 0 ; i < a.length ; i++){
        FileReader fr = new FileReader(a[i]);
        BufferedReader br = new BufferedReader(fr);
        ArrayList uniqueness = new ArrayList();
        FinalString[i] = "" ;
        String string2; 
        String tempstring="";
        int uniquecount = 0;
        while((string2 = br.readLine())!= null){
             FinalString[i]  =  FinalString[i] + string2;
             StringTokenizer str = new StringTokenizer(FinalString[i]," ");
             while(str.hasMoreTokens()){
             tempstring = str.nextToken();
             if(!uniqueness.contains(tempstring)){
                 uniqueness.add(string2);
                 uniquecount++ ;
             }
            }
        }
         classwords[i] = uniquecount;
         System.out.println("unique count"+ uniquecount);
         totalvocab = totalvocab + uniquecount ;
       }System.out.println("total voacb"+totalvocab);
        
   }
   public int calculatefinalresult(String s){
       System.out.println(classwords[0]);
       double maxprobability = 0  ;
       int estimatedrate = 0 ;
       float finalestimate = 0;
        ArrayList str  =new ArrayList();
        int tokencount = 0;
        StringTokenizer strr = new StringTokenizer(s," ");
        while(strr.hasMoreTokens()){
            String process = strr.nextToken();
            str.add(process);
       if(positive.containsKey(process)){
                estimatedrate = (int)positive.get(process) + estimatedrate ;
                tokencount++ ;
               //  System.out.println("Here");
            }if(negative.containsKey(process)){
                estimatedrate = (int)negative.get(process) + estimatedrate ;
                tokencount++ ;
            }
            
        }
        
        finalestimate = (float)estimatedrate / tokencount ;
        int result = 0;
        
       for(int i = 0 ; i < 5 ; i++){
           
         double probability = 0   ;  
         for(int j=0 ; j <str.size() ; j++){  
           
            int count = 0 ;
            int startIndex = FinalString[i].indexOf((String)str.get(j));
          while (startIndex != -1) {
            count++;
            startIndex = FinalString[i].indexOf((String)str.get(j), 
                                  startIndex +str.get(j).toString().length());
          }
            probability =probability + Math.log((double)(count+1)/(double)(classwords[i]+totalvocab));
         }
         
         if(i == 0){
             maxprobability = probability;
         }
         if(probability > maxprobability){
              maxprobability = probability ;
              result = i ;
         }
          
       }
       
       
      System.out.println("Rating is :"+(result+1));
       
       if(finalestimate >= 3 && (result+1) <=2 ){
           if(finalestimate > 3){
               result = 3;
           }
       }
    //   if(estimatedrate == 0 && tokencount == 0){
      //  return 1 ;
    //   }else{
       return result+1 ;
      // }
   }
   
    
}
