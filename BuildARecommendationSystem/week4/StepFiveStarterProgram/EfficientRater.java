
/**
 * Class EfficientRater
 * 
 * @author (Kaylee)
 * @version (10th March 2019)
 */

import java.util.*;

public class EfficientRater implements Rater {

    private String myID; // rater id
    // The key in the HashMap is a movie ID, 
    // and its value is a rating associated with this movie
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id; // rater id
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if(myRatings.containsKey(item)){
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }
    
    public double getRating(String item) {
        for(String s : myRatings.keySet()){
            if (s.equals(item)){
                return myRatings.get(s).getValue();
            }
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s : myRatings.keySet()){
            list.add(s);
        }
        
        return list;
    }
    
    
}
