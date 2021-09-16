package org.eaa690.membership.panels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.eaa690.membership.ApplicationConstants;
import org.eaa690.membership.ApplicationFrame;
import org.eaa690.membership.MembershipApp;
import org.eaa690.membership.model.FindByRFIDResponse;
import org.eaa690.membership.model.Member;
import org.eaa690.membership.util.RFIDListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MembershipStatusPanel extends JPanel implements ActionListener {

    private ApplicationFrame applicationFrame;

    private ObjectMapper mapper = new ObjectMapper();

    private JButton closeButton = new JButton("Close");

    private String serviceURL = "http://127.0.0.1:8080";

    private HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public MembershipStatusPanel(ApplicationFrame applicationFrame) {
        this.applicationFrame = applicationFrame;
        try {
            final Member member = getMember();
            if (member != null) {
                JLabel nameLabel = new JLabel(String.format("Name: %s", member.getName()));
                JLabel expirationDateLabel = new JLabel(String.format("Expiration Date: %s", member.getExpirationDate().toString()));
                add(nameLabel);
                add(expirationDateLabel);
            } else {
                JLabel errorLabel = new JLabel("Error getting member information.");
                add(errorLabel);
            }
        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            e.printStackTrace();
        }
        closeButton.addActionListener(this);
        add(closeButton);
    }

    private Member getMember() throws ExecutionException, InterruptedException, JsonProcessingException {
        try {
            String rfid = MembershipApp.rfidListener.getRfid();
            String body = String.format("{\"rfid\": \"%s\"}", rfid);
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest findByRFID = HttpRequest.newBuilder(URI.create(serviceURL + "/roster/find-by-rfid")).header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(body)).build();

            HttpResponse<String> findByRFIDHttpResponse = client.send(findByRFID, BodyHandlers.ofString());
            FindByRFIDResponse findByRFIDResponse = mapper.readValue(findByRFIDHttpResponse.body(), FindByRFIDResponse.class);

            HttpRequest getMemberData = HttpRequest.newBuilder(URI.create(serviceURL + String.format("/roster/%d/expiration", findByRFIDResponse.getRosterId())))
                .GET().build();
            CompletableFuture<HttpResponse<String>> getMemberDataResponse = client.sendAsync(getMemberData, HttpResponse.BodyHandlers.ofString());
            getMemberDataResponse.thenAccept(res -> System.out.println(res));
            Member member = mapper.readValue(getMemberDataResponse.get().body(), Member.class);
            getMemberDataResponse.join();
            return member;
        } catch (IOException e) {
            System.out.println("Error connecting to aerie");
        }

        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            applicationFrame.switchPanel(ApplicationConstants.MAIN_MENU);
        }
    }
}
