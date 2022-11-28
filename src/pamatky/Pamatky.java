/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pamatky;

import abstroPackage.AbstrTable;
import abstroPackage.AbstroDoubleList;
import druhPamatek.Zamek;
import enums.Enum;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import perzistence.TextovySoubor;

/**
 *
 * @author ludek
 */
public class Pamatky<K> implements IPamatky{

    private AbstrTable tree = new AbstrTable();
    private Enum.druhKlice druhKlice = Enum.druhKlice.GPS;
    
    @Override
    public void importDatZTXT() {
        try {
            
            TextovySoubor textSoubor = new TextovySoubor();
            
            textSoubor.nactiSoubor("data.txt", tree, druhKlice);
        } catch (IOException ex) {
            Logger.getLogger(Pamatky.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void vlozZamek(Zamek zamek) {
        if(druhKlice == Enum.druhKlice.GPS){
            tree.vloz(zamek.getGps(), zamek);
        }else{
            tree.vloz(zamek.getNazevPamatky(), zamek);
        }
    }
    
    @Override
    public Zamek najdiZamek(String klic) {
        return (Zamek) tree.najdi(klic);
    }

    @Override
    public Zamek odeberZamek(String klic) {
        return (Zamek) tree.odeber(klic);
    }

    @Override
    public void zrus() {
        tree.zrus();
    }

    @Override
    public void prebuduj() {
        
        Iterator ite = tree.iterator(Enum.eTypProhl.SIRKY);
        ArrayList<Zamek> arrayListZamek = new ArrayList<>();
        ArrayList<String> arrayListString = new ArrayList<>();
         
        switch(druhKlice){
            case GPS:
                ite = tree.iterator(Enum.eTypProhl.SIRKY);
                while(ite.hasNext()){
                    Zamek zamek = (Zamek) ite.next();
                    arrayListZamek.add(zamek);
                }
                arrayListZamek.sort(new Comparator<Zamek>() {
                    @Override
                    public int compare(Zamek o1, Zamek o2) {
                        return o1.getGps().compareTo(o2.getGps());
                    }
                });
                for (int i = 0; i < arrayListZamek.size(); i++) {
                    arrayListString.add(arrayListZamek.get(i).getGps());
                }
                tree.zrus();
                tree.createBalancedTree(arrayListString, arrayListZamek, 0, arrayListZamek.size() - 1);
                break;
            case NAZEV:
                ite = tree.iterator(Enum.eTypProhl.SIRKY);
                while(ite.hasNext()){
                    Zamek zamek = (Zamek) ite.next();
                    arrayListZamek.add(zamek);
                }
                arrayListZamek.sort(new Comparator<Zamek>() {
                    @Override
                    public int compare(Zamek o1, Zamek o2) {
                        return o1.getNazevPamatky().compareTo(o2.getNazevPamatky());
                    }
                });
                for (int i = 0; i < arrayListZamek.size(); i++) {
                    arrayListString.add(arrayListZamek.get(i).getNazevPamatky());
                }
                
                tree.zrus();
                tree.createBalancedTree(arrayListString, arrayListZamek, 0, arrayListZamek.size() - 1);
                break;
        }
    }

    @Override
    public void nastavKlic(Enum.druhKlice typ) {
        druhKlice = typ;
        prebuduj();
    }

    @Override
    public Zamek najdiNejbliz(String klic) {
        
        Zamek nalezenyZamek = (Zamek) tree.najdi(klic);
        Iterator iterator = tree.iterator(Enum.eTypProhl.HLOUBKY);
        if(tree.jePrazdny()){
            return null;
        }
        Zamek nejblizsi = null;
        if(iterator.hasNext()){
            nejblizsi = (Zamek) iterator.next();
        }
        int vzdalenost = 10000;
        while(iterator.hasNext()){
            if(druhKlice == Enum.druhKlice.GPS){
                Zamek current = (Zamek) iterator.next();
                if(nalezenyZamek != current){
                    if(vzdalenost > Math.abs(nejblizsi.getGps().compareTo(current.getGps()))){
                    vzdalenost = Math.abs(nejblizsi.getGps().compareTo(current.getGps()));
                    nejblizsi = current;
                }
                }
                
            }else{
                Zamek current = (Zamek) iterator.next();
                if(nalezenyZamek != current){
                    if(vzdalenost > Math.abs(nejblizsi.getNazevPamatky().compareTo(current.getNazevPamatky()))){
                        vzdalenost = Math.abs(nejblizsi.getNazevPamatky().compareTo(current.getNazevPamatky()));
                        nejblizsi = current;
                    }
                }
            }
            
        }
        
        return nejblizsi;
        
    }

    @Override
    public Iterator iterator(Enum.eTypProhl typ) {
        return tree.iterator(typ);
    }
    
    public AbstrTable getTree(){
        return tree;
    }
    
    public void setTree(AbstrTable tree){
        this.tree = tree;
    }
    
    public Enum.druhKlice getDruhKlice(){
        return druhKlice;
    }
}
