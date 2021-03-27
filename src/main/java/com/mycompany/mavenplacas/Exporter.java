/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenplacas;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.IOException;
import java.math.BigInteger;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;

public class Exporter {

    public String group, style, brand, model, kidsmall, kidlarge, small, large, numModel[], numKidsModel[];
    public boolean print, set;
    private int smallinfo, largeinfo, smallkids, largekids;

    public Exporter(String group, String style, String brand, String model, String small, String large, boolean print) throws IOException {
        this.group = group;
        this.style = style;
        this.brand = brand;
        this.model = model;
        this.small = small;
        this.large = large;
        this.print = print;
    }

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

    public void groupFilter() {
        switch (group) {
            case "5001": {
                numModel = PrimaryController.catNumAdul;
                break;
            }
            case "5002": {
                numModel = PrimaryController.catNumAdul;
                break;
            }
            case "6001": {
                numModel = PrimaryController.catNumAdul;
                break;
            }
            case "6002": {
                numModel = PrimaryController.catNumAdul;
                break;
            }
            case "6003": {
                numModel = PrimaryController.catNumAdul;
                break;
            }
            case "6004": {
                numModel = PrimaryController.catNumAdul;
                break;
            }
            case "6022": {
                numModel = PrimaryController.catNumAdul;
                break;
            }
            default: {
                numModel = PrimaryController.catNumMalha;
            }
        }
    }

    public void fileFormater() throws IOException {

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
            p1.setSpacingBetween(0.9);

            XWPFRun r1 = p1.createRun();
            r1.setBold(true);
            r1.setFontSize(72);
            r1.setFontFamily("Times New Roman");

            switch (brand) {
                case "91": {
                    numKidsModel = PrimaryController.catNumKids;
                    groupFilter();
                    break;
                }
                case "94": {
                    numModel = PrimaryController.catNumBaby;
                    break;
                }
                case "95": {
                    numModel = PrimaryController.catNumKids;
                    break;
                }
                case "96": {
                    numModel = PrimaryController.catNumYoung;
                    break;
                }
                default: {
                    groupFilter();
                }
            }
            
            if (set) {
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

            if (!print) {
                Saver save = new Saver(doc);
                save.writeFile();
            } else if (print) {
                Printer printing = new Printer(doc);
                printing.nicePrint();
            }
        }
    }
}
