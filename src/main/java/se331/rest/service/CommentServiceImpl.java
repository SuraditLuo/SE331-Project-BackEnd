package se331.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.rest.DAO.CommentDao;
import se331.rest.DAO.DoctorDao;
import se331.rest.DAO.PatientDao;
import se331.rest.entity.Comment;
import se331.rest.entity.Doctor;
import se331.rest.entity.Patient;
import se331.rest.entity.Comment;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    PatientDao patientDao;

    @Autowired
    DoctorDao doctorDao;
    @Autowired
    CommentDao commentDao;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        Doctor doctor = doctorDao.findById(comment.getCommentBy().getId()).orElse(null);
        Patient patient = patientDao.findById(comment.getCommentTo().getId()).orElse(null);
        comment.setCommentBy(doctor);
        comment.setCommentTo(patient);
        return commentDao.save(comment);
    }

    @Override
    public List<Comment> getAllComment() {
        return commentDao.getComment(Pageable.unpaged()).getContent();
    }

}
