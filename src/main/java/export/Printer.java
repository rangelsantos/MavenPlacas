package export;

import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.awt.print.Paper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;

/**
 * JavaFX App, created by Rangel Santos
 */
public class Printer {

    PDDocument doc = new PDDocument();

    public Printer(PDDocument doc) {
        this.doc = doc;
    }

    //funcao que escreve um arquivo temporario do disco e em seguida a abre para a impressao
    public void nicePrint() throws IOException {
        PDFPrintable print = new PDFPrintable(doc);
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        PageFormat pageFormat = printerJob.defaultPage();
        Paper paper = pageFormat.getPaper();
        paper.setImageableArea(0, 0, pageFormat.getWidth(), pageFormat.getHeight());
        //quantidade de copias
        printerJob.setCopies(1);
        pageFormat.setPaper(paper);
        printerJob.setPrintable(print, pageFormat);
        try {
            //tenta a confirmacao da impressao, caso consiga imprime o documento
            if (printerJob.printDialog()) {
                printerJob.print();
            }
        } catch (PrinterException e) {
            e.printStackTrace();
            System.out.println("falha ao imprimir");
        }
    }
}
