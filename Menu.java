


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class Menu
{
    private Juego juego;
    private Stage ventana;
    
    public Menu(){
        juego = new Juego();
        ventana = new Stage();
    }
        
    public Stage getVentana(){
        return ventana;
    }

    public void menu(Stage ventana){
        this.ventana = ventana;
        Image imagen = new Image("imagenes/pacmanlogo.png");
        BackgroundImage fondo = new BackgroundImage(imagen,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                BackgroundSize.DEFAULT);
        Background bg = new Background(fondo);
        
        VBox vb = new VBox(40);
        vb.setBackground(bg);
        vb.setAlignment(Pos.CENTER);
        
        
        Button b1 = new Button("Comenzar");
        b1.setBackground(new Background(new BackgroundImage(new Image("imagenes/boton2.png"),
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.CENTER,
                                        BackgroundSize.DEFAULT)));
        b1.setBorder(Border.EMPTY);
        b1.setTextFill(Color.YELLOW);
        b1.setOnAction(e -> {
            juego.getVentana().show();
            ventana.close();
        });
        vb.getChildren().add(b1);
 
        Scene escena = new Scene(vb,640,480);
        ventana.setScene(escena);
        ventana.setTitle("Menu");
        
        
    }
}





