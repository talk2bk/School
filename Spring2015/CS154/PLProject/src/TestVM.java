public class TestVM {

   public static void main(String[] args) {
      try {
         VM vm = new VM();
         vm.add("load x, 10");//0
         vm.add("load y, 5");//1
         vm.add("loop x");//2
         vm.add("inc y");//3
         vm.add("end");//4
         vm.add("goto AAA");//5
         vm.add("inc y");//6
         vm.add("AAA: inc y");//7
         System.out.print("Pearce's test: ");
         vm.run();
         System.out.println(vm);
         System.out.println("*******************************");
         
         VM vm2 = new VM();
         vm2.add("load n, 5");
         vm2.add("load m, 32");
         vm2.add("loop n");
         vm2.add("inc m");
         vm2.add("end");
         System.out.println("n = 5; m = 32");
         System.out.print("n+m = m: ");
         vm2.run();
         System.out.println(vm2);
         System.out.println("*******************************");
         
         VM vm3 = new VM();
         vm3.add("load n, 2");// n = 2
         vm3.add("load m, 3");// m = 3
         vm3.add("loop n");// loop twice
         vm3.add("loop m");//add m to itself
         vm3.add("inc z");
         vm3.add("end");//end to loop m
         vm3.add("end");//end to the loop n
         System.out.print("n * m = z: ");
         vm3.run();
         System.out.println(vm3);
         System.out.println("*******************************");
         
         VM vm5 = new VM();
         vm5.add("load n, 10"); //10
         vm5.add("load m, 4"); // -4
         vm5.add("load v, n");
         vm5.add("loop m");
         //loop to decrement
         vm5.add("load w, 0");
         vm5.add("loop v");
         vm5.add("load a, w");
         vm5.add("inc w");
         vm5.add("end");
         vm5.add("load v, a");
         //loop to decrement
         vm5.add("end");
         vm5.add("load z, v");
         System.out.println("n = 10; m = 4");
         System.out.print("n - m = z: ");//returns 0 if m < n
         vm5.run();
         System.out.println(vm5);
         System.out.println("*******************************");
         
      } catch(Exception e) {
         System.out.println(e);
      }
   }
}