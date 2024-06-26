package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
* Конструкторы поставил в порядке возрастания числа параметров
* Внес изменения во внедрение зависимостей:
* 1) UserServiceImp - через ламбок (Зависимость подгрузил)
* 2) UserDaoImp - через конструктор
* 3) AppConfig - через сервис
* */
public class MainApp {
    public static void main(String[] args) throws SQLException {
        List<Object> objects = new ArrayList<>();

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        Car car1 = new Car("Car1", 11);
        car1.setUser(user1);
        user1.setCar(car1);

        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        Car car2 = new Car("Car2", 22);
        car2.setUser(user2);
        user2.setCar(car2);

        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        Car car3 = new Car("Car3", 33);
        car3.setUser(user3);
        user3.setCar(car3);

        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        Car car4 = new Car("Car4", 44);
        car4.setUser(user4);
        user4.setCar(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println();
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
        }
        System.out.println();
        System.out.println(userService.getUserByCar("Car1", 11));
        System.out.println();
        context.close();
    }
}
