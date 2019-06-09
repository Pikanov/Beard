package com.beard.servlet;

import com.beard.entity.Role;
import com.beard.entity.User;
import com.beard.repository.UserRepository;
import com.beard.repository.impl.UserRepositoryImpl;
import com.beard.service.UserService;
import com.beard.service.impl.UserServiceImpl;
import com.beard.util.PasswordEncryption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static com.beard.util.Validator.isEmailValid;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserRepository repository = new UserRepositoryImpl();
    private UserService userService = new UserServiceImpl(repository);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = PasswordEncryption.encryption(req.getParameter("password"));
        emailValidation(req, resp, email);
        Optional<User> optionalUser = userService.findByEmail(email);
        checkingExistenceUserInDatabase(req, resp, optionalUser);

        if (!optionalUser.get().getPassword().equals(password)) {
            req.setAttribute("exception", "You entered the wrong password!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/login.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        Role role = optionalUser.get().getRole();
        HttpSession session = req.getSession();
        session.setAttribute("role", role.getRole());
        session.setAttribute("email", email);
        session.setAttribute("userId", optionalUser.get().getUserId());
        resp.sendRedirect("schedule");
    }

    private void emailValidation(HttpServletRequest req, HttpServletResponse resp, String email) throws ServletException, IOException {
        if (!isEmailValid(email)) {
            req.setAttribute("exception", "Your email is invalid.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/registration.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void checkingExistenceUserInDatabase(HttpServletRequest req, HttpServletResponse resp,
                                                 Optional<User> optionalUser) throws ServletException, IOException {
        if (!optionalUser.isPresent()) {
            req.setAttribute("exception", "There is no user with such email!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/login.jsp");
            dispatcher.forward(req, resp);
        }
    }
}

