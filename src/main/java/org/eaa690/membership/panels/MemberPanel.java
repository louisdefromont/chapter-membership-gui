package org.eaa690.membership.panels;

import org.eaa690.membership.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel to display member information.
 */
public class MemberPanel extends JPanel implements ActionListener {

    /**
     * Close button.
     */
    private JButton closeButton = new JButton("Close");

    /**
     * Application frame.
     */
    private ApplicationFrame applicationFrame;

    /**
     * Default constructor.
     *
     * @param applicationFrame ApplicationFrame
     */
    public MemberPanel(final ApplicationFrame applicationFrame) {
        this.applicationFrame = applicationFrame;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        closeButton.setFont(new Font("Times New Roman", Font.BOLD, 64));
        closeButton.addActionListener(this);
        add(closeButton);
    }

    /**
     * Called when a button is pressed.
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            System.out.println("Close Button pressed");
        }
    }

}
