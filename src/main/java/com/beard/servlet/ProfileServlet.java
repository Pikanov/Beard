package com.beard.servlet;

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

import static com.beard.util.Validator.isPasswordValid;
import static com.beard.util.Validator.isPhoneNumberValid;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserRepository repository = new UserRepositoryImpl();
    private UserService userService = new UserServiceImpl(repository);
    private Optional<User> selectedUser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("email");
        selectedUser = userService.findByEmail(email);
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/profile.jsp");
        req.setAttribute("user", selectedUser.get());
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phoneNumber = req.getParameter("phoneNumber");

        passwordValidation(req, resp, password, email);
        phoneNumberValidation(req, resp, phoneNumber);

        User user =User.builder()
                .withUserId(selectedUser.get().getUserId())
                .withFirstName(req.getParameter("firstName"))
                .withLastName(req.getParameter("lastName"))
                .withEmail(selectedUser.get().getEmail())
                .withPassword(PasswordEncryption.encryption(req.getParameter("password")))
                .withPhoneNumber(req.getParameter("phoneNumber"))
                .withRole(selectedUser.get().getRole())
                .build();

        userService.update(user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/profile.jsp");
        req.setAttribute("user",Optional.ofNullable(user).get());
        dispatcher.forward(req, resp);
    }

    private void passwordValidation(HttpServletRequest req, HttpServletResponse resp, String password,
                                    String email)
            throws ServletException, IOException {
        if (!isPasswordValid(password)) {
            Optional<User> userOptional = userService.findByEmail(email);
            req.setAttribute("exception", "Your password is invalid.");
            req.setAttribute("user", userOptional.get());
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/profile.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void phoneNumberValidation(HttpServletRequest req, HttpServletResponse resp, String phoneNumber)
            throws ServletException, IOException {
        if (!isPhoneNumberValid(phoneNumber)) {
            req.setAttribute("exception", "Your phone number is invalid.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/profile.jsp");
            dispatcher.forward(req, resp);
        }
    }
}


