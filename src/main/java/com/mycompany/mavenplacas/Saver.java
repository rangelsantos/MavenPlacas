/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenplacas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.stage.FileChooser;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *
 * @author Acme
 */
public class Saver {
    
    XWPFDocument doc = new XWPFDocument();
    
    public Saver(XWPFDocument doc){
        this.doc = doc;
    }
    
    public void writeFile() throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("novo");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DOCX (*.docx)", "*.docx");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showSaveDialog(null);
        String fileName = selectedFile.getCanonicalPath();
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            doc.write(out);
        }
    }
}
