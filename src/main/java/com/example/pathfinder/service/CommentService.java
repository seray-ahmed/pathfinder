package com.example.pathfinder.service;

import com.example.pathfinder.model.Comment;
import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.dto.UpdateCommentDTO;
import com.example.pathfinder.repository.CommentRepository;
import com.example.pathfinder.user.CurrentUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentReposity;
    private final UserService userService;
    private final RouteService routeService;

    public CommentService(CommentRepository commentReposity, UserService userService, RouteService routeService) {
        this.commentReposity = commentReposity;
        this.userService = userService;
        this.routeService = routeService;
    }

    public void addNewComment(Long routeId, String text){
        String currentUsername = ((CurrentUserDetails)SecurityContextHolder.
                getContext().
                getAuthentication().
                getPrincipal()).
                getUsername();

        User currentLoggedUser = userService.getUser(currentUsername);
        Route routeToAddMessage = routeService.findRouteById(routeId);

        Comment comment = new Comment();
        comment.setApproved(false);
        comment.setAuthor(currentLoggedUser);
        comment.setTextContent(text);
        comment.setRoute(routeToAddMessage);

        commentReposity.save(comment);
    }

    public void deleteCommentById(Long id){
        commentReposity.deleteById(id);
    }

    public Comment getCommentById(Long commentId) {
        return commentReposity.findById(commentId).get();
    }

    public void editCommentById(UpdateCommentDTO updateCommentDTO, long currentCommentId) {
        Comment currentComment = this.getCommentById(currentCommentId);
        currentComment.setTextContent(updateCommentDTO.getTextContent());
        currentComment.setModified(LocalDateTime.now());
        commentReposity.save(currentComment);
    }

    public List<Comment> unapprovedComments(){
        return commentReposity.findAllUnApprovedComments();
    }

    public void approveComment(Long commentId) {
        Comment comment = commentReposity.findById(commentId).get();

        comment.setApproved(true);

        commentReposity.save(comment);
    }
}
