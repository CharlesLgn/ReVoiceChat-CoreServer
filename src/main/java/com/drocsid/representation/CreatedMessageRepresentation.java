package com.drocsid.representation;

import java.time.LocalDateTime;
import java.util.UUID;

import com.drocsid.model.Message;

public record CreatedMessageRepresentation(String text) {
  public Message toEntity() {
    var message = new Message();
    message.setId(UUID.randomUUID());
    message.setText(text);
    message.setCreatedDate(LocalDateTime.now());
    return message;
  }
}
