package com.example.pathfinder.mapper;

import com.example.pathfinder.model.User;
import com.example.pathfinder.model.views.UserProfileView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserProfileView userToUserProfileViewMapper(User user);
}
