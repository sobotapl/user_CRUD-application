package controllers;
import dao.UserDao;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/edit")
public class UserEdit extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = req.getParameter("id");
        int userId;
        try {
            userId = Integer.parseInt(userIdParam);
            UserDao userDao = new UserDao();
            User user = userDao.read(userId);
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/edit.jsp").forward(req, resp);
        } catch (NumberFormatException ex) {
            resp.sendRedirect("/users");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = req.getParameter("id");
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        User user = new User(username, email, null);
        if (validUser(user)) {
            try {
                int userId = Integer.parseInt(userIdParam);
                UserDao userDao = new UserDao();
                User userToUpdated = userDao.read(userId);
                userToUpdated.setUsername(user.getUsername());
                userToUpdated.setEmail(user.getEmail());
                userDao.update(userToUpdated);

            } catch (NumberFormatException e) {

            }
            resp.sendRedirect("/users");
        } else {

            try {
                int userId = Integer.parseInt(userIdParam);
                UserDao userDao = new UserDao();
                User userFromDb = userDao.read(userId);
                req.setAttribute("user", userFromDb);
            } catch (NumberFormatException ex) {

                resp.sendRedirect("/users");
            }
            req.setAttribute("errorMsg", "Niepoprawne dane użytkownika");
            getServletContext().getRequestDispatcher("/WEB-INF/views/users-edit.jsp").forward(req, resp);
        }
    }

    private boolean validUser(User user) {
        if (user.getUsername() == null || user.getUsername().isBlank()) return false;
        if (user.getEmail() == null || user.getEmail().isBlank()) return false;
        return true;
    }
}
