/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package abstroPackage;

import druhPamatek.Gps;
import druhPamatek.Zamek;
import java.util.Iterator;

/**
 *
 * @author ludek
 */
public interface IAbstrHeap<T extends Comparable<T>>{
    
    T[] vybuduj(T[] zadanePole);
    
    void prebuduj(T[] zadanePole, int pozice, int i);
    
    void zrus();
    
    boolean jePrazdny();
    
    void vloz(T prvek);
    
    T odeberMax();
    
    T zpristupniMax();
    
    Iterator iterator();
    
}
