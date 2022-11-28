/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package abstroPackage;


import druhPamatek.Gps;
import druhPamatek.Zamek;
import java.util.ArrayList;
import java.util.Iterator;
import pamatky.Pamatky;

/**
 *
 * @author ludek
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
            
        AbstrTable table = new AbstrTable();
        
        table.vloz(30, "30");
        table.vloz(10, "10");
        table.vloz(20, "20");
        table.vloz(22, "22");
        table.vloz(15, "15");
        table.vloz(24, "24");
        table.vloz(5, "5");
        table.vloz(6, "6");
        table.vloz(21, "21");
        table.vloz(0, "0");
        table.vloz(16, "16");
        
        
        
        System.out.println("výpis dat");
        
        Iterator ite = table.iterator(enums.Enum.eTypProhl.HLOUBKY);
        
        while(ite.hasNext()){
            System.out.println(ite.next());
        }
        
//        ArrayList<String> arrayListString = new ArrayList<>();
//        ArrayList<Integer> arrayListInteger = new ArrayList<>();
//        
//        
//        System.out.println("hloubka");
//        
//        while(ite.hasNext()){
//            System.out.println(ite.next());
//        }
//        System.out.println("");
//        System.out.println("");
//        arrayListString.add("-120");
//        arrayListString.add("-100");
//        arrayListString.add("-96");
//        arrayListString.add("-93");
//        arrayListString.add("-90");
//        arrayListString.add("-80");
//        arrayListString.add("-40");
//        arrayListString.add("0");
//        
//        arrayListInteger.add(-120);
//        arrayListInteger.add(-100);
//        arrayListInteger.add(-96);
//        arrayListInteger.add(-93);
//        arrayListInteger.add(-90);
//        arrayListInteger.add(-80);
//        arrayListInteger.add(-40);
//        arrayListInteger.add(0);
//        
        
        
//        table.vloz(28, "topení");
//        table.vloz(2, "město");
//        table.vloz(1, "kočka");
//        table.vloz(8, "pes");
//        table.vloz(4, "lev");
//        table.vloz(26, "moře");
//        table.vloz(16, "mobil");
//        table.vloz(20, "krabice");
//        table.vloz(41, "kůň");
//        table.vloz(39, "déšť");
//        table.vloz(60, "kolo");
//        table.vloz(71, "rám");
//        table.vloz(65, "postel");
//        Iterator iter2 = table.iterator(enums.Enum.eTypProhl.HLOUBKY);
//        Iterator iterator = table.iterator(enums.Enum.eTypProhl.SIRKY);
//        System.out.println(zamek.getGps());
//        while(iter2.hasNext()){
//            System.out.println(iter2.next());
//        }
//        Gps gps = new Gps("123", "456");
//        
//        table.odeber(gps.getGps().toString());
//        
        
//        System.out.println("\n\ndalší sirky:\n\n");
//        
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
//        
        
    
    }
    
}
