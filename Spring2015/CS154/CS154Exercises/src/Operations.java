import java.util.*;
import java.util.function.*;

public class Operations {
	//exercise 1
	public  static <T> Set<T> intersect(Set<T> a, Set<T> b) {
		if(a.isEmpty() && b.isEmpty()){return a;}
		Set<T> result = new HashSet<T>();
		
		for(T obj : a){
			if(b.contains(obj)){result.add(obj);}
		}
		
		return result;
	}
	public  static <T> Set<T> union(Set<T> a, Set<T> b) {
		if(a.isEmpty() && b.isEmpty()){return a;}
		Set<T> result = new HashSet<T>();
		
		for(T obj : a){
			result.add(obj);
		}
		for(T obj : b){
			result.add(obj);
		}
		return result;
	}
	public  static <T> Set<T> diff(Set<T> a, Set<T> b) {
		if(a.isEmpty() && b.isEmpty()){return a;}
		Set<T> result = new HashSet<T>();
		
		for(T obj: a){
			if(!b.contains(obj)){result.add(obj);}
		}
		
		return result;
	}
	public  static <T> boolean subset(Set<T> a, Set<T> b) {
		//a is a subset of b
		boolean result = true;
		for(T obj : a){
			if(!b.contains(obj)){return false;}
		}
		return result;
		
	}
	//exercise 1
	
	//exercise 2
	public static <T> Set<T> filter(Set<T> a, Predicate<T> f){
		//return set with everything that passes a test(f).
		Set<T> result = new HashSet<T>();
		for(T obj: a ){
			if(f.test(obj)){result.add(obj);}
		}
		return result;
	}
	
	public static <T> Set<T> map(Set<T> a, UnaryOperator<T> f){
		//every element from a, transformed(by f).
		Set<T> result = new HashSet<T>();
		for(T obj: a){
			result.add(f.apply(obj));
		}
		
		return result;
	}
	//exercise 2
	
	//exercise 3
	public static <T> Set<Set<T>> power(Set<T> a) {
		//set of all subsets of a
		Set<Set<T>> result = new HashSet<Set<T>>();//create powerSet
		result.add(new HashSet<T>());//add the empty set
		
		for(T obj: a){//for each object in the set a
			Set<Set<T>> powerSet = new HashSet<Set<T>>(); //create a new set for it
			for(Set<T> set: result){//for each set that already exists
				powerSet.add(set);//add the chosen one to the new set
				
				Set<T> combineSets = new HashSet<T>(set);//create another set out of the already added set
				combineSets.add(obj);//add the new item to the set(so it would add itself to the empty set, resulting in itself being added and then it adds itself to every other item)
				powerSet.add(combineSets);//add the newly created set to the new set
			}
			result = powerSet;//set the full powerSet to the new set with all new items in it
		}
		
		
		return result;
	}
	//exercise 3
	
	//exercise 4 (der's stuff on it in the lecture under java collections. Map function????)
	//Function<Integer, String> unicode = (x) -> {return String.format("\\u%04x", x);};
	public static String unicode(int i){
		Map<Integer, String> unicodeValues = new HashMap<Integer,String>();
		unicodeValues.put(0, "U+0030");
		unicodeValues.put(1, "U+0031");
		unicodeValues.put(2, "U+0032");
		unicodeValues.put(3, "U+0033");
		unicodeValues.put(4, "U+0034");
		unicodeValues.put(5, "U+0035");
		unicodeValues.put(6, "U+0036");
		unicodeValues.put(7, "U+0037");
		unicodeValues.put(8, "U+0038");
		unicodeValues.put(9, "U+0039");
		return unicodeValues.get(i);
		}
	//exercise 4
}
