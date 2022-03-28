package service.impl;

import dao.CrudRepository;
import entity.Comments.Comment;
import service.CommentService;

public class CommentServiceImpl extends GenericServiceImpl<Long, Comment> implements CommentService {


    public CommentServiceImpl(CrudRepository<Long, Comment> genericRepository) {
        super(genericRepository);
    }


}
