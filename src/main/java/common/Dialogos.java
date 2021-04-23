package common;

import java.awt.Toolkit;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Acme
 */
public class Dialogos {

    private String header, content, style;
    private int type;
    private Alert mensage;

    public Dialogos(int type, String header, String content, String style) {
        this.type = type;
        this.header = header;
        this.content = content;
        this.style = style;
    }

    public void dialogMensage() {
        switch (type) {
            case 1:
                mensage = new Alert(Alert.AlertType.INFORMATION);
                break;
            case 2:
                mensage = new Alert(Alert.AlertType.ERROR);
                break;
            case 3:
                mensage = new Alert(Alert.AlertType.INFORMATION);
                Image image = new Image(getClass().getResourceAsStream("icon.png"));
                ImageView imageView = new ImageView(image);
                Stage stage = (Stage) mensage.getDialogPane().getScene().getWindow();
                stage.getIcons().add(image);
                mensage.setGraphic(imageView);
                mensage.setTitle("Sobre");
                break;
            default:
                break;
        }
        DialogPane dialog = mensage.getDialogPane();
        if (type == 1 || type == 2){
        mensage.initStyle(StageStyle.UNDECORATED);
        Toolkit.getDefaultToolkit().beep();
        } else {
            mensage.initStyle(StageStyle.DECORATED);
        }
        dialog.getStylesheets().add(getClass().getResource("errorstyle.css").toExternalForm());
        dialog.getStyleClass().add(style);
        mensage.setHeaderText(header);
        mensage.setContentText(content);
        mensage.showAndWait();
    }
}
