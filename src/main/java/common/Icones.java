package common;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Acme
 */
public class Icones {

    private int tamanho;
    private Image icon;

    public Icones(String arquivo, int tamanho) {
        this.tamanho = tamanho;
        this.icon = new Image(getClass().getResourceAsStream(arquivo));
    }

    public ImageView getIcon() {
        ImageView image = new ImageView(icon);
        image.setFitHeight(tamanho);
        image.setPreserveRatio(true);
        return image;
    }
}
