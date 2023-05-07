
package gitminer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;


import java.util.List;

@Entity
@Table(name = "Issue")
public class Issue {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("ref_id")
    private String refId;

    @JsonProperty("title")
    @Lob
    @Column(columnDefinition = "LONGTEXT", length = 5000)
    private String title;

    @JsonProperty("description")
    @Lob
    @Column(columnDefinition = "LONGTEXT", length = 5000)
    private String description;

    @JsonProperty("state")
    private String state;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("closed_at")
    private String closedAt;

    @JsonProperty("labels")
    @ElementCollection
    private List<String> labels;

    @JsonProperty("author")
    //@NotEmpty(message = "The author of the issue cannot be empty")
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private User author;

    @JsonProperty("assignee")
    @JoinColumn(name = "assignee_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private User assignee;

    @JsonProperty("upvotes")
    private Integer upvotes;

    @JsonProperty("downvotes")
    private Integer downvotes;

    @JsonProperty("web_url")
    private String webUrl;

    @JsonProperty("comments")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "issueId")
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Issue.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("refId");
        sb.append('=');
        sb.append(((this.refId == null) ? "<null>" : this.refId));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null) ? "<null>" : this.title));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null) ? "<null>" : this.description));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null) ? "<null>" : this.state));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null) ? "<null>" : this.createdAt));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null) ? "<null>" : this.updatedAt));
        sb.append(',');
        sb.append("closedAt");
        sb.append('=');
        sb.append(((this.closedAt == null) ? "<null>" : this.closedAt));
        sb.append(',');
        sb.append("labels");
        sb.append('=');
        sb.append(((this.labels == null) ? "<null>" : this.labels));
        sb.append(',');
        sb.append("author");
        sb.append('=');
        sb.append(((this.author == null) ? "<null>" : this.author));
        sb.append(',');
        sb.append("assignee");
        sb.append('=');
        sb.append(((this.assignee == null) ? "<null>" : this.assignee));
        sb.append(',');
        sb.append("upvotes");
        sb.append('=');
        sb.append(((this.upvotes == null) ? "<null>" : this.upvotes));
        sb.append(',');
        sb.append("downvotes");
        sb.append('=');
        sb.append(((this.downvotes == null) ? "<null>" : this.downvotes));
        sb.append(',');
        sb.append("comments");
        sb.append('=');
        sb.append(((this.comments == null) ? "<null>" : this.comments));
        sb.append(',');

        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
