package org.eaa690.membership;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eaa690.membership.service.RosterService;
import org.eaa690.membership.util.RFIDListener;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class MembershipApp {

    public static RFIDListener rfidListener;

    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(MembershipApp.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            System.out.println("Error loading properties: " + e);
        }

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        try {
            GlobalScreen.registerNativeHook();
            rfidListener = new RFIDListener();
            GlobalScreen.addNativeKeyListener(rfidListener);
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        RosterService rosterService = new RosterService(props);

        ApplicationFrame frame = new ApplicationFrame(rosterService);
        frame.setVisible(Boolean.TRUE);
    }
}
