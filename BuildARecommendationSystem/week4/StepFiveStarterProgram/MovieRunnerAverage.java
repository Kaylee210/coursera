
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
     * Get the index of minimum ratin value from specific point. 
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
        
        // Get movie id for specific title
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
    
    /*
     * Method for test
     */
    public void test(){
        
        // Create SecondRatings object
        String mfilename = "data/ratedmoviesfull.csv";
        String rfilename = "data/ratings.csv";
        SecondRatings secondRating = new SecondRatings(mfilename,  rfilename);
        
        // Get movie id for specific title
        String title1 = "The Maze Runner";
        String id1 = secondRating.getID(title1);
        // Get average
        ArrayList<Rating> rating1 = secondRating.getAverageRatings(1);
        for(Rating r : rating1){
            String tmpID = r.getItem ();
            if(id1.equals(tmpID)){
                System.out.println(title1 + "'s rating average is " + r.getValue());
            }
        }
        
        // Get movie id for specific title
        String title2 = "Moneyball";
        String id2 = secondRating.getID(title2);
        // Get average
        ArrayList<Rating> rating2 = secondRating.getAverageRatings(1);
        for(Rating r : rating2){
            String tmpID = r.getItem ();
            if(id2.equals(tmpID)){
                System.out.println(title2 + "'s rating average is " + r.getValue());
            }
        }
        
        // Get movie id for specific title
        String title3 = "Vacation";
        String id3 = secondRating.getID(title3);
        // Get average
        ArrayList<Rating> rating3 = secondRating.getAverageRatings(1);
        for(Rating r : rating3){
            String tmpID = r.getItem ();
            if(id3.equals(tmpID)){
                System.out.println(title3 + "'s rating average is " + r.getValue());
            }
        }
        
        // Get rating count
        ArrayList<Rating> rating4 = secondRating.getAverageRatings(50);
        System.out.println("The movies having 50 or more is " + rating4.size());
        
        // Get rating data
        ArrayList<Rating> rating5 = secondRating.getAverageRatings(20);
        sortByValue(rating5);
        String id5 = rating5.get(0).getItem();
        String title5 = secondRating.getTitle(id5);
        System.out.println("Lowest movie is " + title5);
        
        // Get rating data
        ArrayList<Rating> rating6 = secondRating.getAverageRatings(12);
        sortByValue(rating6);
        String id6 = rating6.get(0).getItem();
        String title6 = secondRating.getTitle(id6);
        System.out.println("Lowest movie is " + title6);
        
        
    }
    
}
