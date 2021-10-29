package com.github.daxxon.PKTStats.Controllers;

import com.github.daxxon.PKTStats.HTTPClient;
import com.github.daxxon.PKTStats.PoolStatResponse;
import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class WebController {

  /*
  Returns the hashes per second per pool
  should return a json that looks like:
  {
    "pktco": 123378912638921739
    "other": 23409328403982
    "other2": 324039284302984
  }
  */
  @GetMapping("/")
  public HashMap<String, Integer> home(Model model) throws Exception {
    HTTPClient httpClient = new HTTPClient();
    return httpClient.getPoolStats();
  }
}