/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abstroPackage;

import java.util.Iterator;

/**
 *
 * @author ludek
 */
public class AbstrHeap<T extends Comparable<T>> implements IAbstrHeap<T>{

    T[] pole;
    int pozice = -1;

    private boolean jePlny(){return pozice == pole.length -1;}
    
    @Override
    public void vybuduj(T[] pole) {
        //(T[])new Object[pole.length];  nove pole genericky
        this.pole = pole;
    }

    @Override
    public T[] prebuduj() {
    
        vybuduj(pole);
        
        return pole;
        
    }

    @Override
    public void zrus() {pole = null;}
    
    @Override
    public boolean jePrazdny() {
        
        if(pole == null){
            return true;
        }
        
        if(pole[0] == null){
            return true;
        }
    
        return false;
    
    }

    @Override
    public void vloz(T prvek) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T odeberMax() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T zpristupniMax() {
        if(jePrazdny()){
            return null;
        }
        return pole[0];
    }

    @Override
    public Iterator iterator() {
        
        Iterator iterator = new Iterator() {

            int aktualni = -1;
            
            @Override
            public boolean hasNext() {
                
                if(!jePrazdny()){
                    aktualni++;
                    if(pole[aktualni] != null){
                        return true;
                    }
                }
                
                return false;
            }

            @Override
            public T next() {
                
                return pole[aktualni];
            
            }

        };
        
        return iterator;
        
    }
    
}
