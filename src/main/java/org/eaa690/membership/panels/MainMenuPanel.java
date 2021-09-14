package org.eaa690.membership.panels;

import java.util.function.BiFunction;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.eaa690.membership.ApplicationFrame;

/**
 * Main menu panel
 */
public class MainMenuPanel extends MenuPanel {

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
    public MainMenuPanel(final ApplicationFrame applicationFrame, BiFunction<ApplicationFrame, MenuPanel, MenuPanel> ... childMenuConstructors) {
        super(applicationFrame, null, childMenuConstructors, "Main Menu");
        this.applicationFrame = applicationFrame;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add (new JTextField("Yo, this is the main menu"));

        // membershipStatusButton.setFont(new Font("Times New Roman", Font.BOLD, 64));
        // membershipReportButton.setFont(new Font("Times New Roman", Font.BOLD, 64));
        // adminFunctionsButton.setFont(new Font("Times New Roman", Font.BOLD, 64));

        // add(membershipStatusButton);
        // add(membershipReportButton);
        // add(adminFunctionsButton);
    }

    @Override
    public void displayPanel() {

    }
}
