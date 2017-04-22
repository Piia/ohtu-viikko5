package ohtu.intjoukkosovellus;

import ohtu.intjoukkosovellus.IntJoukko;

public class IntJoukkoLaskin {
    
    public IntJoukkoLaskin() {
        
    }
    
    public IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = a.getJoukko();
        int[] bTaulu = b.getJoukko();
        for (int i = 0; i < aTaulu.length; i++) {
            joukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            joukko.lisaa(bTaulu[i]);
        }
        return joukko;
    }

    public IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = a.getJoukko();
        int luku;
        for (int i = 0; i < aTaulu.length; i++) {
            luku = aTaulu[i];
            if(b.lukuOnJoukossa(luku)) {
                joukko.lisaa(luku);
            }
        }
        return joukko;
    }
    
    public IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.getJoukko();
        int[] bTaulu = b.getJoukko();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }
 
        return z;
}

}
