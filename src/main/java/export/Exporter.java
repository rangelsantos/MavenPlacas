package export;

import main.PrimaryController;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * JavaFX App, created by Rangel Santos
 */
public class Exporter extends PrimaryController {

    public String group, style, brand, model, kidsmall, kidlarge, small, large, numModel[], numKidsModel[];
    public boolean print, set = true;
    private int smallinfo, largeinfo, smallkids, largekids, marginTop;
    int line = 0;
    PDPage page;

    //construtor que recebe os valores na opção padrão
    public Exporter(String group, String style, String brand, String model, String small, String large, boolean print) throws IOException {
        this.group = group;
        this.style = style;
        this.brand = brand;
        this.model = model;
        this.small = small;
        this.large = large;
        this.print = print;
    }

    //construtor que recebe os valores adcionais na opção para marca '91'
    public Exporter(String group, String style, String brand, String model, String kidsmall, String kidlarge, String small, String large, boolean print, boolean set) throws IOException {
        this.group = group;
        this.style = style;
        this.brand = brand;
        this.model = model;
        this.kidsmall = kidsmall;
        this.kidlarge = kidlarge;
        this.small = small;
        this.large = large;
        this.print = print;
        this.set = set;
    }

    /*funcao que seta o array com tamanhos usados no
    preechimento do documento baseado no valor variavel 'group'*/
    public void groupFilter() {
        if (group == "5001" || group == "5002" || group == "6001" || group == "6002" || group == "6003" || group == "6004" || group == "6022") {
            numModel = lista.getCatNumAdul();
        } else {
            numModel = lista.getCatNumMalha();
        }
    }

    public void addCenterText(PDDocument document, PDPage page, String text) throws IOException {
        PDFont font = PDType1Font.TIMES_BOLD; // Or whatever font you want.
        int fontSize = 72; // Or whatever font size you want.
        float titleWidth = font.getStringWidth(text) / 1000 * fontSize;
        float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;
        PDPageContentStream content = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
        content.beginText();
        content.setFont(font, fontSize);
        content.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, page.getMediaBox().getHeight() - marginTop - titleHeight);
        content.showText(text);
        content.endText();
        content.close();
        marginTop += 82;
    }

    public void addNewPage(PDDocument document) {
        PDPage newPage = new PDPage(PDRectangle.A4);
        document.addPage(newPage);
        marginTop = 0;
        page = newPage;
    }

    //funcao que constroi o documento
    public void fileFormater() throws IOException {

        try (PDDocument document = new PDDocument();) {
            String code, size;
            PDPage page1 = new PDPage(PDRectangle.A4);
            document.addPage(page1);
            page = page1;

            /*seleciona os tamanhos baseados na marca, caso '91' seta o array 'numKidsModel' com 
            os tamanhos da marca '95' e chama a funcao 'groupFilter' que seta os tamanhos adultos,
            caso nao seja infantil chama a funcao 'groupFilter' que seta os tamanhos adultos*/
            switch (brand) {
                case "91": {
                    numKidsModel = lista.getCatNumKids();
                    groupFilter();
                    break;
                }
                case "94": {
                    numModel = lista.getCatNumBaby();
                    break;
                }
                case "95": {
                    numModel = lista.getCatNumKids();
                    break;
                }
                case "96": {
                    numModel = lista.getCatNumYoung();
                    break;
                }
                default: {
                    groupFilter();
                }
            }

            //caso 'set = true' imprime o modelo e os tamanhos da marca '91' selecionados no documento
            if (!set) {
                for (int loop = 0; loop < numKidsModel.length; loop++) {
                    if (numKidsModel[loop] == kidsmall) {
                        this.smallkids = loop;
                    }
                    if (numKidsModel[loop] == kidlarge) {
                        this.largekids = loop + 1;
                    }
                }
                for (int loop = smallkids; loop < largekids; loop++) {
//                    r1.setText(group + style + brand + style + model);
//                    r1.addBreak();
//                    r1.setText(String.valueOf(numKidsModel[loop]));
//                    r1.addBreak();
                }
            }

            //imprime o modelos e os tamanhos selecionados no documento
            for (int loop = 0; loop < numModel.length; loop++) {
                if (numModel[loop] == small) {
                    this.smallinfo = loop;
                }
                if (numModel[loop] == large) {
                    this.largeinfo = loop + 1;
                }
            }

            for (int loop = smallinfo; loop < largeinfo; loop++) {
                code = group + style + brand + style + model;
                addCenterText(document, page, code);
                size = String.valueOf(numModel[loop]);
                addCenterText(document, page, size);
                line += 2;
                if (line > 9 && line <= 10) {
                    addNewPage(document);
                } else if (line > 19 && line <= 20) {
                    addNewPage(document);
                } else if (line > 29 && line <= 30) {
                    addNewPage(document);
                }
            }

            //caso o botao selecionado seja salvar, instanciamos um novo 'Saver'
            if (!print) {
                Saver save = new Saver(document);
                save.writeFile();
                save.closeDoc();
             
            //caso o botao selecionado seja imprimir, instanciamos um novo 'Printer'
            } else if (print) {
                Printer printing = new Printer(document);
                printing.nicePrint();
            }
        } catch (IOException e){
            System.out.println("falha ao criar um novo documento");
        }
    }
}
