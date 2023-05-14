package github.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class CommitAuthor {

    @JsonProperty("name")
    @NotEmpty(message = "Author name cannot be empty.")
    private String authorName;

    @JsonProperty("email")
    private String authorEmail;

    @JsonProperty("date")
    @NotEmpty(message = "Author date cannot be empty.")
    private String authoredDate;

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public String getAuthoredDate() {
        return authoredDate;
    }

}
