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
 * JavaFX App, created by Rangel Santos
 */
public class Printer {

    XWPFDocument doc = new XWPFDocument();

    public Printer(XWPFDocument doc) {
        this.doc = doc;
    }
    
    //funcao que escreve um arquivo temporario do disco e em seguida a abre para a impressao
    public void nicePrint() throws IOException {
        //cria o e salva arquivo temporario no disco
        File tempFile = File.createTempFile("printable", ".docx");
        try (FileOutputStream novo = new FileOutputStream(tempFile.getCanonicalPath())) {
            doc.write(novo);
        } catch (IOException e){
            System.out.println("falha ao criar o arquivo temporario");
        }
        Document document = new Document();
        //carrega o documento do disco
        document.loadFromFile(tempFile.getCanonicalPath());
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        PageFormat pageFormat = printerJob.defaultPage();
        Paper paper = pageFormat.getPaper();
        paper.setImageableArea(0, 0, pageFormat.getWidth(), pageFormat.getHeight());
        //quantidade de copias
        printerJob.setCopies(1);
        pageFormat.setPaper(paper);
        printerJob.setPrintable(document, pageFormat);
        try {
            //tenta a confirmacao da impressao, caso consiga imprime o documento
            if (printerJob.printDialog()){
            printerJob.print();
            }
        } catch (PrinterException e) {
            System.out.println("falha ao imprimir");
        }
    }
}
