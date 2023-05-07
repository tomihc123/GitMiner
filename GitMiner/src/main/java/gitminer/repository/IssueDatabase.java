package gitminer.repository;

import gitminer.model.Commit;
import gitminer.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueDatabase extends JpaRepository<Issue, String> {
    List<Issue> findByAuthorId(String authorId);

    List<Issue> findByState(String state);

}