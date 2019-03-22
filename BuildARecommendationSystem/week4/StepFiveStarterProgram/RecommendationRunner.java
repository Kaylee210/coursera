
/**
 * Class RecommendationRunner 
 * 
 * @author (Kaylee)
 * @version (21th March 2019)
 */

import java.util.*;

public class RecommendationRunner implements Recommender{
    
    private Random myRandom;
    private int toRateNum;
    
    /*
     *  This method returns a list of strings 
     *  representing movie IDs that will be 
     *  used to present movies to the user for them to rate. 
     *  The list returned this method include 20 movies
     */
    public ArrayList<String> getItemsToRate (){
        
        // Make return value
        ArrayList<String> rateList = new ArrayList<String>();
        
        // Get movie list from moviedatabase
        TrueFilter trueF = new TrueFilter();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movieList = MovieDatabase.filterBy(trueF);
        
        // Choose randam movie from movie list
        toRateNum = 20;
        myRandom = new Random();
        int size = MovieDatabase.size();
        for(int i = 0; i < toRateNum; i++){
            // get randam number as index
            int idx = myRandom.nextInt(size);
            // get movie from database
            String movieId = movieList.get(idx);
            if(!rateList.contains(movieId)){
                // If there is not this movie in rateList,
                // add this movie to list.
                rateList.add(movieId);
            }
        }
        
        
        return rateList;
    }
    
    /*
     * This prints out an HTML table of movies recommended by your program 
     * for the user based on the movies they rated. 
     * It has one parameter webRaterID, a String that is the ID of the user,
     * who has been added by our code to the RaterDatabase with the ratings 
     * they entered.
     */
    public void printRecommendationsFor (String webRaterID){
        
        int recMaxNum = 20;
        
        // Get movie database list
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        // Get recommendation list
        FourthRatings fourthRating = new FourthRatings();
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        ArrayList<Rating> recList = fourthRating.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
        
        // In the case, there is not reccomendation movie
        if(recList.size() <= 0){
            System.out.println("Error, please try again!");
            return;
        }
        
        int size = recList.size();
        if(size > recMaxNum){
            size = recMaxNum;
        }
        
        // Start to write recommendation list with html format 
        // set CSS
        printCSS();
        // print headder
        printHeadder();
        // Make recommendation table
        System.out.println("<table align=\"center\">");
        //System.out.println("<tr><th>Tittle</th><th>Genre</th><th>Year</th><th>Min</th></tr>");
        System.out.println("<th>Rank</th>");
        System.out.println("<th>Poster</th>");
        System.out.println("<th>Movie info</th>");
        for(int i = 0; i < size; i++){
            Rating r = recList.get(i);
            String movieTitleID = r.getItem();            
            String aTitle = MovieDatabase.getTitle(movieTitleID);
            String aGenre = MovieDatabase.getGenres(movieTitleID);
            int aYear = MovieDatabase.getYear(movieTitleID);
            int aMin = MovieDatabase.getMinutes(movieTitleID);
            String aPastor = MovieDatabase.getPoster(movieTitleID);
            System.out.println("<tr>");
            System.out.println("<td rowspan=\"4\">" + (i + 1) + "</td>");
            System.out.println("<td rowspan=\"4\"><img src=\"" + aPastor + "\"/ class=\"imgSize\"></td>");
            System.out.println("<td class = \"boldsize\">" + aTitle + "</td>");
            System.out.println("<tr><td>" + aGenre + "</td></tr>");
            System.out.println("<tr><td>" + aYear + " year" + "</td></tr>");
            System.out.println("<tr><td>" +  aMin + " minutes" + "</td></tr>");
            System.out.println("</tr>");
        }
        System.out.println("</table>");
        //print last message
        printLastMessage();
    }
    public void printCSS(){
        System.out.println("<style>");
        System.out.println("th {");
        System.out.println(     "background-color: yellow;");
        System.out.println(     "color: gray;}");
        System.out.println("td.boldsize{");
        System.out.println("font-weight:bold;}");
        System.out.println("th,td {");
        System.out.println(     "border-style: solid;");
        System.out.println(     "border-width: 3px;");
        System.out.println(     "border-color: orange;}");
        System.out.println("table {");
        System.out.println(      "border-collapse:  collapse;");
        System.out.println(      "table-layout: fixed;");
        System.out.println(      "font-size: 150%;}");
        System.out.println("img.imgSize {");
        System.out.println("width: 50%;");
        System.out.println("height: 50%;}");
        System.out.println("</style>");
    }
    public void printHeadder (){
        System.out.println("<h1>Recommendation</h1>");
        
    }
    public void printLastMessage (){
        System.out.println("<h2>Which movie do you want to watch?? :)</h2>");
    }
}
