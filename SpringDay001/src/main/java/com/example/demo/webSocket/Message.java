package com.example.demo.webSocket;

import lombok.Data;

@Data
public class Message {

  private String name;
  private String statement;

  public Message(String name, String statement) {
      this.name = name;
      this.statement = statement;
  }
}