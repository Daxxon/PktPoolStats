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
import java.util.Map;

public class HTTPClient {

  // one instance, reuse
  private final HttpClient httpClient = HttpClient.newBuilder()
    .version(HttpClient.Version.HTTP_2)
    .build();

  public PoolStatResponse sendGet(String myUri) throws Exception {

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

  public JSONObject getPoolStats(){
    this.sendGet("http://paymaker.pktco.in/whotopay");
    this.sendGet("http://pool.pkteer.com");
    this.sendGet("ttp://pool.pktpool.io");
    this.sendGet("http://pool.pkt.world");
    this.sendGet("http://pool.srizbi.com");
    this.sendGet("http://noworries.tech/pool");
    JSONObject poolJSON = new JSONObject();

    return poolJSON;
  };

//  public void sendPost() throws Exception {
//
//    // form parameters
//    Map<Object, Object> data = new HashMap<>();
//    data.put("username", "abc");
//    data.put("password", "123");
//    data.put("custom", "secret");
//    data.put("ts", System.currentTimeMillis());
//
//    HttpRequest request = HttpRequest.newBuilder()
//      .POST(buildFormDataFromMap(data))
//      .uri(URI.create("http://paymaker.pktco.in/whotopay"))
//      .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
//      .header("Content-Type", "application/x-www-form-urlencoded")
//      .build();
//
//    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//
//    // print status code
//    System.out.println(response.statusCode());
//
//    // print response body
//    System.out.println(response.body());
//
//  }

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