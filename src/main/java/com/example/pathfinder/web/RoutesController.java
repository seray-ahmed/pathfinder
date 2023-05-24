package com.example.pathfinder.web;

import com.example.pathfinder.model.Comment;
import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.dto.AddPictureDTO;
import com.example.pathfinder.model.dto.AddRouteDTO;
import com.example.pathfinder.model.enums.CategoryName;
import com.example.pathfinder.model.enums.Level;
import com.example.pathfinder.service.CommentService;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.user.CurrentUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RequestMapping("/routes")
@Controller
public class RoutesController {

    private final RouteService routeService;
    private final CommentService commentService;

    public RoutesController(RouteService routeService, CommentService commentService) {
        this.routeService = routeService;
        this.commentService = commentService;
    }

    @GetMapping("")
    public String routes(Model model){
        var allRoutes = routeService.getAllRoutes();
        model.addAttribute("allRoutes", allRoutes);
        return "routes";
    }

    @GetMapping("/add")
    public String addRoute(@AuthenticationPrincipal CurrentUserDetails currentLoggedUser,
                           AddRouteDTO routeDTO,
                           Model model,
                           HttpServletRequest request){

        if (!currentLoggedUser.getActive()){
            String referer = request.getHeader("Referer");
            var endPointToRedirect = (referer.split("/", 4))[(referer.split("/", 4).length - 1)];
            return "redirect:/" + endPointToRedirect;
        }
        model.addAttribute("route", routeDTO);
        model.addAttribute("levels", Level.values());
        model.addAttribute("categories", CategoryName.values());
        return "add-route";
    }

    @PostMapping("/add")
    public String addNewRoute(AddRouteDTO routeDTO,
                              RedirectAttributes redirectAttributes){
        routeService.saveRoute(routeDTO);
        return "redirect:/routes";
    }

    @PostMapping("/picture/add")
    public String addPicture(AddPictureDTO pictureDTO,
                             HttpServletRequest request){

        String referer = request.getHeader("Referer");
        var routeId = Long.parseLong(referer.split("/")[referer.split("/").length - 1]);
        routeService.addPictureToRoute(routeId, pictureDTO);
        return "redirect:/routes/details/" + routeId;
    }

    @GetMapping("/details/{id}")
    public String routeDetails(@PathVariable("id") Long routeId,
                               Model model,
                               @AuthenticationPrincipal CurrentUserDetails user,
                               AddPictureDTO addPictureDTO){
        Route routeFromDB = routeService.findRouteById(routeId);
        routeFromDB.setComments(routeFromDB.getComments().stream().filter(Comment::isApproved).collect(Collectors.toSet()));

        model.addAttribute("picture", addPictureDTO);
        model.addAttribute("user", user);
        model.addAttribute("route", routeFromDB);
        return "route-details";
    }
}
