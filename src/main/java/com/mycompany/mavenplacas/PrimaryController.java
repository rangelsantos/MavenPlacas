package com.mycompany.mavenplacas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {
    
    @FXML
    private Button btnSave;
    
    @FXML
    private Button btnPrint;
    
    @FXML
    private TextField txtModel;
    
    @FXML
    private ComboBox<Categorias> cbGroup;
    private List<Categorias> catGroup = new ArrayList<>();
    private ObservableList<Categorias> obsCatGroup;
    
    @FXML
    private ComboBox<Categorias> cbBrand;
    private List<Categorias> catBrand = new ArrayList<>();
    private ObservableList<Categorias> obsCatBrand;
    
    @FXML
    private ComboBox<Categorias> cbSmallSize;
    private List<Categorias> catSmallSize = new ArrayList<>();
    private ObservableList<Categorias> obsCatSmallSize;
    
    @FXML
    private ComboBox<Categorias> cbLargeSize;
    private List<Categorias> catLargeSize = new ArrayList<>();
    private ObservableList<Categorias> obsCatLargeSize;
    
    @FXML
    private ComboBox<Categorias> cbStyle;
    private List<Categorias> catStyle = new ArrayList<>();
    private ObservableList<Categorias> obsCatStyle;
    
    @FXML
    private void getResult(ActionEvent ae) throws IOException {
//        Categorias seleGroup = cbGroup.getSelectionModel().getSelectedItem();
//        Categorias seleBrand = cbBrand.getSelectionModel().getSelectedItem();
//        Categorias seleSmall = cbSmallSize.getSelectionModel().getSelectedItem();
//        Categorias seleLarge = cbLargeSize.getSelectionModel().getSelectedItem();
//        Categorias seleStyle = cbStyle.getSelectionModel().getSelectedItem();
//        int menor = Integer.parseInt(seleSmall.getNome());
//        int maior = Integer.parseInt(seleLarge.getNome());
//        while (menor <= maior){
//            System.out.println(seleGroup.getNome()+ seleStyle.getNome() + seleBrand.getNome() + seleStyle.getNome() + txtModel.getText());
//            System.out.println(menor);
//            menor+=2;
//        }
        printer();
    }
    
    public void printer() throws IOException{
        Categorias seleGroup = cbGroup.getSelectionModel().getSelectedItem();
        Categorias seleBrand = cbBrand.getSelectionModel().getSelectedItem();
        Categorias seleSmall = cbSmallSize.getSelectionModel().getSelectedItem();
        Categorias seleLarge = cbLargeSize.getSelectionModel().getSelectedItem();
        Categorias seleStyle = cbStyle.getSelectionModel().getSelectedItem();
        Exporter document = new Exporter(seleGroup.getNome(),seleStyle.getNome(),seleBrand.getNome(),txtModel.getText(),seleSmall.getNome(),seleLarge.getNome());
        document.fileFormater();
    }
    
    public void getterGroup(){
        Categorias grupo1 = new Categorias("5001");
        Categorias grupo2 = new Categorias("5002");
        Categorias grupo3 = new Categorias("5003");
        Categorias grupo4 = new Categorias("5005");
        Categorias grupo5 = new Categorias("5006");
        Categorias grupo6 = new Categorias("5007");
        Categorias grupo7 = new Categorias("5008");
        Categorias grupo8 = new Categorias("5009");
        Categorias grupo9 = new Categorias("5010");
        Categorias grupo10 = new Categorias("5011");
        catGroup.add(grupo1);
        catGroup.add(grupo2);
        catGroup.add(grupo3);
        catGroup.add(grupo4);
        catGroup.add(grupo5);
        catGroup.add(grupo6);
        catGroup.add(grupo7);
        catGroup.add(grupo8);
        catGroup.add(grupo9);
        catGroup.add(grupo10);
        obsCatGroup = FXCollections.observableArrayList(catGroup);
        cbGroup.setItems(obsCatGroup);
    }
    
    public void getterBrand(){
        Categorias brand1 = new Categorias("22");
        Categorias brand2 = new Categorias("89");
        Categorias brand3 = new Categorias("91");
        Categorias brand4 = new Categorias("94");
        Categorias brand5 = new Categorias("95");
        Categorias brand6 = new Categorias("96");
        Categorias brand7 = new Categorias("97");
        catBrand.add(brand1);
        catBrand.add(brand2);
        catBrand.add(brand3);
        catBrand.add(brand4);
        catBrand.add(brand5);
        catBrand.add(brand6);
        catBrand.add(brand7);
        obsCatBrand = FXCollections.observableArrayList(catBrand);
        cbBrand.setItems(obsCatBrand);
    }
    
    public void getterSmallSize(){
        Categorias smallSize1 = new Categorias("32");
        Categorias smallSize2 = new Categorias("34");
        Categorias smallSize3 = new Categorias("36");
        Categorias smallSize4 = new Categorias("38");
        Categorias smallSize5 = new Categorias("40");
        Categorias smallSize6 = new Categorias("42");
        Categorias smallSize7 = new Categorias("44");
        Categorias smallSize8 = new Categorias("46");
        Categorias smallSize9 = new Categorias("48");
        Categorias smallSize10 = new Categorias("50");
        Categorias smallSize11 = new Categorias("52");
        catSmallSize.add(smallSize1);
        catSmallSize.add(smallSize2);
        catSmallSize.add(smallSize3);
        catSmallSize.add(smallSize4);
        catSmallSize.add(smallSize5);
        catSmallSize.add(smallSize6);
        catSmallSize.add(smallSize7);
        catSmallSize.add(smallSize8);
        catSmallSize.add(smallSize9);
        catSmallSize.add(smallSize10);
        catSmallSize.add(smallSize11);
        obsCatSmallSize = FXCollections.observableArrayList(catSmallSize);
        cbSmallSize.setItems(obsCatSmallSize);
    }
    
    public void getterLargeSize(){
        Categorias largeSize1 = new Categorias("32");
        Categorias largeSize2 = new Categorias("34");
        Categorias largeSize3 = new Categorias("36");
        Categorias largeSize4 = new Categorias("38");
        Categorias largeSize5 = new Categorias("40");
        Categorias largeSize6 = new Categorias("42");
        Categorias largeSize7 = new Categorias("44");
        Categorias largeSize8 = new Categorias("46");
        Categorias largeSize9 = new Categorias("48");
        Categorias largeSize10 = new Categorias("50");
        Categorias largeSize11 = new Categorias("52");
        Categorias largeSize12 = new Categorias("54");
        Categorias largeSize13 = new Categorias("56");
        Categorias largeSize14 = new Categorias("58");
        catLargeSize.add(largeSize1);
        catLargeSize.add(largeSize2);
        catLargeSize.add(largeSize3);
        catLargeSize.add(largeSize4);
        catLargeSize.add(largeSize5);
        catLargeSize.add(largeSize6);
        catLargeSize.add(largeSize7);
        catLargeSize.add(largeSize8);
        catLargeSize.add(largeSize9);
        catLargeSize.add(largeSize10);
        catLargeSize.add(largeSize11);
        catLargeSize.add(largeSize12);
        catLargeSize.add(largeSize13);
        catLargeSize.add(largeSize14);
        obsCatLargeSize = FXCollections.observableArrayList(catLargeSize);
        cbLargeSize.setItems(obsCatLargeSize);
    }
    
    public void getterStyle(){
        Categorias style1 = new Categorias("-");
        Categorias style2 = new Categorias("+");
        Categorias style3 = new Categorias(" ");
        Categorias style4 = new Categorias("*");
        Categorias style5 = new Categorias(".");
        Categorias style6 = new Categorias("_");
        Categorias style7 = new Categorias("■");
        catStyle.add(style1);
        catStyle.add(style2);
        catStyle.add(style3);
        catStyle.add(style4);
        catStyle.add(style5);
        catStyle.add(style6);
        catStyle.add(style7);
        obsCatStyle = FXCollections.observableArrayList(catStyle);
        cbStyle.setItems(obsCatStyle);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getterGroup();
        getterBrand();
        getterSmallSize();
        getterLargeSize();
        getterStyle();
    }
}
