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

      //      Создается несколько пользователей с машинами,
      //      добавляются в базу данных

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setUserCar(new Car("BMW", 3));
      userService.add(user1);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setUserCar(new Car("ОКА", 1111));
      userService.add(user2);
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user3.setUserCar(new Car("ВАЗ", 2106));
      userService.add(user3);
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setUserCar(new Car("ГАЗ", 31105));
      userService.add(user4);

      //      Вынимаются обратно

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getUserCar());
         System.out.println();
      }

      // В сервис был добавлен метод, который с помощью hql-запроса достает юзера,
      // владеющего машиной по ее модели и серии.

      System.out.println(userService.userByCar("ГАЗ", 31105) + "\n");
      System.out.println(userService.userByCar("ВАЗ", 2106) + "\n");
      System.out.println(userService.userByCar("ОКА", 1111) + "\n");
      System.out.println(userService.userByCar("BMW", 3));

      context.close();
   }
}
