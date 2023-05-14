package github.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommitAux {

    @JsonProperty("author")
    private CommitAuthor author;

    @JsonProperty("committer")
    private CommitCommitter committer;

    @JsonProperty("message")
    private String message;

    public CommitAuthor getAuthor() {
        return author;
    }

    public void setAuthor(CommitAuthor author) {
        this.author = author;
    }

    public CommitCommitter getCommitter() {
        return committer;
    }

    public String getMessage() {
        return message;
    }

}
