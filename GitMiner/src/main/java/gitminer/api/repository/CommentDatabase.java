package gitminer.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gitminer.api.model.Comment;
import gitminer.api.model.Commit;
import gitminer.api.model.Issue;

import java.util.List;

@Repository
public interface CommentDatabase extends JpaRepository<Comment, String> {}