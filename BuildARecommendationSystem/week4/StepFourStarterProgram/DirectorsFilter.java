
/**
 * クラス DirectorsFilter の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import java.util.Arrays;

public class DirectorsFilter implements Filter{

    private String[] myDirector;
	
    public DirectorsFilter(String director) {
        myDirector = director.split(",", 0);
    }
    
    @Override
    public boolean satisfies(String id) {
        String aDirector = MovieDatabase.getDirector(id);
        return Arrays.asList(myDirector).contains(aDirector);
    }
}
