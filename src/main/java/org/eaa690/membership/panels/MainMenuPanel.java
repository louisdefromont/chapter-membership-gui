package org.eaa690.membership.panels;

import org.eaa690.membership.ApplicationConstants;
import org.eaa690.membership.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main menu panel
 */
public class MainMenuPanel extends JPanel implements ActionListener {

    /**
     * Membership status button.
     */
    private JButton membershipStatusButton = new JButton("Check Membership Status");

    /**
     * Membership report button.
     */
    private JButton membershipReportButton = new JButton("Membership Report");

    /**
     * Admin functions button.
     */
    private JButton adminFunctionsButton = new JButton("Admin Functions");

    /**
     * Application frame.
     */
    private ApplicationFrame applicationFrame;

    /**
     * Default constructor.
     *
     * @param applicationFrame ApplicationFrame
     */
    public MainMenuPanel(final ApplicationFrame applicationFrame) {
        this.applicationFrame = applicationFrame;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        membershipStatusButton.setFont(new Font("Times New Roman", Font.BOLD, 64));
        membershipReportButton.setFont(new Font("Times New Roman", Font.BOLD, 64));
        adminFunctionsButton.setFont(new Font("Times New Roman", Font.BOLD, 64));

        membershipStatusButton.addActionListener(this);
        membershipReportButton.addActionListener(this);
        adminFunctionsButton.addActionListener(this);

        add(membershipStatusButton);
        add(membershipReportButton);
        add(adminFunctionsButton);
    }

    /**
     * Called when a button is pressed.
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == membershipStatusButton) {
            applicationFrame.switchPanel(ApplicationConstants.MEMBERSHIP_STATUS);
        }
        if (e.getSource() == membershipReportButton) {
            System.out.println("Membership Report Button pressed");
        }
        if (e.getSource() == adminFunctionsButton) {
            applicationFrame.switchPanel(ApplicationConstants.ADMIN);
        }
    }

}
