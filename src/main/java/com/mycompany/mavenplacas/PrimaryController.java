package com.mycompany.mavenplacas;

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
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class PrimaryController implements Initializable {

    boolean print;
    String catGroup[] = {"5001", "5002", "5003", "5005", "5006", "5007", "5008", "5009", "5010", "5011", "5014", "5015", "5016", "5017", "5018", "5016", "5017", "5018", "5019", "5030", "5049", "5050", "5059", "6001", "6002", "6003", "6004", "6005", "6006", "6007", "6008", "6009", "6010", "6011", "6012", "6013", "6014", "6015", "6016", "6017", "6018", "6019", "6020", "6022", "6024", "6025", "6030", "6034", "6040", "6042", "6045", "7007"};
    String catBrand[] = {"22", "89", "91", "94", "95", "96", "97"};
    String catStyle[] = {"-", "+", ".", " ", "_", "■"};
    String catNumAdul[] = {"34", "36", "38", "40", "42", "44", "46", "48", "50", "52", "54", "56", "58"};
    String catNumMalha[] = {"PP", "P", "M", "G", "GG", "EG", "EGG", "XGG", "XXG"};
    String catNumBaby[] = {"P", "M", "G", "GG", "EG"};
    String catNumKids[] = {"1", "2", "3", "4", "6", "8", "10", "12", "14", "16", "18"};
    String catNumYoung[] = {"12", "14", "16", "18"};

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
    private void cbBrandListener() {
        switch (cbBrand.getValue()) {
            case "94": {
                cbSmallSize.getItems().setAll(catNumBaby);
                cbLargeSize.getItems().setAll(catNumBaby);
                break;
            }
            case "95": {
                cbSmallSize.getItems().setAll(catNumKids);
                cbLargeSize.getItems().setAll(catNumKids);
                break;
            }
            case "96": {
                cbSmallSize.getItems().setAll(catNumYoung);
                cbLargeSize.getItems().setAll(catNumYoung);
                break;
            }
            default: {
                if ("22".equals(cbBrand.getValue()) || "97".equals(cbBrand.getValue()) || "89".equals(cbBrand.getValue())) {
                    cbGroupListener();
                }
            }
        }
        setDefault();
    }

    @FXML
    public void cbGroupListener() {
        if (!"5001".equals(cbGroup.getValue()) /*|| !"5002".equals(cbGroup.getValue()) || !"6001".equals(cbGroup.getValue()) || !"6002".equals(cbGroup.getValue()) || !"6003".equals(cbGroup.getValue()) || !"6004".equals(cbGroup.getValue()) || !"6022".equals(cbGroup.getValue())*/) {
            cbSmallSize.getItems().setAll(catNumMalha);
            cbLargeSize.getItems().setAll(catNumMalha);
        } else {
            cbSmallSize.getItems().setAll(catNumAdul);
            cbLargeSize.getItems().setAll(catNumAdul);
        }
//        if (cbBrand.getValue() != null) {
//            cbBrandListener();
//        }
        setDefault();
    }

    public void setDefault() {
        cbSmallSize.getSelectionModel().selectFirst();
        cbLargeSize.getSelectionModel().selectLast();
    }

    public void emptyErrorMensage() {
        Alert errorMensage = new Alert(AlertType.ERROR);
        errorMensage.setTitle("Erro!");
        errorMensage.setHeaderText("Existem Campo Vazios!");
        errorMensage.setContentText("Por favor, preencha todos os campos");
        errorMensage.showAndWait();
    }

    public void printer() throws IOException {
        if (cbStyle.getValue() == null) {
            cbStyle.getSelectionModel().selectFirst();
        }
//        if (cbGroup.getValue() == null || cbBrand.getValue() == null || txtModel.getText() == null || txtModel.getText() == null || cbSmallSize.getValue() == null || cbLargeSize.getValue() == null) {
//            emptyErrorMensage();
//        }
        Exporter document = new Exporter(cbGroup.getValue(), cbStyle.getValue(), cbBrand.getValue(), txtModel.getText(), cbSmallSize.getValue(), cbLargeSize.getValue(), print);
        document.fileFormater();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbGroup.getItems().setAll(catGroup);
        cbGroup.setEditable(true);
        cbBrand.getItems().setAll(catBrand);
        cbBrand.setEditable(true);
        cbStyle.getItems().setAll(catStyle);
        cbStyle.setEditable(true);
        cbSmallSize.getItems().setAll(catNumAdul);
        cbLargeSize.getItems().setAll(catNumAdul);
    }
}
