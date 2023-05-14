package github.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;

public class CommitCommitter {

    @JsonProperty("name")
    @NotEmpty(message = "Author name cannot be empty.")
    private String committerName;

    @JsonProperty("email")
    private String committerEmail;

    @JsonProperty("date")
    @NotEmpty(message = "Author date cannot be empty.")
    private String committedDate;

    public String getCommitterName() {
        return committerName;
    }

    public String getCommitterEmail() {
        return committerEmail;
    }

    public String getCommittedDate() {
        return committedDate;
    }

}
