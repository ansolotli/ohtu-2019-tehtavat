
package laskin;

public class Miinus extends Komento {
    
    @Override
    public int laske(int luku1, int luku2) {
        edellinen = luku1;
        return luku1 - luku2;
    }
}
