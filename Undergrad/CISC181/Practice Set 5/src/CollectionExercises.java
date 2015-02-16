package ps5;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class CollectionExercises {

    /**
     * Returns a new List that contains all of the Integers in the
     * given List that are less than zero
     */
    public static List<Integer> lessThanZero(List<Integer> l)
    {
        ArrayList<Integer> lessThanZero = new ArrayList<Integer>();
        
        for(Integer i : l)
        {
        	if(i.intValue() < 0)
        	{
        		lessThanZero.add(i);
        	}
        }
        
        return lessThanZero;
    }
    
    /**
     * Mutates the given List by removing all of the Integers in the
     * List that are less than zero
     */
    public static void removeLessThanZero(List<Integer> l) 
    { 	
    	l.removeAll(lessThanZero(l));
    }
    
    /**
     * Returns a new Set that contains all of the elements in a and
     * all of the elements in b (i.e. the union of two sets).
     */
    public static Set<Integer> union(Set<Integer> a, Set<Integer> b) 
    {	  
    	Set<Integer> c = new HashSet<Integer>();
    	c.addAll(a);
    	c.addAll(b);
    	return c;
    }
    
    /**
     * Returns a new Set that contains all of the elements that are
     * in a and also in b (i.e. the intersection of two sets).
     */
    public static Set<Integer> intersection(Set<Integer> a, Set<Integer> b) 
    {   
    	Set<Integer> c = union(a,b);
    	c.removeAll(difference(a, b));
    	c.removeAll(difference(b, a));
    	return c;
    }
    
    /**
     * Returns a new Set that contains all of the elements in a that
     * are not in b (i.e. the difference of a from b, or a subtract b).
     */
    public static Set<Integer> difference(Set<Integer> a, Set<Integer> b)
    {
    	Set<Integer> c = union(a,b);
        c.removeAll(b);
    	return c;
    }
    
    /**
     * Returns a new Map that contains a reverse mapping of the given map.
     * Thus each key in the given map will appear as a value in the result,
     * and each value in the given map will appear as a key in the result.
     * 
     * Because a Map contains a many to one mapping (i.e. two keys can map
     * to the same value), to properly return a reverse mapping each key
     * in the result could have multiple values. To represent this, each key
     * in the result has a Set as its mapped value where the Set contains
     * all of the multiple values.
     * 
     * See the test class for a concrete example.
     * 
     * I know this code is ugly, forgive me. - Alex
     */
    public static Map<String, Set<String>> reverseMapping(Map<String, String> mapping) 
    {   	
    	Map<String, Set<String>> reverseMapping = new HashMap<String, Set<String>>();
    	
    	for(Iterator<Entry<String, String>> i = mapping.entrySet().iterator(); i.hasNext();)
    	{
    		Map.Entry<String, String> o = i.next();
    		String newKey = o.getValue();
    		Set<String> newValues = new HashSet<String>();   
    		for(Iterator<Entry<String, String>> j = mapping.entrySet().iterator(); j.hasNext();)
    		{
    			Map.Entry<String, String> p = j.next();
    			if(mapping.get(o.getKey()).equals(p.getValue()))
    			{
    				newValues.add(p.getKey());
    			}    		
    		}
    		reverseMapping.put(newKey, newValues);			
    	}
    	return reverseMapping; 
    }   
}


