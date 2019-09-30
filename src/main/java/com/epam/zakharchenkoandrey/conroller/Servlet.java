package com.epam.zakharchenkoandrey.conroller;

import com.epam.zakharchenkoandrey.service.Service;
import com.epam.zakharchenkoandrey.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {

    public static final String PARAMETER = "action";

    @Override
    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        String serviceAction = httpServletRequest.getParameter(PARAMETER);
        ServiceFactory factory = new ServiceFactory();
        Service service = factory.getService(serviceAction);
        service.execute(httpServletRequest, httpServletResponse);
    }
}