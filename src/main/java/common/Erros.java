package common;

import java.awt.Toolkit;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;

/**
 *
 * @author Acme
 */
public class Erros {

    private String header, content, style;
    private int type;
    private Alert mensage;

    public Erros(int type, String header, String content, String style) {
        this.type = type;
        this.header = header;
        this.content = content;
        this.style = style;
    }

    public void errorMensage() {
        if (type == 1) {
            mensage = new Alert(Alert.AlertType.INFORMATION);
        } else if (type == 2) {
            mensage = new Alert(Alert.AlertType.ERROR);
        }
        DialogPane dialog = mensage.getDialogPane();
        mensage.initStyle(StageStyle.UNDECORATED);
        dialog.getStylesheets().add(getClass().getResource("errorstyle.css").toExternalForm());
        dialog.getStyleClass().add(style);
        mensage.setHeaderText(header);
        mensage.setContentText(content);
        Toolkit.getDefaultToolkit().beep();
        mensage.showAndWait();
    }
}
