
/**
 * Filter MinutesFilter
 * 
 * @author (Kaylee)
 * @version (11th March 2019)
 */
public class MinutesFilter implements Filter{
    
    private int myMin;
    private int myMax;
        
    public MinutesFilter(int min, int max) {
		myMin = min;
		myMax = max;
	}
    
    @Override
    public boolean satisfies(String id) {
        
        int minute = MovieDatabase.getMinutes(id);
        
        if((myMin <= minute) && (minute <= myMax) ){
            return true;
        }
        
        return false;
    }

}
