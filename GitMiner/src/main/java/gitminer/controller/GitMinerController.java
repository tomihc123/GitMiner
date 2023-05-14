package gitminer.controller;

import gitminer.model.Comment;
import gitminer.model.Commit;
import gitminer.model.Issue;
import gitminer.model.Project;
import gitminer.service.GitMinerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer")
public class GitMinerController {
    @Autowired
    GitMinerService repository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/projects")
    public Project saveProject(@Valid @RequestBody Project project) {
        repository.saveProject(project);
        return project;
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return repository.getAllProjects();
    }

    @GetMapping("/projects/{projectId}")
    public Project getProject(@PathVariable String projectId) {
        return repository.findProject(projectId).get();
    }

    @GetMapping("/commits")
    public List<Commit> getAllCommits(@RequestParam(value = "email") Optional<String> email) {
        List<Commit> commits;
        if (email.isPresent()) {
            commits = repository.getCommitByEmail(email.get());
        } else {
            commits = repository.getAllCommits();
        }
        return commits;
    }

    @GetMapping("/commits/{commitId}")
    public Commit getCommitById(@PathVariable String commitId) {
        return repository.getCommit(commitId).get();
    }

    @GetMapping("/issues")
    public List<Issue> getAllIssues(@RequestParam(value = "authorId") Optional<String> authorId, @RequestParam(value = "state") Optional<String> state) {
        List<Issue> issues;
        if (authorId.isPresent()) {
            issues =  repository.getIssuesByAuthorId(authorId.get());
        } else if (state.isPresent()) {
            issues =  repository.getIssuesByState(state.get());
        } else {
            issues =  repository.getAllIssues();
        }
        return issues;
    }

    @GetMapping("/issues/{issueId}")
    public Issue getIssueById(@PathVariable String issueId) {
        return repository.getIssue(issueId).get();
    }

    @GetMapping("/issues/{issueId}/comments")
    public List<Comment> getCommentsByIssueId(@PathVariable String issueId) {
        return repository.getCommentsByIssueId(issueId);
    }

    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return repository.getAllComments();
    }

    @GetMapping("/comments/{commentId}")
    public Comment getCommentById(@PathVariable String commentId) {
        return repository.getComment(commentId).get();
    }

}