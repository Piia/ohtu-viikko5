package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkoOperaatiotTest {

    private IntJoukkoLaskin laskin;

    @Before
    public void setUp() {
        laskin = new IntJoukkoLaskin();
    }

    private IntJoukko alustaJoukko(int... luvut) {
        IntJoukko joukko = new IntJoukko();

        for (int luku : luvut) {
            joukko.lisaa(luku);
        }

        return joukko;
    }

    @Test
    public void yhdisteTesti() {
        IntJoukko eka = alustaJoukko(1, 2);
        IntJoukko toka = alustaJoukko(3, 4);

        IntJoukko tulos = laskin.yhdiste(eka, toka);

        int[] vastauksenLuvut = tulos.getJoukko();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {1, 2, 3, 4};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }
    
    @Test
    public void leikkausTesti() {
        IntJoukko eka = alustaJoukko(1, 2, 3);
        IntJoukko toka = alustaJoukko(4, 3, 2);

        IntJoukko tulos = laskin.leikkaus(eka, toka);

        int[] vastauksenLuvut = tulos.getJoukko();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {2, 3};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }
    
    @Test
    public void erotusTesti() {
        IntJoukko eka = alustaJoukko(1, 2, 3);
        IntJoukko toka = alustaJoukko(4, 3, 2);

        IntJoukko tulos = laskin.erotus(eka, toka);

        int[] vastauksenLuvut = tulos.getJoukko();
        Arrays.sort(vastauksenLuvut);

        int[] odotettu = {1};

        assertArrayEquals(odotettu, vastauksenLuvut);
    }
    
    

}
