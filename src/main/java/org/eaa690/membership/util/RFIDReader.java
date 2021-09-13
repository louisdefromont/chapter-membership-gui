package org.eaa690.membership.util;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

/**
 * Reads input from an RFID reader.
 */
public class RFIDReader {

    private RFIDListener rfidListener;

    /**
     * Default constructor.
     */
    public RFIDReader() { 
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        try {
            GlobalScreen.registerNativeHook();
            rfidListener = new RFIDListener();
            GlobalScreen.addNativeKeyListener(rfidListener);
        } catch (NativeHookException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

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
            }, 15000, TimeUnit.MILLISECONDS);
            executor.shutdown();
            return future.get();
        } catch (ExecutionException | InterruptedException | CancellationException e) {
            System.out.println("getRFID() Error: " + e.getMessage());
        }
        return null;
    }

    private String getRFIDValue() {
        return rfidListener.getRfid();
    }

}
