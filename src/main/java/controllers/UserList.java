package controllers;
import dao.UserDao;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String added = req.getParameter("added");
        if (added != null && !added.isBlank()) {
            req.setAttribute("added", Integer.parseInt(added));
        }


    UserDao userDao = new UserDao();
    List<User> users = userDao.findAll();
    req.setAttribute("users", users);
    getServletContext().getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);

    }

}

