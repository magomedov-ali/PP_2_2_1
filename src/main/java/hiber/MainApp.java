package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car bmw7 = new Car("bmw", 3);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", bmw7));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("mercedes", 3)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("audi", 6)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("zhiguli", 7)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car model = " + user.getCar().getModel());
            System.out.println("Car series = " + user.getCar().getSeries());
            System.out.println();
        }

        List<User> usersWithBMW = userService.getUserByCar(bmw7);
        for (User user : usersWithBMW) {
            System.out.println("User " + user.getFirstName() + " has a " + user.getCar().getModel() + " " + user.getCar().getSeries() + " series");
        }

        context.close();
    }
}
