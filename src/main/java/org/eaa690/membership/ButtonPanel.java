package org.eaa690.membership;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {

    private JButton exitButton = new JButton("Exit");
    
    private JButton rebootButton = new JButton("Reboot");

    private JButton membershipStatusButton = new JButton("Check Membership Status");

    private JButton membershipReportButton = new JButton("Membership Report");

    private JButton rfidCardAssignmentButton = new JButton("RFID Card Assignment");

    public ButtonPanel() {
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 64));
        membershipStatusButton.setFont(new Font("Times New Roman", Font.BOLD, 64));
        membershipReportButton.setFont(new Font("Times New Roman", Font.BOLD, 64));
        rfidCardAssignmentButton.setFont(new Font("Times New Roman", Font.BOLD, 64));
        rebootButton.setFont(new Font("Times New Roman", Font.BOLD, 64));

        exitButton.addActionListener(this);
        membershipStatusButton.addActionListener(this);
        membershipReportButton.addActionListener(this);
        rfidCardAssignmentButton.addActionListener(this);
        rebootButton.addActionListener(this);

        add(membershipStatusButton, BorderLayout.NORTH);
        add(membershipReportButton, BorderLayout.WEST);
        add(rebootButton, BorderLayout.CENTER);
        add(rfidCardAssignmentButton, BorderLayout.EAST);
        add(exitButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.out.println("Exit Button pressed");
            System.exit(0);
        }
        if (e.getSource() == membershipStatusButton) {
            System.out.println("Membership Status Button pressed");
        }
        if (e.getSource() == membershipReportButton) {
            System.out.println("Membership Report Button pressed");
        }
        if (e.getSource() == rfidCardAssignmentButton) {
            System.out.println("RFID Card Assignment Button pressed");
        }
        if (e.getSource() == rebootButton) {
            Runtime.getRuntime().exec("sudo reboot");
        }
    }
}
