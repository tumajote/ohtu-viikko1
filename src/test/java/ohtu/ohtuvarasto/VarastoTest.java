package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(2, 1);
    }

    @Test
    public void konstruktoriEiLuoNegatiivistaVarastoa() {
        varasto = new Varasto(-10);
        assertEquals(0.0, varasto.getTilavuus() ,vertailuTarkkuus);
    }
    
    @Test
    public void konstruktori2EiLuoNegatiivistaTilavuutta() {
        varasto = new Varasto(-10,0);
        assertEquals(0.0, varasto.getTilavuus() ,vertailuTarkkuus);
    }
    @Test
    public void konstruktori2EiLuoNegatiivistaSaldoa2() {
        varasto = new Varasto(10,-5);
        assertEquals(0.0, varasto.getSaldo() ,vertailuTarkkuus);
    }
     @Test
    public void konstruktori2EiLuoYliTilavuudenSaldoa() {
        varasto = new Varasto(1,2);
        assertEquals(2, varasto.getTilavuus()+varasto.getSaldo() ,vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void uudellaToisellaVarastollaOikeaTilavuus() {
        assertEquals(2, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void uudellaToisellaVarastollaOikeaSaldo() {
        assertEquals(1, varasto2.getSaldo(), vertailuTarkkuus);
    }
    

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void negaLisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(-8);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ylimaaraLisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(18);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    @Test
    public void negaOttaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-2.0);

        assertEquals(0.0, saatuMaara, vertailuTarkkuus);
    }
    @Test
    public void ottaminenPalauttaaOikeanMaaran3() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(9.0);

        assertEquals(8.9, saatuMaara, vertailuTarkkuus);
    }
    @Test
    public void ottaminenPalauttaaOikeanMaaran4() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(9.0);

        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(10);
        String s1 = varasto.toString();
        String s2 = "saldo = 10.0, vielä tilaa 0.0";
        
        assertEquals(s2,s1);
    }
    

}