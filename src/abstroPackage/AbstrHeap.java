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
    int velikost;

    private boolean jePlny(){return pole[pole.length - 1] != null;}
    
    @Override
    public T[] vybuduj(T[] zadanePole) {
        //(T[])new Object[pole.length];  nove pole genericky
        
        pole = zadanePole;
        velikost = pole.length;
        
        for(int i = velikost / 2 - 1; i >= 0; i--){
            prebuduj(pole, velikost, i);
        }
        
        for(int i = velikost - 1; i > 0; i--){
            T pomocna = pole[0];
            pole[0] = pole[i];
            pole[i] = pomocna;
            
            prebuduj(pole, i, 0);
        }
        
        return this.pole;
        
    }

    @Override
    public void prebuduj(T[] zadanePole, int pozice, int i) {
    
        int next = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if(left < pozice && pole[left].compareTo(pole[next]) > 0){
            next = left;
        }
        
        if(right < pozice && pole[right].compareTo(pole[next]) > 0){
            next = right;
        }
        
        if(next != i){
            T pomocna = pole[i];
            pole[i] = pole[next];
            pole[next] = pomocna;
            
            prebuduj(pole, pozice, next);
        }
        
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
        T[] pomocnePole = (T[])new Object[pole.length-1];
        
        T odebranyPrvek = pole[0];
        
        for (int i = 0; i < pomocnePole.length; i++) {
            pomocnePole[i] = pole[i+1];
        }
        
        pole = pomocnePole;
        
        return odebranyPrvek;
        
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
