package org.eaa690.membership.util;

import org.apache.commons.lang3.StringUtils;

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
        while (Boolean.TRUE) {
            String readLine = in.nextLine();
            if (StringUtils.isNotEmpty(readLine)) {
                lastRead = readLine;
                System.out.println("You entered string: " + lastRead);
            }
        }
    }

    public String getLastRead() {
        return lastRead;
    }
}
