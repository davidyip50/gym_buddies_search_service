package search.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchGymbuddyReponseModel {
    private String message;
    private int resultCode;
    private GymBuddy[] gymBuddies;

    public SearchGymbuddyReponseModel(@JsonProperty(value = "message",required = true) String message,
                                      @JsonProperty(value = "resultCode", required = true) int resultCode,
                                      @JsonProperty(value = "gymbuddies") GymBuddy[] gymBuddies) {
        this.message = message;
        this.resultCode = resultCode;
        this.gymBuddies = gymBuddies;
    }

    public SearchGymbuddyReponseModel(@JsonProperty(value = "message",required = true) String message,
                                      @JsonProperty(value = "resultCode", required = true) int resultCode) {
        this.message = message;
        this.resultCode = resultCode;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("resultCode")
    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    @JsonProperty("gymBuddies")
    public GymBuddy[] getGymBuddies() {
        return gymBuddies;
    }

    public void setGymBuddies(GymBuddy[] gymBuddies) {
        this.gymBuddies = gymBuddies;
    }
}
