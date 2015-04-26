
import java.util.function.*;

public class RecClosures {
    //all both
    public static Predicate<Integer> union(Predicate<Integer> memA, Predicate<Integer> memB) {
		return (n) -> memA.test(n) || memB.test(n);
    }
    
    //only both
    public static Predicate<Integer> intersect(Predicate<Integer> memA, Predicate<Integer> memB) {
		return (n) -> memA.test(n) && memB.test(n);
    }
    
    //in A but not B
    public static Predicate<Integer> relativeComplement(Predicate<Integer> memA, Predicate<Integer> memB) {
		return (n) -> memA.test(n) && !memB.test(n);
    }
    
    //first coordinate in A and second in B
    public static BiPredicate<Integer, Integer> cartesianProduct(Predicate<Integer> memA, Predicate<Integer> memB) {
		return (n,m) -> (memA.test(n) && memB.test(m));
    }
	// tests
	
	public static boolean isSmall(Integer n) { return n < 10; }
	public static boolean divBy5(Integer n) { return n % 5 == 0; }
	
	
	public static void main(String args[]) {
            System.out.println("MEMBERSHIP FUNCTIONS:");
		Predicate<Integer> mem = union(RecClosures::isSmall, RecClosures::divBy5);
		for(int i = 0; i < 21; i++) {
			System.out.println("union(" + i + ") = " + mem.test(i));
		}
                
                mem = intersect(RecClosures::isSmall, RecClosures::divBy5);
		for(int i = 0; i < 21; i++) {
			System.out.println("intersect(" + i + ") = " + mem.test(i));
		}
                
                mem = relativeComplement(RecClosures::isSmall, RecClosures::divBy5);
		for(int i = 0; i < 21; i++) {
			System.out.println("relativeComplement(" + i + ") = " + mem.test(i));
		}
                
                BiPredicate<Integer, Integer> mem2 = cartesianProduct(RecClosures::isSmall, RecClosures::divBy5);
		for(int i = 0; i < 21; i++) {
                    for(int j = 0; j < 21; j++){
                        System.out.println("cartesianProduct(" + i + "," + j + ") = " + mem2.test(i,j));
                    }
		}
                System.out.println("END MEMBERSHIP FUNCTIONS.");
	}
        
        

}
