   
 

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Pacman extends Personaje {
    private int puntos;
    public boolean derecha;
    public boolean izquierda;
    public boolean arriba;
    public boolean abajo;
    public int vidas;
    public Pacman( int x, int y,int puntos, String nombreImagen,String animacionActual, int vidas) {
        super(x, y, nombreImagen,animacionActual);
        this.puntos = puntos;
        this.animaciones=new HashMap<String,Animacion>();
        this.vidas = vidas;
        inicializarAnimaciones();
    }
    
    public int getVidas(){
        return vidas;
    }
    
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    @Override
    public void inicializarAnimaciones(){
        Rectangle coordenadasDerecha[]={
            new Rectangle(66,0,30,30),
            new Rectangle(33,0,30,30)
            
        };
        Animacion animacionDerecha=new Animacion(0.2,coordenadasDerecha);
        animaciones.put("derecha", animacionDerecha);
        
        Rectangle coordenadasIzquierda[]={
            new Rectangle(0,0,30,30),
            new Rectangle(33,0,30,30)
        };
        Animacion animacionIzquierda=new Animacion(0.2,coordenadasIzquierda);
        animaciones.put("izquierda",animacionIzquierda);
        
        Rectangle coordenadasArriba[]={
            new Rectangle(130,0,30,30),
            new Rectangle(33,0,30,30)
        };
        Animacion animacionArriba=new Animacion(0.2,coordenadasArriba);
        animaciones.put("arriba",animacionArriba);
        
        Rectangle coordenadasAbajo[]={
            new Rectangle(97,0,30,30),
            new Rectangle(33,0,30,30)
        };
        Animacion animacionAbajo=new Animacion(0.2,coordenadasAbajo);
        animaciones.put("abajo",animacionAbajo);
        
        Rectangle coordeandasMuerte[]={
            new Rectangle(0,33,30,30),
            new Rectangle(33,33,30,30),
            new Rectangle(63,33,30,30),
            new Rectangle(96,33,30,30),
            new Rectangle(130,33,30,30),
            new Rectangle(96,33,30,30)
        };
        Animacion animacionMorir=new Animacion(0.2,coordeandasMuerte);
        animaciones.put("morir",animacionMorir);
        
    }
    @Override
    public void calcularFrame(double t){
        Rectangle coordenadas=animaciones.get(animacionActual).calcularFrameActual(t);
        this.xImagen= (int)coordenadas.getX();
        this.yImagen=(int)coordenadas.getY();
        this.alto= (int)coordenadas.getWidth();
        this.ancho=(int)coordenadas.getHeight();
    }
    public Rectangle obtenerRectangulo(){
        return new Rectangle(x, y, ancho, alto);
    }
    @Override
    public boolean verificarColision(ArrayList<Tile> tile){
        boolean res=false;
         for(int i=0;i<tile.size();i++){
            if(this.obtenerRectangulo().getBoundsInLocal().intersects(tile.get(i).obtenerRectangulo().getBoundsInLocal())){
                res=true;
            }
        }
        return res;
    }
    public boolean verificarColisionEsfera(ArrayList<Tile>tile){
        boolean res=false;
        for(int i=0;i<tile.size();i++){
            if(!tile.get(i).isEsferaCapturada() && this.obtenerRectangulo().getBoundsInLocal().intersects(tile.get(i).ObtenerRectanguloEsfera().getBoundsInLocal())){
                res=true;
                this.puntos+= tile.get(i).getCantidadPuntos();
                tile.get(i).setEsferaCapturada(true);
            }
        }
        return res;
    }
    
    public boolean verificarColisionesFantasmas(Fantasma fant1,Fantasma fant2,Fantasma fant3,Fantasma fant4){
        boolean res = false;
        if(this.obtenerRectangulo().getBoundsInLocal().intersects(fant1.obtenerRectangulo().getBoundsInLocal())
           || this.obtenerRectangulo().getBoundsInLocal().intersects(fant2.obtenerRectangulo().getBoundsInLocal())
           || this.obtenerRectangulo().getBoundsInLocal().intersects(fant3.obtenerRectangulo().getBoundsInLocal())
           || this.obtenerRectangulo().getBoundsInLocal().intersects(fant4.obtenerRectangulo().getBoundsInLocal())){
            abajo = false;
            arriba = false;
            izquierda = false;
            derecha = false;
            setAnimacionActual("morir");
            fant1.detener();
            fant2.detener();
            fant3.detener();
            fant4.detener();
            res = true;
        }
        return res;
    }
    
    @Override
    public void pintar(GraphicsContext graficos){
        graficos.drawImage(Juego.imagenes.get(nombreImagen),xImagen,yImagen,ancho,alto,x,y,ancho,alto);
    }
    
    @Override
    public void mover(ArrayList<Tile>tile, ArrayList<Tile>tile2){
        if(this.derecha){
            if(this.verificarColision(tile)!=true){
                x+=velocidad;
                if(this.verificarColisionEsfera(tile2)==true){
                    x+=velocidad;
                }
                }else{
                    derecha=false;
                    x-=velocidad;            
            }
        }
        if(this.izquierda){
            if(this.verificarColision(tile)!=true && this.verificarColisionEsfera(tile2)!=true){
                x-=velocidad;
                if(this.verificarColisionEsfera(tile2)==true){
                    x-=velocidad;
                }
            }else{
                izquierda=false;  
                x+=velocidad;
            }
        }
        if(this.arriba){
            if(this.verificarColision(tile)!=true && this.verificarColisionEsfera(tile2)!=true){
                y-=velocidad;
                if(this.verificarColisionEsfera(tile2)==true){
                    y-=velocidad;
                }
            }else{
                arriba=false;
                y+=velocidad;
            }
        }
        if(this.abajo){
            if(this.verificarColision(tile)!=true && this.verificarColisionEsfera(tile2)!=true){
                y+=velocidad;
                if(this.verificarColisionEsfera(tile2)==true){
                    y+=velocidad;
                }
            }else{
                abajo=false;
                y-=velocidad;
            }
        }
    }
}