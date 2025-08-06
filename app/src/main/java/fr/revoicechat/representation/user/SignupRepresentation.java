package fr.revoicechat.representation.user;

public record SignupRepresentation(
    String username,
    String password,
    String email
) {
}
