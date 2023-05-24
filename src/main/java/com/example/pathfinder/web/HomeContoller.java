package com.example.pathfinder.web;

import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.enums.CategoryName;
import com.example.pathfinder.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeContoller {

    private final RouteService routeService;

    public HomeContoller(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/")
    public String home(Model model){

        List<Route> routes = routeService.getMostCommented();

        model.addAttribute("mostCommented", routes.get(0));
        return "index";
    }

    @GetMapping("/pedestrian")
    public String pedestrian(Model model){
        var allRoutesForPEDESTRIANs = routeService.getAllRoutesByCategory(CategoryName.PEDESTRIAN);
        model.addAttribute("pedestrianRoutes", allRoutesForPEDESTRIANs);
        return "pedestrian";
    }

    @GetMapping("/bicycle")
    public String bicycle(Model model){
        var allRoutesForBICYCLErs = routeService.getAllRoutesByCategory(CategoryName.BICYCLE);
        model.addAttribute("bicycleRoutes", allRoutesForBICYCLErs);
        return "bicycle";
    }

    @GetMapping("/motorcycle")
    public String motorcycle(Model model){
        var allRoutesForMOTORCYCLErs = routeService.getAllRoutesByCategory(CategoryName.MOTORCYCLE);
        model.addAttribute("motorRoutes", allRoutesForMOTORCYCLErs);
        return "motorcycle";
    }

    @GetMapping("/car")
    public String car(Model model){
        var allRoutesForCARdrivers = routeService.getAllRoutesByCategory(CategoryName.CAR);
        model.addAttribute("carRoutes", allRoutesForCARdrivers);
        return "car";
    }
}
