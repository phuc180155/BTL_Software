
public class Test {
    public static void main(String[] args){
        TestClass tc = TestClass.getInstance();
        TestClass tc2 = TestClass.getInstance();
        tc.setX(1);
        tc2.setX(2);
        System.out.println(tc.getX());
        System.out.println(tc2.getX());
    }
}
