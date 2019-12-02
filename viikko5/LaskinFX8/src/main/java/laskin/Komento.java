
package laskin;


public abstract class Komento {
    
    protected int edellinen;
   
    public abstract int laske(int luku1, int luku2);
    
    public int undo() {
        return edellinen;
    }
}
