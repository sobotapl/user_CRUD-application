import model.User;
import dao.UserDao;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        User user = new User();
        user.setUsername("Piotr");
        user.setEmail("mail33@mail.com");
        user.setPassword("haslo");

        UserDao userDao = new UserDao();

        User userRead = userDao.read(4);
        System.out.println(userRead);

        userDao.delete(4);

        User userToUpdate = userDao.read(6);
        userToUpdate.setUsername("Jan");
        userToUpdate.setEmail("nowak@mail.pl");
        userToUpdate.setPassword("haslo");
        userDao.update(userToUpdate);

        User user2 = new User();
        user2.setUsername("tomek");
        user2.setEmail("tomek@pl");
        user2.setPassword("haslo");
        userDao.create(user2);
        List<User> all = userDao.findAll();
        for (User u : all) {
            System.out.println(u);
        }

    }

}
