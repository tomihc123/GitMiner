package gitlab.api.controller;

import gitlab.api.model.Project;
import gitlab.api.repository.GitLabMinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/gitlab")
public class GitLabMinerController {

    @Autowired
    GitLabMinerRepository repository;

    @GetMapping("/{projectId}")
    public Project findProject(@PathVariable String projectId, @RequestParam(value = "sinceCommits") Optional<Integer> sinceCommits, @RequestParam("sinceIssues") Optional<Integer> sinceIssues, @RequestParam("maxPages") Optional<Integer> maxPages) {
        return repository.findProject(projectId, sinceCommits, sinceIssues, maxPages);
    }

    @PostMapping("/{projectId}")
    public Project create(@PathVariable String projectId, @RequestParam(value = "sinceCommits") Optional<Integer> sinceCommits, @RequestParam("sinceIssues") Optional<Integer> sinceIssues, @RequestParam("maxPages") Optional<Integer> maxPages) {
        return repository.postProject(repository.findProject(projectId, sinceCommits, sinceIssues, maxPages));
    }

}
