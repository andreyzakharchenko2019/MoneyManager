package com.epam.zakharchenkoandrey.filters;

import javax.servlet.*;
import java.io.IOException;

public class PremiumFilter implements Filter {

    public static final String PREMIUM_ATTRIBUTE = "premiumUser";
    public static final String PARAMETER = "action";
    public static final String WALLET_LIST_ACTION = "walletList";
    public static final String PREMIUM_VALUE = "1";
    public static final String USER_ISN_T_PREMIUM = "userIsnTPremium";
    public static final String USER_ISN_T_PREMIUM_VALUE = "true";
    public static final String USER_ISN_T_PREMIUM_VALUE_FALSE = "false";


    @Override
    public void init (FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

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
