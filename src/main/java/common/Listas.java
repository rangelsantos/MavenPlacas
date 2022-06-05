package common;

/**
 *
 * @author Acme
 */
public class Listas {

    private String catGroup[] = {"5001", "5002", "5003", "5005", "5006", "5007", "5008", "5009", "5010", "5011", "5014", "5015", "5016", "5017", "5018", "5016", "5017", "5018", "5019", "5030", "5049", "5050", "5059", "6001", "6002", "6003", "6004", "6005", "6006", "6007", "6008", "6009", "6010", "6011", "6012", "6013", "6014", "6015", "6016", "6017", "6018", "6019", "6020", "6022", "6024", "6025", "6027", "6030", "6034", "6040", "6042", "6045", "7007"};
    private String catBrand[] = {"1" ,"22", "89", "91", "94", "95", "96", "97", "99", "156", "166", "177", "229", "320"};
    private String catStyle[] = {"-", "+", "*", ".", " ", "_", "?", "#"};
    private String catNumAdul[] = {"34", "36", "38", "40", "42", "44", "46", "48", "50", "52", "54", "56", "58", "PP", "P", "M", "G", "GG", "EG", "EGG", "XGG", "XXG"};
    private String catNumMalha[] = {"PP", "P", "M", "G", "GG", "EG", "EGG", "XGG", "XXG"};
    private String catNumBaby[] = {"P", "M", "G", "GG", "EG"};
    private String catNumKids[] = {"1", "2", "3", "4", "6", "8", "10", "12", "14", "16", "18"};
    private String catNumYoung[] = {"12", "14", "16", "18"};
    private String catNumBoti[] = {"34", "36", "38", "40", "42", "44", "46", "48", "50", "52", "54", "56", "58", "PP", "P", "M", "G", "GG", "EG", "EGG", "EG1", "EG2", "EG3", "EG4"};
    private int lastSmall = -1;
    private int lastLarge = -1;
    private int lastSmallKids = -1;
    private int lastLargeKids = -1;

    public String[] getCatGroup() {
        return catGroup;
    }

    public void setCatGroup(String[] catGroup) {
        this.catGroup = catGroup;
    }

    public String[] getCatBrand() {
        return catBrand;
    }

    public void setCatBrand(String[] catBrand) {
        this.catBrand = catBrand;
    }

    public String[] getCatStyle() {
        return catStyle;
    }

    public void setCatStyle(String[] catStyle) {
        this.catStyle = catStyle;
    }

    public String[] getCatNumAdul() {
        return catNumAdul;
    }

    public void setCatNumAdul(String[] catNumAdul) {
        this.catNumAdul = catNumAdul;
    }

    public String[] getCatNumMalha() {
        return catNumMalha;
    }

    public void setCatNumMalha(String[] catNumMalha) {
        this.catNumMalha = catNumMalha;
    }

    public String[] getCatNumBaby() {
        return catNumBaby;
    }

    public void setCatNumBaby(String[] catNumBaby) {
        this.catNumBaby = catNumBaby;
    }

    public String[] getCatNumKids() {
        return catNumKids;
    }

    public void setCatNumKids(String[] catNumKids) {
        this.catNumKids = catNumKids;
    }

    public String[] getCatNumYoung() {
        return catNumYoung;
    }

    public void setCatNumYoung(String[] catNumYoung) {
        this.catNumYoung = catNumYoung;
    }

    public String[] getCatNumBoti() {
        return catNumBoti;
    }

    public void setCatNumBoti(String[] catNumBoti) {
        this.catNumBoti = catNumBoti;
    }

    public int getLastSmall() {
        return lastSmall;
    }

    public void setLastSmall(int lastSmall) {
        this.lastSmall = lastSmall;
    }

    public int getLastLarge() {
        return lastLarge;
    }

    public void setLastLarge(int lastLarge) {
        this.lastLarge = lastLarge;
    }

    public int getLastSmallKids() {
        return lastSmallKids;
    }

    public void setLastSmallKids(int lastSmallKids) {
        this.lastSmallKids = lastSmallKids;
    }

    public int getLastLargeKids() {
        return lastLargeKids;
    }

    public void setLastLargeKids(int lastLargeKids) {
        this.lastLargeKids = lastLargeKids;
    }
    
    public void setEG(){
        this.catNumMalha[5] = "EG";
        this.catNumAdul[18] = "EG";
    }
    
    public void setXG(){
        this.catNumMalha[5] = "XG";
        this.catNumAdul[18] = "XG";
    }
    
    public void set3436(){
        this.catNumAdul[1] = "34-36";
    }
    
    public void set36(){
        this.catNumAdul[1] = "36";
    }
    
    public void set5052(){
        this.catNumAdul[8] = "50-52";
    }
    
    public void set50(){
        this.catNumAdul[8] = "50";
    }
    
    public void setPPP(){
        this.catNumMalha[1] = "PP-P";
        this.catNumAdul[14] = "PP-P";
    }
    
    public void setP(){
        this.catNumMalha[1] = "P";
        this.catNumAdul[14] = "P";
    }
    
    public void setGGG(){
        this.catNumMalha[3] = "G-GG";
        this.catNumAdul[16] = "G-GG";
    }
    
    public void setG(){
        this.catNumMalha[3] = "G";
        this.catNumAdul[16] = "G";
    }
}
