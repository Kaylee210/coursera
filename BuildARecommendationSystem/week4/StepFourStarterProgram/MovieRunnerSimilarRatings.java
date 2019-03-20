
/**
 * Class MovieRunnerSimilarRatings
 * 
 * @author (Kaylee)
 * @version (13th March 2019)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {

    public void printAverageRatings(){
        // Create SecondRatings object
        //String rfilename = "ratings_short.csv";
        String rfilename = "ratings.csv"; // for test
        //ThirdRatings thirdRating = new ThirdRatings(rfilename);
        FourthRatings fourthRating = new FourthRatings(rfilename);
        // System.out.println("read data for " + fourthRating.getRaterSize() + " raters");
        
        // Call the MovieDatabase initialize method 
        // with the moviefile to set up the movie database
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); // for test
        // System.out.println("read data for " + fourthRating.getRaterSize() + " movies");
        
        //int minimalRaters = 1;    
        int minimalRaters = 35; // for test
        ArrayList<Rating> rating = fourthRating.getAverageRatings(minimalRaters);              
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
    
    
    public void printAverageRatingsByYearAfterAndGenre(){
        
        // Create SecondRatings object
        // String rfilename = "ratings_short.csv";
        String rfilename = "ratings.csv"; // for test
        //ThirdRatings thirdRating = new ThirdRatings(rfilename);
        FourthRatings fourthRating = new FourthRatings(rfilename);
        
        // System.out.println("read data for " + thirdRating.getRaterSize() + " raters");
        
        // Call the MovieDatabase initialize method 
        // with the moviefile to set up the movie database
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); // for test
        // System.out.println("read data for " + thirdRating.getRaterSize() + " movies");
        
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
        ArrayList<Rating> rating = fourthRating.getAverageRatingsByFilter(minimalRaters, allFilter);
        
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
    
    /*
     * Check getSimilarRatings method
     */
    public void printSimilarRatings(){
        // Create SecondRatings object
        // String rfilename = "ratings_short.csv";
        String rfilename = "ratings.csv"; // for test
        //RaterDatabase.initialize(rfilename); // for test
        FourthRatings fR = new FourthRatings(rfilename);
        
        // Call the MovieDatabase initialize method 
        // with the moviefile to set up the movie database
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); // for test
        
        // Call getSimilarRatings
        /*
        String id = "65";
        int numSimilarRaters = 20;
        int minimalRaters = 5;*/
        // for test
        String id = "337";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        // for test 2
        /*String id = "71";
        int numSimilarRaters = 20;
        int minimalRaters = 5;*/
        ArrayList<Rating> result = fR.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        
        
        // for log
        System.out.println("[the top rated average]-----");
        for(Rating r : result){
            String item = r.getItem();
            double value = r.getValue();
            String title = MovieDatabase.getTitle(item);
            
            System.out.println("movie id: " + item + ", title: " + title + ", value: " + value);
            
        }
    
    }
    
    /*
     * Check getgetSimilarRatingsSimilarRatingsByFilter method
     */
    public void printSimilarRatingsByGenre(){
        // Create SecondRatings object
        // String rfilename = "ratings_short.csv";
        String rfilename = "ratings.csv"; // for test
        //RaterDatabase.initialize(rfilename); // for test
        FourthRatings fR = new FourthRatings(rfilename);
        
        // Call the MovieDatabase initialize method 
        // with the moviefile to set up the movie database
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); // for test
        
        // Call getSimilarRatings
        /*String id = "65";
        int numSimilarRaters = 20;
        int minimalRaters = 5;*/
        // for test
        String id = "964";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        // String genre = "Action";
        String genre = "Mystery"; // for test
        GenreFilter genreFilter = new GenreFilter(genre);
        ArrayList<Rating> result = fR.getgetSimilarRatingsSimilarRatingsByFilter(id, numSimilarRaters,
                                                                               minimalRaters, genreFilter);
        
        /*
        Rating r = result.get(0);
        String item = r.getItem();
        double value = r.getValue();
        
        String rmovieID = result.get(0).getItem();
        String title = MovieDatabase.getTitle(rmovieID);
        
        System.out.println("the top rated average is " + title);
        
        */
        
        // for log
        System.out.println("[the top rated average]-----");
        for(Rating r : result){
            String item = r.getItem();
            double value = r.getValue();
        
            //String rmovieID = result.get(0).getItem();
            String aTitle = MovieDatabase.getTitle(item);
            String aGenre = MovieDatabase.getGenres(item);
            System.out.println("movie id: " + item + ", title: " + aTitle + ", value: " + value);
            System.out.println("    genre: " + aGenre);
        }
    
    }
    
    
    /*
     * Check getgetSimilarRatingsSimilarRatingsByFilter method
     */
    public void printSimilarRatingsByDirector(){
        // Create SecondRatings object
        // String rfilename = "ratings_short.csv";
        String rfilename = "ratings.csv"; // for test
        //RaterDatabase.initialize(rfilename); // for test
        FourthRatings fR = new FourthRatings(rfilename);
        
        // Call the MovieDatabase initialize method 
        // with the moviefile to set up the movie database
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); // for test
        
        // Call getSimilarRatings
        /*String id = "1034";
        int numSimilarRaters = 10;
        int minimalRaters = 3; */
        // for test
        String id = "120";
        int numSimilarRaters = 10;
        int minimalRaters = 2;
        //String director = "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone"; 
        String director = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"; // for test
        DirectorsFilter directorFilter = new DirectorsFilter(director);
        ArrayList<Rating> result = fR.getgetSimilarRatingsSimilarRatingsByFilter(id, numSimilarRaters,
                                                                               minimalRaters, directorFilter);
        
        /*
        Rating r = result.get(0);
        String item = r.getItem();
        double value = r.getValue();
        
        String rmovieID = result.get(0).getItem();
        String title = MovieDatabase.getTitle(rmovieID);
        
        System.out.println("the top rated average is " + title);
        
        */
        
        // for log
        System.out.println("[the top rated average]-----");
        for(Rating r : result){
            String item = r.getItem();
            double value = r.getValue();
        
            //String rmovieID = result.get(0).getItem();
            String aTitle = MovieDatabase.getTitle(item);
            String aDirector = MovieDatabase.getDirector(item);
            System.out.println("movie id: " + item + ", title: " + aTitle + ", value: " + value);
            System.out.println("    directors: " + aDirector);
        }
    
    }
    
    
    /*
     * Check getgetSimilarRatingsSimilarRatingsByFilter method
     */
    public void printSimilarRatingsByGenreAndMinutes(){
        // Create SecondRatings object
        // String rfilename = "ratings_short.csv";
        String rfilename = "ratings.csv"; // for test
        //RaterDatabase.initialize(rfilename); // for test
        FourthRatings fR = new FourthRatings(rfilename);
        
        // Call the MovieDatabase initialize method 
        // with the moviefile to set up the movie database
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); // for test
        
        // Call getSimilarRatings
        /*String id = "65";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        AllFilters allFilter = new AllFilters();
        int min = 100;
        int max = 200;
        String genre = "Adventure";*/
        // for test
        String id = "168";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        AllFilters allFilter = new AllFilters();
        int min = 80;
        int max = 160;
        String genre = "Drama";
        MinutesFilter minutesFilter = new MinutesFilter(min, max); 
        GenreFilter genreFilter = new GenreFilter(genre);
        allFilter.addFilter(minutesFilter);
        allFilter.addFilter(genreFilter);
        
        ArrayList<Rating> result = fR.getgetSimilarRatingsSimilarRatingsByFilter(id, numSimilarRaters,
                                                                               minimalRaters, allFilter);
        
        /*
        Rating r = result.get(0);
        String item = r.getItem();
        double value = r.getValue();
        
        String rmovieID = result.get(0).getItem();
        String title = MovieDatabase.getTitle(rmovieID);
        
        System.out.println("the top rated average is " + title);
        
        */
        
        // for log
        System.out.println("[the top rated average]-----");
        for(Rating r : result){
            String item = r.getItem();
            double value = r.getValue();
        
            //String rmovieID = result.get(0).getItem();
            String aTitle = MovieDatabase.getTitle(item);
            //String aDirector = MovieDatabase.getDirector(item);
            String aGenre = MovieDatabase.getGenres(item);
            int    minumte = MovieDatabase.getMinutes(item);
            System.out.println("movie id: " + item + ", title: " + aTitle + " ,minutes: " +minumte + ", value: " + value);
            //System.out.println("    directors: " + aDirector);
            System.out.println("    genre: " + aGenre);
        }
    
    }
    
    /*
     * Check getgetSimilarRatingsSimilarRatingsByFilter method
     */
    public void printSimilarRatingsByYearAfterAndMinutes(){
        // Create SecondRatings object
        // String rfilename = "ratings_short.csv";
        String rfilename = "ratings.csv"; // for test
        //RaterDatabase.initialize(rfilename); // for test
        FourthRatings fR = new FourthRatings(rfilename);
        
        // Call the MovieDatabase initialize method 
        // with the moviefile to set up the movie database
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv"); // for test
        
        // Call getSimilarRatings
        /*String id = "65";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        AllFilters allFilter = new AllFilters();
        int min = 80;
        int max = 100;
        int year = 2000; */
        // for test
        String id = "314";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        AllFilters allFilter = new AllFilters();
        int min = 70;
        int max = 200;
        int year = 1975; 
        MinutesFilter minutesFilter = new MinutesFilter(min, max);
        
        YearAfterFilter yearFilter = new YearAfterFilter(year);
        allFilter.addFilter(minutesFilter);
        allFilter.addFilter(yearFilter);
        
        ArrayList<Rating> result = fR.getgetSimilarRatingsSimilarRatingsByFilter(id, numSimilarRaters,
                                                                               minimalRaters, allFilter);
        
        /*
        Rating r = result.get(0);
        String item = r.getItem();
        double value = r.getValue();
        
        String rmovieID = result.get(0).getItem();
        String title = MovieDatabase.getTitle(rmovieID);
        
        System.out.println("the top rated average is " + title);
        
        */
        
        // for log
        System.out.println("[the top rated average]-----");
        for(Rating r : result){
            String item = r.getItem();
            double value = r.getValue();
        
            //String rmovieID = result.get(0).getItem();
            String aTitle = MovieDatabase.getTitle(item);
            //String aDirector = MovieDatabase.getDirector(item);
            String aGenre = MovieDatabase.getGenres(item);
            int    minumte = MovieDatabase.getMinutes(item);
            System.out.println("movie id: " + item + ", title: " + aTitle + " ,minutes: " +minumte + ", value: " + value);
            //System.out.println("    directors: " + aDirector);
            System.out.println("    genre: " + aGenre);
        }
    
    }
    
}
