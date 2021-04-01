package com.mycompany.mavenplacas;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrimaryController implements Initializable {

    boolean print;
    boolean set = false;
    public static String catGroup[] = {"5001", "5002", "5003", "5005", "5006", "5007", "5008", "5009", "5010", "5011", "5014", "5015", "5016", "5017", "5018", "5016", "5017", "5018", "5019", "5030", "5049", "5050", "5059", "6001", "6002", "6003", "6004", "6005", "6006", "6007", "6008", "6009", "6010", "6011", "6012", "6013", "6014", "6015", "6016", "6017", "6018", "6019", "6020", "6022", "6024", "6025", "6030", "6034", "6040", "6042", "6045", "7007"};
    public static String catBrand[] = {"22", "89", "91", "94", "95", "96", "97"};
    public static String catStyle[] = {"-", "+", "*", ".", " ", "_", "■"};
    public static String catNumAdul[] = {"34", "36", "38", "40", "42", "44", "46", "48", "50", "52", "54", "56", "58", "P", "M", "G", "GG"};
    public static String catNumMalha[] = {"PP", "P", "M", "G", "GG", "EG", "EGG", "XGG", "XXG"};
    public static String catNumBaby[] = {"P", "M", "G", "GG", "EG"};
    public static String catNumKids[] = {"1", "2", "3", "4", "6", "8", "10", "12", "14", "16", "18"};
    public static String catNumYoung[] = {"12", "14", "16", "18"};
    
    @FXML
    private Label iconImage = new Label();
    
    @FXML
    public Button closeWindow;
    
    @FXML
    public Button minimizeWindow;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnPrint;

    @FXML
    private TextField txtModel;

    @FXML
    private ComboBox<String> cbGroup;

    @FXML
    private ComboBox<String> cbBrand;

    @FXML
    private ComboBox<String> cbSmallSize;

    @FXML
    private ComboBox<String> cbLargeSize;

    @FXML
    private ComboBox<String> cbStyle;

    @FXML
    private ComboBox<String> cbKidsSmallSize;

    @FXML
    private ComboBox<String> cbKidsLargeSize;

    @FXML
    private void getSave(ActionEvent ae) throws IOException {
        print = false;
        printer();
    }

    @FXML
    private void getPrint(ActionEvent ae) throws IOException {
        print = true;
        printer();
    }

    @FXML
    private void cbBrandListener() throws IOException {
        switch (cbBrand.getValue()) {
            case "91": {
                cbGroupListener();
                cbKidsSmallSize.getItems().setAll(catNumKids);
                cbKidsSmallSize.getSelectionModel().selectFirst();
                cbKidsLargeSize.getItems().setAll(catNumKids);
                cbKidsLargeSize.getSelectionModel().selectLast();
                set = true;
                showKids(set);
                break;
            }
            case "94": {
                cbSmallSize.getItems().setAll(catNumBaby);
                cbLargeSize.getItems().setAll(catNumBaby);
                set = false;
                showKids(set);
                break;
            }
            case "95": {
                cbSmallSize.getItems().setAll(catNumKids);
                cbLargeSize.getItems().setAll(catNumKids);
                set = false;
                showKids(set);
                break;
            }
            case "96": {
                cbSmallSize.getItems().setAll(catNumYoung);
                cbLargeSize.getItems().setAll(catNumYoung);
                set = false;
                showKids(set);
                break;
            }
            default: {
                if ("22".equals(cbBrand.getValue()) || "97".equals(cbBrand.getValue()) || "89".equals(cbBrand.getValue())) {
                    cbGroupListener();
                    set = false;
                    showKids(set);
                }
            }
        }
        setDefault();
    }

    @FXML
    public void cbGroupListener() {
        if (cbBrand.getValue() == null) {
            cbBrand.getSelectionModel().selectFirst();
        }
        switch (cbBrand.getValue()) {
            case "94": {
                break;
            }
            case "95": {
                break;
            }
            case "96": {
                break;
            }
            default: {
                switch (cbGroup.getValue()) {
                    case "5001": {
                        setNumAdul();
                        break;
                    }
                    case "5002": {
                        setNumAdul();
                        break;
                    }
                    case "6001": {
                        setNumAdul();
                        break;
                    }
                    case "6002": {
                        setNumAdul();
                        break;
                    }
                    case "6003": {
                        setNumAdul();
                        break;
                    }
                    case "6004": {
                        setNumAdul();
                        break;
                    }
                    case "6022": {
                        setNumAdul();
                        break;
                    }
                    default: {
                        cbSmallSize.getItems().setAll(catNumMalha);
                        cbLargeSize.getItems().setAll(catNumMalha);
                    }
                }
            }
            setDefault();
        }
    }

    public void setNumAdul() {
        cbSmallSize.getItems().setAll(catNumAdul);
        cbLargeSize.getItems().setAll(catNumAdul);
    }

    public void setDefault() {
        cbSmallSize.getSelectionModel().selectFirst();
        cbLargeSize.getSelectionModel().selectLast();
    }

    public void showKids(boolean set) {
        cbKidsSmallSize.setVisible(set);
        cbKidsLargeSize.setVisible(set);
    }

    public void emptyErrorMensage() {
        Alert errorMensage = new Alert(AlertType.INFORMATION);
        DialogPane errorDialog = errorMensage.getDialogPane();
        errorMensage.initStyle(StageStyle.UNDECORATED);
        errorDialog.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        errorDialog.getStyleClass().add("emptyDialog");
        errorMensage.setHeaderText("Existem Campos Vazios!");
        errorMensage.setContentText("Por favor, preencha todos os campos");
        Toolkit.getDefaultToolkit().beep();
        errorMensage.showAndWait();
    }
    
    public void sizeErrorMensage() {
        Alert errorMensage = new Alert(AlertType.ERROR);
        DialogPane errorDialog = errorMensage.getDialogPane();
        errorMensage.initStyle(StageStyle.UNDECORATED);
        errorDialog.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        errorDialog.getStyleClass().add("sizeDialog");
        errorMensage.setHeaderText("Tamanhos Invalidos!");
        errorMensage.setContentText("O tamanho menor selecionado é maior que o tamanho maior selecionado");
        Toolkit.getDefaultToolkit().beep();
        errorMensage.showAndWait();
    }
    
    public void loadIcon(){
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        ImageView image = new ImageView(icon);
        image.setFitHeight(20);
        image.setPreserveRatio(true);
        iconImage.setGraphic(image);
    }

    public void printer() throws IOException {
        if (cbStyle.getValue() == null) {
            cbStyle.getSelectionModel().selectFirst();
        }
        if (cbGroup.getValue() == null) {
            emptyErrorMensage();
        } else if (cbBrand.getValue() == null) {
            emptyErrorMensage();
        } else if (txtModel.getText().equals("")) {
            emptyErrorMensage();
        } else if (cbSmallSize.getValue() == null) {
            emptyErrorMensage();
        } else if (cbLargeSize.getValue() == null) {
            emptyErrorMensage();
        } else if (set) {
            if (cbKidsSmallSize.getValue() == null) {
                emptyErrorMensage();
            } else if (cbKidsLargeSize.getValue() == null) {
                emptyErrorMensage();
            } else if (cbKidsSmallSize.getSelectionModel().getSelectedIndex() > cbKidsLargeSize.getSelectionModel().getSelectedIndex()) {
                sizeErrorMensage();
            } else if (cbSmallSize.getSelectionModel().getSelectedIndex() > cbLargeSize.getSelectionModel().getSelectedIndex()) {
                sizeErrorMensage();
            } else {
                Exporter document = new Exporter(cbGroup.getValue(), cbStyle.getValue(), cbBrand.getValue(), txtModel.getText(), cbKidsSmallSize.getValue(), cbKidsLargeSize.getValue(), cbSmallSize.getValue(), cbLargeSize.getValue(), print, set);
                document.fileFormater();
            }
        } else if (cbSmallSize.getSelectionModel().getSelectedIndex() > cbLargeSize.getSelectionModel().getSelectedIndex()) {
            sizeErrorMensage();
        } else {
            Exporter document = new Exporter(cbGroup.getValue(), cbStyle.getValue(), cbBrand.getValue(), txtModel.getText(), cbSmallSize.getValue(), cbLargeSize.getValue(), print);
            document.fileFormater();
        }
    }
    
    @FXML
    public void buttonClose(ActionEvent event) {
        Stage stage = (Stage) closeWindow.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void buttonMinimize(ActionEvent event) {
        Stage stage = (Stage) minimizeWindow.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadIcon();
        cbGroup.getItems().setAll(catGroup);
        cbGroup.setEditable(true);
        cbBrand.getItems().setAll(catBrand);
        cbBrand.setEditable(true);
        cbStyle.getItems().setAll(catStyle);
        cbStyle.setEditable(true);
        cbSmallSize.getItems().setAll(catNumAdul);
        cbLargeSize.getItems().setAll(catNumAdul);
        showKids(set);
    }
}
