package fr.revoicechat.voice.web;

import java.util.Map;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import fr.revoicechat.voice.socket.stream.StreamWebSocket;
import fr.revoicechat.voice.socket.voice.VoiceWebSocket;

/**
 * Here for documentation purpose.
 *
 * @see VoiceWebSocket
 * @see StreamWebSocket
 */
@Path("/ws")
@Tag(name = "WebSocket")
@Produces(MediaType.APPLICATION_JSON)
public class WsDocResource {

  @GET
  @Path("/voice")
  @Operation(summary = "WebSocket endpoint for voice",
      description = """
          Connect via `ws://*url*/api/voice/{roomId}?token={jwtToken}`.
            - Text messages: JSON control
            - Binary messages: audio chunks
          @param roomId: id of the room. it must be of type "VOICE"
          @param token: needed to know which user is connected. it can also be given in a subsubject for mor secure connection""")
  public Response wsVoiceInfo() {
    return Response.ok(Map.of("url", "ws://*url*/api/voice/{roomId}?token={jwtToken}")).build();
  }

  @GET
  @Path("/voice")
  @Operation(summary = "WebSocket endpoint for voice",
      description = """
          Connect via `ws://*url*/api/voice/{roomId}?token={jwtToken}`.
            - Text messages: JSON control
            - Binary messages: audio or video chunks
          @param userId: id of the user that is streaming something. it is mostly a webcam or a screen share.
          @param name:   name of the stream. it's a technical param that allow a user to browse multiple stream at the same time.
                         With that a user can stream his webcam, at the same time of all his screens, and another user can watch only a specific stream""")
  public Response wsStreamInfo() {
    return Response.ok(Map.of("url", "ws://*url*/api/stream/{userId}/{name}")).build();
  }
}
