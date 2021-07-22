package com.github.daxxon.PKTStats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PoolStatResponse {

  private int totalEncryptionsPerSecond;

  public int getTotalEncryptionsPerSecond() {
    return totalEncryptionsPerSecond;
  }

  public void setTotalEncryptionsPerSecond(int totalEncryptionsPerSecond) {
    this.totalEncryptionsPerSecond = totalEncryptionsPerSecond;
  }

  @Override
  public String toString() {
    return "PoolStatResponse{" +
      "totalEncryptionsPerSecond=" + totalEncryptionsPerSecond +
      '}';
  }
}
