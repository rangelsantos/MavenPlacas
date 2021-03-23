/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenplacas;

import com.spire.doc.Document;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
    public boolean print;

    public Exporter(String group, String style, String brand, String model, String small, String large, boolean print) throws IOException {
        this.group = group;
        this.style = style;
        this.brand = brand;
        this.model = model;
        this.small = small;
        this.large = large;
        this.print = print;
    }

    public void fileFormater() throws IOException {
        int sml = Integer.parseInt(small);
        int lrg = Integer.parseInt(large);

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
            if (!print) {
                Saver save = new Saver(doc);
                save.writeFile();
            } else if (print) {
                Printer printing = new Printer(doc);
                printing.nicePrint();
            }

//            if (!print) {
//                FileChooser fileChooser = new FileChooser();
//                fileChooser.setInitialFileName("novo");
//                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
//                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DOCX (*.docx)", "*.docx");
//                fileChooser.getExtensionFilters().add(extFilter);
//                File selectedFile = fileChooser.showSaveDialog(null);
//                String fileName = selectedFile.getCanonicalPath();
//                try (FileOutputStream out = new FileOutputStream(fileName)) {
//                    doc.write(out);
//                }
//            } else if (print){
//                File tempFile = File.createTempFile("printable", ".docx");
//                String fileName = tempFile.getCanonicalPath();
//                try (FileOutputStream out = new FileOutputStream(fileName)) {
//                    doc.write(out);
//                }
//                Document document = new Document();
//                System.out.println(fileName);
//                document.loadFromFile(fileName);
//                PrinterJob printerJob = PrinterJob.getPrinterJob();
//                PageFormat pageFormat = printerJob.defaultPage();
//                Paper paper = pageFormat.getPaper();
//                paper.setImageableArea(0, 0, pageFormat.getWidth(), pageFormat.getHeight());
//                printerJob.setCopies(1);
//                pageFormat.setPaper(paper);
//                printerJob.setPrintable(document, pageFormat);
//                try {
//                    printerJob.printDialog();
//                    printerJob.print();
//                } catch (PrinterException e) {
//                }
//            }
        }

    }

//    public void save(XWPFDocument doc) throws IOException {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialFileName("novo");
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DOCX (*.docx)", "*.docx");
//        fileChooser.getExtensionFilters().add(extFilter);
//        File selectedFile = fileChooser.showSaveDialog(null);
//        String fileName = selectedFile.getCanonicalPath();
//        try (FileOutputStream out = new FileOutputStream(fileName)) {
//            doc.write(out);
//        }
//    }

//    public void print(XWPFDocument doc) throws IOException {
//        File tempFile = File.createTempFile("printable", ".docx");
//        String fileName = tempFile.getCanonicalPath();
//        Printer printing = new Printer(fileName);
//        printing.nicePrint();
//        try (FileOutputStream out = new FileOutputStream(fileName)) {
//            doc.write(out);
//            Document document = new Document();
//            System.out.println(fileName);
//            document.loadFromFile(fileName);
//            PrinterJob printerJob = PrinterJob.getPrinterJob();
//            PageFormat pageFormat = printerJob.defaultPage();
//            Paper paper = pageFormat.getPaper();
//            paper.setImageableArea(0, 0, pageFormat.getWidth(), pageFormat.getHeight());
//            printerJob.setCopies(1);
//            pageFormat.setPaper(paper);
//            printerJob.setPrintable(document, pageFormat);
//            try {
//                printerJob.printDialog();
//                printerJob.print();
//            } catch (PrinterException e) {
//            }
//        }
    }
