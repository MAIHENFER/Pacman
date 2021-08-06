
 

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;


public abstract class Personaje extends ElementosJuego {
    protected HashMap<String,Animacion>animaciones;
    protected String animacionActual;
    protected int velocidad=1;
    
    public Personaje(int x, int y, String nombreImagen,String animacionActual) {
        super(x, y, nombreImagen);
        this.animacionActual=animacionActual;
    }
    
    @Override
    public abstract void pintar(GraphicsContext graficos);
    
    @Override
    public abstract Rectangle obtenerRectangulo();
    
    public abstract void calcularFrame(double t);
    
    public abstract boolean verificarColision(ArrayList<Tile> tile);
    
    public abstract void inicializarAnimaciones();
    
    public abstract void mover(ArrayList<Tile>tile,ArrayList<Tile>tile2);

    public String getAnimacionActual() {
        return animacionActual;
    }

    public void setAnimacionActual(String animacionActual) {
        this.animacionActual = animacionActual;
    }
}