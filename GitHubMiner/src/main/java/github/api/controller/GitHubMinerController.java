package github.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import github.api.model.Project;
import github.api.service.GitHubMinerService;

import java.util.Optional;

@RestController
@RequestMapping("/github")
public class GitHubMinerController {

    @Autowired
    GitHubMinerService repository;

    @GetMapping("/{ownerId}/{projectId}")
    public Project findProject(@PathVariable String ownerId, @PathVariable String projectId, @RequestParam(value = "sinceCommits") Optional<Integer> sinceCommits, @RequestParam("sinceIssues") Optional<Integer> sinceIssues, @RequestParam("maxPages") Optional<Integer> maxPages) {
        return repository.findProject(ownerId, projectId, sinceCommits, sinceIssues, maxPages);
    }

    @PostMapping("/{ownerId}/{projectId}")
    public Project create(@PathVariable String ownerId, @PathVariable String projectId, @RequestParam(value = "sinceCommits") Optional<Integer> sinceCommits, @RequestParam("sinceIssues") Optional<Integer> sinceIssues, @RequestParam("maxPages") Optional<Integer> maxPages) {
        return repository.postProject(repository.findProject(ownerId, projectId, sinceCommits, sinceIssues, maxPages));
    }

}
