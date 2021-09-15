package org.eaa690.membership.util;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class RFIDListener implements NativeKeyListener {

    private String rawInput;
    private String rfid;

    public RFIDListener() {
        rawInput = "";
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeEvent) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        String keyPressed = NativeKeyEvent.getKeyText(nativeEvent.getKeyCode());

        if (keyPressed.equals("Enter")) {
            rfid = rawInput;
            rawInput = "";
        } else {
            try {
                Integer.parseInt(keyPressed);
                rawInput += keyPressed;
            } catch (NumberFormatException e) {
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
    }

    public String getRfid() {
        return rfid;
    }
}
