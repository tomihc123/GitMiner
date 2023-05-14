package github.api.utils;

import org.springframework.http.HttpHeaders;

import github.api.model.Commit;
import github.api.model.CommitParentAux;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String getNextPageUrl(HttpHeaders headers) {
        String result = null;

        // If there is no link header, return null
        List<String> linkHeader = headers.get("Link");
        if (linkHeader == null)
            return null;

        // If the header contains no links, return null
        String links = linkHeader.get(0);
        if (links == null || links.isEmpty())
            return null;

        // Return the next page URL or null if none.
        for (String token : links.split(", ")) {
            if (token.endsWith("rel=\"next\"")) {
                // Found the next page. This should look something like
                // <https://api.github.com/repos?page=3&per_page=100>; rel="next"
                int idx = token.indexOf('>');
                result = token.substring(1, idx);
                break;
            }
        }

        return result;
    }

    public static LocalDateTime StringToLocalDateTime(String dateToParse) {

        Instant instant = Instant.parse(dateToParse);
        LocalDateTime parsedDate = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        return parsedDate;
    }

    public static LocalDateTime getDateSince(Integer since) {
        return LocalDateTime.now().plusDays(-since);
    }

    public static List<Commit> transformCommits(List<CommitParentAux> data) {

        List<Commit> aux = new ArrayList<>();

        data.forEach(commitParentAux -> {
            aux.add(new Commit(commitParentAux.getId(), commitParentAux.getCommit().getMessage(),
                    commitParentAux.getCommit().getMessage(), commitParentAux.getCommit().getAuthor().getAuthorName(),
                    commitParentAux.getCommit().getAuthor().getAuthorEmail(), commitParentAux.getCommit().getAuthor().getAuthoredDate(), commitParentAux.getCommit().getCommitter().getCommitterName(), commitParentAux.getCommit().getCommitter().getCommitterEmail(), commitParentAux.getCommit().getCommitter().getCommittedDate(), commitParentAux.getWeb_url()));
        });

        return aux;

    }

}
