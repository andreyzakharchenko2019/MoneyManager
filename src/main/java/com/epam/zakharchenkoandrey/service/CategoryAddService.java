package com.epam.zakharchenkoandrey.service;

import com.epam.zakharchenkoandrey.database.CategoryDAO;
import com.epam.zakharchenkoandrey.entity.Category;
import com.epam.zakharchenkoandrey.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CategoryAddService implements Service {

    public static final String AUTHORIZED_USER_ATTRIBUTE = "currentUser";
    public static final String NAME_CATEGORY_PARAMETER = "nameCategory";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute(AUTHORIZED_USER_ATTRIBUTE);

        Category category = new Category();
        category.setId_user(user.getId_user());
        category.setName_category(request.getParameter(NAME_CATEGORY_PARAMETER));

        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.addCategory(category);

        CategoryListService categoryListService = new CategoryListService();
        categoryListService.execute(request, response);
    }
}
