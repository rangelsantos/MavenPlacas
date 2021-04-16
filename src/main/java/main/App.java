package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

/**
 * JavaFX App, created by Rangel Santos
 */
public class App extends Application {

    private static Scene scene;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws IOException {
        //Carrega o FXML
        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
        //Torna a janela arrastavel
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        //Aplica o JMetro ao root
        JMetro metro = new JMetro(root, Style.DARK);
        scene = new Scene(root);
        //Remove o frame de janela padrão do sistema
        stage.initStyle(StageStyle.UNDECORATED);
        //Torna o tamanho da janela fixo
        stage.setResizable(false);
        //Seta o titulo
        stage.setTitle("Placas (TESTE v0.9-b.2 FAT) - Rangel");
        //Carrega o icone
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        //Seta a cena e mostra a janela
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
