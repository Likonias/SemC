/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generator;

import druhPamatek.Gps;
import druhPamatek.Zamek;



/**
 *
 * @author ludek
 */
public class Generator {
    
    int nahodnaData;

    public Zamek generuj(){
        
        return new Zamek(("Zamek" + nahodneCislo(50)), new Gps(Integer.toString(nahodneCislo(50)) + " ", Integer.toString(nahodneCislo(50))));
        
    }
    
    
    
    
    private int nahodneCislo(int cislo){
        
        return (int) (Math.random() * cislo);
        
    }
    
    
    
    
}
