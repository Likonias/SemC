/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pamatky;

import druhPamatek.Zamek;
import java.util.Iterator;

/**
 *
 * @author ludek
 */
public interface IPamatky {
    
    void importDatZTXT();
    
    void vlozZamek(Zamek zamek);
    
    Zamek najdiZamek(String klic);
    
    Zamek odeberZamek(String klic);
    
    void zrus();
    
    void prebuduj();
    
    void nastavKlic(enums.Enum.druhKlice typ);
    
    Zamek najdiNejbliz(String klic);
    
    Iterator iterator(enums.Enum.eTypProhl typ);
    
}
