package se331.rest.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.rest.entity.Comment;
import se331.rest.entity.Patient;
import se331.rest.entity.Comment;

import java.net.ContentHandler;

public interface CommentDao {
    Comment save(Comment comment);

    Page<Comment> getComment(Pageable pageRequest);
}
