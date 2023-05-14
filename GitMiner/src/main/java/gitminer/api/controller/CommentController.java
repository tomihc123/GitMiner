package gitminer.api.controller;

import gitminer.api.model.Comment;
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

@Tag(name= "Comentarios", description= "API encargada del manejo de Comentarios")
@RestController
@RequestMapping("/gitminer")
public class CommentController {
    @Autowired
    GitMinerService service;

    @Operation(
    		summary = "Obtener todos los comentarios",
    		description = "Devuelve una lista con todos los comentarios"
    		)
    @ApiResponses({
    	@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Project.class))}),
    	@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/comments")
    public List<Comment> getComments() {
        return service.getComments();
    }

    @Operation(
    		summary = "Obtener un comentario por id",
    		description = "Devuelve el comentario cuyo id coincide con el aportado"
    		)
    @ApiResponses({
    	@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Project.class))}),
    	@ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/comments/{commentId}")
    public Comment getCommentById(@Parameter(description = "id del comentario a obtener") @PathVariable String commentId) {
        return service.getComment(commentId).get();
    }
}