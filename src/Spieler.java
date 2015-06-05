import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by abq335 on 05/06/15.
 */
public class Spieler extends Thread {

    private Map<Integer,String> haende;
    private Random random;
    private String hand;
    private Tisch tisch;

    public Spieler(String name, Map<Integer,String> haende,Tisch tisch){
        super(name);
        this.haende = haende;
        this.random = new Random();
        this.tisch = tisch;
    }

    public void run(){
        while (!isInterrupted()){
            try {
                hand = haende.get(random.nextInt(haende.size()-1));
                tisch.macheZug(this);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }

    public String toString(){
        return super.getName()+" "+hand;
    }
}
