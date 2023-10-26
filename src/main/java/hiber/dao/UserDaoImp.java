package hiber.dao;


import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUserByCar(String model, int series) {
        Session session = sessionFactory.openSession();
        String hql = "FROM User user WHERE user.car.model = :carModel AND user.car.series = :carSeries";
        TypedQuery<User> typedQuery = session.createQuery(hql, User.class)
                .setParameter("carModel", model)
                .setParameter("carSeries", series);

        User user = typedQuery.getSingleResult();

        session.close();
        return user;

    }

    @Override
    public List<User> listUsers() {
        List<User> userList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        userList = session.createQuery("from User", User.class).list();
        return userList;
    }
}
