/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tomos
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }
    
    @Test
    public void oikeaRahamaara() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void eiLounaitaMyyty() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void KateisostoToimiiKunMaksuOnRiittavaEdullinen(){
        assertEquals(260, kassa.syoEdullisesti(500));
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void KateisostoToimiiKunMaksuOnRiittavaMaukas(){
        assertEquals(100, kassa.syoMaukkaasti(500));
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void LounaidenMaaraKasvaaKateisostoEdullinen(){
        kassa.syoEdullisesti(500);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void LounaidenMaaraKasvaaKateisostoMaukas(){
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void KateisostoEiToimiKunMaksuEiOleRiittavaEdullinen(){
        assertEquals(200, kassa.syoEdullisesti(200));
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void KateisostoEiToimiKunMaksuEiOleRiittavaMaukas(){
        assertEquals(200, kassa.syoMaukkaasti(200));
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void LounaidenMaaraEiKasvaKateisostoEdullinen(){
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void LounaidenMaaraEiKasvaKateisostoMaukas(){
        kassa.syoMaukkaasti(300);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
        
    Maksukortti kortti;

    @Test
    public void EdullinenOstoToimiiKunKortillaOnTarpeeksiRahaa(){
        kortti = new Maksukortti(500);
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(260, kortti.saldo());
    }
    
    @Test
    public void MaukasOstoToimiiKunKortillaOnTarpeeksiRahaa(){
        kortti = new Maksukortti(500);
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void EdullistenMyyntiKasvaaKunKortillaOnTarpeeksiRahaa(){
        kortti = new Maksukortti(500);
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void MaukkaidenMyyntiKasvaaKunKortillaOnTarpeeksiRahaa(){
        kortti = new Maksukortti(500);
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void EdullinenOstoEiToimiKunKortillaEiOleTarpeeksiRahaa(){
        kortti = new Maksukortti(200);
        assertFalse(kassa.syoEdullisesti(kortti));
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void MaukasOstoEiToimiKunKortillaEiOleTarpeeksiRahaa(){
        kortti = new Maksukortti(300);
        assertFalse(kassa.syoMaukkaasti(kortti));
        assertEquals(300, kortti.saldo());
    }
    
    @Test
    public void EdullistenMyyntiEiKasvaKunKortillaEiOleTarpeeksiRahaa(){
        kortti = new Maksukortti(200);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void MaukkaidenMyyntiEiKasvaKunKortillaEiOleTarpeeksiRahaa(){
        kortti = new Maksukortti(300);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void KassanRahaEiMuutuKortillaOstaessaEdullisesti() {
        kortti = new Maksukortti(500);
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void KassanRahaEiMuutuKortillaOstaessaMaukkaasti() {
        kortti = new Maksukortti(500);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void RahaLiikkuuKorttiaLadatessa(){
        kortti = new Maksukortti(100);
        kassa.lataaRahaaKortille(kortti, 350);
        assertEquals(450, kortti.saldo());
        assertEquals(100350, kassa.kassassaRahaa());
    }
    
    @Test
    public void EiVoiLadataNegatiivistaMaaraaKortille() {
        kortti = new Maksukortti(100);
        kassa.lataaRahaaKortille(kortti, -50);
        assertEquals(100, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
