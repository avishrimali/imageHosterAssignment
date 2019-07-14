package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "image/{imageId}/{imageTitle}/comments")
    public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, HttpSession session,Model model) {
        Image image = imageService.getImage(imageId);
        model.addAttribute("comments", image.getComments());
        return "images/image";
    }

    @RequestMapping(value="image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createCommentSubmit(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle,
                                @RequestParam("comments") String commentText, HttpSession session, Model model){
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImage(imageId);

        Comment comment= new Comment();
        comment.setText(commentText);
        comment.setCreatedDate(LocalDate.now());
        comment.setImage(image);
        comment.setUser(image.getUser());
        commentService.addComments(comment);
//        image.setComments(comment);
//        imageService.updateImage(image);
        return "images/image";
    }
}
