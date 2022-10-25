package se331.rest.service;


import se331.rest.entity.Comment;
import se331.rest.entity.Doctor;
import se331.rest.entity.Patient;
import se331.rest.entity.Vaccine;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);

    List<Comment> getAllComment();
}
