package com.example.pathfinder.web;

import com.example.pathfinder.model.dto.UserRegistrationDTO;
import com.example.pathfinder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserRegisterController {
    private final UserService userService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    //    @ModelAttribute("userRegistrationDTO")
//    public UserRegistrationDTO initForm(){
//        return new UserRegistrationDTO();
//    }

    @ModelAttribute("userModel")
    public void initUserModel(Model model){
        model.addAttribute("userModel", new UserRegistrationDTO());
    }

    @GetMapping("/register")
    public String register(UserRegistrationDTO userRegistrationDTO){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDTO userRegistrationDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userModel", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:/register";
        }

        userService.saveNewUser(userRegistrationDTO);

        return "redirect:/";
    }
}
