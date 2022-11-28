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
public interface IAbstrDoubleList<T> extends Iterable<T> {
    
    void zrus();
    
    boolean jePrazdny();
    
    void vlozPrvni (T data);
    
    void vlozPosledni (T data);
    
    void vlozNaslednika (T data);
    
    void vlozPredchudce (T data);
    
    T zpristupniAktualni();
    
    T zpristupniPrvni();
    
    T zpristupniPosledni();
    
    T zpristupniNaslednika();
    
    T zpristupniPredchudce();
    
    T odeberAktualni();
    
    T odeberPrvni();
    
    T odeberPosledni();
    
    T odeberNaslednika();
    
    T odeberPredchudce();
    
    Iterator<T> iterator();
    
}
