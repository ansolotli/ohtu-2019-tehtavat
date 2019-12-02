
package laskin;

import java.util.HashMap;
import javafx.event.EventTarget;
import javafx.scene.control.Button;


public class Komentotehdas {
    private HashMap<EventTarget, Komento> komennot;
    
    public Komentotehdas(Button plus, Button miinus, Button nollaa, Button undo) {
        komennot = new HashMap<>();
        komennot.put(plus, new Plus());
        komennot.put(miinus, new Miinus());
        komennot.put(nollaa, new Nollaa());
    }
    
    public Komento hae(EventTarget operaatio) {
        return komennot.get(operaatio);
    }
}
