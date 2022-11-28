/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package druhPamatek;

/**
 *
 * @author ludek
 */
public class Gps implements Comparable<Gps>{
    
    private String x;
    private String y;
    
    private Double xNum;
    private Double yNum;

    private Double vzdalenost;
    
    public Gps(String x, String y) {
        this.x = x;
        this.y = y;
        calculateNumValue();
    }

    private void calculateNumValue(){
        
        String xDegrees = x.substring(1, 3);
        String yDegrees = y.substring(2, 4);
        String xMinutes = x.substring(4, 11);
        String yMinutes = y.substring(5, 12);
    
        xNum = Double.parseDouble(xDegrees) + Double.parseDouble(xMinutes)/60;
        yNum = Double.parseDouble(yDegrees) + Double.parseDouble(yMinutes)/60;
        
    }
    
    public String getGps(){
        return x + y;
    }

    public Double getxNum() {
        return xNum;
    }

    public Double getyNum() {
        return yNum;
    }
    
    public Double getVzdalenostOd(Gps gps){
        
        final Double polomerZeme = 6.37100;
        
        Double vzdalenost = null;
        
        Double num1 = yNum * Math.PI / 180;
        Double num2 = gps.yNum * Math.PI / 180;
        Double num3 = (gps.yNum - yNum) * Math.PI / 180;
        Double num4 = (gps.xNum - xNum) * Math.PI / 180;
        
        Double a = Math.sin(num3/2) * Math.sin(num3/2) + Math.cos(num1) * Math.cos(num2) * Math.sin(num4/2) * Math.sin(num4/2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        
        this.vzdalenost = polomerZeme * c * 1000;
        
        return this.vzdalenost;
    }

    public Double getVzdalenost() {
        return vzdalenost;
    }
    
    @Override
    public int compareTo(Gps o) {
        return this.getGps().compareTo(o.getGps());
    }

}
