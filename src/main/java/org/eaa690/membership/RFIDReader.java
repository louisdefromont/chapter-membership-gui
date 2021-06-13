package org.eaa690.membership;

import java.util.Scanner;

public class RFIDReader {

    private String lastRead = null;

    public RFIDReader() {
        Scanner in = new Scanner(System.in);
        lastRead = in.nextLine();
        System.out.println("You entered string: " + lastRead);
    }

    public String getLastRead() {
        return lastRead;
    }
}
