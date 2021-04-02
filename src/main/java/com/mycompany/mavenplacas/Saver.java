package com.mycompany.mavenplacas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.stage.FileChooser;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * JavaFX App, created by Rangel Santos
 */
public class Saver {
    
    XWPFDocument doc = new XWPFDocument();
    
    public Saver(XWPFDocument doc){
        this.doc = doc;
    }
    
    //salva o documento criado em um local selecionado pela usuario
    public void writeFile() throws IOException{
        FileChooser fileChooser = new FileChooser();
        //nome padrao 'novo'
        fileChooser.setInitialFileName("novo");
        //diretorio padrao 'user home'
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        //seta a extensao padrao do arquivo '.docx'
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DOCX (*.docx)", "*.docx");
        fileChooser.getExtensionFilters().add(extFilter);
        //abre o dialogo para que o usuario escolha o local e nome do documento
        File selectedFile = fileChooser.showSaveDialog(null);
        //recebe o caminho e nome do arquivo que sera criado
        String fileName = selectedFile.getCanonicalPath();
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            //escreve o arquivo no disco
            doc.write(out);
        } catch (IOException e){
            System.out.println("falha ao salvar o arquivo");
        }
    }
}
