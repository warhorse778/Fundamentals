public class ByTwos implements Series{
    int val;

    ByTwos(){
        val = 0;
    }

    public int getNext() {
        val += 2;
        return val;
    }
    public int getPrev() {
        val -= 2;
        return val;
    }
}
