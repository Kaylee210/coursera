
/**
 * Class MovieRunnerWithFilters
 * 
 * @author (Kaylee)
 * @version (11th March 2019)
 */

import java.util.*;

public class MovieRunnerWithFilters {

    public void printAverageRatings(){
        // Create SecondRatings object
        //String rfilename = "ratings_short.csv";
        String rfilename = "ratings.csv"; // for test
        ThirdRatings thirdRating = new ThirdRatings(rfilename);
        
        System.out.println("read data for " + thirdRating.getRaterSize() + " raters");
        
        // Call the MovieDatabase initialize method 
        // with the moviefile to set up the movie database
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); // for test
        System.out.println("read data for " + thirdRating.getRaterSize() + " movies");
        
        //int minimalRaters = 1;    
        int minimalRaters = 35; // for test
        ArrayList<Rating> rating = thirdRating.getAverageRatings(minimalRaters);              
        // Sort by rating value
        sortByValue(rating);
        
        System.out.println("Matched movies: " + rating.size());
        
        // Print out rating and movie title
        for(Rating r : rating){
            String aID = r.getItem();
            String title = MovieDatabase.getTitle(aID);
            //System.out.println(r.getValue() + " " + title);
        }
    }
    

    public void printAverageRatingsByYear(){
        // Create SecondRatings object
        // String rfilename = "ratings_short.csv";
        String rfilename = "ratings.csv"; // for test
        ThirdRatings thirdRating = new ThirdRatings(rfilename);
        
        System.out.println("read data for " + thirdRating.getRaterSize() + " raters");
        
        // Call the MovieDatabase initialize method 
        // with the moviefile to set up the movie database
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); // for test
        System.out.println("read data for " + thirdRating.getRaterSize() + " movies");
        
        // Make filter
        int year = 2000;
        YearAfterFilter yearFilter = new YearAfterFilter(year);
        
        //int minimalRaters = 1;   
        int minimalRaters = 20; // for test 
        ArrayList<Rating> rating = thirdRating.getAverageRatingsByFilter(minimalRaters, yearFilter);
        
        System.out.println("found " + rating.size() + " movies");
        
        // Sort by rating value
        sortByValue(rating);
        // Print out rating and movie title
        for(Rating r : rating){
            String aID = r.getItem();
            String title = MovieDatabase.getTitle(aID);
            //System.out.println(r.getValue() + " " + title);
        }
        
    }
    
    public void printAverageRatingsByGenre(){
        // Create SecondRatings object
        //String rfilename = "ratings_short.csv";
        String rfilename = "ratings.csv"; // for test
        ThirdRatings thirdRating = new ThirdRatings(rfilename);
        
        System.out.println("read data for " + thirdRating.getRaterSize() + " raters");
        
        // Call the MovieDatabase initialize method 
        // with the moviefile to set up the movie database
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); // for test
        System.out.println("read data for " + thirdRating.getRaterSize() + " movies");
        
        // Make filter
        //String genre = "Crime";
        String genre = "Comedy"; // for test
        GenreFilter genreFilter = new GenreFilter(genre);
        
        //int minimalRaters = 1;      
        int minimalRaters = 20; 
        ArrayList<Rating> rating = thirdRating.getAverageRatingsByFilter(minimalRaters, genreFilter);
        
        System.out.println("found " + rating.size() + " movies");
        
        // Sort by rating value
        sortByValue(rating);
        // Print out rating and movie title
        for(Rating r : rating){
            String aID = r.getItem();
            String title = MovieDatabase.getTitle(aID);
            //System.out.println(r.getValue() + " " + title);
            //System.out.println("    " + MovieDatabase.getGenres(aID));
        }
        
        
    }
    
    
    public void printAverageRatingsByMinutes(){
        // Create SecondRatings object
        // String rfilename = "ratings_short.csv";
        String rfilename = "ratings.csv"; // for test
        ThirdRatings thirdRating = new ThirdRatings(rfilename);
        
        System.out.println("read data for " + thirdRating.getRaterSize() + " raters");
        
        // Call the MovieDatabase initialize method 
        // with the moviefile to set up the movie database
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); // for test
        System.out.println("read data for " + thirdRating.getRaterSize() + " movies");
        
        // Make filter
        // int min = 110;
        // int max = 170;
        int min = 105;
        int max = 135;
        MinutesFilter minutesFilter = new MinutesFilter(min, max);
        
        // int minimalRaters = 1;     
        int minimalRaters = 5; 
        ArrayList<Rating> rating = thirdRating.getAverageRatingsByFilter(minimalRaters, minutesFilter);
        
        System.out.println("found " + rating.size() + " movies");
        
        // Sort by rating value
        sortByValue(rating);
        // Print out rating and movie title
        for(Rating r : rating){
            String aID = r.getItem();
            String title = MovieDatabase.getTitle(aID);
            int minute = MovieDatabase.getMinutes(aID);
            // System.out.println(r.getValue() + " " + "Time: " + minute +" "+ title);
        }
        
        
    }
    
    public void printAverageRatingsByDirectors(){
        // Create SecondRatings object
        // String rfilename = "ratings_short.csv";
        String rfilename = "ratings.csv"; // for test
        ThirdRatings thirdRating = new ThirdRatings(rfilename);
        
        System.out.println("read data for " + thirdRating.getRaterSize() + " raters");
        
        // Call the MovieDatabase initialize method 
        // with the moviefile to set up the movie database
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); // for test
        System.out.println("read data for " + thirdRating.getRaterSize() + " movies");
        
        // Make filter
        String director = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        DirectorsFilter directorFilter = new DirectorsFilter(director);
        
        // int minimalRaters = 1;         
        int minimalRaters = 4; 
        ArrayList<Rating> rating = thirdRating.getAverageRatingsByFilter(minimalRaters, directorFilter);
        
        System.out.println("found " + rating.size() + " movies");
        
        // Sort by rating value
        sortByValue(rating);
        // Print out rating and movie title
        for(Rating r : rating){
            String aID = r.getItem();
            String title = MovieDatabase.getTitle(aID);
            // System.out.println(r.getValue() +" "+ title);
            // System.out.println("    " + MovieDatabase.getDirector(aID));
        }
        
        
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        
        // Create SecondRatings object
        // String rfilename = "ratings_short.csv";
        String rfilename = "ratings.csv"; // for test
        ThirdRatings thirdRating = new ThirdRatings(rfilename);
        
        System.out.println("read data for " + thirdRating.getRaterSize() + " raters");
        
        // Call the MovieDatabase initialize method 
        // with the moviefile to set up the movie database
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); // for test
        System.out.println("read data for " + thirdRating.getRaterSize() + " movies");
        
        // Make filter
        AllFilters allFilter = new AllFilters();
        // int year = 1980;
        int year = 1990;
        YearAfterFilter yearFilter = new YearAfterFilter(year);
        // String genre = "Romance";
        String genre = "Drama";
        GenreFilter genreFilter = new GenreFilter(genre);
        allFilter.addFilter(yearFilter);
        allFilter.addFilter(genreFilter);
        
        // int minimalRaters = 1;         
        int minimalRaters = 8;   
        ArrayList<Rating> rating = thirdRating.getAverageRatingsByFilter(minimalRaters, allFilter);
        
        System.out.println("found " + rating.size() + " movies");
        
        // Sort by rating value
        sortByValue(rating);
        // Print out rating and movie title
        for(Rating r : rating){
            String aID = r.getItem();
            String title = MovieDatabase.getTitle(aID);
            int aYear = MovieDatabase.getYear(aID);
            // System.out.println(r.getValue() +" " + aYear + " " +title);
            // System.out.println("    " + MovieDatabase.getGenres(aID));
        }
        
    }
    
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        
        // Create SecondRatings object
        // String rfilename = "ratings_short.csv";
        String rfilename = "ratings.csv";
        ThirdRatings thirdRating = new ThirdRatings(rfilename);
        
        System.out.println("read data for " + thirdRating.getRaterSize() + " raters");
        
        // Call the MovieDatabase initialize method 
        // with the moviefile to set up the movie database
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + thirdRating.getRaterSize() + " movies");
        
        // Make filter
        AllFilters allFilter = new AllFilters();
        // int min = 110;
        // int max = 170;
        int min = 90;
        int max = 180;
        MinutesFilter minutesFilter = new MinutesFilter(min, max);
        String director = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        DirectorsFilter directorFilter = new DirectorsFilter(director);
        allFilter.addFilter(minutesFilter);
        allFilter.addFilter(directorFilter);
        
        // int minimalRaters = 1;      
        int minimalRaters = 3;  
        ArrayList<Rating> rating = thirdRating.getAverageRatingsByFilter(minimalRaters, allFilter);
        
        System.out.println("found " + rating.size() + " movies");
        
        // Sort by rating value
        sortByValue(rating);
        // Print out rating and movie title
        for(Rating r : rating){
            String aID = r.getItem();
            String title = MovieDatabase.getTitle(aID);
            int    minumte = MovieDatabase.getMinutes(aID);
            String name = MovieDatabase.getDirector(aID);
   
            // System.out.println(r.getValue() +" Time: " +  minumte + " " +title);
            // System.out.println("    " + name);
        }
        
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
}
