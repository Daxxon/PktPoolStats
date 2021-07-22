package com.github.daxxon.PKTStats.Controllers;

import com.github.daxxon.PKTStats.HTTPClient;
import com.github.daxxon.PKTStats.PoolStatResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

  @GetMapping("/")
  public PoolStatResponse home(Model model) throws Exception {
    HTTPClient httpClient = new HTTPClient();
    return httpClient.getPoolStats();
  }
}