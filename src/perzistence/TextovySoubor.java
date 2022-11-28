/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perzistence;

import abstroPackage.AbstrTable;
import druhPamatek.Gps;
import druhPamatek.Zamek;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;


/**
 *
 * @author ludek
 */
public class TextovySoubor {

    public TextovySoubor() {
    }
    
    public void ulozSoubor(String nazev, AbstrTable seznam) throws IOException {
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(nazev));
        try {
            Iterator iterator = seznam.iterator(enums.Enum.eTypProhl.HLOUBKY);
            
            while(iterator.hasNext()){
                Zamek zamek = (Zamek) iterator.next();
                bw.write(zamek.getId() + ";" + zamek.getGps() + ";" + zamek.getNazevPamatky());
                bw.write("\n");
            }
            
            System.out.println("Soubor byl úspěšně uložen!");
        } catch (Exception e) {
            System.out.println("Při ukládání souboru nastala chyba!");
        }
        
        bw.close();
    }
    
    public void nactiSoubor(String nazev, AbstrTable seznam, enums.Enum.druhKlice klic) throws FileNotFoundException, IOException{
        
        BufferedReader br = new BufferedReader(new FileReader(nazev));
        try {
            String line;
            while((line = br.readLine()) != null){
                
                String gpsX = "";
                String gpsY = "";
                String name = "";
                
                for (int i = 19; i < 31; i++) {
                    if(line.charAt(i) == ' ' && line.charAt(i+1) == ' '){
                        break;
                    }
                    gpsX = gpsX + line.charAt(i);
                }
                
                for (int i = 31; i < 43; i++) {
                    if(line.charAt(i) == ' ' && line.charAt(i+1) == ' '){
                        break;
                    }
                    gpsY = gpsY + line.charAt(i);
                }
                
                for (int i = 69; i < 89; i++) {
                    if(line.charAt(i) == ' ' && line.charAt(i+1) == ' '){
                        break;
                    }
                    name = name + line.charAt(i);
                }
                
                Zamek zamek = new Zamek(name, new Gps(gpsX, gpsY));
                
                switch(klic){
                    case GPS:
                        seznam.vloz(zamek.getGps(), zamek);
                        break;
                    case NAZEV:
                        seznam.vloz(zamek.getNazevPamatky(), zamek);
                        break;
                }
                
            }  
            
            System.out.println("Soubor byl úspěšně načten!");
            
            
        } catch (Exception e) {
            System.out.println("Soubor nebylo možné načíst!");
        }
        
        
        
        br.close();
        
    }
    
    
}
