package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {

    private TextField tuloskentta;
    private TextField syotekentta;
    private Button plus;
    private Button miinus;
    private Button nollaa;
    private Button undo;
    private Komentotehdas komennot;
    private int tulos;
    private Komento edellinen;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.komennot = new Komentotehdas(plus, miinus, nollaa, undo);
        this.tulos = 0;
        this.edellinen = null;
    }

    @Override
    public void handle(Event event) {
        int arvo = 0;

        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }

        if (event.getTarget() != undo) {
            Komento komento = komennot.hae(event.getTarget());
            tulos = komento.laske(tulos, arvo);
            edellinen = komento;
        } else {
            tulos = edellinen.undo();
            edellinen = null;
        }

        syotekentta.setText("");
        tuloskentta.setText("" + tulos);

        nollaa.disableProperty().set(tulos == 0);
        undo.disableProperty().set(false);
    }
}
