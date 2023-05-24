package com.example.pathfinder.web;

import com.example.pathfinder.model.dto.AddPictureDTO;
import com.example.pathfinder.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/picture")
@Controller
public class PictureController {

    private final RouteService routeService;

    public PictureController(RouteService routeService) {
        this.routeService = routeService;
    }



}
