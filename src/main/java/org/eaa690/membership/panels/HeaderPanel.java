package org.eaa690.membership.panels;

import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.function.BiFunction;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.eaa690.membership.ApplicationFrame;

import lombok.Getter;

public class HeaderPanel extends JPanel {

    private JLabel titleLabel = new JLabel("EAA690 Membership");
    @Getter
    private MenuNavigationPanel menuNavigationPanel;

    public HeaderPanel(ApplicationFrame applicationFrame, MenuNavigationPanel menuNavigationPanel) {
        this.menuNavigationPanel = menuNavigationPanel;

        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 64));
        File logoFile = new File(this.getClass().getResource("/EAA_690_Logo.png").getPath());
        try {
            add(new JLabel(new ImageIcon(ImageIO.read(logoFile))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(titleLabel);
        add(menuNavigationPanel);
    }

    public void setMenuNavigationPanel(MenuNavigationPanel menuNavigationPanel) {
        remove(this.menuNavigationPanel);
        add(menuNavigationPanel);
        revalidate();
        repaint();
    }
}
