package fr.revoicechat.risk.web.api;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import fr.revoicechat.risk.representation.ServerRoleRepresentation;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Tag(name = "Role", description = "Endpoints for managing roles")
@Path("server/{id}/role")
public interface ServerRoleController {

  @Operation(summary = "Get role of a server", description = "Get role list of a specific server")
  @APIResponse(responseCode = "200", description = "Role successfully retrieved")
  @APIResponse(
      responseCode = "404",
      description = "Room not found",
      content = @Content(
          mediaType = "text/plain",
          schema = @Schema(implementation = String.class, examples = "Server not found")
      )
  )
  @GET
  List<ServerRoleRepresentation> getByServer(@PathParam("id") UUID serverId);
}
