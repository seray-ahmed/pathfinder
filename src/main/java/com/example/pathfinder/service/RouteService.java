package com.example.pathfinder.service;

import com.example.pathfinder.mapper.RouteMapper;
import com.example.pathfinder.model.API_DTO.RouteResponse;
import com.example.pathfinder.model.Category;
import com.example.pathfinder.model.Picture;
import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.dto.AddPictureDTO;
import com.example.pathfinder.model.dto.AddRouteDTO;
import com.example.pathfinder.model.enums.CategoryName;
import com.example.pathfinder.repository.CategoryRepository;
import com.example.pathfinder.repository.PictureRepository;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.user.CurrentUserDetails;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

import static com.example.pathfinder.model.API_DTO.RouteResponse.*;

@Service
public class RouteService {
    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final PictureRepository pictureRepository;

    public RouteService(RouteRepository routeRepository, RouteMapper routeMapper, UserService userService, CategoryRepository categoryRepository, PictureRepository pictureRepository) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
        this.pictureRepository = pictureRepository;
    }

    public List<Route> getMostCommented() {
        return routeRepository.findMostCommented();
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Cacheable("routes")
    public List<Route> getAllRoutesByCategory(CategoryName categoryName) {
        return delegate.apply(categoryName);
    }

    public List<RouteResponse> getAllRoutesForAPI(){
        return this.getAllRoutes().stream().map(this::routeToRouteResponseMapper).toList();
    }

    public List<RouteResponse> getAllRoutesForAPIByCategory(CategoryName categoryName){
        return this.delegate.apply(categoryName).stream().map(this::routeToRouteResponseMapper).toList();
    }



    public Route findRouteById(Long routeId) {
        Optional<Route> routeFromDB = routeRepository.findById(routeId);
        return routeFromDB.get();
    }

    public void saveRoute(AddRouteDTO routeDTO) {
        Route routeToAdd = routeMapper.routeDTOtoRouteEntityMapper(routeDTO);
        routeToAdd.setAuthor(currentLoggedUser());
        routeToAdd.setGpxCoordinates("someRandomText!");
        var allCategoriesToNewRoute = categoryMapper(routeDTO.getCategories());
        routeToAdd.setCategories(allCategoriesToNewRoute);
        routeRepository.save(routeToAdd);
    }

    public void addPictureToRoute(Long routeId, AddPictureDTO pictureDTO){
        Picture pic = new Picture();
        Route route = routeRepository.findById(routeId).get();
        pic.setAuthor(currentLoggedUser());
        pic.setTitle(route.getName());
        pic.setRoute(route);
        pic.setUrl(pictureDTO.getURL());

        pictureRepository.save(pic);
    }

    private Set<Category> categoryMapper(String[] categories) {
        List<Category> allCategories = categoryRepository.findAll();
        Set<Category> resultSet = new HashSet<>();
        for (var category: categories) {
            for (var categoryFromDb: allCategories) {
                if (category.equals(categoryFromDb.getName().name())){
                    resultSet.add(categoryFromDb);
                }
            }
        }
        return resultSet;
    }

//    private Set<Picture> pictureMapper(String pictures, Route route) {
//        int i = 0;
//        Set<Picture> resultSet = new HashSet<>();
//        for (var picture: pictures.split("\r\n")) {
//            Picture pic = new Picture();
//            pic.setTitle(route.getName() + (i == 0 ? "" : i));
//            pic.setUrl(picture);
//            resultSet.add(pic);
//        }
//        return resultSet;
//    }

    private Function<CategoryName, List<Route>> delegate = methodType -> this.
            getAllRoutes().
            stream().
            filter(x -> x.
                    getCategories().
                    stream().
                    anyMatch(y -> y
                            .getName()
                            .name()
                            .equals(methodType.name()))
            ).toList();

    private User currentLoggedUser(){
        String currentUsername = ((CurrentUserDetails) SecurityContextHolder.
                getContext().
                getAuthentication().
                getPrincipal()).
                getUsername();

        User currentLoggedUser = userService.getUser(currentUsername);
        return currentLoggedUser;
    }

    private RouteResponse routeToRouteResponseMapper(Route route){
        return new RouteResponse(route.getId(),
                route.getDescription(),
                route.getLevel(),
                route.getName(),
                route.getAuthor(),
                route.getCategories().stream().toList(),
                route.getPictures().stream().toList(),
                route.getCommentsSortedByData().stream().toList());

    }
}

