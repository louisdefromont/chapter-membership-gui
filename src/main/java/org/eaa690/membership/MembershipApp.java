package org.eaa690.membership;

import org.eaa690.membership.service.RosterService;
import org.eaa690.membership.util.RFIDReader;

import java.io.IOException;
import java.util.Properties;

public class MembershipApp {

    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(MembershipApp.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            System.out.println("Error loading properties: " + e);
        }

        RosterService rosterService = new RosterService(props);

        ApplicationFrame frame = new ApplicationFrame(rosterService);
        frame.setVisible(Boolean.TRUE);
    }
}
