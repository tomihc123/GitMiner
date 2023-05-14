package gitminer.api.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name= "Proyectos", description= "API encargada del manejo de Proyectos")
@RestController
@RequestMapping("/gitminer")
public class ProjectController {
    @Autowired
    GitMinerService service;

    @Operation(
    		summary = "Guardar un proyecto",
    		description = "Guarda un proyecto nuevo"
    		)
    @ApiResponses({
    	@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Project.class))}),
    	@ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/projects")
    public Project saveProject(@Valid @RequestBody Project project) {
        service.saveProject(project);
        return project;
    }

    @Operation(
    		summary = "Obtener una lista de proyectos",
    		description = "Devuelve una lista de proyectos"
    		)
    @ApiResponses({
    	@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Project.class))}),
    	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/projects")
    public List<Project> getProjects() {
        return service.getProjects();
    }

    @Operation(
    		summary = "Obtener un proyecto por id",
    		description = "Devuelve el proyecto cuyo id coincide con el aportado"
    		)
    @ApiResponses({
    	@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Project.class))}),
    	@ApiResponse(responseCode = "404")
    })
    @GetMapping("/projects/{projectId}")
    public Project getProject(@Parameter(description = "id del proyecto a obtener") @PathVariable String projectId) {
        return service.findProject(projectId).get();
    }

}