package fr.revoicechat.nls;

public enum ServerErrorCode implements LocalizedMessage {
  APPLICATION_DOES_NOT_ALLOW_SERVER_CREATION;

  @Override
  public String toString() {
    return translate();
  }
}
