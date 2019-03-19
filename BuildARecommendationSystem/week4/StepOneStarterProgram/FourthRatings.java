
/**
 * Class FourthRatings
 * 
 * @author (Kaylee)
 * @version (13th March 2019)
 */
import java.util.*;

public class FourthRatings {

    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public FourthRatings(String ratingsfile) {
        // Read in all the movie and ratings
        FirstRatings fRating = new FirstRatings();
        // Initialize rater database
        RaterDatabase.initialize(ratingsfile);
    }
    
    
    /*
     * Judge especial movie(id) average rating,
     * if there is raters higher than minimalRaters.
     */
    private double getAverageByID (String id ,int minimalRaters){
        
        // Get is's average rating
        double sum = 0.0;
        int    cnt = 0;
        //for(EfficientRater r : myRaters){
        ArrayList<Rater> list = RaterDatabase.getRaters();
        for(Rater r : list){
            EfficientRater eR = (EfficientRater)r;
            double result = eR.getRating(id);
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
    
    /*
     * Calculate the dot product of the ratings of movies that they both rated
     */
    private double dotProduct( Rater me, Rater r){
        
        // Transalate scale from 0-10 to -5-5
        // Get rated movie list
        ArrayList<String> meMovieList = me.getItemsRated();
        ArrayList<String> rMovieList = r.getItemsRated();
        
        double dotProduct = 0.0;
        
        // Check all movie 
        for(String meMovieID : meMovieList){
            if(rMovieList.contains(meMovieID)){
                // if there is both rated movie
                // calcurated the dot product
                
                double meRating = me.getRating(meMovieID);
                double rRating = r.getRating(meMovieID);
                
                dotProduct += (meRating - 5) * (rRating - 5);
            }
        }
        
        return dotProduct;
    }
    
    /*
     * this method computes a similarity rating for each rater 
     * in the RaterDatabase (except the rater with the ID given by the parameter) 
     * to see how similar they are to the Rater whose ID is the parameter 
     * to getSimilarities
     */
    private  ArrayList<Rating> getSimilarities(String id){
        // Mek result
        // Rating-item:  rater id
        // Rating-value: dot-product
        ArrayList<Rating> result = new ArrayList<Rating>();
        double value = 0.0;
        
        // Rater me
        Rater meRater = RaterDatabase.getRater(id);
        
        // Check all rater
        ArrayList<Rater> raterList = RaterDatabase.getRaters();
        for(Rater r : raterList){
            String rID = r.getID();
            if(!rID.equals(id)){
                // Get  dot-product
                value = dotProduct( meRater, r);
                if(value > 0.0){
                    Rating tmpRating = new Rating (rID, value);
                    result.add(tmpRating);
                }
            }
        }
        
        // Sort
        Collections.sort(result, Collections.reverseOrder());
        
        return result;
    }
    
    /*
     * This method should return an ArrayList of type Rating, of movies 
     * and their weighted average ratings using only the top numSimilarRaters 
     * with positive ratings and including only those movies that have at least 
     * minimalRaters ratings from those most similar raters
     */
    public ArrayList<Rating> getSimilarRatings( String id, int numSimilarRaters, int minimalRaters){
        
        // Make return value
        // Rating-item: movie id
        // Rating-value: dot-production
        ArrayList<Rating> result = new ArrayList<Rating>();
        
        // Get similar raters
        // Rating-item: rater id
        // Rater-value: dot product
        ArrayList<Rating> similarRater = getSimilarities(id);
        
        // Get relation between movie and other value
        // as HashMap<String, ArrayList<Rating>> mapMovie
        // key: movie id
        // Rating-item: rater id
        // Rating-value: rating*dot-production
        HashMap<String, ArrayList<Rating>> mapMovie = new HashMap<String, ArrayList<Rating>>();
        for(int i = 0; i < numSimilarRaters; i++){
            // Check by numSimilarRaters data

            Rating r = similarRater.get(i);
            String tmpRaterID = r.getItem();
            double tmpDotPro = r.getValue();
            
            Rater tmpRater = RaterDatabase.getRater(tmpRaterID);
            ArrayList<String> tmpMovieList = tmpRater.getItemsRated();
            for(String m : tmpMovieList){
                
                double tmpValue = tmpRater.getRating(m);
                Rating tmpR = new Rating (tmpRaterID, tmpValue * tmpDotPro);
                
                if(mapMovie.containsKey(m)){
                    ArrayList<Rating> tmpRatingList = mapMovie.get(m);
                    tmpRatingList.add(tmpR);
                }else{
                    ArrayList<Rating> tmpRatingList = new ArrayList<Rating>();
                    tmpRatingList.add(tmpR);
                    mapMovie.put(m, tmpRatingList);
                }
            }
        }
        
        /*// for log
        System.out.println("[log]-------------");
        for(String m : mapMovie.keySet()){
            String logTitle = MovieDatabase.getTitle(m);
            int flg = -1;
            if(m.equals("2582846")){
                System.out.println("movie id: " + m + "title: " + logTitle);
                flg = 1;
            }
            ArrayList<Rating> logRating = mapMovie.get(m);
            for(Rating logR : logRating){
                String logItem = logR.getItem();
                double logValue = logR.getValue();
                if(flg == 1){
                    System.out.println("    " + "item: " + logItem + ", " + logValue);
                }
            }
            
        }*/
        
        // Get weighted average rating
        for(String m : mapMovie.keySet()){
            ArrayList<Rating> tmpRatingList = mapMovie.get(m);
            // Check number of rater
            int numRater = tmpRatingList.size();
            if(numRater >= minimalRaters){
                double sumWA = 0.0;   // sum of weighted average
                for(Rating r : tmpRatingList){
                    sumWA += r.getValue();
                }
                Rating tmpR = new Rating (m, sumWA/numRater);
                result.add(tmpR);
            }
        }
        
        /* // for log
        System.out.println("[log]-------------");
        for(Rating logR : result){
             String logItem = logR.getItem();
             double logValue = logR.getValue();
             System.out.println("    " + "movie id: " + logItem + ", " + logValue);
            
        }*/
        
        
        // Sort result
        Collections.sort(result, Collections.reverseOrder());
        
        /*// for log
        System.out.println("[log]-------------");
        for(Rating logR : result){
             String logItem = logR.getItem();
             double logValue = logR.getValue();
             System.out.println("    " + "movie id: " + logItem + ", " + logValue);
            
        }*/
        
        return result;
    }
    
    /*
     * Get result meeting with getSimilarRatings method and filterCriteria filter
     */
    public ArrayList<Rating> getgetSimilarRatingsSimilarRatingsByFilter(String id, int numSimilarRaters,
                                                      int minimalRaters, Filter filterCriteria){
        
        // Make return value
        // Rating-item: movie id
        // Rating-value: dot-production
        ArrayList<Rating> result = new ArrayList<Rating>();                                                  
                                                          
        // do filter
        // Get movie id list satisfied filter
        ArrayList<String> list = MovieDatabase.filterBy(filterCriteria);
        
        // Call getSimilarRatings method
        ArrayList<Rating> similarRating = getSimilarRatings(id, numSimilarRaters,minimalRaters);
        
        // CHeck all rating as result of getSimilarRatings method
        for(Rating r : similarRating){
            String movieID = r.getItem();
            // Check if movieID matches filter
            if(list.contains(movieID)){
                result.add(r);
            }
        }
        
        return result;
        
    }
}
