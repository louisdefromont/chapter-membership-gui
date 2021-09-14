package org.eaa690.membership.panels;

import javax.swing.JLabel;

import org.eaa690.membership.ApplicationFrame;

public class TestMenuPanel extends MenuPanel {

    public TestMenuPanel(ApplicationFrame applicationFrame, MenuPanel parentMenu) {
        super(applicationFrame, parentMenu, null, "Test Menu");
        add(new JLabel("This is a test menu."));
    }

    @Override
    public void displayPanel() {
    }
}
