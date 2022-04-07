public class zadchar {
    public Character a = 'a';
}
 class apple extends zadchar{
    public static void main(String[] args) {
apple myobj = new apple();
myobj.a = 'b';

        System.out.println(myobj.a.charValue());
    }
}