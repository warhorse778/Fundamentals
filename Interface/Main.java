public class Main {
    public static void main(String[] args) {

        ByTwos byTwos = new ByTwos();
        ByThrees byThrees = new ByThrees();

        Series ob;

        for (int i = 0; i < 5; i++) {

            ob = byTwos;
            System.out.println(ob.getNext());



        }
    }
}
