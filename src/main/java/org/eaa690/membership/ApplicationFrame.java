package org.eaa690.membership;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.eaa690.membership.panels.AdminFunctionsPanel;
import org.eaa690.membership.panels.MainMenuPanel;
import org.eaa690.membership.panels.MemberPanel;
import org.eaa690.membership.panels.MembershipStatusPanel;
import org.eaa690.membership.panels.TitlePanel;
import org.eaa690.membership.service.RosterService;
import org.eaa690.membership.util.RFIDListener;

/**
 * ApplicationFrame
 */
public class ApplicationFrame extends JFrame {

    /**
     * Title panel.
     */
    private final TitlePanel titlePanel = new TitlePanel();

    /**
     * Main menu panel.
     */
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

    private List<JPanel> menuPanels = new ArrayList<JPanel>();

    private List<JPanel> dynamicMenuPanels = new ArrayList<JPanel>();

    private JLayeredPane layeredPane;

    /**
     * Default constructor.
     *
     * @param rosterService RosterService
     */
    public ApplicationFrame(final RosterService rosterService) {
        this.rosterService = rosterService;

        add(titlePanel, BorderLayout.NORTH);
        layeredPane = buildLayeredPane();
        add(layeredPane, BorderLayout.CENTER);
        add(new JLabel("This device can be used to check your membership status, assign or update an assignment " +
                "of an RFID card, and perform other administrative tasks.  Certain functions may only be performed " +
                "by authorized users."), BorderLayout.SOUTH);

        setUndecorated(Boolean.TRUE);
        setAlwaysOnTop(Boolean.TRUE);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        //pack();
        setExtendedState(Frame.MAXIMIZED_BOTH);

        this.menuPanels.add(mainMenuPanel);
        this.menuPanels.add(adminFunctionsPanel);
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
            final String rfid = MembershipApp.rfidListener.getRfid();
            System.out.println("retrieved ["+rfid+"] from reader.");
            if (rosterService.isAdmin(rfid)) {
                setPanelVisible(adminFunctionsPanel);
            } else {
                final String msg = String.format("RFID [%s] is not an admin user", rfid);
                System.out.println(msg);
                JOptionPane.showMessageDialog(null, msg);
            }
        }
        if (ApplicationConstants.MAIN_MENU.equalsIgnoreCase(panelToDisplay)) {
            setPanelVisible(mainMenuPanel);
        }
        if (ApplicationConstants.MEMBERSHIP_STATUS.equalsIgnoreCase(panelToDisplay)) {
            MembershipStatusPanel membershipStatusPanel = new MembershipStatusPanel(this);
            setPanelVisible(membershipStatusPanel);
        }
    }

    public void setPanelVisible(JPanel panel) {
        while (dynamicMenuPanels.size() > 0) {
            dynamicMenuPanels.get(0).setVisible(Boolean.FALSE);
            layeredPane.remove(dynamicMenuPanels.get(0));
            dynamicMenuPanels.remove(0);
        }
        for (JPanel p : menuPanels) {
            p.setVisible(Boolean.FALSE);
        }
        if (! menuPanels.contains(panel)) {
            layeredPane.add(panel);
            dynamicMenuPanels.add(panel);
        }
        panel.setVisible(Boolean.TRUE);
    }

}
