package com.example.pathfinder.web;

import com.example.pathfinder.model.Comment;
import com.example.pathfinder.model.dto.AddCommentDTO;
import com.example.pathfinder.model.dto.UpdateCommentDTO;
import com.example.pathfinder.service.CommentService;
import com.example.pathfinder.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentsController {
    private final CommentService commentService;
    private final UserService userService;

    public CommentsController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/comment/add")
    public String addComment(AddCommentDTO addCommentDTO,
                             HttpServletRequest request,
                             Model model){

        String referer = request.getHeader("Referer");
        var currentRouteId = Long.parseLong(referer.split("/")[referer.split("/").length - 1]);
        commentService.addNewComment(currentRouteId, addCommentDTO.getMessage());
        return "redirect:"+ referer;
    }

    @GetMapping("/comment/delete/{id}")
    public String deleteComment(@PathVariable("id") Long commentId,
                                HttpServletRequest request){
        commentService.deleteCommentById(commentId);
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping("/comment/edit/{id}")
    public String editCommentById(@PathVariable("id") Long commentId,
                              UpdateCommentDTO updateCommentDTO,
                              Model model
    ){

        Comment comment = commentService.getCommentById(commentId);
        model.addAttribute("comment", comment);

        return "updateComment";
    }

    @PostMapping("/comment/update")
    public String applyEdit(UpdateCommentDTO updateCommentDTO,
                            HttpServletRequest request){
        String referer = request.getHeader("Referer");
        var currentCommentId = Long.parseLong(referer.split("/")[referer.split("/").length - 1]);
        commentService.editCommentById(updateCommentDTO, currentCommentId);
        return "redirect:/routes";
    }


    @GetMapping("/comment/approver")
    @Secured({ "ROLE_ADMIN" })
    public String commentApprover(Model model){
        var allComments = commentService.unapprovedComments();
        model.addAttribute("comments", allComments);
        return "commentApprover";
    }

    @GetMapping("/comment/approver/{id}")
    public void commentApproverByOne(@PathVariable("id") Long commentId){

        commentService.approveComment(commentId);
//        return "redirect:/comment/approver";
    }
}
