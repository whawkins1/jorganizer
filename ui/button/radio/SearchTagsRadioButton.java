package ui.button.radio;

import java.awt.Dimension;

import javax.swing.JRadioButton;

public final class SearchTagsRadioButton extends JRadioButton {
   
    private static final long serialVersionUID = 1L;
    
    public SearchTagsRadioButton() {
        super("Tags");
        setToolTipText("Search Album's Tags");
        setPreferredSize(new Dimension(78, 28));
        setEnabled(false);
    }
}