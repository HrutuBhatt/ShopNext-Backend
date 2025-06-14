package com.project.ecommerce_app.controller;

import com.project.ecommerce_app.entity.Payment;
import com.project.ecommerce_app.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.ecommerce_app.dto.AddressDto;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // Process a payment for a customer
    @PostMapping("/process/{customerId}")
    public ResponseEntity<Payment> processPayment(@PathVariable Integer customerId, @RequestBody AddressDto address) {
        return ResponseEntity.ok(paymentService.processPayment(customerId, address));
    }

    // Get payment details by payment ID
    @GetMapping("/{paymentId}")
    public ResponseEntity<Optional<Payment>> getPaymentById(@PathVariable Integer paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }

    // Get all payments
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
}