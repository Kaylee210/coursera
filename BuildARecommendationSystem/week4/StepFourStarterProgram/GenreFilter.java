
/**
 * Filter GenreFilter の注釈をここに書きます.
 * 
 * @author (Kaylee)
 * @version (11th March 2019)
 */
public class GenreFilter implements Filter{

    private String myGenre;
    
    public GenreFilter(String genre) {
		myGenre = genre;
	}
    
    @Override
    public boolean satisfies(String id) {
        if(MovieDatabase.getGenres(id).indexOf(myGenre) != -1){
            return true;
        }
        
        return false;
    }
    
}
