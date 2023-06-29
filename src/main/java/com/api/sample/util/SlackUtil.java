package com.api.sample.util;


import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class SlackUtil {

    @Value("${slack.webhook.url}")
    private String webhookUrl;

    public void send(String msg) throws IOException, JSONException {
        URL url = new URL(webhookUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        con.setDoOutput(true);

        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put("text", msg);

        try (OutputStream os = con.getOutputStream()) {
            os.write(jsonMessage.toString().getBytes(StandardCharsets.UTF_8));
            os.flush();
        }

        con.getResponseCode();
        con.disconnect();
    }
}
