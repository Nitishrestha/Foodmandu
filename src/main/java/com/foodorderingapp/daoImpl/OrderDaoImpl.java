package com.foodorderingapp.daoImpl;

import com.foodorderingapp.dao.OrderDAO;
import com.foodorderingapp.requestdto.OrderListMapperDto;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.DataConflictException;
import com.foodorderingapp.model.Orders;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDAO{

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory ){
        this.sessionFactory=sessionFactory;
    }

    public Orders getOrderByUserWithConfirm(int userId) {
       try {
           return sessionFactory.getCurrentSession()
                   .createQuery("from Orders o where o.user.userId=:userId and o.confirm is false", Orders.class)
                   .setParameter("userId", userId).getSingleResult();
       } catch (Exception e) {
            return null;
       }
   }

    public Orders add(Orders orders) {
        try {
            sessionFactory.getCurrentSession().save(orders);
            return  orders;
        } catch (Exception e) {
            throw new BadRequestException("cannnot add order"+e.getMessage());
        }
    }

    public List<OrderListMapperDto> getOrderForAdminForMonth() {
        List<OrderListMapperDto> orderListMapperDtoList=sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT  o.order_id , o.ordered_date, o.user_id, u.first_name," +
                        "u.middle_name,u.last_name,o.confirm \n" +
                        "FROM tbl_orders as o inner join tbl_users as u " +
                        "on  o.user_id=u.user_id and year(o.ordered_date)= year(current_date)\n" +
                        "   and month(o.ordered_date)= month(current_date) ORDER BY o.order_id","OrderMapping")
                .getResultList();
        if(orderListMapperDtoList==null || orderListMapperDtoList.size()==0){
                throw new DataNotFoundException("cannot find list.");
        }
        return orderListMapperDtoList;
    }

    @Override
    public List<OrderListMapperDto> getOrderLogForAdminForToday() {
        Query qry = sessionFactory
                .getCurrentSession()
                .createNativeQuery("SELECT   tbl_orders.order_id,tbl_orders.ordered_date ,\n" +
                        "tbl_orders.user_id ,tbl_users.first_name,tbl_orders.confirm," +
                        "tbl_users.middle_name,tbl_users.last_name" +
                        " FROM tbl_orders  INNER JOIN tbl_users ON tbl_orders.user_id=tbl_users.user_id\n" +
                        "WHERE CAST(tbl_orders.ordered_date AS DATE)=CURRENT_DATE\n" +
                        "AND tbl_users.user_role=\"user\" ORDER BY tbl_orders.ordered_date DESC","OrderMapping");
        List<OrderListMapperDto> orderListMapperDtoList=qry.getResultList();
        if(orderListMapperDtoList==null || orderListMapperDtoList.size()==0){
            throw new DataNotFoundException("cannot find list.");
        }
        return orderListMapperDtoList;
    }


    public Boolean update(Orders orders) {
        try {
            sessionFactory.getCurrentSession().update(orders);
            return true;
        } catch (Exception ex) {
            throw  new DataConflictException("cannot update order.");
        }
    }

    public Orders getOrder(int orderId) {
        try {
            Orders orders = sessionFactory.getCurrentSession().get(Orders.class, orderId);
            return orders;
        } catch (Exception ex) {
            throw new DataNotFoundException("cannot find order");
        }
    }

    @Override
    public boolean delete(Orders orders) {
        sessionFactory.getCurrentSession().delete(orders);
        return true;
    }
}
