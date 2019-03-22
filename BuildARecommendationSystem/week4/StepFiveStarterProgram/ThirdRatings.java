
/**
 * クラス ThirdRatings の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        // Read in all the movie and ratings
        FirstRatings fRating = new FirstRatings();
        // store them in the private ArrayList variable
        myRaters = fRating.loadRaters(ratingsfile);
    }
    
    
    
    /*
     * Get the number of raters 
     * that were read in and stored in the ArrayList of type Rater
     */
    public int getRaterSize(){
        return myRaters.size(); 
    }
    
    /*
     * Judge especial movie(id) average rating,
     * if there is raters higher than minimalRaters.
     */
    private double getAverageByID (String id ,int minimalRaters){
        
        // Get is's average rating
        double sum = 0.0;
        int    cnt = 0;
        for(EfficientRater r : myRaters){
            double result = r.getRating(id);
            if(result != -1){
                sum += result;
                cnt++;
            }
        }
        
        // Check average is higher or not than minimalRaters
        if(cnt >= minimalRaters && cnt != 0){
            return sum/cnt;
        }
        
        // If there is no result, return 0.0
        return 0.0;
    }
    
    /*
     * This method should find the average rating 
     * for every movie that has been rated 
     * by at least minimalRaters raters
     */
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        
        ArrayList<Rating> rating = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        // Check all movies
        for(String id : movies){
            // Check if the number of rater is higher or not than minimalRaters
            // Call getAverageByID method
            double result = getAverageByID (id, minimalRaters);
            if(result != 0.0){
                // the number of rater is higher than minimalRaters
                // Make Rating object
                Rating aRating = new Rating(id, result);
                // Add to rating arraylist
                rating.add(aRating);
            }
        }
        
        return rating;
    }
    
    /*
     * Get rating list least minimalRaters ratings 
     * and satisfies the filter criteria
     */
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        
        ArrayList<Rating> rating = new ArrayList<Rating>();
        
        // do filter
        // Get movie id list satisfied filter
        ArrayList<String> list = MovieDatabase.filterBy(filterCriteria);
        
        for(String id : list){
            double result = getAverageByID (id, minimalRaters);
            if(result != 0.0){
                // the number of rater is higher than minimalRaters
                // Make Rating object
                Rating aRating = new Rating(id, result);
                // Add to rating arraylist
                rating.add(aRating);
            }
        }
        
        return rating;
        
    }
    
}

