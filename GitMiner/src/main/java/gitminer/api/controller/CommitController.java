package gitminer.api.controller;

import gitminer.api.model.Commit;
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

@Tag(name= "Commit", description= "API encargada del manejo de Commits")
@RestController
@RequestMapping("/gitminer")
public class CommitController {
    @Autowired
    GitMinerService service;

    @Operation(
    		summary = "Obtener todos los commits",
    		description = "Devuelve una lista con todos los commits"
    		)
    @ApiResponses({
    	@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Project.class))}),
    	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/commits")
    public List<Commit> getCommits(@RequestParam(value = "email") Optional<String> email) {
        List<Commit> commits;
        if (email.isPresent()) {
            commits = service.getCommitByEmail(email.get());
        } else {
            commits = service.getCommits();
        }
        return commits;
    }

    @Operation(
    		summary = "Obtener un commit por id",
    		description = "Devuelve el commit cuyo id coincide con el aportado"
    		)
    @ApiResponses({
    	@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Project.class))}),
    	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/commits/{commitId}")
    public Commit getCommitById(@Parameter(description = "id del commit a obtener") @PathVariable String commitId) {
        return service.getCommit(commitId).get();
    }
}