import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abq335 on 05/06/15.
 */
public class Tisch {

    private List<String> ablage;
    private List<Spieler> zug;

    public Tisch(){
        ablage = new ArrayList<String>();
        zug = new ArrayList<Spieler>();
    }

    public synchronized void macheZug(Spieler spieler) throws InterruptedException {
        if(!zug.contains(spieler)){
            zug.add(spieler);
            notifyAll();
        }else {
            wait();
        }
    }

    public synchronized void macheZug() throws InterruptedException {
        if(zug.size()>1){
            String zugMsg = zug.get(0)+" -VS- "+zug.get(1);
            System.err.println(zugMsg);
            ablage.add(zugMsg);
            zug.clear();
            notify();
        }else{
            wait();
        }
    }

    public List<String> getAblage(){
        return ablage;
    }

    public static void main(String[] args){
        try {
            Tisch tisch = new Tisch();
            Spielleiter sl = new Spielleiter(tisch);
            sl.start();
            Thread.sleep(10000);
            sl.interrupt();
        } catch (InterruptedException e) {

        }
    }
}
