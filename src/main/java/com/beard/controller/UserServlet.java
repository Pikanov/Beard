package com.beard.controller;

import com.beard.entity.User;
import com.beard.repository.UserRepository;
import com.beard.repository.impl.UserRepositoryImpl;
import com.beard.service.UserService;
import com.beard.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserRepository repository = new UserRepositoryImpl();
    private UserService userService = new UserServiceImpl(repository);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int currentPage;
        if (req.getParameter("currentPage") == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }

        int recordsPerPage;
        if (req.getParameter("recordsPerPage") == null) {
            recordsPerPage = 5;
        } else {
            recordsPerPage = Integer.valueOf(req.getParameter("recordsPerPage"));
        }


        int numberOfRows = userService.getNumberOfRows();

        int nOfPages = numberOfRows / recordsPerPage;
        if (numberOfRows % recordsPerPage > 0) {
            nOfPages++;
        }

        int start = currentPage * recordsPerPage - recordsPerPage;

        List<User> users = userService.findUsersForPagination(start, recordsPerPage);

        req.setAttribute("noOfPages", nOfPages);
        req.getSession().setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.setAttribute("userList", users);

        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/users.jsp");
        dispatcher.forward(req, resp);
    }
}