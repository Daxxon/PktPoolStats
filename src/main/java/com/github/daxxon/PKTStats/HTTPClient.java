package com.github.daxxon.PKTStats;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HTTPClient {

  // one instance, reuse
  private final HttpClient httpClient = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2)
    .build();

  public PoolStatResponse getPoolStats(String myUri) throws Exception {

    HttpRequest request = HttpRequest.newBuilder()
      .GET()
      .uri(URI.create(myUri))
      .setHeader("User-Agent", "Java 11 HttpClient Bot")
      .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    // print status code
    System.out.println(response.statusCode());

    // print response body
    String body = response.body();
    System.out.println(body);
    ObjectMapper objectMapper = new ObjectMapper();
    PoolStatResponse poolStatResponse = objectMapper.readValue(body, PoolStatResponse.class);
    return poolStatResponse;

  }

  public HashMap<String, Integer> getPoolStats() throws Exception {
    HashMap<String, Integer> encryptionsPerSecondByPool = new HashMap<String, Integer>();
    encryptionsPerSecondByPool.put("pktco", this.getPoolStats("http://paymaker.pktco.in/whotopay").getTotalEncryptionsPerSecond());
    return encryptionsPerSecondByPool;
  };

  public static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
    var builder = new StringBuilder();
    for (Map.Entry<Object, Object> entry : data.entrySet()) {
      if (builder.length() > 0) {
        builder.append("&");
      }
      builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
      builder.append("=");
      builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
    }
    System.out.println(builder.toString());
    return HttpRequest.BodyPublishers.ofString(builder.toString());
  }

}