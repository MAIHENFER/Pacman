
 

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Fantasma extends Personaje {
    private String direccion="derecha";
    
    public Fantasma(int x, int y, String nombreImagen,String animacionActual) {
        super(x, y, nombreImagen,animacionActual);
        this.animaciones=new HashMap<String,Animacion>();
        
        inicializarAnimaciones();
    }
    
    @Override
    public void inicializarAnimaciones(){
        
        Rectangle fantasmaRojo[]={
            new Rectangle(125,1,30,30),
            new Rectangle(156,1,30,30),
            new Rectangle(187,1,30,30),
            new Rectangle(218,1,30,30),
            new Rectangle(63,1,30,30),
            new Rectangle(94,1,30,30),
            new Rectangle(1,1,30,30),
            new Rectangle(32,1,30,30)
            
            
        };
        Animacion rojo=new Animacion(0.3,fantasmaRojo);
        animaciones.put("rojo", rojo);
        
        Rectangle fantasmaCeleste[]={
            new Rectangle(125,32,30,30),
            new Rectangle(156,32,30,30),
            new Rectangle(187,32,30,30),
            new Rectangle(218,32,30,30),
            new Rectangle(63,32,30,30),
            new Rectangle(94,32,30,30),
            new Rectangle(1,32,30,30),
            new Rectangle(32,32,30,30)
        };
        Animacion celeste=new Animacion(0.3,fantasmaCeleste);
        animaciones.put("celeste", celeste);
        
        Rectangle fantasmaAmarillo[]={
            new Rectangle(125,63,30,30),
            new Rectangle(156,63,30,30),
            new Rectangle(187,63,30,30),
            new Rectangle(218,63,30,30),
            new Rectangle(63,63,30,30),
            new Rectangle(94,63,30,30),
            new Rectangle(1,63,30,30),
            new Rectangle(32,63,30,30)
        };
        Animacion amarillo=new Animacion(0.3,fantasmaAmarillo);
        animaciones.put("amarillo", amarillo);
        
        Rectangle fantasmaRosado[]={
            new Rectangle(125,94,30,30),
            new Rectangle(156,94,30,30),
            new Rectangle(187,94,30,30),
            new Rectangle(218,94,30,30),
            new Rectangle(63,94,30,30),
            new Rectangle(94,94,30,30),
            new Rectangle(1,94,30,30),
            new Rectangle(32,94,30,30)
            
        };
        Animacion rosado=new Animacion(0.3,fantasmaRosado);
        animaciones.put("rosado", rosado);
        
        
    }

    @Override
    public void pintar(GraphicsContext graficos) {
        graficos.drawImage(Juego.imagenes.get(nombreImagen),xImagen,yImagen,ancho,alto,x,y,ancho,alto);
    }
    @Override
    public void calcularFrame(double t){
        Rectangle coordenadas=animaciones.get(animacionActual).calcularFrameActual(t);
        this.xImagen= (int)coordenadas.getX();
        this.yImagen=(int)coordenadas.getY();
        this.alto= (int)coordenadas.getWidth();
        this.ancho=(int)coordenadas.getHeight();
    
    }
    @Override
    public Rectangle obtenerRectangulo() {
        return new Rectangle(x, y, ancho, alto);
    }

    @Override
    public boolean verificarColision(ArrayList<Tile> tile) {
        boolean res=false;
         for(int i=0;i<tile.size();i++){
            if(this.obtenerRectangulo().getBoundsInLocal().intersects(tile.get(i).obtenerRectangulo().getBoundsInLocal())){
                
                res=true;   
            }
        }
         return res;
    }
    
    public void detener(){
        velocidad=0;
    }
    
    public int numAleatorio(){
        int num=(int)(Math.random()*4);
        return num;
    }
      public int verificarDireccionFantasma(int num){
          if(num==0){
              
              x+=velocidad;
              direccion="derecha";
          }if(num==1){
              
              x-=velocidad;
              direccion="izquierda";
          }if(num==2){
              
              y-=velocidad;
              direccion="arriba";
          }if(num==3){
             
              y+=velocidad;
              direccion="abajo";
          }
          
          return num;
          
    }
    
   @Override
    public void mover(ArrayList<Tile>tile,ArrayList<Tile>tile2){
        
        if(direccion == "derecha" && verificarColision(tile)!=true){
            x += velocidad;
        }
        if(direccion == "derecha" && verificarColision(tile)==true){
            x -= velocidad;
            if(verificarColision(tile)!=true){
                this.verificarDireccionFantasma(numAleatorio());
            }
        }
        if(direccion == "izquierda" && verificarColision(tile)!=true){
            x -= velocidad;
        }
        if(direccion == "izquierda" && verificarColision(tile)==true){
            x += velocidad;
            if(verificarColision(tile)!=true){
                this.verificarDireccionFantasma(numAleatorio());
            }
        }
        if(direccion == "abajo" && verificarColision(tile)!=true){
            y += velocidad;
        }
        if(direccion == "abajo" && verificarColision(tile)==true){
            y -= velocidad;
            if(verificarColision(tile)!=true){
                this.verificarDireccionFantasma(numAleatorio());
            }
        }
        if(direccion == "arriba" && verificarColision(tile)!=true){
            y -= velocidad;
        }
        if(direccion == "arriba" && verificarColision(tile)==true){
            y += velocidad;
            if(verificarColision(tile)!=true){
                this.verificarDireccionFantasma(numAleatorio());
            }
        }
    }
}
        
    
    
    
    

