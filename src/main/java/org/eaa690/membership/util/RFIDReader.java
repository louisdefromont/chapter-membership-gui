package org.eaa690.membership.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Reads input from an RFID reader.
 *
 * Implemented as a thread to allow for the reader to operate independently of the application.
 */
public class RFIDReader extends Thread {

    /**
     * Reads data from System.in.
     */
    private Scanner in = new Scanner(System.in);

    /**
     * Last value read by scanner.
     */
    private String lastRead = null;

    /**
     * Default constructor.
     */
    public RFIDReader() { }

    /**
     * Infinite loop to read input.
     */
    public void run() {
        try {
            Runtime.getRuntime().exec("sudo python main.py");
        } catch (IOException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        }
    }

    public String getLastRead() {
        String currentLine = null;
        try {
            String file ="/home/pi/chapter-membership-gui/rfid.last";

            BufferedReader reader = new BufferedReader(new FileReader(file));
            currentLine = reader.readLine();
            reader.close();
        } catch (IOException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        }
        return currentLine;
    }
}
