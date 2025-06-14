package com.project.ecommerce_app.repository;

import com.project.ecommerce_app.entity.Order;
import com.project.ecommerce_app.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByOrder(Order order);
//    List<OrderItem> findByOrderId(Integer orderId);
    @Query("SELECT oi FROM OrderItem oi WHERE oi.product.user.user_id = :sellerId AND oi.complete = false")
    List<OrderItem> findPendingOrderItemsBySellerId(@Param("sellerId") Integer sellerId);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.product.user.user_id = :sellerId AND oi.complete = true")
    List<OrderItem> findCompletedOrderItemsBySellerId(@Param("sellerId") Integer sellerId);
}
