package org.eaa690.membership;

import org.eaa690.membership.panels.AdminFunctionsPanel;
import org.eaa690.membership.panels.MainMenuPanel;
import org.eaa690.membership.panels.MemberPanel;
import org.eaa690.membership.panels.MenuNavigationPanel;
import org.eaa690.membership.panels.MenuPanel;
import org.eaa690.membership.panels.TestMenuPanel;
import org.eaa690.membership.panels.HeaderPanel;
import org.eaa690.membership.service.RosterService;
import org.eaa690.membership.util.RFIDReader;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * ApplicationFrame
 */
public class ApplicationFrame extends JFrame {

    /**
     * Title panel.
     */
    private final HeaderPanel headerPanel;
    /**
     * Main menu panel.
     */
    @Getter
    private MainMenuPanel mainMenuPanel;

    /**
     * Admin functions panel.
     */
    private AdminFunctionsPanel adminFunctionsPanel;

    /**
     * Member panel.
     */
    private MemberPanel memberPanel;

    /**
     * Roster Service.
     */
    private final RosterService rosterService;

    private final RFIDReader rfidReader = new RFIDReader();

    /**
     * Default constructor.
     *
     * @param rosterService RosterService
     */
    public ApplicationFrame(final RosterService rosterService) {
        this.rosterService = rosterService;
        mainMenuPanel = new MainMenuPanel(this, TestMenuPanel::new);
        mainMenuPanel.displayPanel();
        headerPanel = new HeaderPanel(this, mainMenuPanel.getMenuNavigationPanel());

        add(headerPanel, BorderLayout.NORTH);
        //add(buildLayeredPane(), BorderLayout.CENTER);
        add(mainMenuPanel, BorderLayout.CENTER);
        add(new JLabel("This device can be used to check your membership status, assign or update an assignment " +
                "of an RFID card, and perform other administrative tasks.  Certain functions may only be performed " +
                "by authorized users."), BorderLayout.SOUTH);

        setUndecorated(Boolean.TRUE);
        setAlwaysOnTop(Boolean.TRUE);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //pack();
        setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    /**
     * Builds a layered panel for alternating between panels.
     *
     * @return JLayeredPane
     */
    private JLayeredPane buildLayeredPane() {
        final JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new BoxLayout(layeredPane, BoxLayout.Y_AXIS));

        mainMenuPanel = new MainMenuPanel(this);
        mainMenuPanel.setVisible(Boolean.TRUE);

        adminFunctionsPanel = new AdminFunctionsPanel(this);
        adminFunctionsPanel.setVisible(Boolean.FALSE);

        memberPanel = new MemberPanel(this);
        memberPanel.setVisible(Boolean.FALSE);

        layeredPane.add(mainMenuPanel, 2);
        layeredPane.add(adminFunctionsPanel, 1);
        layeredPane.add(memberPanel, 0);

        return layeredPane;
    }

    /**
     * Switches between panels.
     *
     * @param panelToDisplay String
     */
    public void switchPanel(final String panelToDisplay) {
        if (ApplicationConstants.ADMIN.equalsIgnoreCase(panelToDisplay)) {
            System.out.println("Fetching RFID from reader...");
            final String rfid = rfidReader.getRFID();
            System.out.println("retrieved ["+rfid+"] from reader.");
            try {
                Runtime.getRuntime().exec("matchbox-keyboard");
            } catch (IOException ioe) {
                System.out.println("Error: " + ioe.getMessage());
            }
            if (rosterService.isAdmin(rfid)) {
                mainMenuPanel.setVisible(Boolean.FALSE);
                adminFunctionsPanel.setVisible(Boolean.TRUE);
            } else {
                final String msg = String.format("RFID [%s] is not an admin user", rfid);
                System.out.println(msg);
                JOptionPane.showMessageDialog(null, msg);
            }
        }
        if (ApplicationConstants.MAIN_MENU.equalsIgnoreCase(panelToDisplay)) {
            mainMenuPanel.setVisible(Boolean.TRUE);
            adminFunctionsPanel.setVisible(Boolean.FALSE);
        }
    }

    public void switchMenu(MenuPanel menuPanel) {
        menuPanel.displayPanel();
        add(menuPanel, BorderLayout.CENTER);
        headerPanel.setMenuNavigationPanel(menuPanel.getMenuNavigationPanel());
        revalidate();
    }

}
