package github.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import github.api.model.Project;
import github.api.service.GitHubMinerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

@Tag(name= "GitHub", description= "API encargada del intercambio de datos de Proyectos con GitHub")
@RestController
@RequestMapping("/github")
public class GitHubMinerController {

    @Autowired
    GitHubMinerService service;

    @Operation(
    		summary = "Obtener todos los comentarios",
    		description = "Devuelve el proyecto cuyo id coincide con el aportado"
    		)
    @ApiResponses({
    	@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Project.class))}),
    	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{ownerId}/{projectId}")
    public Project findProject(@PathVariable String ownerId, @PathVariable String projectId, @RequestParam(value = "sinceCommits") Optional<Integer> sinceCommits, @RequestParam("sinceIssues") Optional<Integer> sinceIssues, @RequestParam("maxPages") Optional<Integer> maxPages) {
        return service.findProject(ownerId, projectId, sinceCommits, sinceIssues, maxPages);
    }

    @Operation(
    		summary = "Guardar un proyecto",
    		description = "Guarda un proyecto nuevo"
    		)
    @ApiResponses({
    	@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Project.class))}),
    	@ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
    })
    @PostMapping("/{ownerId}/{projectId}")
    public Project create(@PathVariable String ownerId, @PathVariable String projectId, @RequestParam(value = "sinceCommits") Optional<Integer> sinceCommits, @RequestParam("sinceIssues") Optional<Integer> sinceIssues, @RequestParam("maxPages") Optional<Integer> maxPages) {
        return service.postProject(service.findProject(ownerId, projectId, sinceCommits, sinceIssues, maxPages));
    }

}
