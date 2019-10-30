package com.epam.zakharchenkoandrey.service;

import com.epam.zakharchenkoandrey.database.dao.UserDAO;
import com.epam.zakharchenkoandrey.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInService implements Service {

    private static final String LOGIN_ATTRIBUTE = "login";
    private static final String PASSWORD_ATTRIBUTE = "password";
    private static final String AUTHORIZED_USER_ATTRIBUTE = "currentUser";
    private static final String EXCEPTION = "exception";
    private static final String INCORRECT_LOGIN_OR_PASSWORD = "101";
    private static final String INDEX_JSP = "index.jsp";

    private User user;

    private static final Logger LOGGER = Logger.getLogger(LogInService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userLogin = request.getParameter(LOGIN_ATTRIBUTE);
        String userPassword = request.getParameter(PASSWORD_ATTRIBUTE);

        if (checkLoginAndPassword(userLogin, userPassword)) {

            request.getSession().setAttribute(AUTHORIZED_USER_ATTRIBUTE, user);

            TransactionService transactionService = new TransactionService();
            transactionService.execute(request, response);
            LOGGER.info("Current user: " + user.getIdUser() + " " + user.geteMail());
        } else {
            request.setAttribute(EXCEPTION, INCORRECT_LOGIN_OR_PASSWORD);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(INDEX_JSP);
            requestDispatcher.forward(request, response);
        }
    }

    private boolean checkLoginAndPassword(String login, String password) {
        UserDAO userDAO = new UserDAO();
        user = userDAO.getUserByEmail(login);

        return user.getPassword() != null && user.getPassword().equals(password);
    }
}
