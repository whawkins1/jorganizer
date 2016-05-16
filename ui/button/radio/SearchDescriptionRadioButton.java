package ui.button.radio;

import java.awt.Dimension;

import javax.swing.JRadioButton;

public final class SearchDescriptionRadioButton extends JRadioButton {
  
    private static final long serialVersionUID = 1L;
    
    public SearchDescriptionRadioButton() {
        super("Description");
        setToolTipText("Search Album's Descriptions");
        setPreferredSize(new Dimension(78, 28));
        setEnabled(false);       
    }
}