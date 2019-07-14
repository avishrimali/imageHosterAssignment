package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Class for Comment functionality
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * @param comment
     */
    public void addComments(Comment comment) {
        commentRepository.addComments(comment);
    }
}