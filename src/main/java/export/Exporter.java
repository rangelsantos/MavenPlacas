package export;

import main.PrimaryController;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.IOException;
import java.math.BigInteger;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;

/**
 * JavaFX App, created by Rangel Santos
 */
public class Exporter extends PrimaryController {

    public String group, style, brand, model, kidsmall, kidlarge, small, large, numModel[], numKidsModel[];
    public boolean print, set = true;
    private int smallinfo, largeinfo, smallkids, largekids;
    
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
        if(group == "5001" || group == "5002" || group == "6001" || group == "6002" || group == "6003" || group == "6004" || group == "6022"){
            numModel = lista.getCatNumAdul();
        } else {
            numModel = lista.getCatNumMalha();
        }
    }
    
    //funcao que constroi o documento
    public void fileFormater() throws IOException {

        try (XWPFDocument doc = new XWPFDocument()) {
            //seta as margem da pagina
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
            pageMar.setTop(BigInteger.valueOf(50));
            pageMar.setBottom(BigInteger.valueOf(50));
            pageMar.setFooter(BigInteger.valueOf(0));
            pageMar.setHeader(BigInteger.valueOf(0));
            pageMar.setGutter(BigInteger.valueOf(0));
            //cria o paragrafo
            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.CENTER);
            p1.setSpacingBetween(0.95f);
            //cria a linha do paragrafo, e seta como seram os caracteres
            XWPFRun r1 = p1.createRun();
            r1.setBold(true);
            r1.setFontSize(72);
            r1.setFontFamily("Times New Roman");
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
                    r1.setText(group + style + brand + style + model);
                    r1.addBreak();
                    r1.setText(String.valueOf(numKidsModel[loop]));
                    r1.addBreak();
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
                r1.setText(group + style + brand + style + model);
                r1.addBreak();
                r1.setText(String.valueOf(numModel[loop]));
                r1.addBreak();
            }
            
            //caso o botao selecionado seja salvar, instanciamos um novo 'Saver'
            if (!print) {
                Saver save = new Saver(doc);
                save.writeFile();
             
            //caso o botao selecionado seja imprimir, instanciamos um novo 'Printer'
            } else if (print) {
                Printer printing = new Printer(doc);
                printing.nicePrint();
            }
        } catch (IOException e){
            System.out.println("falha ao criar um novo documento");
        }
    }
}
