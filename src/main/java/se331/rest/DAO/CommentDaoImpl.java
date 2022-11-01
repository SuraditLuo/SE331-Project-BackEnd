package se331.rest.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import se331.rest.entity.Comment;
import se331.rest.entity.Comment;
import se331.rest.repository.CommentRepository;

@Repository
public class CommentDaoImpl implements CommentDao{
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Page<Comment> getComment(Pageable pageRequest) {
        return commentRepository.findAll(pageRequest);
    }
}
