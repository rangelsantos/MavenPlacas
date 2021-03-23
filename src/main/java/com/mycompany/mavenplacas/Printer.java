/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenplacas;

import com.spire.doc.Document;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.awt.print.Paper;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *
 * @author Acme
 */
public class Printer {

    XWPFDocument doc = new XWPFDocument();

    public Printer(XWPFDocument doc) {
        this.doc = doc;
    }

    public void nicePrint() throws IOException {
        File tempFile = File.createTempFile("printable", ".docx");
        try (FileOutputStream novo = new FileOutputStream(tempFile.getCanonicalPath())) {
            doc.write(novo);
        }
        Document document = new Document();
        System.out.println(tempFile.getCanonicalPath());
        document.loadFromFile(tempFile.getCanonicalPath());
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        PageFormat pageFormat = printerJob.defaultPage();
        Paper paper = pageFormat.getPaper();
        paper.setImageableArea(0, 0, pageFormat.getWidth(), pageFormat.getHeight());
        printerJob.setCopies(1);
        pageFormat.setPaper(paper);
        printerJob.setPrintable(document, pageFormat);
        try {
            printerJob.printDialog();
            printerJob.print();
        } catch (PrinterException e) {
        }
    }
}
