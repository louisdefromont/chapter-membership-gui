package org.eaa690.membership;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {

    private JLabel textLabel = new JLabel("This device can be used to check your membership status, assign or update an assignment of an RFID card, and perform other administrative tasks.  Certain functions may only be performed by authorized users.");

    private TitlePanel titlePanel = new TitlePanel();

    private ButtonPanel buttonPanel = new ButtonPanel();

    public ApplicationFrame() {
        add(titlePanel, BorderLayout.NORTH);
        add(textLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setUndecorated(Boolean.TRUE);
        setAlwaysOnTop(Boolean.TRUE);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
    }
}
