package com.project.ecommerce_app.service;

import com.project.ecommerce_app.dto.AddressDto;
import com.project.ecommerce_app.entity.*;
import com.project.ecommerce_app.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnalyticsService analyticsService;
    @Override
    @Transactional
    public Order placeOrder(Integer userId, AddressDto address) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Cart cart = cartRepository.findByUser(user);

        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot place order.");
        }

        // Create a new Order
        Order order = new Order();
        order.setUser(cart.getUser()); // Assign user
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.pending);
        order.setCity(address.city);
        order.setState(address.state);
        order.setStreet(address.street);
        order.setZipcode(address.zipcode);
        order.setPhone(address.phone);
        // Convert CartItems to OrderItems
        List<OrderItem> orderItems = cart.getCartItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
            return orderItem;
        }).collect(Collectors.toList());

        // Set order total price
        double totalAmount = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
        order.setTotalAmount(totalAmount);

        // Save order and order items
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        // Clear cart after order is placed
//        cartItemRepository.deleteByCartId(cart.getCart_id());
//        List<CartItem> cartItems = cartItemRepository.findByCart_Cart_id(cart.getCart_id());
//        cartItemRepository.deleteAll(cartItems);


        return order;
    }

    @Override
    @Transactional
    public Optional<Order> getOrderById(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }



    @Override
    @Transactional
    public void cancelOrder(Integer orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    @Override
    @Transactional
    public List<Order> getOrdersByUserId(Integer userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return orderRepository.findByUser(user);
    }

    @Override
    @Transactional
    public List<OrderItem> getNewOrderItemsForSeller(Integer sellerId) {
        return orderItemRepository.findPendingOrderItemsBySellerId(sellerId);
    }

    @Override
    @Transactional
    public List<OrderItem> getPastOrderItemsForSeller(Integer sellerId) {
        return orderItemRepository.findCompletedOrderItemsBySellerId(sellerId);
    }

    @Override
    @Transactional
    public String markOrderItemAsCompleted(Integer orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        orderItem.setComplete(true);
        orderItemRepository.save(orderItem);
        analyticsService.updateSellerStats(orderItemId);
        return "Order Item marked as completed";
    }
}

