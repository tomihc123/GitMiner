package gitlab.api.controller;

import gitlab.api.model.Project;
import gitlab.api.repository.GitHubMinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/github")
public class GitHubMinerController {

    @Autowired
    GitHubMinerRepository repository;

    @GetMapping("/{ownerId}/{projectId}")
    public Project findProject(@PathVariable String ownerId, @PathVariable String projectId, @RequestParam(value = "sinceCommits") Optional<Integer> sinceCommits, @RequestParam("sinceIssues") Optional<Integer> sinceIssues, @RequestParam("maxPages") Optional<Integer> maxPages) {
        return repository.findProject(ownerId, projectId, sinceCommits, sinceIssues, maxPages);
    }

    @PostMapping("/{ownerId}/{projectId}")
    public Project create(@PathVariable String ownerId, @PathVariable String projectId, @RequestParam(value = "sinceCommits") Optional<Integer> sinceCommits, @RequestParam("sinceIssues") Optional<Integer> sinceIssues, @RequestParam("maxPages") Optional<Integer> maxPages) {
        return repository.postProject(repository.findProject(ownerId, projectId, sinceCommits, sinceIssues, maxPages));
    }

}
