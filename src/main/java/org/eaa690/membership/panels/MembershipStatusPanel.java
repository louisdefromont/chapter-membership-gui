package org.eaa690.membership.panels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eaa690.membership.model.Member;
import org.eaa690.membership.util.RFIDReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MembershipStatusPanel extends JPanel implements ActionListener {

    private ObjectMapper mapper = new ObjectMapper();

    private JButton closeButton = new JButton("Close");

    private String serviceURL = "";

    private HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public MembershipStatusPanel() {
        try {
            final Member member = getMember();
        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            e.printStackTrace();
        }
        add(closeButton);
    }

    private Member getMember() throws ExecutionException, InterruptedException, JsonProcessingException {
        RFIDReader reader = new RFIDReader();
        String rfid = reader.getLastRead();
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "/roster/find-by-rfid"))
                .GET().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        response.thenAccept(res -> System.out.println(res));
        Member member = mapper.readValue(response.get().body(), Member.class);
        response.join();
        return member;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            setVisible(Boolean.FALSE);
        }
    }
}
