package github.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import github.api.model.Project;
import github.api.service.GitHubMinerService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GitHubMinerApplicationTests {

	@Autowired
    GitHubMinerService githubService;

	@Test
	void shouldFindTheProject() {
		Project project = githubService.findProject("ignaciosige", "PRY-EJ02-ajedrez", Optional.of(2), Optional.of(20), Optional.of(2));
		assertTrue(project != null, "Could not find the project");
		System.out.println(project);
	}

}
