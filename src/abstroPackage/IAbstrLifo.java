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
public interface IAbstrLifo<T> {
    
    void zrus();
    
    boolean jePrazdny();
    
    void vlozData(T data);
    
    T odeber();
    
    Iterator iterator();
}
