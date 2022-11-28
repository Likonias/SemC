/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package abstroPackage;

import java.util.Iterator;

/**
 *
 * @author ludek
 */
public interface IAbstrTable<K extends Comparable<K>, V>{
    
    void zrus();
    
    boolean jePrazdny();
    
    V najdi(K key);
    
    void vloz(K key, V value);
    
    V odeber(K key);
    
    Iterator iterator(enums.Enum.eTypProhl typ);
    
}
