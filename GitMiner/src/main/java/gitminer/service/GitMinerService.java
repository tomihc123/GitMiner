package gitminer.service;

import gitminer.model.Comment;
import gitminer.model.Commit;
import gitminer.model.Issue;
import gitminer.model.Project;
import gitminer.repository.CommentDatabase;
import gitminer.repository.CommitDatabase;
import gitminer.repository.IssueDatabase;
import gitminer.repository.ProjectDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class GitMinerService {

    @Autowired
    RestTemplate _template;

    @Autowired
    ProjectDatabase projectDatabase;
    @Autowired
    CommitDatabase commitDatabase;
    @Autowired
    IssueDatabase issueDatabase;
    @Autowired
    CommentDatabase commentDatabase;


    public void saveProject(Project project) {
        projectDatabase.save(project);
    }

    public List<Project> getAllProjects() {
        return projectDatabase.findAll();
    }

    public Optional<Project> findProject(String projectId) {
        return projectDatabase.findById(projectId);
    }

    public List<Commit> getAllCommits() {
        return commitDatabase.findAll();
    }

    public Optional<Commit> getCommit(String commitId) {
        return commitDatabase.findById(commitId);
    }

    public List<Commit> getCommitByEmail(String email) {
        return commitDatabase.findByAuthorEmail(email);
    }

    public List<Issue> getAllIssues() {
        return issueDatabase.findAll();
    }

    public Optional<Issue> getIssue(String issueId) {
        return issueDatabase.findById(issueId);
    }

    public List<Comment> getCommentsByIssueId(String issueId) {
        return getIssue(issueId).get().getComments();
    }

    public List<Issue> getIssuesByAuthorId(String authorId) {
        return issueDatabase.findByAuthorId(authorId);
    }

    public List<Issue> getIssuesByState(String state) {
        return issueDatabase.findByState(state);
    }

    public List<Comment> getAllComments() {
        return commentDatabase.findAll();
    }

    public Optional<Comment> getComment(String commentId) {
        return commentDatabase.findById(commentId);
    }

}
