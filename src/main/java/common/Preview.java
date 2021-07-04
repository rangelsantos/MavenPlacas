package common;

import com.dansoftware.pdfdisplayer.PDFDisplayer;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

/**
 *
 * @author Acme
 */
public class Preview extends Application {

    private PDFDisplayer pdfView = new PDFDisplayer();

//    @FXML
//    VBox vbCampo = new VBox();
    
    @FXML
    Button btnNext = new Button();

    @FXML
    Button btnBack = new Button();

    @FXML
    Label lblPagina;

    public static void main(String[] args) {
        launch(args);
    }

    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
            if (e.getSource() == btnNext) {
                pdfView.navigateByPage(pdfView.getActualPageNumber() + 1);
                lblPagina.setText("Pagina: " + pdfView.getActualPageNumber());
            } else if (e.getSource() == btnBack) {
                pdfView.navigateByPage(pdfView.getActualPageNumber() - 1);
                lblPagina.setText("Pagina: " + pdfView.getActualPageNumber());
            }
        }
    };

//    public void handle(ActionEvent e) {
//        if (e.getSource() == btnNext) {
//            pdfView.navigateByPage(pdfView.getActualPageNumber() + 1);
//            lblPagina.setText("Pagina: " + pdfView.getActualPageNumber());
//        } else if (e.getSource() == btnBack) {
//            pdfView.navigateByPage(pdfView.getActualPageNumber() - 1);
//            lblPagina.setText("Pagina: " + pdfView.getActualPageNumber());
//        }
//    }

    public void loadView() throws IOException {
        pdfView.setVisibilityOf("toolbarContainer", false);
        pdfView.loadPDF(new File("C:\\Users\\Acme\\Desktop\\novo.pdf"));
        pdfView.navigateByPage(1);
        //vbCampo.getChildren().add(pdfView.toNode());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("preview.fxml"));
        JMetro metro = new JMetro(pdfView.toNode(), Style.DARK);
        JMetro metro2 = new JMetro(root, Style.DARK);
        //Scene scene = new Scene(root);
        loadView();
        btnNext.setOnAction(buttonHandler);
        btnBack.setOnAction(buttonHandler);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        primaryStage.setScene(new Scene(new VBox(new StackPane(root), pdfView.toNode())));
        primaryStage.setTitle("Visualizar - Projeto Placas");
        primaryStage.show();
    }
}
