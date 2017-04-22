package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {

        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        //Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void tilisiirronParametritYhdellaOstoksella() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        //tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void tilisiirronParametritKahdellaEriOstoksella() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        when(varasto.saldo(2)).thenReturn(20);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 10));
        
        

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        //tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 15);
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void tilisiirronParametritKahdellaSamallaOstoksella() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(2)).thenReturn(20);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 10));
        
        

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        //tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 20);
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void tilisiirronParametritKunToinenOstettavaOnLoppu() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        when(varasto.saldo(2)).thenReturn(20);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 10));
        
        

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        //tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void metodiAloitaAsiointiNollaaEdellisetOstokset() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(2);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        when(varasto.saldo(2)).thenReturn(20);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 10));
        
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        //tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 15);
        
        
        // tehdään uudet ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.tilimaksu("pekka", "12345");

        //tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }
    
    @Test
    public void ostoksilleKaytetaanOmaaViitetta() {
        // määritellään että viitegeneraattori palauttaa viitteen
        when(viite.uusi())
                .thenReturn(42)
                .thenReturn(43);
        

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(2);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        when(varasto.saldo(2)).thenReturn(20);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 10));
        
        
        
        
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        //tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 15);
        
        
        // tehdään uudet ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.tilimaksu("pekka", "12345");

        //tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa)
        verify(pankki).tilisiirto("pekka", 43, "12345", "33333-44455", 5);
    }
    
    @Test
    public void koristaPoistaminen() {
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 10));
        k.aloitaAsiointi();
        k.lisaaKoriin(2);  
        k.lisaaKoriin(1);  
        k.poistaKorista(2);  
        verify(varasto).palautaVarastoon(varasto.haeTuote(2));
    }

}
