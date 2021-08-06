
 

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;


public abstract class ElementosJuego{
    protected int x;
    protected int y;
    protected  String nombreImagen ;
    protected int ancho;
    protected int alto;
    protected int xImagen;
    protected int yImagen;
    public ElementosJuego(int x, int y, String nombreImagen) {
        this.x = x;
        this.y = y;
        this.nombreImagen = nombreImagen;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }
    
    public abstract void pintar(GraphicsContext graficos);
    
    public abstract Rectangle obtenerRectangulo();
}
