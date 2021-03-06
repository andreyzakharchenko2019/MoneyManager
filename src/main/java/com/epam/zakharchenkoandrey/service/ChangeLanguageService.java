package com.epam.zakharchenkoandrey.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageService implements Service {

    private static final String INDEX_JSP = "index.jsp";

    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String LOCALE_ATTRIBUTE_RU = "ru";
    private static final String LOCALE_ATTRIBUTE_EN = "en";
    private static final String LOCALE_PARAMETER = "clRU";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getParameter(LOCALE_PARAMETER) != null) {
            request.getSession().setAttribute(LOCALE_ATTRIBUTE, LOCALE_ATTRIBUTE_RU);
        } else {
            request.getSession().setAttribute(LOCALE_ATTRIBUTE, LOCALE_ATTRIBUTE_EN);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(INDEX_JSP);
        requestDispatcher.forward(request, response);
    }
}
