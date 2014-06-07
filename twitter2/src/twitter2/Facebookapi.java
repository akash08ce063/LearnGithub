/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter2;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.types.Page;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import twitter4j.User;

/**
 *
 * @author Ricardo KAKA
 */
public class Facebookapi {
    
    Facebookapi(){
      String Accesstoken  = "CAACEdEose0cBAHo0Aa0oiJDy4utaNfPdtazr5jzVDTH21jwiDBgyZCWpVZAH2QWdKvj5Br0JqZCzdEfTSH66znkTOzjsm3bH8xAm5E6MZC4MZBvSoXe5TkQ1ju6zGfCHE6V5c4vPPGfZBlAwU2i83REvZCOI2q1NI6VjirZA6dfmKtu2NW1b9cmo2dPNx6gqgSsZD";  
      FacebookClient facebookClient = new DefaultFacebookClient(Accesstoken);
      FacebookClient publicOnlyFacebookClient = new DefaultFacebookClient();
      User user = facebookClient.fetchObject("me", User.class);
      
      // Testing//
      Page page = facebookClient.fetchObject("cocacola", Page.class);
      Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
    Map<String, String> queries = new HashMap<String, String>() {
      {
        put("movies", "SELECT  movies from user WHERE uid In (select uid2 from friend where uid1=me())");
    
       }
    };

    /// Fetch the data of movie
    MultiqueryResults multiqueryResults =
    facebookClient.executeFqlMultiquery(queries, MultiqueryResults.class);
    Iterator ie =  multiqueryResults.movies.iterator();
    while(ie.hasNext()){
       String find = (String) ie.next() ;
       String finding = find.toLowerCase();
       if(finding.indexOf("andaz apna apna") != -1){
           System.out.println("Found out the movie");
       }
   }
  } 
}
class MultiqueryResults {


  @Facebook
  List<Fqlmovies> movies ;  
}