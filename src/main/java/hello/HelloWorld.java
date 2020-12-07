package hello;

public class HelloWorld {
    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());
        Test11.geo(new String[]{"a","b","c"});
    }
}

class Test11 {
    public static void geo(String[] args){
        for (String g: args) {
            System.out.println(g);
        }
    }

}