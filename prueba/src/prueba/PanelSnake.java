package snake_proyectopoo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class PanelSnake extends JPanel {
    Color colorSnake = new Color(200,150,200);
    Color colorComida = new Color(200,200,100);
    int tammax,tam,cantidad,sobrante;
    List<int[]> snake= new ArrayList<>();
    int[] comida= new int[2];
    String proxDireccion="der";
    String direccion = "der";
    
    Thread hilo;
    Andar andar;
    
    public PanelSnake(int tammax,int can){
        this.tammax= tammax;
        this.cantidad= can;
        this.tam= tammax/can;  
        this.sobrante= tammax%can;
        int[] a ={can/2-1,can/2-1};
        int[] b ={can/2,can/2-1};
        this.snake.add(a);
        this.snake.add(b);
        generarComida();
        andar= new Andar(this);
        hilo = new Thread(andar);
        hilo.start();
    }
    @Override
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(colorSnake);
        for(int[] par:snake){
            pintor.fillRect(sobrante/2+par[0]*tam, sobrante/2+par[1]*tam, tam-2, tam-2);
        }
        pintor.setColor(colorComida);
        pintor.fillRect(sobrante/2+comida[0]*tam, sobrante/2+comida[1]*tam, tam-2, tam-2);
    }
    public void avanzar(){
        igualar();
        int[] cabeza = snake.get(snake.size()-1);
        int addX =0;
        int addY =0;
        switch(this.direccion){
            case "der": addX=1;break;
            case "izq": addX=-1;break;
            case "arr": addY=-1;break;
            case "aba": addY=1;break;
        }
        int[] nuevo={Math.floorMod(cabeza[0]+addX,cantidad),Math.floorMod(cabeza[1]+addY,cantidad)};
        boolean existe = false;
        for(int i=0;i<snake.size();i++){
            if(nuevo[0]==snake.get(i)[0]&&nuevo[1]==snake.get(i)[1]){
                existe=true;
                break;
            }
        }
        if(existe){
            JOptionPane.showMessageDialog(this,"Haz perdidio");
            //añadir que pasa despues de morir
        }
        else{
            if(nuevo[0]==comida[0]&&nuevo[1]==comida[1]){
                snake.add(nuevo);
                generarComida();
            }
            else{
                snake.add(nuevo);
                snake.remove(0);
            }
        }
        
    }
    public void generarComida(){
        boolean existe = false;
        int a= (int) (Math.random()*cantidad);
        int b= (int) (Math.random()*cantidad);
        for(int[] par:snake){
            if(a==par[0]&&b==par[1]){
                existe= true;
                generarComida();
                break;
            }
        }
        if(!existe){
            this.comida[0]=a;
            this.comida[1]=b;
        }
    }
    public void cambiarDireccion(String dir){
        if((this.direccion.equals("der")||this.direccion.equals("izq"))&&(dir.equals("arr")||dir.equals("aba"))){
            this.proxDireccion= dir; 
        }
        if((this.direccion.equals("arr")||this.direccion.equals("aba"))&&(dir.equals("der")||dir.equals("izq"))){
            this.proxDireccion= dir; 
            System.out.print(dir);
        }
    }
    public void igualar(){
        this.direccion= this.proxDireccion;
    }
}
