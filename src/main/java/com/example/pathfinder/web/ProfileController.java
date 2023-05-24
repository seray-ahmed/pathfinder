package com.example.pathfinder.web;

import com.example.pathfinder.mapper.UserMapper;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.dto.UpdateUserDetailsDTO;
import com.example.pathfinder.model.views.UserProfileView;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.user.CurrentUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ProfileController {

    private final UserService userService;
    private final UserMapper userMapper;

    public ProfileController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal CurrentUserDetails userDetails,
                          UpdateUserDetailsDTO updateUserDetailsDTO,
                          Model model){
        getUserAndPopulateData(userDetails, model);
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String updateProfile(@AuthenticationPrincipal CurrentUserDetails userDetails,
                          Model model){
        getUserAndPopulateData(userDetails, model);
        return "updateProfile";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(UpdateUserDetailsDTO updateUserDetailsDTO,
                                @AuthenticationPrincipal CurrentUserDetails userDetails){
        User user = userService.getUser(userDetails.getUsername());
        userService.updateCurrentLoggedUser(updateUserDetailsDTO, user);
        return "redirect:/profile";
    }

    @GetMapping("/user/details/{id}")
    public String userDetails(@PathVariable("id") Long userId,
                              Model model,
                              @AuthenticationPrincipal CurrentUserDetails userDetails
    ){
        User user = userService.getUserById(userId);
        UserProfileView userView = userMapper.userToUserProfileViewMapper(user);
        model.addAttribute("userToLoadData", userView);
        if (userId.equals(userDetails.getId())){
            return "redirect:/profile";
        }
        return "profileDetails";
    }

    private void getUserAndPopulateData(@AuthenticationPrincipal CurrentUserDetails userDetails,
                                        Model model){
        String currentUserUsername = userDetails.getUsername();
        User user = userService.getUser(currentUserUsername);
        UserProfileView userView = userMapper.userToUserProfileViewMapper(user);
        model.addAttribute("userToLoadData", userView);
    }
}
