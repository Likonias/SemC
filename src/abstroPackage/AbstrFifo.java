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
public class AbstrFifo<T> implements IAbstrLifo<T>{
    
    AbstroDoubleList<T> abstroDoubleList = new AbstroDoubleList<>();
    
    @Override
    public void zrus() {
        abstroDoubleList.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return abstroDoubleList.jePrazdny();
    }

    @Override
    public void vlozData(T data) {
        abstroDoubleList.vlozPrvni(data);
    }

    @Override
    public T odeber() {
        return abstroDoubleList.odeberPrvni();
    }

    @Override
    public Iterator iterator() {
        return abstroDoubleList.iterator();
    }
    
}
