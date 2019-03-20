
/**
 * Class FirstRatings 
 * 
 * @author (Kaylee)
 * @version (7th March 2019)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    /*
     * Load movie data file
     */
    public ArrayList<Movie> loadMovies(String filename){
        
        // Make ArrayList<Movie>
        ArrayList<Movie> fData = new ArrayList<Movie>();
        
        // Read file
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        // Get each data
        for(CSVRecord record : parser){
            
            String tmpId = record.get("id");
            String tmpTitle = record.get("title");
            String tmpYear = record.get("year");
            String tmpCountry = record.get("country");  
            String tmpGenre = record.get("genre");
            String tmpDirector = record.get("director");
            String tmpMinutes = record.get("minutes");
            String tmpPoster = record.get("poster");           
            
            Movie mRecord = new Movie(tmpId, tmpTitle, tmpYear, tmpGenre, tmpDirector,
                                      tmpCountry, tmpPoster, Integer.parseInt(tmpMinutes));
            // Add a Movie data
            fData.add(mRecord);
        }
        
        return fData;
        
    }
    
    /*
     * Test loadMovies method
     */
    public void testLoadMovies(){
        
        // Read file name
        //String filename = "data/ratedmovies_short.csv";
        String filename = "data/ratedmoviesfull.csv";
        
        // Call loadMovies method
        ArrayList<Movie> mData = loadMovies(filename);
        
        System.out.println("Total movie number is " + mData.size());
        /*
        System.out.println("[All data]------------");
        for(Movie m : mData){
            String result = m.toString();
            System.out.println(result);
        }
        System.out.println("---------------------");
        */
       
        // Determine how many movies include the Comedy genre
        // and how many movies are greater than 150 minutes in length
        int genreCnt = 0;
        int minCnt = 0;
        for(Movie m : mData){
            String genre = m.getGenres ();
            int min = m.getMinutes();
            if(genre.indexOf("Comedy") != -1){
                genreCnt++;
            }
            if(min > 150){
                minCnt++;
            }
        }
        System.out.println("The number of including the Comedy genre is " + genreCnt);
        System.out.println("The number of greater than 150 minutes is " + minCnt);
        
        // Determine the maximum number of movies by any director, 
        // and who the directors are that directed that many movies
        
        // Call getNumberOfMovieByEachDirector method
        HashMap<String, Integer> map = getNumberOfMovieByEachDirector(mData);
        int max = 0;
        ArrayList<String> maxDire = new ArrayList<String>();
        for(String s : map.keySet()){
            int value = map.get(s);
            if(max == value){
                maxDire.add(s);
            }
            if(max < value){
                maxDire.clear();
                max = value;
                maxDire.add(s);
            }
        }
        
        System.out.println("the maximum number of movies by any director is " + max);
        System.out.println("directors are " + maxDire);
    }
    
    
    
    /*
     * Relationship between director and movie number
     */
    public HashMap<String, Integer> getNumberOfMovieByEachDirector(ArrayList<Movie> mData){
        
        // Make return value
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        // Check all mData
        for(Movie m : mData){
            String tmpDirector = m.getDirector();
            if(map.containsKey(tmpDirector)){
                // If there is director in map
                // increase value
                int value = map.get(tmpDirector) + 1;
                map.put(tmpDirector, value);
            }else{
                map.put(tmpDirector, 1);
            }
        }
        
        return map;
        
    }
    
    /*
     * Load rater file
     */
    public ArrayList<Rater> loadRaters(String filename){
        
        // Make ArrayList<Movie>
        ArrayList<Rater> fData = new ArrayList<Rater>();
        
        // Read file
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        // Get each data
        for(CSVRecord record : parser){
            String tmpRaterID = record.get("rater_id");
            String tmpRating = record.get("rating");
            String tmpItem = record.get("movie_id");
            
            int flg = -1;
            
            for(int i = 0; i < fData.size(); i++){
                String prevRaterID = fData.get(i).getID();
                if(prevRaterID.equals(tmpRaterID)){
                    // Check if there is already tmpRaterID in fData
                    fData.get(i).addRating(tmpItem, Double.parseDouble(tmpRating));
                    flg = 1;
                    break;
                }
            }
            
            if(flg == -1){
                // There isn't already
                    Rater rater = new Rater(tmpRaterID);
                    rater.addRating(tmpItem, Double.parseDouble(tmpRating));
                    fData.add(rater);
            }
            
        }
        
        return fData;
        
    }
    
    /*
     * Test loadRaters method
     */
    public void testLoadRaters(){
        // Read file name
        //String filename = "data/ratings_short.csv";
        String filename = "data/ratings.csv";
        
        // Call loadMovies method
        ArrayList<Rater> rData = loadRaters(filename);
        
        //  Print the total number of raters
        System.out.println("Total number of rater id is " + rData.size());
        /*
        // Print the rater’s ID and the number of ratings
        System.out.println("[All data]------------");
        for(Rater r : rData){
            System.out.print("    [id] " + r.getID() + " [movieID, rating] " );
            ArrayList<String> list = r.getItemsRated();
            for(String mID : list){
                System.out.print("[" + mID);
                double rating = r.getRating(mID);
                System.out.print("," + rating + "]");
                System.out.print(" ");
            }
            System.out.println("  ");
        }
        System.out.println("---------------------");
        */
       
        // find the number of ratings for a particular rater
        //String aID = "2";
        String aID = "193";
        int size = 0;
        for(Rater r : rData){
            String tmpID = r.getID();
            if(aID.equals(tmpID)){
                size = r.numRatings();
                break;
            }
        }
        System.out.println("the number of ratings is " + size);
        
        // find the maximum number of ratings by any rater
        int max = 0;
        ArrayList<String> rMaxList = new ArrayList<String>();
        for(Rater r : rData){
            int tmpSize = r.numRatings();
            if(tmpSize > max){
                rMaxList.clear();
                max = tmpSize;
                rMaxList.add(r.getID());
            }
            else if(tmpSize == max){
                rMaxList.add(r.getID());
            }
        }
        System.out.println("the maximum number of ratings is " + max);
        System.out.println("Then rater is " + rMaxList.size() + " people");
        
        // find the number of ratings a particular movie
        String pMovieID = "1798709";
        ArrayList<Double> pMovieIDRating = new ArrayList<Double>();
        for(Rater r : rData){
            double result = r.getRating(pMovieID);
            if(result != -1){
                pMovieIDRating.add(result);
            }
        }
        System.out.println("the number of ratings a particular movie is " + pMovieIDRating.size());
        
        // Determine how many different movies have been rated by all these raters.
        ArrayList<String> movieList =  new ArrayList<String>();
        for(Rater r : rData){
            ArrayList<String> tmpMovieList = r.getItemsRated();
            for(String m : tmpMovieList){
               if(!movieList.contains(m)){
                   movieList.add(m);
                   }
                }
            }
         System.out.println("The number of rated movie is " + movieList.size());   
        }
        
}
