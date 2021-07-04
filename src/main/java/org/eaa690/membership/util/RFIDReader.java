package org.eaa690.membership.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Reads input from an RFID reader.
 */
public class RFIDReader {

    /**
     * Default constructor.
     */
    public RFIDReader() { }

    /**
     * Timeboxed read of RFID from RFID card reader.
     *
     * @return read RFID value
     */
    public String getRFID() {
        try {
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
            Future<String> future = executor.submit(this::getRFIDValue);
            executor.schedule(() -> {
                future.cancel(true);
            }, 10000, TimeUnit.MILLISECONDS);
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("getRFID() Error: " + e.getMessage());
        }
        return null;
    }

    private String getRFIDValue() {
        String line = null;
        BufferedReader in = null;
        try {
            final Process process = Runtime.getRuntime().exec("python main.py");
            in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (StringUtils.isEmpty(line)) {
                line = in.readLine();
            }
        } catch (IOException ioe) {
            System.out.println("getRFIDValue() Error: " + ioe.getMessage());
        } finally {
            try { Objects.requireNonNull(in).close(); } catch (Exception ignored) {}
        }
        return line;
    }

}
