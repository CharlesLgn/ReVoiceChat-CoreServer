package fr.revoicechat.core.representation.message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import fr.revoicechat.core.representation.media.CreatedMediaDataRepresentation;

public record CreatedMessageRepresentation(
    String text,
    UUID answerTo,
    List<CreatedMediaDataRepresentation> medias) {

  public CreatedMessageRepresentation(
      String text,
      UUID answerTo,
      List<CreatedMediaDataRepresentation> medias) {
    this.text = Optional.ofNullable(text).map(String::trim).orElse("");
    this.answerTo = answerTo;
    this.medias = Optional.ofNullable(medias).orElse(List.of());
  }
}
