package gitminer.repository;

import gitminer.model.Comment;
import gitminer.model.Commit;
import gitminer.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDatabase extends JpaRepository<Comment, String> {}