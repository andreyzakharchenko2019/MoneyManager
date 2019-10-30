package com.epam.zakharchenkoandrey.service;

import com.epam.zakharchenkoandrey.database.dao.CategoryDAO;
import com.epam.zakharchenkoandrey.entity.Category;
import com.epam.zakharchenkoandrey.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryListService implements Service {

    private static final String AUTHORIZED_USER_ATTRIBUTE = "currentUser";
    private static final String AUTHORIZED_LIST_CATEGORY_ATTRIBUTE = "listCategory";
    private static final String LIST_CATEGORY_JSP = "listCategory.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CategoryDAO categoryDAO = new CategoryDAO();
        User user = (User) request.getSession().getAttribute(AUTHORIZED_USER_ATTRIBUTE);
        List<Category> listCategory = categoryDAO.categoryList(user);
        request.getSession().setAttribute(AUTHORIZED_LIST_CATEGORY_ATTRIBUTE, listCategory);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(LIST_CATEGORY_JSP);
        requestDispatcher.forward(request, response);
    }
}
