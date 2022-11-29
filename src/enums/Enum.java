/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author ludek
 */
public class Enum {
    
    public enum Pozice{
        PRVNI,
        POSLEDNI,
        AKTUALNI,
        PREDCHUDCE,
        NASLEDNIK;
    }
    
    public enum Reorg{
        DEKOMPOZICE,
        AGREGACE;
    }
    
    public enum eTypProhl{
        SIRKY,
        HLOUBKY;
    }
    
    public enum druhKlice{
        GPS,
        NAZEV,
        VZDALENOST;
    }
}
