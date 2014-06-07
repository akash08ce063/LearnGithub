/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter2;

import com.restfb.Facebook;
import java.util.List;

// Movie list.
public class Fqlmovies {
      @Facebook
  String movies;
  
  @Override
  public String toString() {
    return String.format("%s", movies);
  }
}
