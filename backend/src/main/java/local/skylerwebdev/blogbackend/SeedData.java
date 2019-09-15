package local.skylerwebdev.blogbackend;

import local.skylerwebdev.blogbackend.model.Blogpost;
import local.skylerwebdev.blogbackend.model.Role;
import local.skylerwebdev.blogbackend.model.User;
import local.skylerwebdev.blogbackend.model.UserRoles;
import local.skylerwebdev.blogbackend.repository.BlogpostRepository;
import local.skylerwebdev.blogbackend.repository.RoleRepository;
import local.skylerwebdev.blogbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    RoleRepository rolerepos;
    UserRepository userrepos;
    BlogpostRepository postrepos;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos, BlogpostRepository postrepos) {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.postrepos = postrepos;
    }

    @Override
    public void run(String[] args) throws Exception {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        rolerepos.save(r1);
        rolerepos.save(r2);

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u1 = new User("barnbarn", "ILuvM4th!", users);
        u1.getPosts().add(new Blogpost("Finish java-orders-swagger", new Date(),"This is the post body", u1));
        u1.getPosts().add(new Blogpost("Feed the turtles", new Date(), "This is the post body", u1));
        u1.getPosts().add(new Blogpost("Complete the sprint challenge", new Date(),"This is the post body", u1));
        userrepos.save(u1);

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        User u2 = new User("admin", "password", admins);
        userrepos.save(u2);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("Bob", "password", users);
        userrepos.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Jane", "password", users);
        userrepos.save(u4);
    }
}