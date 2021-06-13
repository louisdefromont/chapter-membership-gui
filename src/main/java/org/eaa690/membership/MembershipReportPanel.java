package org.eaa690.membership;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MembershipReportPanel extends JPanel implements ActionListener {

    private JButton closeButton = new JButton("Close");

    HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();

    public MembershipReportPanel() {
        final MembershipReport report = getMembershipReport();
        add(closeButton);
    }

    private MembershipReport getMembershipReport() {
        HttpRequest req = HttpRequest.newBuilder(URI.create(serviceURL+"getDetails"))
            .GET().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, BodyHandlers.ofString());
        response.thenAccept(res -> System.out.println(res));
        MembershipReport report = convertFromJsonToList(response.get().body(), new TypeReference<MembershipReport>() {});
        response.join();
        return report;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            setVisible(Boolean.FALSE);
        }
    }

    private <T> List<T> convertFromJsonToList(String json, TypeReference<List<T>> var) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, var);
    }

    private <T> T covertFromJsonToObject(String json, Class<T> var) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, var);//Convert Json into object of Specific Type
    }

    private String covertFromObjectToJson(Object obj) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
