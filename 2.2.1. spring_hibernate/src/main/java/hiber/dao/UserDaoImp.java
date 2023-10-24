package hiber.dao;


import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
        String hql = "SELECT user FROM User user JOIN user.car car WHERE car.model = :carModel AND car.series = :carSeries";
        TypedQuery<User> typedQuery = session.createQuery(hql, User.class)
                .setParameter("carModel", model)
                .setParameter("carSeries", series);

        User user = typedQuery.getSingleResult();

        session.close();
        return user;

    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }
}
