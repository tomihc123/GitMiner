package gitlab.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class IssueAux {

    @JsonProperty("id")
    private String id;

    @JsonProperty("node_id")
    private String refId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("body")
    private String description;

    @JsonProperty("state")
    private String state;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("closed_at")
    private String closedAt;

    @JsonProperty("user")
    private User author;

    @JsonProperty("assignee")
    private User assignee;

    @JsonProperty("reactions")
    private Reactions reactions;

    @JsonProperty("url")
    private String webUrl;

    public String getId() {
        return id;
    }

    public String getRefId() {
        return refId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Reactions getReactions() {
        return reactions;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public User getAuthor() {
        return author;
    }

    public User getAssignee() {
        return assignee;
    }

    public String getWebUrl() {
        return webUrl;
    }

}
