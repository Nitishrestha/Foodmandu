package com.foodorderingapp.daoImpl;

import com.foodorderingapp.dao.OrderDetailDAO;
import com.foodorderingapp.requestdto.OrderDetailRequestDto;
import com.foodorderingapp.exception.BadRequestException;
import com.foodorderingapp.exception.DataNotFoundException;
import com.foodorderingapp.exception.DataConflictException;
import com.foodorderingapp.model.OrderDetail;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("OrderDetailDAO")
public class OrderDetailDaoImpl implements OrderDetailDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDetailDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public OrderDetail add(OrderDetail orderDetail) {
        try {
            sessionFactory.getCurrentSession().save(orderDetail);
            return orderDetail;
        } catch (Exception ex) {
            throw new BadRequestException("cannot add orderDetail"+ex.getMessage());
        }
    }

    public List<OrderDetailRequestDto> getOrderDetail() {

        List<OrderDetailRequestDto> orderDetailRequestDtos = sessionFactory.getCurrentSession()
                .createNativeQuery(" SELECT tbl_users.first_name,tbl_users.middle_name," +
                        "tbl_order_detail.food_price,tbl_users.last_name," +
                        "tbl_order_detail.restaurant_name,tbl_orders.ordered_date," +
                        "tbl_orders.order_id, tbl_order_detail.food_name ,tbl_order_detail.quantity " +
                        "FROM ((tbl_order_detail INNER join tbl_orders " +
                        "ON tbl_orders.order_id = tbl_order_detail.order_id) INNER " +
                        "JOIN tbl_users on tbl_users.user_id = tbl_orders.user_id)", "OrderDetailMapping").getResultList();
        if(orderDetailRequestDtos.size()==0||orderDetailRequestDtos==null){
            throw new DataNotFoundException("cannot find list");
        }
        return orderDetailRequestDtos;
    }

    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {
        List<OrderDetail> orderDetailList = sessionFactory
                .getCurrentSession()
                .createQuery("FROM OrderDetail where orders.orderId=:orderId", OrderDetail.class)
                .setParameter("orderId", orderId).getResultList();
         if(orderDetailList==null || orderDetailList.size()==0){
             throw new DataNotFoundException("cannot find list");
         }
         return orderDetailList;
    }

    public Boolean updateOrderDetail(OrderDetail orderDetail) {
        try {
            sessionFactory.getCurrentSession().update(orderDetail);
            return true;
        } catch (Exception ex) {
            throw new DataConflictException("cannot update orderdetail");
        }
    }

    public OrderDetail getOrderDetailByUserId(int userId, String foodName, String restaurantName) {
        try {
            OrderDetail orderDetail = sessionFactory.getCurrentSession()
                    .createQuery("from OrderDetail od where od.orders.user.userId=:userId and" +
                            " od.foodName=:foodName " +
                            "and od.restaurantName=:restaurantName " +
                            "and od.orders.confirm is false", OrderDetail.class)
                    .setParameter("userId", userId)
                    .setParameter("foodName", foodName)
                    .setParameter("restaurantName", restaurantName)
                    .getSingleResult();
            if(orderDetail==null){
                throw new DataNotFoundException("cannot find orderDetailList.");
            }
            return orderDetail;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
            }
    }

    @Override
    public boolean deleteAllOrderDetail() {
        sessionFactory.getCurrentSession()
                .createQuery("delete from OrderDetail")
                .executeUpdate();
        return true;
    }
}
