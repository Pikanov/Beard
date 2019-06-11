package com.beard.controller;

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
import java.io.IOException;
import java.util.Optional;

import static com.beard.util.Validator.*;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserRepository repository = new UserRepositoryImpl();
    private UserService userService = new UserServiceImpl(repository);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/registration.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = User.builder()
                .withFirstName(req.getParameter("firstName"))
                .withLastName(req.getParameter("lastName"))
                .withEmail(req.getParameter("email"))
                .withPassword(PasswordEncryption.encryption(req.getParameter("password")))
                .withPhoneNumber(req.getParameter("phoneNumber"))
                .withRole(new Role(1L))
                .build();

        System.out.println(user);

        String password2 = PasswordEncryption.encryption(req.getParameter("password2"));
        System.out.println(password2);
        emailValidation(req, resp, user);
        phoneNumberValidation(req, resp, user);
        passwordValidation(req, resp, user);
        passwordVerification(req, resp, user, password2);
        Optional<User> optionalUser = userService.findByEmail(user.getEmail());
        checkingExistenceUserInDatabase(req, resp, optionalUser);

        userService.add(user);
        req.setAttribute("successful_registration", "Successful! Now you can login!");
        resp.sendRedirect("/login");
    }

    private void emailValidation(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        if (!isEmailValid(user.getEmail())) {
            req.setAttribute("exception", "Your email is invalid.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/registration.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void passwordValidation(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        if (!isPasswordValid(user.getPassword())) {
            req.setAttribute("exception", "Your password is invalid.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/registration.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void passwordVerification(HttpServletRequest req, HttpServletResponse resp, User user,
                                      String password2) throws ServletException, IOException {
        if (!user.getPassword().equals(password2)) {
            req.setAttribute("exception", "Your passwords are different!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/registration.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void phoneNumberValidation(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        if (!isPhoneNumberValid(user.getPhoneNumber())) {
            req.setAttribute("exception", "Your phone number is invalid.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/registration.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void checkingExistenceUserInDatabase(HttpServletRequest req, HttpServletResponse resp,
                                                 Optional<User> optionalUser) throws ServletException, IOException {
        if (optionalUser.isPresent()) {
            req.setAttribute("exception", "User with this email is already registered!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/registration.jsp");
            dispatcher.forward(req, resp);
        }
    }

}
