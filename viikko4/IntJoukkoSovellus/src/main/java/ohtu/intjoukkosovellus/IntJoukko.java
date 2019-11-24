
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int aloitustaulukonKoko = 5, // aloitustalukon koko
                            taulukonKoonKasvu = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] taulukonTahanastisetLuvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int taulukonAlkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        luoAloitusTaulukko(aloitustaulukonKoko, taulukonKoonKasvu);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        luoAloitusTaulukko(kapasiteetti, taulukonKoonKasvu);
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        luoAloitusTaulukko(kapasiteetti, kasvatuskoko);
    }

    private void luoAloitusTaulukko(int kapasiteetti, int kasvatuskoko1) {
        taulukonTahanastisetLuvut = new int[kapasiteetti];
        for (int i = 0; i < taulukonTahanastisetLuvut.length; i++) {
            taulukonTahanastisetLuvut[i] = 0;
        }
        taulukonAlkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko1;
    }
    
    public boolean lisaa(int luku) {

        if (taulukonAlkioidenLkm == 0) {
            taulukonTahanastisetLuvut[0] = luku;
            taulukonAlkioidenLkm++;
            return true;
        } else {
        }
        if (!kuuluu(luku)) {
            luoSuurempiTaulukko(luku);
            return true;
        }
        return false;
    }

    private void luoSuurempiTaulukko(int luku) {
        taulukonTahanastisetLuvut[taulukonAlkioidenLkm] = luku;
        taulukonAlkioidenLkm++;
        if (taulukonAlkioidenLkm % taulukonTahanastisetLuvut.length == 0) {
            int[] vanhaTaulukko = new int[taulukonTahanastisetLuvut.length];
            vanhaTaulukko = taulukonTahanastisetLuvut;
            kopioiTaulukko(taulukonTahanastisetLuvut, vanhaTaulukko);
            taulukonTahanastisetLuvut = new int[taulukonAlkioidenLkm + kasvatuskoko];
            kopioiTaulukko(vanhaTaulukko, taulukonTahanastisetLuvut);
        }
    }

    public boolean kuuluu(int luku) {
        int on = 0;
        for (int i = 0; i < taulukonAlkioidenLkm; i++) {
            if (luku == taulukonTahanastisetLuvut[i]) {
                on++;
            }
        }
        if (on > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean poista(int luku) {
        int kohta = -1;
        for (int i = 0; i < taulukonAlkioidenLkm; i++) {
            if (luku == taulukonTahanastisetLuvut[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                taulukonTahanastisetLuvut[kohta] = 0;
                break;
            }
        }
        if (poistaTyhjaAlkio(kohta)) return true;

        return false;
    }

    private boolean poistaTyhjaAlkio(int kohta) {
        int apu;
        if (kohta != -1) {
            for (int j = kohta; j < taulukonAlkioidenLkm - 1; j++) {
                apu = taulukonTahanastisetLuvut[j];
                taulukonTahanastisetLuvut[j] = taulukonTahanastisetLuvut[j + 1];
                taulukonTahanastisetLuvut[j + 1] = apu;
            }
            taulukonAlkioidenLkm--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return taulukonAlkioidenLkm;
    }

    @Override
    public String toString() {
        if (taulukonAlkioidenLkm == 0) {
            return "{}";
        } else if (taulukonAlkioidenLkm == 1) {
            return "{" + taulukonTahanastisetLuvut[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < taulukonAlkioidenLkm - 1; i++) {
                tuotos += taulukonTahanastisetLuvut[i];
                tuotos += ", ";
            }
            tuotos += taulukonTahanastisetLuvut[taulukonAlkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[taulukonAlkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = taulukonTahanastisetLuvut[i];
        }
        return taulu;
    }
   
    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }
 
        return z;
    }
}
