package gitminer.repository;

import gitminer.model.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitDatabase extends JpaRepository<Commit, String> {
    List<Commit> findByAuthorEmail(String email);

}
