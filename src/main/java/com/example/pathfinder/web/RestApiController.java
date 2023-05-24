package com.example.pathfinder.web;

import com.example.pathfinder.model.API_DTO.RouteResponse;
import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.enums.CategoryName;
import com.example.pathfinder.service.CommentService;
import com.example.pathfinder.service.RouteService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.net.httpserver.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class RestApiController {
    private final CommentService commentService;

    private final RouteService routeService;

    public RestApiController(CommentService commentService, RouteService routeService) {
        this.commentService = commentService;
        this.routeService = routeService;
    }

    @GetMapping("/comment/approver/{id}")
    public ResponseEntity<?> commentApproverByOne(@PathVariable("id") Long commentId){
        commentService.approveComment(commentId);
        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
    }

    @GetMapping("/routes")
    public ResponseEntity<List<RouteResponse>> allRoutes(){
        return new ResponseEntity<>(routeService.getAllRoutesForAPI(), HttpStatus.OK);
    }
    @GetMapping("/routes/pedestrian")
    public ResponseEntity<List<RouteResponse>> allPedestianRoutes(){
        return new ResponseEntity<>(routeService.getAllRoutesForAPIByCategory(CategoryName.PEDESTRIAN), HttpStatus.OK);
    }

    @GetMapping("/routes/car")
    public ResponseEntity<List<RouteResponse>> allCarRoutes(){
        return new ResponseEntity<>(routeService.getAllRoutesForAPIByCategory(CategoryName.CAR), HttpStatus.OK);
    }
    @GetMapping("/routes/motorcycle")
    public ResponseEntity<List<RouteResponse>> allMotorcycleRoutes(){
        return new ResponseEntity<>(routeService.getAllRoutesForAPIByCategory(CategoryName.MOTORCYCLE), HttpStatus.OK);
    }
    @GetMapping("/routes/bicycle")
    public ResponseEntity<List<RouteResponse>> allBikeRoutes(){
        return new ResponseEntity<>(routeService.getAllRoutesForAPIByCategory(CategoryName.BICYCLE), HttpStatus.OK);
    }
}
