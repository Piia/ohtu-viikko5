
import java.util.Scanner;
import ohtu.intjoukkosovellus.IntJoukko;
import ohtu.intjoukkosovellus.IntJoukkoLaskin;

public class App {

    private static IntJoukko A, B, C;
    private static IntJoukkoLaskin laskin;
    private static Scanner lukija;

    public static void main(String[] args) {
        A = new IntJoukko();
        B = new IntJoukko();
        C = new IntJoukko();
        laskin = new IntJoukkoLaskin();
        lukija = new Scanner(System.in);
        String luettu;

        System.out.println("Tervetuloa joukkolaboratorioon!");
        System.out.println("Käytössäsi ovat joukot A, B ja C.");
        System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e), leikkaus(le) ja lopetus(quit)(q).");
        System.out.println("Joukon nimi komentona tarkoittaa pyyntöä tulostaa joukko.");

        while (true) {
            luettu = lukija.nextLine();
            switch(luettu) {
                case "lisää":
                case "li":
                    lisaa();
                case "poista":
                case "p":
                    poista();
                case "kuuluu":
                case "k":
                    kuuluu();
                case "yhdiste":
                case "y":
                    yhdiste();
                case "leikkaus":
                case "le":
                    leikkaus();
                case "erotus":
                case "e":
                    erotus();
                case "A":
                case "a":
                    System.out.println(A);
                case "B":
                case "b":
                    System.out.println(B);
                case "C":
                case "c":
                    System.out.println(C);
                case "lopeta":
                case "quit":
                case "q":
                    System.out.println("Lopetetaan, moikka!");
                    break;
                default:
                    System.out.println("Virheellinen komento! " + luettu);
                    System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), "
                            + "yhdiste(y), erotus(e) ja leikkaus(le).");                
            }
            System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e) ja leikkaus(le).");
            
        }
    }

    private static IntJoukko mikaJoukko() {
        String luettu;
        while (true) {
            luettu = lukija.nextLine();
            switch (luettu) {
                case "A":
                case "a":
                    return A;
                case "B":
                case "b":
                    return B;
                case "C":
                case "c":
                    return C;
                default:
                    System.out.println("Virheellinen joukko! " + luettu);
                    System.out.print("Yritä uudelleen!");
                    break;
            }
        }
    }

    private static void lisaa() {
        System.out.print("Mihin joukkoon? ");
        IntJoukko joukko = mikaJoukko();
        System.out.println("");
        
        System.out.print("Mikä luku lisätään? ");
        int lisLuku = lukija.nextInt();
        
        joukko.lisaa(lisLuku);
    }

    private static void yhdiste() {
        System.out.print("1. joukko? ");
        IntJoukko aJoukko = mikaJoukko();
        
        System.out.print("2. joukko? ");
        IntJoukko bJoukko = mikaJoukko();
        
        IntJoukko c = laskin.yhdiste(aJoukko, bJoukko);
        System.out.println("A yhdiste B = " + c.toString());
    }

    private static void leikkaus() {
        System.out.print("1. joukko? ");
        IntJoukko aJoukko = mikaJoukko();
        
        System.out.print("2. joukko? ");
        IntJoukko bJoukko = mikaJoukko();
        
        IntJoukko c = laskin.leikkaus(aJoukko, bJoukko);
        System.out.println("A leikkaus B = " + c.toString());
    }

    private static void erotus() {
        System.out.print("1. joukko? ");
        IntJoukko aJoukko = mikaJoukko();
        
        System.out.print("2. joukko? ");
        IntJoukko bJoukko = mikaJoukko();
        
        IntJoukko c = laskin.erotus(aJoukko, bJoukko);
        System.out.println("A erotus B = " + c.toString());
    }

    private static void poista() {
        System.out.print("Mistä joukosta? ");
        IntJoukko joukko = mikaJoukko();
        
        System.out.print("Mikä luku poistetaan? ");
        int lisLuku = lukija.nextInt();
        
        joukko.poista(lisLuku);
    }

    private static void kuuluu() {
        System.out.print("Mihin joukkoon? ");
        IntJoukko joukko = mikaJoukko();
        
        System.out.print("Mikä luku? ");
        int kysLuku = lukija.nextInt();
        
        boolean kuuluuko = joukko.lukuOnJoukossa(kysLuku);
        
        if (kuuluuko) {
            System.out.println(kysLuku + " kuuluu joukkoon ");
        } else {
            System.out.println(kysLuku + " ei kuulu joukkoon ");
        }
    }
}
