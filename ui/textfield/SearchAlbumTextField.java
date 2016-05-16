package ui.textfield;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;

import listener.focus.MediaTableFocusListener;
import listener.key.PressEnterSearchListener;

public final class SearchAlbumTextField extends JTextField {
    private static final long serialVersionUID = 1L;

    public SearchAlbumTextField() {
        super(20);
        
        addFocusListener(new MediaTableFocusListener(this));
        addKeyListener(new PressEnterSearchListener());
        setEditable(false);
        setPreferredSize(new Dimension(200, 20));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        setHorizontalAlignment(JTextField.LEFT);
        setForeground(Color.LIGHT_GRAY);
        setFont(new Font("Dialog", Font.ITALIC, 11));
    }    
}