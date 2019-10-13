package com.epam.zakharchenkoandrey.service;

import com.epam.zakharchenkoandrey.database.UserDAO;
import com.epam.zakharchenkoandrey.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInService implements Service {

    public static final String LOGIN_ATTRIBUTE = "login";
    public static final String PASSWORD_ATTRIBUTE = "password";
    public static final String AUTHORIZED_USER_ATTRIBUTE = "currentUser";
    public static final String EXCEPTION = "exception";
    public static final String INCORRECT_LOGIN_OR_PASSWORD = "101";
    public static final String INDEX_JSP = "index.jsp";

    private User user;

    public static final Logger LOGGER = Logger.getLogger(LogInService.class);

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

    private Boolean checkLoginAndPassword(String login, String password) {
        UserDAO userDAO = new UserDAO();
        boolean isCheck = false;
        user = userDAO.getUserByEmail(login);

        if (user.getPassword() == null) {
            isCheck = false;
        } else if (user.getPassword().equals(password)) {
            isCheck = true;
        }

        return isCheck;
    }
}
