public class TestVM {

   public static void main(String[] args) {
      try {
         VM vm = new VM();
         vm.add("load x, 10");
         vm.add("load y, 5");
         vm.add("loop x");
         vm.add("inc y");
         vm.add("end");
         vm.add("goto AAA");
         vm.add("inc y");
         vm.add("AAA: inc y");
         vm.run();
         System.out.println(vm);
      } catch(Exception e) {
         System.out.println(e);
      }
   }
}