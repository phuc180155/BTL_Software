public class TestClass {
    private static TestClass instance;

    private static int x = 5;
    private TestClass(){}

    public static TestClass getInstance(){
        if(instance == null){
            instance = new TestClass();
        }
        return instance;
    }

    public void setX(int a){
        x = a;
    }

    public int getX(){
        return 1;
    }

}


