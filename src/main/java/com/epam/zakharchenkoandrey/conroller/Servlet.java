package com.epam.zakharchenkoandrey.conroller;

import com.epam.zakharchenkoandrey.service.Service;
import com.epam.zakharchenkoandrey.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {

    public static final String PARAMETER = "action";

    public static final Logger LOGGER = Logger.getLogger(Servlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        LOGGER.info("Servlet start.");
    }

    @Override
    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        String serviceAction = httpServletRequest.getParameter(PARAMETER);
        ServiceFactory factory = new ServiceFactory();
        Service service = factory.getService(serviceAction);
        service.execute(httpServletRequest, httpServletResponse);
    }
}