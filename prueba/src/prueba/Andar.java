
package snake_proyectopoo;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Andar implements Runnable{
    
    private PanelSnake panel;
    boolean estado = true;
    
    public Andar(PanelSnake panel){
        this.panel=panel;
    }
    @Override
    public void run() {
        while(estado){
            this.panel.avanzar();
            this.panel.repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Andar.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }   
    }
    
    public void parar(){
        this.estado=false;
    }
    
}
