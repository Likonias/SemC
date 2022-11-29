/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package druhPamatek;

/**
 *
 * @author ludek
 */
public class Zamek implements Comparable<Zamek>{
    
    private static int idStatic = 1;
    private String nazevPamatky;
    private Gps gps;
    private int id;

    public Zamek(String nazevPamatky, Gps gps) {
        id = idStatic++;
        this.nazevPamatky = nazevPamatky;
        this.gps = gps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazevPamatky() {
        return nazevPamatky;
    }

    public void setNazevPamatky(String nazevPamatky) {
        this.nazevPamatky = nazevPamatky;
    }

    public String getGps() {
        return gps.getGps();
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }
    
    public Gps getGpsGps(){
        return gps;
    }

    @Override
    public String toString() {
        return "Zamek{" + "id=" + id + ", nazevPamatky=" + nazevPamatky + ", gps=" + gps.getGps() + '}' + gps.getVzdalenost();
    }

    @Override
    public int compareTo(Zamek o) {
        return this.gps.getVzdalenost().compareTo(o.gps.getVzdalenost());
    }
    
}
