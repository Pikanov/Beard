package com.beard.servlet;

import com.beard.entity.Comment;
import com.beard.entity.User;
import com.beard.repository.CommentRepository;
import com.beard.repository.impl.CommentRepositoryImpl;
import com.beard.service.CommentService;
import com.beard.service.impl.CommentServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet ("/comment")
public class CommentServlet extends HttpServlet {

    private CommentRepository commentRepository = new CommentRepositoryImpl();
    private CommentService commentService = new CommentServiceImpl(commentRepository);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int currentPage;
        if (req.getParameter("currentPage")== null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }

        int recordsPerPage;
        if (req.getParameter("recordsPerPage")==null) {
            recordsPerPage = 5;
        } else {
            recordsPerPage = Integer.valueOf(req.getParameter("recordsPerPage"));
        }


        int numberOfRows = commentService.getNumberOfRows();

        int nOfPages = numberOfRows / recordsPerPage;
        if (numberOfRows % recordsPerPage > 0) {
            nOfPages++;
        }

        int start = currentPage * recordsPerPage - recordsPerPage;

        List<Comment> comments = commentService.findCommentsForPagination(start, recordsPerPage);

        req.setAttribute("noOfPages", nOfPages);
        req.getSession().setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.setAttribute("commentList", comments);

        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/comment.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        Long userId = (Long) session.getAttribute("userId");

        Comment comment = Comment.builder()
                .withComment(req.getParameter("comment"))
                .withUser(User.builder()
                        .withUserId(userId)
                        .build())
                .build();

       commentRepository.add(comment);
       req.setAttribute("comment", comment);
       resp.sendRedirect("/comment");
    }
}
