package gitlab.api.controller;

import gitlab.api.model.Project;
import gitlab.api.service.GitLabMinerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/gitlab")
public class GitLabMinerController {

    @Autowired
    GitLabMinerService service;

    @GetMapping("/{projectId}")
    public Project findProject(@PathVariable String projectId, @RequestParam(value = "sinceCommits") Optional<Integer> sinceCommits, @RequestParam("sinceIssues") Optional<Integer> sinceIssues, @RequestParam("maxPages") Optional<Integer> maxPages) {
        return service.findProject(projectId, sinceCommits, sinceIssues, maxPages);
    }

    @PostMapping("/{projectId}")
    public Project create(@PathVariable String projectId, @RequestParam(value = "sinceCommits") Optional<Integer> sinceCommits, @RequestParam("sinceIssues") Optional<Integer> sinceIssues, @RequestParam("maxPages") Optional<Integer> maxPages) {
        return service.postProject(service.findProject(projectId, sinceCommits, sinceIssues, maxPages));
    }

}
