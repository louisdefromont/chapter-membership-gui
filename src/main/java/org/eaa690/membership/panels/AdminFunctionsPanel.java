package org.eaa690.membership.panels;

import org.eaa690.membership.ApplicationConstants;
import org.eaa690.membership.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Panel to display administrative function buttons.
 */
public class AdminFunctionsPanel extends JPanel implements ActionListener {

    /**
     * Exit button.
     */
    private JButton exitButton = new JButton("Exit");

    /**
     * Reboot button.
     */
    private JButton rebootButton = new JButton("Reboot");

    /**
     * Main menu button.
     */
    private JButton mainMenuButton = new JButton("Main Menu");

    /**
     * RFID card assignment button
     */
    private JButton rfidCardAssignmentButton = new JButton("RFID Card Assignment");

    /**
     * Application frame.
     */
    private ApplicationFrame applicationFrame;

    /**
     * Default constructor.
     *
     * @param applicationFrame ApplicationFrame
     */
    public AdminFunctionsPanel(final ApplicationFrame applicationFrame) {
        this.applicationFrame = applicationFrame;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 64));
        rebootButton.setFont(new Font("Times New Roman", Font.BOLD, 64));
        mainMenuButton.setFont(new Font("Times New Roman", Font.BOLD, 64));
        rfidCardAssignmentButton.setFont(new Font("Times New Roman", Font.BOLD, 64));

        exitButton.addActionListener(this);
        rebootButton.addActionListener(this);
        mainMenuButton.addActionListener(this);
        rfidCardAssignmentButton.addActionListener(this);

        add(mainMenuButton);
        add(rfidCardAssignmentButton);
        add(rebootButton);
        add(exitButton);
    }

    /**
     * Called when a button is pressed.
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.out.println("Exit Button pressed");
            System.exit(0);
        }
        if (e.getSource() == rebootButton) {
            try {
                Runtime.getRuntime().exec("sudo reboot");
            } catch (IOException ioe) {
                System.out.println("Error: " + ioe.getMessage());
            }
        }
        if (e.getSource() == rfidCardAssignmentButton) {
            System.out.println("RFID Card Assignment Button pressed");
        }
        if (e.getSource() == mainMenuButton) {
            applicationFrame.switchPanel(ApplicationConstants.MAIN_MENU);
        }
    }
}
