package gitminer.api.controller;

import gitminer.api.model.Comment;
import gitminer.api.model.Issue;
import gitminer.api.model.Project;
import gitminer.api.service.GitMinerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name= "Issue", description= "API encargada del manejo de Issues")
@RestController
@RequestMapping("/gitminer")
public class IssueController {
    @Autowired
    GitMinerService service;
    
    @Operation(
    		summary = "Obtener todas las issues",
    		description = "Devuelve una lista con todas las issues"
    		)
    @ApiResponses({
    	@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Project.class))}),
    	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/issues")
    public List<Issue> getIssues(@RequestParam(value = "authorId") Optional<String> authorId, @RequestParam(value = "state") Optional<String> state) {
        List<Issue> issues;
        if (authorId.isPresent()) {
            issues =  service.getIssuesByAuthorId(authorId.get());
        } else if (state.isPresent()) {
            issues =  service.getIssuesByState(state.get());
        } else {
            issues =  service.getIssues();
        }
        return issues;
    }

    @Operation(
    		summary = "Obtener una issue por id",
    		description = "Devuelve la issue cuyo id coincide con el aportado"
    		)
    @ApiResponses({
    	@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Project.class))}),
    	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/issues/{issueId}")
    public Issue getIssueById(@Parameter(description = "id de la issue a obtener") @PathVariable String issueId) {
        return service.getIssue(issueId).get();
    }

    @Operation(
    		summary = "Obtener comentarios de una issue",
    		description = "Devuelve los comentarios de la issue cuyo id coincide con el aportado"
    		)
    @ApiResponses({
    	@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Project.class))}),
    	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/issues/{issueId}/comments")
    public List<Comment> getCommentsByIssueId(@Parameter(description = "id de la issue cuyos comentarios queremos obtener") @PathVariable String issueId) {
        return service.getCommentsByIssueId(issueId);
    }
}
