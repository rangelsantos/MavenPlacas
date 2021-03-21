/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenplacas;

import java.io.File;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import javafx.stage.FileChooser;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;

public class Exporter {

    public String group, style, brand, model, small, large;

    public Exporter(String group, String style, String brand, String model, String small, String large) throws IOException {
        this.group = group;
        this.style = style;
        this.brand = brand;
        this.model = model;
        this.small = small;
        this.large = large;
    }

    public void fileFormater() throws IOException {
        int sml = Integer.parseInt(small);
        int lrg = Integer.parseInt(large);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("novo");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DOCX (*.docx)", "*.docx");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showSaveDialog(null);
        String fileName = selectedFile.getCanonicalPath();

        try (XWPFDocument doc = new XWPFDocument()) {

            CTSectPr sectPr = doc.getDocument().getBody().getSectPr();
            if (sectPr == null) {
                sectPr = doc.getDocument().getBody().addNewSectPr();
            }
            CTPageMar pageMar = sectPr.getPgMar();
            if (pageMar == null) {
                pageMar = sectPr.addNewPgMar();
            }
            pageMar.setLeft(BigInteger.valueOf(0));
            pageMar.setRight(BigInteger.valueOf(0));
            pageMar.setTop(BigInteger.valueOf(0));
            pageMar.setBottom(BigInteger.valueOf(0));
            pageMar.setFooter(BigInteger.valueOf(0));
            pageMar.setHeader(BigInteger.valueOf(0));
            pageMar.setGutter(BigInteger.valueOf(0));

            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.CENTER);
            p1.setSpacingBefore(100);

            XWPFRun r1 = p1.createRun();
            r1.setBold(true);
            r1.setFontSize(72);
            r1.setFontFamily("Times New Roman");
            while (sml <= lrg) {
                r1.setText(group + style + brand + style + model);
                r1.addBreak();
                r1.setText(String.valueOf(sml));
                r1.addBreak();
                sml += 2;
            }

            try (FileOutputStream out = new FileOutputStream(fileName)) {
                doc.write(out);
            }
        }
    }
}
