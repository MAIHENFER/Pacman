
 

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends ElementosJuego {
    public int tipoTile;
    private int cantidadPuntos;
    private boolean esferaCapturada=false;
    public Tile(int tipoTile,int x, int y, String nombreImagen,int ancho,int alto,int puntos) {
        super(x, y, nombreImagen);
        this.cantidadPuntos=puntos;
        this.alto=alto;
        this.ancho=ancho;
        
        
        if(tipoTile==1){
            this.xImagen=0;
            this.yImagen=0;
                
        }if(tipoTile==2){
            this.xImagen=30;
                this.yImagen=0;
        }
    }       

    public boolean isEsferaCapturada() {
        return esferaCapturada;
    }

    public void setEsferaCapturada(boolean esferaCapturada) {
        this.esferaCapturada = esferaCapturada;
    }
    
    public int getCantidadPuntos() {
        return cantidadPuntos;
    }

    public void setCantidadPuntos(int cantidadPuntos) {
        this.cantidadPuntos = cantidadPuntos;
    }
    

    public int getxImagen() {
        return xImagen;
    }

    public void setxImagen(int xImagen) {
        this.xImagen = xImagen;
    }

    public int getyImagen() {
        return yImagen;
    }

    public void setyImagen(int yImagen) {
        this.yImagen = yImagen;
    }
    @Override
    public Rectangle obtenerRectangulo(){
        return new Rectangle(x, y, ancho, alto);
    }
    public Rectangle ObtenerRectanguloEsfera(){
        return new Rectangle(x+7, y+7, ancho-13, alto-13);
    }
    @Override
    public void pintar(GraphicsContext graficos) {
        
        graficos.drawImage(Juego.imagenes.get(nombreImagen), xImagen, 
                          yImagen, ancho, alto, x, y,ancho, alto);
           
        
    }
    public void pintarEsfera(GraphicsContext graficos){
        if(this.esferaCapturada){
            return;
        }else{
            graficos.drawImage(Juego.imagenes.get(nombreImagen), xImagen, 
                          yImagen, ancho, alto, x, y,ancho, alto);
            
        }
    }
    
}
