package org.eaa690.membership;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TitlePanel extends JPanel {

    private JLabel titleLabel = new JLabel("EAA690 Membership");

    public TitlePanel() {
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 64));
        File logoFile = new File(this.getClass().getResource("/EAA_690_Logo.png").getPath());
        try {
            add(new JLabel(new ImageIcon(ImageIO.read(logoFile))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(titleLabel);
    }
}
