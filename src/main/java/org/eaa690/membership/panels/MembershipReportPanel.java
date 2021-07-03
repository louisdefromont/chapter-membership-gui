package org.eaa690.membership.panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eaa690.membership.model.MembershipReport;

public class MembershipReportPanel extends JPanel implements ActionListener {

    private ObjectMapper mapper = new ObjectMapper();

    private JButton closeButton = new JButton("Close");

    private String serviceURL = "";

    private HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public MembershipReportPanel() {
        try {
            final MembershipReport report = getMembershipReport();
        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            e.printStackTrace();
        }
        add(closeButton);
    }

    private MembershipReport getMembershipReport()
            throws ExecutionException, InterruptedException, JsonProcessingException {
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL + "/roster/report"))
            .GET().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());
        response.thenAccept(res -> System.out.println(res));
        MembershipReport report = mapper.readValue(response.get().body(), MembershipReport.class);
        response.join();
        return report;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            setVisible(Boolean.FALSE);
        }
    }

}
