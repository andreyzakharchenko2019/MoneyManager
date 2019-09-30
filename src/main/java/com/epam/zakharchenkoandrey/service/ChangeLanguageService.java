package com.epam.zakharchenkoandrey.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageService implements Service {

    public static final String INDEX_JSP = "index.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getParameter("clRU") != null) {
            request.getSession().setAttribute("locale", "ru");
        } else {
            request.getSession().setAttribute("locale", "en");
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(INDEX_JSP);
        requestDispatcher.forward(request, response);
    }
}
