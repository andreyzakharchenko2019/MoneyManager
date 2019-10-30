package com.epam.zakharchenkoandrey.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutService implements Service {

    private static final String INDEX_JSP = "index.jsp";
    private static final String AUTHORIZED_USER_ATTRIBUTE = "currentUser";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().setAttribute(AUTHORIZED_USER_ATTRIBUTE, null);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(INDEX_JSP);
        requestDispatcher.forward(request, response);
    }
}
