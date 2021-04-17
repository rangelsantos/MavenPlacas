package common;

import javafx.scene.control.ComboBox;

/**
 *
 * @author Acme
 */
public class VerificaCampos {
    
    private boolean resultado;
    private ComboBox menor, maior;
    private String combobox;

    public VerificaCampos(String combobox) {
        this.combobox = combobox;
    }
    
    public VerificaCampos(ComboBox menor, ComboBox maior) {
        this.menor = menor;
        this.maior = maior;
    }
    
    public boolean validaCampo(){
        resultado = combobox != null;
        return resultado;
    }
    
    public boolean validaTamanho(){
        resultado = menor.getSelectionModel().getSelectedIndex() > maior.getSelectionModel().getSelectedIndex();
        return resultado;
    }
}
