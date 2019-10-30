package com.epam.zakharchenkoandrey.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class PremiumFilter implements Filter {

    private static final String PREMIUM_ATTRIBUTE = "premiumUser";
    private static final String PARAMETER = "action";
    private static final String WALLET_LIST_ACTION = "walletList";
    private static final String PREMIUM_VALUE = "1";
    private static final String USER_ISN_T_PREMIUM = "userIsnTPremium";
    private static final String USER_ISN_T_PREMIUM_VALUE = "true";
    private static final String USER_ISN_T_PREMIUM_VALUE_FALSE = "false";

    private static final Logger LOGGER = Logger.getLogger(PremiumFilter.class);


    @Override
    public void init (FilterConfig filterConfig) {
        LOGGER.info("Filter start.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletRequest.setCharacterEncoding("UTF-8");

        String serviceAction = servletRequest.getParameter(PARAMETER);

        if (serviceAction.equals(WALLET_LIST_ACTION)) {
            if (servletRequest.getParameter((PREMIUM_ATTRIBUTE)).equals(PREMIUM_VALUE)) {
                servletRequest.setAttribute(USER_ISN_T_PREMIUM, USER_ISN_T_PREMIUM_VALUE_FALSE);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                servletRequest.setAttribute(USER_ISN_T_PREMIUM, USER_ISN_T_PREMIUM_VALUE);
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
