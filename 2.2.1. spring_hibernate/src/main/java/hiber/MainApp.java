package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        Car car1 = new Car("Volvo", 1111);
        Car car2 = new Car("Skoda", 2222);
        Car car3 = new Car("Audi", 3333);
        Car car4 = new Car("Kia", 4444);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru",car1));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru",car4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println(userService.getUserByCar("Kia", 4444));

        context.close();
    }
}
