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
         vm.run();
         System.out.println(vm);
      } catch(Exception e) {
         System.out.println(e);
      }
   }
}