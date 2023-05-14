package github.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reactions {

    @JsonProperty("+1")
    private Integer upvotes;

    @JsonProperty("-1")
    private Integer downvotes;

    public Integer getUpvotes() {
        return upvotes;
    }

    public Integer getDownvotes() {
        return downvotes;
    }

}
