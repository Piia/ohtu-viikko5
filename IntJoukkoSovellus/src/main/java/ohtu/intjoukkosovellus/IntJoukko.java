package ohtu.intjoukkosovellus;

public class IntJoukko {

    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko(int kasvatus) {
        if (kasvatus < 0) {
            this.kasvatuskoko = 5;
        } else {
            this.kasvatuskoko = kasvatus;
        }
        this.joukko = new int[kasvatuskoko];
        for (int i = 0; i < joukko.length; i++) {
            joukko[i] = 0;
        }
        this.alkioidenLkm = 0;
    }

    public IntJoukko() {
        this.kasvatuskoko = 5;
        this.joukko = new int[kasvatuskoko];
        for (int i = 0; i < joukko.length; i++) {
            joukko[i] = 0;
        }
        this.alkioidenLkm = 0;
    }

    public int getKasvatuskoko() {
        return this.kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            joukko[0] = luku;
            alkioidenLkm++;
            return true;
        }

        if (!lukuOnJoukossa(luku)) {
            if (alkioidenLkm == joukko.length) {
                kasvataTaulukkoa();
            }
            joukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            return true;
        }
        return false;
    }

    public boolean lukuOnJoukossa(int luku) {
        return luvunIndeksi(luku) != -1;
    }

    public boolean poista(int luku) {
        int kohta = luvunIndeksi(luku);
        if (kohta == -1) {
            return false;
        }
        int apu;
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            apu = joukko[j];
            joukko[j] = joukko[j + 1];
            joukko[j + 1] = apu;
        }
        alkioidenLkm--;
        return true;
    }

    public int getAlkioidenLkm() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + joukko[0] + "}";
        } else {
            String merkkijono = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                merkkijono += joukko[i];
                merkkijono += ", ";
            }
            merkkijono += joukko[alkioidenLkm - 1];
            merkkijono += "}";
            return merkkijono;
        }
    }

    public int[] getJoukko() {
        if (alkioidenLkm == 0) {
            return new int[0];
        }
        int[] taulu = new int[alkioidenLkm];
        kopioiTaulukko(joukko, taulu);
        return taulu;
    }

    // PRIVATE METHODS
    private void kasvataTaulukkoa() {
        joukko = kopioiTaulukko(joukko, new int[alkioidenLkm + kasvatuskoko]);
    }

    private int luvunIndeksi(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukko[i]) {
                return i;
            }
        }
        return -1;
    }

    private int[] kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, alkioidenLkm);
        return uusi;
    }

    // STATIC METHODS TRANSFERRED TO INTJOUKKOLASKIN CLASS
}
