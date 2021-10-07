
package snake_proyectopoo;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class Panel extends JPanel {
    Color colorFondo = new Color(100,200,200);
    int tammax,tam,cantidad,sobrante;
    
    public Panel(int tammax,int can){
        this.tammax= tammax;
        this.cantidad= can;
        this.tam= tammax/can;  
        this.sobrante= tammax%can;
    }
    @Override
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(colorFondo);
        for(int i=0;i<cantidad;i++){
            for(int j=0;j<cantidad;j++){
                pintor.fillRect(sobrante/2+i*tam, sobrante/2+j*tam, tam-2, tam-2);
            }
        }
    }
}
