package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MChatMessage {
  @JsonProperty("message")
  private String chatMessage;

  @JsonProperty("username")
  private String userName;

  public String getChatMessage() {
    return chatMessage;
  }

  public void setChatMessage(String chatMessage) {
    this.chatMessage = chatMessage;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
