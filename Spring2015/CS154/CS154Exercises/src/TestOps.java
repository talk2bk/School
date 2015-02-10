//test Operations.java
import java.util.*;
import java.util.function.Function;

public class TestOps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Set<String> s1 = new HashSet<String>();
			s1.add("cat");
			s1.add("bat");
			s1.add("rat");
			s1.add("dog");
			Set<String> s2 = new HashSet<String>();
			s2.add("cat");
			s2.add("bat");
			s2.add("pig");
			s2.add("cow");
			
			//example 1
			System.out.println("s1 = "+s1);
			System.out.println("s2 = "+s2);
			System.out.println("intersect: "+Operations.intersect(s1,s2));
			
			System.out.println("s1 = "+s1);
			System.out.println("s2 = "+s2);
			System.out.println("union: "+Operations.union(s1,s2));
			
			System.out.println("s1 = "+s1);
			System.out.println("s2 = "+s2);
			System.out.println("diff of s1 to s2: "+Operations.diff(s1, s2));
			System.out.println("diff of s2 to s1: "+Operations.diff(s2, s1));
			
			System.out.println("s1 = "+s1);
			System.out.println("s2 = "+s2);
			Set<String> s3 = Operations.intersect(s1, s2);
			System.out.println("Is s1 a subset of s2: "+Operations.subset(s1,s2));
			System.out.println("Is the intersect of s1 and s2 a subset of s1: "+Operations.subset(s3,s1));
			//example 1
			System.out.println("");
			//example 2
			System.out.println("Filter and Map: ");
			Set<Integer> a = new HashSet<Integer>();
		    // a = first 20 multiples of 3
		    for(int i = 0; i < 20; i++) a.add(i * 3);
		  
		    Set<Integer>  b = Operations.filter(a,(Integer x)->x%2==0);
		    Set<Integer> c = Operations.map(b, (x)->2 * x);
		    System.out.println("First 20 multiples of 3 = " + a);
		    System.out.println("All divisible by 2 = " + b);
		    System.out.println("Multiply each by 2 = " + c);
		    
			//example 2
		    System.out.println("");
		    //example 3
		    System.out.println("Power Sets: ");
		    Set<Integer> power = new HashSet<Integer>();
		    System.out.println("Empty set: "+Operations.power(power));
		    
		    for(int i = 1; i <= 3; i++){
		    	power.add(i);
		    }
		    System.out.println("{1,2,3}"+Operations.power(power));
		    //example 3
		    System.out.println("");
		    //example 4
		    System.out.println("Unicode Values: ");
		    Function<Integer, String> unicode = Operations::unicode;
		    for(int i = 0; i <= 9; i++){
		    System.out.println(i+": "+unicode.apply(i));
		    }
		    //example 4

	}

}
