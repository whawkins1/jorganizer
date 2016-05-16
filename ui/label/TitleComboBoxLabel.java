package ui.label;

import java.awt.Font;

import javax.swing.JLabel;

public final class TitleComboBoxLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    public TitleComboBoxLabel() {
        super("Title:");
        setFont(new Font("Stencil", Font.PLAIN, 14));
    }
}