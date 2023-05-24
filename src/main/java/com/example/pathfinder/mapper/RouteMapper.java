package com.example.pathfinder.mapper;

import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.dto.AddRouteDTO;
import com.example.pathfinder.model.views.UserProfileView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    @Mapping(target = "categories", ignore = true)
    Route routeDTOtoRouteEntityMapper(AddRouteDTO routeDTO);
}
