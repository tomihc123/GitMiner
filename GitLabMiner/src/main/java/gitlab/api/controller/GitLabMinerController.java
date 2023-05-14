package gitlab.api.controller;

import gitlab.api.model.Project;
import gitlab.api.service.GitLabMinerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name= "Gitlab", description= "API encargada del intercambio de datos de Proyectos con GitLab")
@RestController
@RequestMapping("/gitlab")
public class GitLabMinerController {

    @Autowired
    GitLabMinerService service;

    @Operation(
    		summary = "Obtener un proyecto",
    		description = "Devuelve el proyecto cuyo id coincide con el aportado"
    		)
    @ApiResponses({
    	@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Project.class))}),
    	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{projectId}")
    public Project findProject(@PathVariable String projectId, @RequestParam(value = "sinceCommits") Optional<Integer> sinceCommits, @RequestParam("sinceIssues") Optional<Integer> sinceIssues, @RequestParam("maxPages") Optional<Integer> maxPages) {
        return service.findProject(projectId, sinceCommits, sinceIssues, maxPages);
    }

    @Operation(
    		summary = "Guardar un proyecto",
    		description = "Guarda un proyecto nuevo"
    		)
    @ApiResponses({
    	@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Project.class))}),
    	@ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
    })
    @PostMapping("/{projectId}")
    public Project create(@PathVariable String projectId, @RequestParam(value = "sinceCommits") Optional<Integer> sinceCommits, @RequestParam("sinceIssues") Optional<Integer> sinceIssues, @RequestParam("maxPages") Optional<Integer> maxPages) {
        return service.postProject(service.findProject(projectId, sinceCommits, sinceIssues, maxPages));
    }

}
