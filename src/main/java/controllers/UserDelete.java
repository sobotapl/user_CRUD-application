package controllers;

import dao.UserDao;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/remove")
public class UserDelete extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = req.getParameter("id");
        try {
            int userId = Integer.parseInt(userIdParam);
            UserDao userDao = new UserDao();
            User user = userDao.read(userId);
            if (user == null) {
                resp.sendRedirect("/users");
                return;
            }
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/delete.jsp").forward(req, resp);
        } catch (NumberFormatException ex) {
            resp.sendRedirect("/users");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = req.getParameter("id");
        int userId;
        try {
            userId = Integer.parseInt(userIdParam);
            UserDao userDao = new UserDao();
            User user = userDao.read(userId);
            if (user == null) {
                resp.sendRedirect("/users");
                return;
            }
            userDao.delete(userId);
        } catch (NumberFormatException ex) {
        }
        resp.sendRedirect("/users");
    }
}
