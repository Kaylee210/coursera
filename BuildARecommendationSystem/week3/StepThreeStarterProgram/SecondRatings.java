
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        // Read in all the movie and ratings
        FirstRatings fRating = new FirstRatings();
        // store them in the two private ArrayList variables
        myMovies = fRating.loadMovies(moviefile);
        myRaters = fRating.loadRaters(ratingsfile);
    }
    
    /*
     * Get the number of movies 
     * that were read in and stored in the ArrayList of type Movie
     */
    public int getMovieSize(){
        return myMovies.size(); 
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
        
        // Check all movies
        for(Movie m: myMovies){
            // Get movie id
            String mID = m.getID();
            // Check if the number of rater is higher or not than minimalRaters
            // Call getAverageByID method
            double result = getAverageByID (mID, minimalRaters);
            if(result != 0.0){
                // the number of rater is higher than minimalRaters
                // Make Rating object
                Rating aRating = new Rating(mID, result);
                // Add to rating arraylist
                rating.add(aRating);
            }
        }
        
        return rating;
    }
    
    /*
     * Get specific id's movie title
     */
    public String getTitle(String id){
        
        // Check all movies
        for(Movie m: myMovies){
            // get id
            String tmpID = m.getID();
            
            // Check if tmpID matches id
            if(tmpID.equals(id)){
                // return title
                return m.getTitle();
            }
        }
        
        // If there is no movie id matched with id
        return "the ID was not found";
    }
    
    /*
     * Get the movie ID matched with title
     */
    public String getID(String title){
    
        // Check all movies
        for(Movie m: myMovies){
            // get title
            String tmpTitle = m.getTitle();
            
            // Check if tmpTitle matches title
            if(tmpTitle.equals(title)){
                // return title
                return m.getID();
            }
        }
        
        // If there is no movie id matched with id
        return "NO SUCH TITLE.";
        
    }
}
