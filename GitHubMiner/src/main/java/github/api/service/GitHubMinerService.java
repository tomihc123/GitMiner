package github.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import github.api.model.*;
import github.api.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class GitHubMinerService {

    @Autowired
    RestTemplate _template;

    private final String BASE_URL = "https://api.github.com/repos/";
    public Integer _commits = 2;
    public Integer _issues = 20;
    public Integer _maxPages = 2;

    public Project findProject(String ownerId, String projectId, Optional<Integer> sinceCommits, Optional<Integer> sinceIssues, Optional<Integer> maxPages) {

        String url = BASE_URL + ownerId + "/" + projectId;
        //Project project = _template.getForObject(url, Project.class);


        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        ResponseEntity<Project> response = _template.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(null), Project.class);

        sinceCommits.ifPresent(value -> _commits = value);
        sinceIssues.ifPresent(value -> _issues = value);
        maxPages.ifPresent(value -> _maxPages = value);

        Project project = response.getBody();

        if (project != null) {
            project.setCommits(getCommits(ownerId, projectId));
            project.setIssues(getIssues(ownerId, projectId));

        }
        return project;
    }

    private List<Issue> getIssues(String ownerId, String projectId) {

        String url = BASE_URL + ownerId + "/" + projectId + "/" + "issues";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (_issues != 0) builder.queryParam("since", Utils.getDateSince(_issues));
        ResponseEntity<IssueAux[]> response = _template.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(null), IssueAux[].class);


        List<IssueAux> res = new ArrayList<>();
        if (response.getBody() != null) {
            if (_maxPages != 0 && response.getBody().length > 0) {
                for (int i = 0; i < _maxPages; i++) {
                    String pageNext = Utils.getNextPageUrl(response.getHeaders());
                    if (pageNext != null) {
                        response = _template.exchange(pageNext, HttpMethod.GET, new HttpEntity<>(null), IssueAux[].class);
                        if (response.getBody() != null) {
                            res.addAll(Arrays.stream(response.getBody()).toList());
                        }
                    }
                }
            } else {
                res.addAll(Arrays.stream(response.getBody()).toList());
            }
        }

        if (res.isEmpty()) {
            res.addAll(Arrays.stream(response.getBody()).toList());
        }

        List<Issue> resIssue = new ArrayList<>();
        res.forEach(issueAux -> {
            Issue issue = new Issue();
            issue.setId(issueAux.getId());
            issue.setRef_id(issueAux.getRefId());
            issue.setTitle(issueAux.getTitle());
            issue.setDescription(issueAux.getDescription());
            issue.setState(issueAux.getState());
            issue.setCreatedAt(issueAux.getCreatedAt());
            issue.setUpdatedAt(issueAux.getUpdatedAt());
            issue.setClosedAt(issueAux.getClosedAt());
            issue.setAuthor(issueAux.getAuthor());
            issue.setAssignee(issueAux.getAssignee());
            issue.setUpvotes(issueAux.getReactions().getUpvotes());
            issue.setDownvotes(issueAux.getReactions().getDownvotes());
            issue.setWebUrl(issueAux.getWebUrl());
            issue.setComments(getComments(issueAux.getWebUrl() + "/comments"));
            resIssue.add(issue);

        });

        return resIssue;
    }

    private List<Commit> getCommits(String ownerId, String projectId) {
        String url = BASE_URL + ownerId + "/" + projectId + "/" + "commits";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (_commits != 0) builder.queryParam("since", Utils.getDateSince(_commits));
        ResponseEntity<CommitParentAux[]> response = _template.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(null), CommitParentAux[].class);

        List<CommitParentAux> res = new ArrayList<>();
        if (response.getBody() != null) {
            if (_maxPages != 0 && response.getBody().length > 0) {
                for (int i = 0; i < _maxPages; i++) {
                    String pageNext = Utils.getNextPageUrl(response.getHeaders());
                    if (pageNext != null) {
                        response = _template.exchange(pageNext, HttpMethod.GET, new HttpEntity<>(null), CommitParentAux[].class);
                        if (response.getBody() != null) {
                            res.addAll(Arrays.stream(response.getBody()).toList());
                        }
                    }
                }
            } else {
                res.addAll(Arrays.stream(response.getBody()).toList());
            }
        }

        if (res.isEmpty()) {
            res.addAll(Arrays.stream(response.getBody()).toList());
        }

        return Utils.transformCommits(res);
    }

    public List<Comment> getComments(String url) {

        List<Comment> res = new ArrayList<>();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        ResponseEntity<Comment[]> response = _template.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(null), Comment[].class);
        if (response.getBody() != null) {
            res.addAll(Arrays.stream(response.getBody()).toList());
        }
        return res;
    }


    public Project postProject(Project project) {

        String url = "http://localhost:8080/gitminer/projects";
        HttpEntity<Project> request = new HttpEntity<>(project);
        ResponseEntity<Project> response = _template.exchange(url, HttpMethod.POST, request, Project.class);
        return response.getBody();
    }

}


