package com.foodorderingapp.daoImpl;

import com.foodorderingapp.dao.UserDAO;
import com.foodorderingapp.responsedto.UserListMapperDto;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User addUser(User user) {
        try {
            sessionFactory.getCurrentSession().save(user);
            return user;
        } catch (Exception ex) {
            throw new DataNotFoundException("cannot add user"+ex.getMessage());
        }
    }


    public List<User> getUsers() {

        List<User> userList=sessionFactory.getCurrentSession()
                .createQuery("FROM User", User.class)
                .getResultList();
        if(userList==null|| userList.size()==0){
            throw new DataNotFoundException("cannot find userlist.");
        }
        return userList;
    }

    public User getUser(int userId) {
        User user= sessionFactory
                .getCurrentSession()
                .get(User.class, userId);
        if(user==null){
            throw new DataNotFoundException("cannot find user.");
        }
        return user;
    }

    public User getUserByEmailId(String email) {
        try {
            User user1 = sessionFactory.getCurrentSession().
                    createQuery("FROM User WHERE email=:email", User.class).
                    setParameter("email", email).setMaxResults(1).
                    getSingleResult();
            return user1;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean update(User user) {
        try {
            sessionFactory.getCurrentSession().merge(user);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw  new BadRequestException("cannot update user.");
        }
    }

    @Override
    public List<UserListMapperDto> getUsersByUserForAMonth(int userId) {
        List<UserListMapperDto> userListMapperDtos= sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT tbl_orders.order_id,tbl_orders.user_id,\n" +
                        "tbl_orders.ordered_date,tbl_orders.confirm from tbl_orders\n" +
                        "INNER JOIN tbl_users ON tbl_orders.user_id=tbl_users.user_id\n" +
                        "WHERE YEAR(CURRENT_TIMESTAMP) = YEAR(ordered_date)\n" +
                        "AND MONTH(CURRENT_TIMESTAMP) = MONTH(ordered_date)\n" +
                        "AND tbl_users.user_id=? AND tbl_users.user_role=\"user\"\n" +
                        "ORDER BY tbl_orders.ordered_date DESC", "UserMapping")
                        .setParameter(1,userId).getResultList();
        if(userListMapperDtos==null||userListMapperDtos.size()==0){
            throw new DataNotFoundException("cannnot find required list.");
        }

        return userListMapperDtos;
    }


    public List<UserListMapperDto> getByUserForToday(int userId) {
        List<UserListMapperDto> userListMapperDtos= sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT tbl_orders.order_id,tbl_orders.user_id,\n" +
                        "tbl_orders.ordered_date,tbl_orders.confirm from tbl_orders\n" +
                        "INNER JOIN tbl_users ON tbl_orders.user_id=tbl_users.user_id\n" +
                        "WHERE  CAST(tbl_orders.ordered_date AS DATE)=CURRENT_DATE \n" +
                        " AND tbl_users.user_id=? AND tbl_users.user_role=\"user\" " +
                        "ORDER BY tbl_orders.ordered_date DESC","UserMapping")
                .setParameter(1, userId).getResultList();

        if(userListMapperDtos==null||userListMapperDtos.size()==0){
            throw new DataNotFoundException("cannnot find required list.");
        }

        return userListMapperDtos;
    }

    public boolean updateBalance() {
        double balance=1500.00;
        Query qry=sessionFactory
                .getCurrentSession()
                .createQuery("update User  set balance=:balance")
        .setParameter("balance",balance);
        qry.executeUpdate();
        return true;
    }

    public boolean deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
        return true;
    }
}

