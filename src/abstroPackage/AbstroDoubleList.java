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
public class AbstroDoubleList<T> implements IAbstrDoubleList<T>{

    public class Prvek<T> {
        
        private T hodnotaPrvku;
        
        private Prvek<T> predchudce;
        private Prvek<T> naslednik;

        public Prvek(T hodnotaPrvku, Prvek<T> predchudce, Prvek<T> naslednik) {
            this.hodnotaPrvku = hodnotaPrvku;
            this.predchudce = predchudce;
            this.naslednik = naslednik;
        }

        public T getHodnotaPrvku() {
            return hodnotaPrvku;
        }

        public Prvek<T> getPredchudce() {
            return predchudce;
        }

        public Prvek<T> getNaslednik() {
            return naslednik;
        }

        public void setPredchudce(Prvek<T> predchudce) {
            this.predchudce = predchudce;
        }

        public void setNaslednik(Prvek<T> naslednik) {
            this.naslednik = naslednik;
        }
        
    }
    
    private Prvek<T> prvniPrvek;
    private Prvek<T> posledniPrvek;
    private Prvek<T> aktualniPrvek;
    
    //zruší celý seznam
    @Override
    public void zrus() {
        prvniPrvek = null;
        posledniPrvek = null;
        aktualniPrvek = null;
    }

    @Override
    public boolean jePrazdny() {return prvniPrvek == null;}

    //vlkádá prvek vždy na začátek seznamu
    @Override
    public void vlozPrvni(T data) {
        if(jePrazdny()){
            prvniPrvek = new Prvek<>(data, prvniPrvek, prvniPrvek);
            prvniPrvek.setNaslednik(prvniPrvek);
            prvniPrvek.setPredchudce(prvniPrvek);
            posledniPrvek = prvniPrvek;
        }else{
            Prvek<T> pomocnyPrvek = new Prvek<>(data, posledniPrvek, prvniPrvek);
            prvniPrvek.setPredchudce(pomocnyPrvek);
            prvniPrvek = pomocnyPrvek;
            posledniPrvek.setNaslednik(prvniPrvek);
        }
    }

    //vkládá prvek vždy na konec seznamu
    @Override
    public void vlozPosledni(T data) {
        if(jePrazdny()){
            prvniPrvek = new Prvek<>(data, prvniPrvek, prvniPrvek);
            prvniPrvek.setNaslednik(prvniPrvek);
            prvniPrvek.setPredchudce(prvniPrvek);
            posledniPrvek = prvniPrvek;
        }else{
            Prvek<T> pomocnyPrvek = new Prvek<>(data, posledniPrvek, prvniPrvek);
            posledniPrvek.setNaslednik(pomocnyPrvek);
            posledniPrvek = pomocnyPrvek;
            prvniPrvek.setPredchudce(posledniPrvek);
        }
    }
    
    //vlkádá prvek za aktuální, ten musí být předem stanoven
    @Override
    public void vlozNaslednika(T data) {
        if(aktualniPrvek != null){
            if(aktualniPrvek == posledniPrvek){
                vlozPosledni(data);
            }else{
                Prvek<T> zaAktualnim = aktualniPrvek.getNaslednik();
                Prvek<T> pomocnyPrvek = new Prvek<>(data, aktualniPrvek, zaAktualnim);
                aktualniPrvek.setNaslednik(pomocnyPrvek);
                zaAktualnim.setPredchudce(pomocnyPrvek);
            }
        }
    }
    
    //vlkádá prvek před aktuální, ten musí být předem stanoven
    @Override
    public void vlozPredchudce(T data) {
        if(aktualniPrvek != null){
            if(aktualniPrvek == prvniPrvek){
                vlozPrvni(data);
            }else{
                Prvek<T> predAktualnim = aktualniPrvek.getPredchudce();
                Prvek<T> pomocnyPrvek = new Prvek<>(data, predAktualnim, aktualniPrvek);
                aktualniPrvek.setPredchudce(pomocnyPrvek);
                predAktualnim.setNaslednik(pomocnyPrvek);
            }
        }
    }
    
    //zpřístupňuje aktuální prvek
    @Override
    public T zpristupniAktualni() {
        return aktualniPrvek.getHodnotaPrvku();
    }
    
    //nastaví aktuální prvek na první pozici v seznamu
    @Override
    public T zpristupniPrvni() {
        aktualniPrvek = prvniPrvek;
        return aktualniPrvek.getHodnotaPrvku();
    }

    //nastaví aktuální prvek na poslední pozici v seznamu
    @Override
    public T zpristupniPosledni() {
        aktualniPrvek = posledniPrvek;
        return aktualniPrvek.getHodnotaPrvku();
    }
    
    //posune aktuální prvek na následníka
    @Override
    public T zpristupniNaslednika() {
        aktualniPrvek = aktualniPrvek.getNaslednik();
        return aktualniPrvek.getNaslednik().hodnotaPrvku;
    }

    //posune aktuální prvek na předchůdce
    @Override
    public T zpristupniPredchudce() {
        aktualniPrvek = aktualniPrvek.getPredchudce();
        return aktualniPrvek.getPredchudce().getHodnotaPrvku();
    }
    
    //odebere aktuální prvek
    @Override
    public T odeberAktualni() {
        if(jePrazdny() || aktualniPrvek == null){
            return null;
        }
        Prvek<T> odebranyPrvek = aktualniPrvek;
        if(aktualniPrvek == prvniPrvek){
            return odeberPrvni();
        }
        if(aktualniPrvek == posledniPrvek){
            return odeberPosledni();
        }
        
        odebranyPrvek.predchudce.setNaslednik(odebranyPrvek.naslednik);
        odebranyPrvek.naslednik.setPredchudce(odebranyPrvek.predchudce);
        
        return odebranyPrvek.getHodnotaPrvku();
    }
    
    //odebere první prvek
    @Override
    public T odeberPrvni() {
        if(jePrazdny()){
            return null;
        }
        Prvek<T> odebranyPrvek = prvniPrvek;
        if(odebranyPrvek == prvniPrvek.getPredchudce() && odebranyPrvek == prvniPrvek.getNaslednik()){
            zrus();
        }else{
            prvniPrvek = prvniPrvek.getNaslednik();
            posledniPrvek.setNaslednik(prvniPrvek);
            prvniPrvek.setPredchudce(posledniPrvek);
            
        }
        return odebranyPrvek.getHodnotaPrvku();
    }

    //odebere poslední prvek
    @Override
    public T odeberPosledni() {
        if(jePrazdny()){
            return null;
        }
        Prvek<T> odebranyPrvek = posledniPrvek;
        if(odebranyPrvek == posledniPrvek.getPredchudce() && odebranyPrvek == posledniPrvek.getNaslednik()){
            zrus();
        }else{
            posledniPrvek = posledniPrvek.getPredchudce();
            posledniPrvek.setNaslednik(prvniPrvek);
        }
        return odebranyPrvek.getHodnotaPrvku();
    }

    //odebere následníka aktuálního prvku
    @Override
    public T odeberNaslednika() {
        if(jePrazdny() || aktualniPrvek == null){
            return null;
        }
        Prvek<T> odebranyPrvek = aktualniPrvek.getNaslednik();
        if(odebranyPrvek == posledniPrvek){
            return odeberPosledni();
        }
        if(odebranyPrvek == prvniPrvek){
            return odeberPrvni();
        }
        odebranyPrvek.getNaslednik().setPredchudce(odebranyPrvek.getPredchudce());
        odebranyPrvek.getPredchudce().setNaslednik(odebranyPrvek.getNaslednik());
        return odebranyPrvek.getHodnotaPrvku();
    }

    //odebere předchůdce aktuálního prvku
    @Override
    public T odeberPredchudce() {
        if(jePrazdny() || aktualniPrvek == null){
            return null;
        }
        Prvek<T> odebranyPrvek = aktualniPrvek.getPredchudce();
        if(odebranyPrvek == posledniPrvek){
            return odeberPosledni();
        }
        if(odebranyPrvek == prvniPrvek){
            return odeberPrvni();
        }
        odebranyPrvek.getNaslednik().setPredchudce(odebranyPrvek.getPredchudce());
        odebranyPrvek.getPredchudce().setNaslednik(odebranyPrvek.getNaslednik());
        return odebranyPrvek.getHodnotaPrvku();
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> prvek = new Iterator<T>() {
            
            boolean cyklus = false;
            Prvek<T> p = prvniPrvek;
            
            @Override
            public boolean hasNext() {
                if(jePrazdny()){
                    return false;
                }
                return !((p == prvniPrvek) && (cyklus == true));
            }
            
            @Override
            public T next() {
                if(hasNext()){
                    cyklus = true;
                    T data = p.hodnotaPrvku;
                    p = p.naslednik;
                    return data;
                }
                return null;
            }
        };
        return prvek;
    }
    
}
