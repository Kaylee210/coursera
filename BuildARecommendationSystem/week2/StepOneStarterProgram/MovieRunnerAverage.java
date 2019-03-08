
/**
 * Class MovieRunnerAverage 
 * 
 * 
 * @author (Kaylee)
 * @version (9th May 2019)
 */

import java.util.*;

public class MovieRunnerAverage {

    public void printAverageRatings(){
        // Create SecondRatings object
        String mfilename = "data/ratedmovies_short.csv";
        String rfilename = "data/ratings_short.csv";
        SecondRatings secondRating = new SecondRatings(mfilename,  rfilename);
        
        System.out.println("movie file size is " + secondRating.getMovieSize() +
                           ", rater size is " + secondRating.getRaterSize());
                
        int minimalRaters = 3;          
        ArrayList<Rating> rating = secondRating.getAverageRatings(minimalRaters);              
        // Sort by rating value
        sortByValue(rating);
        // Print out rating and movie title
        for(Rating r : rating){
            String aID = r.getItem();
            String title = secondRating.getTitle(aID);
            System.out.println(r.getValue() + " " + title);
        }
    }
    
    /*
     * Get the index of minimum ratin value from especially point. 
     */
    private int getSmallestAverage(ArrayList<Rating> rating, int from){
        
        int minIdx = from;
        for(int i = from + 1; i< rating.size(); i++){
            if (rating.get(i).getValue() < rating.get(minIdx).getValue()) {
                minIdx = i;
            }
        }
        
        return minIdx;
    }
    
    /*
     * Sort by rating value 
     */
    private void sortByValue(ArrayList<Rating> rating){
        
        for(int i = 0; i < rating.size(); i++){
            int minIdx = getSmallestAverage(rating, i);
            Rating ri = rating.get(i);
            Rating rmin = rating.get(minIdx);
            rating.set(i,rmin);
            rating.set(minIdx,ri);
        }
    }
    
    /*
     * print out the average ratings for a specific movie title
     */
    public void getAverageRatingOneMovie(){
        
        // Create SecondRatings object
        String mfilename = "data/ratedmovies_short.csv";
        String rfilename = "data/ratings_short.csv";
        SecondRatings secondRating = new SecondRatings(mfilename,  rfilename);
        
        // Get movie id for especially title
        String title = "The Godfather";
        String id = secondRating.getID(title);
        // Get average
        ArrayList<Rating> rating = secondRating.getAverageRatings(1);
        for(Rating r : rating){
            String tmpID = r.getItem ();
            if(id.equals(tmpID)){
                System.out.println(title + "'s rating average is " + r.getValue());
            }
        }
    }
    
    
}
