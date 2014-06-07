/*
 *  Social scoring. It is basically movie rating but it uses social connection and sentiment anyalysis to give the rating
 *  Multinomial Bysian classifier has been used for sentiment anaylysis. with same prior probability for all class.
 */
package twitter2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
/**
 *
 * @author Ricardo KAKA
 */
public class Twitter2 {
 private final static String CONSUMER_KEY = "G0z6UhwhTeTUPbtnr9A5AQ";
    private final static String CONSUMER_KEY_SECRET =
     "2OBrYSmOG7h2FAU1qbDC7qNE1dlwecM6VSHzcYdsxAc";

    public void start() throws TwitterException, IOException {

 Twitter twitter = new TwitterFactory().getInstance();
 twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);

 // here's the difference
 String accessToken = getSavedAccessToken();
 String accessTokenSecret = getSavedAccessTokenSecret();
 AccessToken oathAccessToken = new AccessToken(accessToken,
  accessTokenSecret);

 twitter.setOAuthAccessToken(oathAccessToken);
 // end of difference
      User u1 = null ;   // User u1 it is for only users
      long cursor = -1;  // cursor it is long int.
      IDs ids;           // Id's are the class
      System.out.println("Listing followers's ids.");
     
      cursor = -1;
      // Get the followers ..... 
      //long cursor = -1;
      //PagableResponseList<User> followers;
      //do {
      //    followers = twitter.getFollowersList("screenName", cursor);
      //   for (User follower : followers) {
        // TODO: Collect top 10 followers here
     //       System.out.println(follower.getName() + " has " + follower.getFollowersCount() + " follower(s)");
    //   }
    // } while ((cursor = followers.getNextCursor()) != 0);
     //  PagableResponseList<User> pp =    (PagableResponseList<User>) twitter.getFriendsIDs(oathAccessToken.getUserId(), cursor);
        
      
     try {
         /*File f = new File("Classify.txt");
         FileReader reader = new FileReader(f);
         BufferedReader br = new BufferedReader(reader);
         
         String query = "" ;
         while((query = br.readLine())!= null){
             if(query.startsWith("#")){
                 query = query.substring(1);
                 query = query +" movie" ;
              //   System.out.println(query);
                 performQuery(query,twitter,true);
             }else{
                 query = query +" movie" ;
                 performQuery(query,twitter,false);
              //   System.out.println(query);
             }
         }
         */
       //  Facebookapi fapi = new Facebookapi();
         
         int a = 0 ;
         while(true){
             Scanner sc = new Scanner(System.in);
         System.out.println("1.Sentiment Rate 2.Enter name of movie for rating 4.quite ");
         System.out.println("2.Enter name of movie for rating  ");
         System.out.println("4.quite ");
         a = sc.nextInt();
         if(a == 4){break;}
         if(a == 1){
             System.out.println("Ok now write down the sentiment");
             String query = sc.next();
             Multinomial mm = new Multinomial();
             mm.calculateprobability();
             mm.calculatefinalresult(query) ;
         }else if(a==2){
             System.out.println("Ok Enter the name of movie for sentiment analysis");
             String movie = sc.next();
             performQuery(movie,twitter);
         }else{
             System.out.println("Try again you entered wrong key");
             
         }};
     } catch (Exception ex) {
         Logger.getLogger(Twitter2.class.getName()).log(Level.SEVERE, null, ex);
     }  
     PagableResponseList<User> pp  =  twitter.getFriendsList(oathAccessToken.getUserId(), cursor) ;
   int index = 0 ;
   for(int i = 0 ; i < pp.size() ; i++){
       User u = pp.get(index);
       
       index ++ ;
       if(u.getStatus()!= null ){
           List<Status> statuss = twitter.getUserTimeline(u.getId());
           if(index <2){
           for(Status s: statuss){
              String ss =  s.getText();
              if(ss.contains("status")){
              StringTokenizer str = new StringTokenizer(ss, " ");
              while(str.hasMoreTokens()){
                  //System.out.println("akash");
              }
              
            }
           }
          }
       }
       
    }
    }

    private String getSavedAccessTokenSecret() {
 // consider this is method to get your previously saved Access Token
 // Secret
 return "DrNgGiVYrs1I9GmyzVXXSqQQpH7BT7xp2tBLGPgYofcjW";
    }

    private String getSavedAccessToken() {
 // consider this is method to get your previously saved Access Token
 return "1212146767-UdG7Az6ddknW1t2KDWNVbqt9QzG3GXW9YktJ1A3";
    }
    public static void main(String[] args) throws TwitterException, IOException {
        // TODO code application logic here
        new Twitter2().start();
        
    }
     
      // This class is used for Training the data
      // It is advisable to train the data first otherwise it might be complicated.
    	public void performQuery(String inQuery,Twitter twitter,boolean flag) throws InterruptedException, IOException, TwitterException {
		String filename= "MyFile.txt";
                
                FileWriter fw = new FileWriter(filename,true); //the true will append the new data
                BufferedWriter br = new BufferedWriter(fw);
                Query query = new Query(inQuery); // Query q = new Query(String) ; QueryResult r ; r.getTweets(); ts.getStatus
                int LIMIT;
                if(flag == true){
                 LIMIT= 21;
		 query.setCount(21);
                }else{
                 LIMIT= 5;
		 query.setCount(5);
                
                }
		try {
			int count=0;
			QueryResult r;
			do {
				r = twitter.search(query);
				ArrayList ts= (ArrayList) r.getTweets();

				for (int i = 0; i < ts.size() && count < LIMIT; i++) {
					count++;
					Status t = (Status) ts.get(i);
					String text = t.getText();
                                        text = text.toLowerCase() ;
					System.out.println("Text: " + text);
                                        // Tokenize the text and
                                        StringTokenizer str = new StringTokenizer(text," ");
                                        String temp = "" ;
                                        while(str.hasMoreTokens()){
                                            text = str.nextToken();
                                            if(text.startsWith("http://") || text.startsWith("@")){
                                                text ="";
                                            }else{
                                                text = text.replaceAll("[^A-Za-z0-9 ]","");
                                            }
                                            temp = temp + " " + text ;
                                        }
                                        System.out.println(temp);
                                       
                                        temp = temp+ " ";
                                        br.write(temp);
                                        
					String name = t.getUser().getScreenName();
					System.out.println("User: " + name);
					// String sent = sentClassifier.classify(t.getText());
					//System.out.println("Sentiment: " + sent); 
				}   
			} while ((query = r.nextQuery()) != null && count < LIMIT);
		}
		
                
		catch (TwitterException te) {
			System.out.println("Couldn't connect: " + te);
		}
                br.close();
	}
        /// Apply the Query to fetch the data from Twitter. (Related to movie)
        public void performQuery(String inQuery,Twitter twitter) throws InterruptedException, IOException, TwitterException {
		Multinomial m = new Multinomial();
                ArrayList ratings = new ArrayList();
                m.calculateprobability();
                Query query = new Query(inQuery);
                int LIMIT = 200;
		query.setCount(200);
		try {
			int count=0;
			QueryResult r;
			do {
				r = twitter.search(query);
				ArrayList ts= (ArrayList) r.getTweets();

				for (int i = 0; i < ts.size() && count < LIMIT; i++) {
					count++;
					Status t = (Status) ts.get(i);
					String text = t.getText();
					System.out.println("Text: " + text);
					String name = t.getUser().getScreenName();
					System.out.println("User: " + name);
					System.out.println("Text: " + text);
                                        // Tokenize the text and
                                        StringTokenizer str = new StringTokenizer(text," ");
                                        String temp = "" ;
                                        while(str.hasMoreTokens()){
                                            text = str.nextToken();
                                            if(text.startsWith("http://") || text.startsWith("@")){
                                                text ="";
                                            }else{
                                                text = text.replaceAll("[^A-Za-z0-9 ]","");
                                            }
                                            temp = temp + " " + text ;
                                            
                                        }
                                        ratings.add(m.calculatefinalresult(temp));
                                    }   
			} while ((query = r.nextQuery()) != null && count < LIMIT);
                                         int  sum= 0 ;
                                         int counting = 0; 
                                         for(int index= 0 ;index < ratings.size() ; index++){
                                                sum = sum + (int)ratings.get(index);
                                         }
                                         float avg = ((float)sum / ratings.size()) ;
                                    
                                         // Adjust the value here a little bit
                                         if(Math.round(avg) <= 4){
                                             System.out.println("-----Final Rating----"+((avg)+1)+"-------------");
                                         }else{
                                             System.out.println("-----Final Rating----"+5+"-------------");
                                         }
		}
		
		catch (TwitterException te) {
			System.out.println("Couldn't connect: " + te);
		}
	}
}
