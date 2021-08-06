
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import static javafx.scene.paint.Color.WHITE;
import javafx.stage.Stage;

public class Juego extends Application{
    private GraphicsContext graficos;
    private Group root;
    private Scene escena,escena2;
    private Canvas lienzo;
    private Stage ventanaJuego;
    private Menu menu;
    private Pacman pacman;
    private Fantasma fantasma;
    private Fantasma fantasma2;
    private Fantasma fantasma3;
    private Fantasma fantasma4;
    public static HashMap<String,Image>imagenes;
    public ArrayList<Tile>tiles;
    public ArrayList<Tile>esfera;
    public int [][]tilemap={
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,2,2,2,2,2,2,2,2,2,1,2,2,1,1,2,2,2,1,2,2,2,2,2,2,1,2,2,2,1},
        {1,2,1,1,1,1,1,2,2,1,1,1,2,1,1,2,2,2,1,2,2,1,1,1,2,1,2,2,2,1},
        {1,2,1,2,2,2,2,2,2,2,2,2,2,1,1,2,1,2,2,2,2,2,1,2,2,2,2,1,2,1},
        {1,2,1,2,2,1,1,1,1,1,1,1,2,2,2,2,1,2,2,1,1,1,1,1,1,2,2,1,2,1},
        {1,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,1,2,2,2,2,1,2,1},
        {1,2,1,1,2,2,1,1,2,2,1,1,1,1,1,2,1,2,2,1,2,2,1,2,2,1,1,1,2,1},
        {1,2,2,2,2,2,2,1,2,2,2,2,2,2,2,2,2,2,2,1,2,2,2,2,2,2,2,2,2,1},
        {1,2,2,2,2,2,2,1,2,2,1,1,2,0,0,1,1,1,2,1,2,1,2,2,2,2,2,2,0,1},
        {1,1,1,1,1,2,2,1,2,2,2,1,2,1,0,0,0,1,2,2,2,1,2,2,2,2,1,1,1,1},
        {1,1,1,1,1,2,2,2,2,2,2,1,2,1,0,0,0,1,1,2,2,1,1,1,2,2,1,1,1,1},
        {1,2,2,2,2,2,2,2,2,2,2,1,2,1,1,1,2,2,2,2,2,1,2,2,2,2,2,2,2,1},
        {1,2,2,2,2,2,2,1,1,1,2,2,2,2,2,1,2,1,1,2,2,1,2,2,2,2,2,2,2,1},
        {1,2,2,2,1,2,2,2,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,2,2,1},
        {1,2,2,2,1,2,2,2,2,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,2,2,1},
        {1,2,1,1,1,1,1,2,2,2,2,1,2,2,1,1,1,2,2,1,1,2,2,1,1,1,1,1,2,1},
        {1,2,2,2,1,2,2,2,2,2,2,1,2,2,2,1,2,2,2,1,2,2,2,2,2,1,2,2,2,1},
        {1,2,2,2,1,2,2,2,2,1,1,1,2,2,2,1,2,2,2,1,2,1,1,2,2,1,2,2,2,1},
        {1,2,2,2,2,2,1,1,2,2,2,2,2,2,1,1,1,2,2,1,2,2,2,2,2,2,2,2,2,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };   
    public static void main(String[] args) {
        launch(args);
    }
    
    public Juego(){
        ventanaJuego = new Stage();
        escena2();
    }
    
    public Stage getVentana(){
        return ventanaJuego;
    }
    
    @Override
    public void start(Stage ventana) throws Exception {
 
        desplegarMenu();
        
    }
    public void escena2(){
        inicializarComponentes();
        gestionEventos();
        cicloJuego();
        
        //pasa a la siguiente escena -> escena3
        
    }  
    public void desplegarMenu(){
        Stage ventanaMenu = new Stage();
        menu = new Menu();
        ventanaMenu = menu.getVentana();
        menu.menu(ventanaMenu);
        ventanaMenu.show();
    }
    
    public void cicloJuego(){
        long tiempoInicial= System.nanoTime();
        AnimationTimer animationTimer= new AnimationTimer(){
            //este metodo se ejecuta aproximadamente 60 veces por seg
            @Override
            public void handle(long tiempoActual) {
                double t=(tiempoActual-tiempoInicial)/1000000000.0;
                actualizarEstado(t);
                pintar();
            }
        };
        animationTimer.start();
    }
   
    public void actualizarEstado(double t){
        pacman.verificarColision(esfera);
        pacman.verificarColision(tiles);
        pacman.calcularFrame(t);
        pacman.mover(tiles,esfera);
        fantasma.calcularFrame(t);
        fantasma.mover(tiles,esfera);
        fantasma.verificarColision(tiles);
        fantasma2.calcularFrame(t);
        fantasma2.mover(tiles,esfera);
        fantasma2.verificarColision(tiles);
        fantasma3.calcularFrame(t);
        fantasma3.mover(tiles,esfera);
        fantasma3.verificarColision(tiles);
        fantasma4.calcularFrame(t);
        fantasma4.mover(tiles,esfera);
        fantasma4.verificarColision(tiles);
        pacman.verificarColisionesFantasmas(fantasma,fantasma2,fantasma3,fantasma4);
        if(pacman.verificarColisionesFantasmas(fantasma,fantasma2,fantasma3,fantasma4) == true && pacman.getVidas() > 0){
            //esta linea de cambio de animacion no funciona pero si se respetan los 3 segundos del try:
            pacman.setAnimacionActual("morir");
            try{
                Thread.sleep(3000);
            } 
            catch(InterruptedException e){
            }
            reinicio();
        }
        if(pacman.verificarColisionesFantasmas(fantasma,fantasma2,fantasma3,fantasma4) == true && pacman.getVidas() == 0){
            pacman.verificarColisionesFantasmas(fantasma,fantasma2,fantasma3,fantasma4);
        }
    }
    public void inicializarComponentes(){
        imagenes= new HashMap<String,Image>();
        cargarImagenes();
        pacman=new Pacman(838,238,0,"pacman.png","izquierda", 3);
        fantasma=new Fantasma(420,270,"fantasma.png","rojo");
        fantasma2 =new Fantasma(450,270,"fantasma.png","celeste");
        fantasma3 =new Fantasma(480,270,"fantasma.png","amarillo");
        fantasma4 =new Fantasma(480,270,"fantasma.png","rosado");
        inicializarTiles();
        
        root = new Group();
        escena2= new Scene(root,900,630);
        
        lienzo = new Canvas(900,630);
        root.getChildren().add(lienzo);
        graficos= lienzo.getGraphicsContext2D();
        ventanaJuego.setTitle("Pac-man");
        ventanaJuego.setScene(escena2);
        
    }    
    
    public void inicializarTiles(){
        tiles= new ArrayList<Tile>();
        esfera=new ArrayList<Tile>();
        for(int i=0;i<tilemap.length;i++){
            for(int j=0;j<tilemap[i].length;j++){
                if(tilemap[i][j]==1){
                   
                    this.tiles.add(new Tile(tilemap[i][j],j*30,i*30,"tile.png",28,28,100));
                        
                }
                if(tilemap[i][j]==2){
                    this.esfera.add(new Tile(tilemap[i][j],j*30,i*30,"tile.png",28,28,100));
                    
                }
            }
        }
    }
    
    public void cargarImagenes(){
        imagenes.put("tile.png",new Image("imagenes/tile.png"));
        imagenes.put("fantasma.png",new Image("imagenes/animaFantasma.png"));
        imagenes.put("pacman.png",new Image("imagenes/paraAnimacion1.png"));
    }
    
    public void pintar(){
        graficos.fillRect(0, 0, 900, 630);
        for(int i=0;i<tiles.size();i++){
            
                tiles.get(i).pintar(graficos);
               
        }
        for(int j=0;j<esfera.size();j++){
             esfera.get(j).pintarEsfera(graficos);
        }
        
        pacman.pintar(graficos);
        fantasma.pintar(graficos);
        fantasma2.pintar(graficos);
        fantasma3.pintar(graficos);
        fantasma4.pintar(graficos);
        graficos.setStroke(Color.RED);
        graficos.strokeText("S c o r e :  "+pacman.getPuntos(), 20, 615);
        
    }
  
    public void gestionEventos(){
     
        escena2.setOnKeyPressed(new EventHandler<KeyEvent>(){
           @Override
           public void handle(KeyEvent evento) {
               if(evento.getCode().toString()=="RIGHT"){
                   pacman.setAnimacionActual("derecha");
                   pacman.derecha=true;
                   pacman.arriba=false;
                   pacman.abajo=false;
                   pacman.izquierda=false;
                    
                 
                    
               }
               if(evento.getCode().toString()=="LEFT"){
                   pacman.setAnimacionActual("izquierda");
                   pacman.izquierda=true;
                   pacman.arriba=false;
                   pacman.abajo=false;
                   pacman.derecha=false;
                    
                   
               }
               if(evento.getCode().toString()=="UP"){
                   pacman.setAnimacionActual("arriba");
                   pacman.arriba=true; 
                   pacman.abajo=false;
                   pacman.derecha=false;
                   pacman.izquierda=false;
                   
                   
               }
               if(evento.getCode().toString()=="DOWN"){
                   pacman.setAnimacionActual("abajo");
                   pacman.abajo=true;
                   pacman.derecha=false;
                   pacman.izquierda=false;
                   pacman.arriba=false;
                   
                   
               }  
           }
           
       
        });
        
        escena2.setOnKeyReleased(new EventHandler<KeyEvent>(){
           
           @Override
           public void handle(KeyEvent evento) {
               if(evento.getCode().toString()=="RIGHT"){
                   pacman.arriba=false;
                   pacman.abajo=false;
                   pacman.izquierda=false;
                   pacman.derecha=true;
               }
               if(evento.getCode().toString()=="LEFT"){
                   pacman.arriba=false;
                   pacman.abajo=false;
                   pacman.derecha=false;
                   pacman.izquierda=true;
               }
               if(evento.getCode().toString()=="UP"){
                   pacman.abajo=false;
                   pacman.derecha=false;
                   pacman.izquierda=false;
                   pacman.arriba=true;
               }
               if(evento.getCode().toString()=="DOWN"){
                   pacman.derecha=false;
                   pacman.izquierda=false;
                   pacman.arriba=false;
                   pacman.abajo=true;
               }    
           }   
        });
        
    }
    public  void reinicio(){
        
        pacman=new Pacman(838,238,0,"pacman.png","izquierda", pacman.getVidas() - 1);
        fantasma=new Fantasma(420,270,"fantasma.png","rojo");
        fantasma2 =new Fantasma(450,270,"fantasma.png","celeste");
        fantasma3 =new Fantasma(480,270,"fantasma.png","amarillo");
        fantasma4 =new Fantasma(480,270,"fantasma.png","rosado");
    }
}
