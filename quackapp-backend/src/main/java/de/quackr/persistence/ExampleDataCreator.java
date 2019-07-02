package de.quackr.persistence;

import de.quackr.persistence.entities.Quack;
//import de.quackr.persistence.entities.Quack_;
import de.quackr.persistence.entities.User;
//import de.quackr.persistence.entities.User_;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Singleton
@Startup
public class ExampleDataCreator {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void startup() {
        createExampleData();
    }

    private void createExampleData() {
        Date now = new Date(System.currentTimeMillis());

        final User tom = new User();
        tom.setUsername("EinfachTom");
        tom.setEmail("tom@quackrmail.de");
        tom.setSignUpTimestamp(now);
        this.entityManager.persist(tom);

        final User max = new User();
        max.setUsername("M4xTheModerat0r");
        max.setEmail("maxthemod@nomail.xy");
        max.setModerator(true);
        max.setBirthday(new GregorianCalendar(1990, Calendar.FEBRUARY, 1).getTime());
        max.setSignUpTimestamp(now);
        this.entityManager.persist(max);

        final User admin = new User();
        admin.setUsername("admin");
        admin.setPasswordHash("admin");
        admin.setModerator(true);
        admin.setAdmin(true);
        admin.setSignUpTimestamp(new Date(0));
        this.entityManager.persist(admin);

        final Quack q1 = new Quack();
        q1.setTitle("First Quack");
        q1.setText("Quack, yeah.");
        q1.setAuthor(tom);
        q1.setDate(now);
        q1.setBackgroundColor("smoky-black");
        this.entityManager.persist(q1);

        final Quack q2 = new Quack();
        q2.setTitle("Second Quack");
        q2.setText("Quack, yeah yeah.");
        q2.setAuthor(max);
        q2.setDate(now);
        q2.setBackgroundColor("dark-lavender");
        this.entityManager.persist(q2);

        final Quack q3 = new Quack();
        q3.setTitle("Lorem Ipsum");
        q3.setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt " +
            "ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores " +
            "et ea rebum.");
        q3.setAuthor(max);
        q3.setDate(now);
        q3.setBackgroundColor("mountbatten-pink");
        this.entityManager.persist(q3);
    }
}
