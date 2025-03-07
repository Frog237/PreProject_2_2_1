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


      userService.add((new User("User1", "Lastname1", "user1@mail.ru"))
              .setCar(new Car("Camry", 25)));
      userService.add((new User("User2", "Lastname2", "user2@mail.ru"))
              .setCar(new Car("Camry", 35)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCarInfo());
         System.out.println();
      }

      List<User> foundUsers = userService.findUserByCar("Camry", 25);
      int i = 1;
      for (User user : foundUsers) {
         if (i++ == 1) {
            System.out.println(user.getCar().getModel() + " "
                    + user.getCar().getSeries() + " owner(s):\n");
         }
         System.out.println(i-1 + ") First Name: " + user.getFirstName());
         System.out.println("   Last Name: " + user.getLastName());
         System.out.println("   Email: " + user.getEmail() + "\n");
      }

      context.close();
   }
}
