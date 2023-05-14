package gitlab.api.service;

import gitlab.api.model.Commit;
import gitlab.api.model.Issue;
import gitlab.api.model.Project;
import gitlab.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class GitLabMinerService {

    @Autowired  //Con esta etiqueta es spring la que instancia esto al iniciar
    RestTemplate _template;

    private final String BASE_URL = "https://gitlab.com/api/v4/projects/";
    public Integer _commits = 2;
    public Integer _issues = 20;
    public Integer _maxPages = 2;

    public Project findProject(String projectUri, Optional<Integer> sinceCommits, Optional<Integer> sinceIssues, Optional<Integer> maxPages) {

        String url = BASE_URL + projectUri;
        Project project = _template.getForObject(url, Project.class);

        sinceCommits.ifPresent(value -> _commits = value);
        sinceIssues.ifPresent(value -> _issues = value);
        maxPages.ifPresent(value -> _maxPages = value);

        if (project != null) {
            project.setCommits(getCommits(projectUri));
            project.setIssues(getIssues(projectUri));
        }

        return project;

    }

    private List<Issue> getIssues(String project) {

        String url = BASE_URL + project + "/issues";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (_issues != 0) builder.queryParam("sinceIssues", Utils.getDateSince(_issues));
        ResponseEntity<Issue[]> response = _template.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(null, null), Issue[].class);

        List<Issue> res = new ArrayList<>();
        if (response.getBody() != null) {
            if (_maxPages != 0 && response.getBody().length > 0) {
                for (int i = 0; i < _maxPages; i++) {
                    String pageNext = Utils.getNextPageUrl(response.getHeaders());
                    if (pageNext != null) {
                        response = _template.exchange(pageNext, HttpMethod.GET, new HttpEntity<>(null, null), Issue[].class);
                    }
                    if (response.getBody() != null) {
                        res.addAll(Arrays.stream(response.getBody()).toList());
                    }
                }
            }
        }

        return res;
    }

    private List<Commit> getCommits(String project) {
        String url = BASE_URL + project + "/repository/commits";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (_commits != 0) builder.queryParam("since", Utils.getDateSince(_commits));
        ResponseEntity<Commit[]> response = _template.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(null, null), Commit[].class);

        List<Commit> res = new ArrayList<>();
        if (response.getBody() != null) {
            if (_maxPages != 0 && response.getBody().length > 0) {
                for (int i = 0; i < _maxPages; i++) {
                    String pageNext = Utils.getNextPageUrl(response.getHeaders());
                    if (pageNext != null) {
                        response = _template.exchange(pageNext, HttpMethod.GET, new HttpEntity<>(null, null), Commit[].class);
                    }
                    if (response.getBody() != null) {
                        res.addAll(Arrays.stream(response.getBody()).toList());
                    }
                }
            }
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
