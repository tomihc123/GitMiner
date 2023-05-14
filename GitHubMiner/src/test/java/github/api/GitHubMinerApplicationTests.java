package gitlab.api;

import gitlab.api.model.Project;
import gitlab.api.repository.GitHubMinerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GitLabMinerApplicationTests {

	@Autowired
    GitHubMinerRepository gitlabService;

	@Test
	void findProject() {
		Project project = gitlabService.findProject("4207231", "dksn", Optional.of(2), Optional.of(20), Optional.of(20));
		assertTrue(project != null, "The list of commits is empty!!!");
		System.out.println(project);
	}

}
