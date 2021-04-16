package main;

import common.Icones;
import common.Listas;
import export.Exporter;
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

/**
 * JavaFX App, created by Rangel Santos
 */
public class PrimaryController implements Initializable {
    //booleano que controla a opção de impressão ou salvar
    private boolean print;
    //booleano que vai indicar a opção por uso dos tamanhos adcionais
    private boolean set = true;
    //instancia o objeto com os arrays de tamanhos, tambem são usados na classe exporter
    public Listas lista = new Listas();

    //Controles da interface FXML
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
    
    //botao de salvar, seta o print como false e chama o printer
    @FXML
    private void getSave(ActionEvent ae) throws IOException {
        print = false;
        printer();
    }
    
    //botao de imprimir, seta o print como true e chama o printer
    @FXML
    private void getPrint(ActionEvent ae) throws IOException {
        print = true;
        printer();
    }
    
    /*Listener do combobox cbBrand (seta a marca), baseado na seleção 
    atual ele muda o array que é passado para os comboboxes de tamanho*/
    @FXML
    private void cbBrandListener() throws IOException {
        switch (cbBrand.getValue()) {
            /*caso a seleção seja o 91, ele carrega os comboboxes com os devidos arrays e
            seta booleano set como true e passa como parametro para a funcao 'showKids', 
            que torna os campos adicionais visiveis*/
            case "91": {
                cbGroupListener();
                cbKidsSmallSize.getItems().setAll(lista.getCatNumKids());
                cbKidsSmallSize.getSelectionModel().selectFirst();
                cbKidsLargeSize.getItems().setAll(lista.getCatNumKids());
                cbKidsLargeSize.getSelectionModel().selectLast();
                set = false;
                showKids(set);
                break;
            }
            /*caso a seleção seja o 94, 95 ou 96 ele carrega os comboboxes com os devidos arrays e
            seta booleano set como false e passa como parametro para a funcao 'showKids', 
            que torna os campos adicionais invisiveis*/
            case "94": {
                cbSmallSize.getItems().setAll(lista.getCatNumBaby());
                cbLargeSize.getItems().setAll(lista.getCatNumBaby());
                set = true;
                showKids(set);
                break;
            }
            case "95": {
                cbSmallSize.getItems().setAll(lista.getCatNumKids());
                cbLargeSize.getItems().setAll(lista.getCatNumKids());
                set = true;
                showKids(set);
                break;
            }
            case "96": {
                cbSmallSize.getItems().setAll(lista.getCatNumYoung());
                cbLargeSize.getItems().setAll(lista.getCatNumYoung());
                set = true;
                showKids(set);
                break;
            }
            default: {
                /*caso a seleção seja uma marca de tamanhos adultos, ele chama a 
                função 'cbGroupListener' que seta dos tamanhos adultos, em seguida seta o 
                booleano set como false e passa como parametro para a funcao 'showKids', 
                que torna os campos adicionais invisiveis*/
                if ("22".equals(cbBrand.getValue()) || "97".equals(cbBrand.getValue()) || "89".equals(cbBrand.getValue())) {
                    cbGroupListener();
                    set = true;
                    showKids(set);
                }
            }
        }
        setDefault();
    }

    /*listener do combobox cbGroup, controla os tamanhos que seram setados nos comboxes de tamanhos pricipais*/
    @FXML
    public void cbGroupListener() {
        //caso não exista uma marca selecionada, seleciona a primeira opção
        if (cbBrand.getValue() == null) {
            cbBrand.getSelectionModel().selectFirst();
        }
        /*caso a marca selecionada seja infantil 'break'!, os tamanhos não seram alterados
        porque não 'OR'? porque nao funcionou, and i don't know why...*/
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
                /*chama a funcao setNumAdul, que seta os tamanhos de 34 a 58 e P a GG nos comboxes de tamanhos pricipais
                caso entre no 'default', seta os tamanhos de malha nos comboxes de tamanhos pricipais
                porque não 'OR'? porque nao funcionou, and i don't know why...*/
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
                        cbSmallSize.getItems().setAll(lista.getCatNumMalha());
                        cbLargeSize.getItems().setAll(lista.getCatNumMalha());
                    }
                }
            }
            setDefault();
        }
    }
    
    //seta os tamanhos de 34 a 58 e P a GG nos comboxes de tamanhos pricipais
    public void setNumAdul() {
        cbSmallSize.getItems().setAll(lista.getCatNumAdul());
        cbLargeSize.getItems().setAll(lista.getCatNumAdul());
    }
    
    /*seta o cbSmallSize (combobox pricipal do tamanho menor) na primeira opção
    seta o cbLargeSize (combobox pricipal do tamanho menor) na ultima opção*/
    public void setDefault() {
        cbSmallSize.getSelectionModel().selectFirst();
        cbLargeSize.getSelectionModel().selectLast();
    }
    
    /*controla se os comboboxes de tamanhos infantis, que valem apenas para a marca '91'
    visiveis ou não, baseado no booleano 'set'*/
    public void showKids(boolean set) {
        cbKidsSmallSize.setDisable(set);
        cbKidsLargeSize.setDisable(set);
    }
    
    //mostra um dialogo de atencao caso o usuario selecione salvar ou imprimir com algum campo sem valor
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
    
    /*mostra um dialogo de erro caso o usuario selecione salvar ou imprimir 
    com opçoes reversas de tamanhos selecionadas nos respectivos campos*/
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
    
    //carrega os icones usados na interface
    public void loadIcon() {
        btnSave.setGraphic(new Icones("save_white.png", 40).getIcon());
        btnPrint.setGraphic(new Icones("print_white.png", 40).getIcon());
        iconImage.setGraphic(new Icones("icon.png", 20).getIcon());
    }
    
    /*valida se todos os campos padrao tem valores, se o booleano 'set' for true, valida os campos de tamanhos exclusivos da marca '91'
    e instancia um novo 'Exporter' passando os parametros adcionais 'cbKidsSmallSize', 'cbKidsSmallSize', 'set' para o construtor*/
    public void printer() throws IOException {
        //caso o sinal não tenha sido selecionado, seleciona a primeira opçao
        if (cbStyle.getValue() == null) {
            cbStyle.getSelectionModel().selectFirst();
        }
        //porque não 'OR'? porque nao funcionou, and i don't know why...
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
            //caso 'set' seja true entra na validacao para o construtor com parametros adcionais
        } else if (!set) {
            /*valida os campos de tamanhos exclusivos da marca '91'
            e instancia um novo 'Exporter' passando os parametros adcionais 'cbKidsSmallSize', 'cbKidsSmallSize', 'set' para o construtor
            porque não 'OR'? porque nao funcionou, and i don't know why...*/
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
            //valida os tamanhos e instancia o objeto passando os parametros
        } else if (cbSmallSize.getSelectionModel().getSelectedIndex() > cbLargeSize.getSelectionModel().getSelectedIndex()) {
            sizeErrorMensage();
        } else {
            Exporter document = new Exporter(cbGroup.getValue(), cbStyle.getValue(), cbBrand.getValue(), txtModel.getText(), cbSmallSize.getValue(), cbLargeSize.getValue(), print);
            document.fileFormater();
        }
    }
    
    //fecha a janela e encerra o programa
    @FXML
    public void buttonClose(ActionEvent event) {
        Stage stage = (Stage) closeWindow.getScene().getWindow();
        stage.close();
    }
    
    //minimiza o programa
    @FXML
    public void buttonMinimize(ActionEvent event) {
        Stage stage = (Stage) minimizeWindow.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //carrega o icone
        loadIcon();
        //carrega os arrays com as opçoes padrao para os comboboxes
        cbGroup.getItems().setAll(lista.getCatGroup());
        cbGroup.setEditable(true);
        cbBrand.getItems().setAll(lista.getCatBrand());
        cbBrand.setEditable(true);
        cbStyle.getItems().setAll(lista.getCatStyle());
        cbStyle.setEditable(true);
        cbSmallSize.getItems().setAll(lista.getCatNumAdul());
        cbLargeSize.getItems().setAll(lista.getCatNumAdul());
        //seta os tamanhos da marca '91' como invisiveis (padrao)
        showKids(set);
    }
}