import java.util.HashMap;
import java.util.Map;

/**
 * Created by abq335 on 05/06/15.
 */
public class Spielleiter extends Thread {

    private Spieler player1;
    private Spieler player2;

    private Tisch tisch;

    public Spielleiter(Tisch tisch){
        Map<Integer,String> haende = new HashMap<Integer, String>();
        haende.put(0,"SCHERE");
        haende.put(1,"STEIN");
        haende.put(2,"PAPIER");

        this.tisch = tisch;

        player1 = new Spieler("spieler1",haende,tisch);
        player1.start();
        player2 = new Spieler("spieler2",haende,tisch);
        player2.start();
    }

    public void run(){
        while (!isInterrupted()){
            try {
                tisch.macheZug();
                sleep(1000);
            } catch (InterruptedException e) {
                player1.interrupt();
                player2.interrupt();
                System.err.println("es wurden "+tisch.getAblage().size()+" spiele gespielt");
                this.interrupt();
            }
        }
    }
}
