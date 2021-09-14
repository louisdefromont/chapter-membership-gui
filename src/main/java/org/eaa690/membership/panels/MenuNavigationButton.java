package org.eaa690.membership.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.eaa690.membership.ApplicationFrame;

import lombok.Getter;

public class MenuNavigationButton extends JButton implements ActionListener {
    @Getter
    private MenuPanel menuPanel;
    private ApplicationFrame applicationFrame;

    public MenuNavigationButton(String text, MenuPanel menuPanel, ApplicationFrame applicationFrame) {
        super(text);
        this.menuPanel = menuPanel;
        this.applicationFrame = applicationFrame;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        applicationFrame.switchMenu(menuPanel);
    }
}
