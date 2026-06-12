package WEEK1.task3;

import WEEK1.task3.Entity.Order;
import WEEK1.task3.Service.OrderProcessor;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        Order order = new Order("ORD101", "Sai", List.of("Laptop", "Mouse"), 5000.0,
                        "PENDING", "NOT_ASSIGNED", "GENAI10");
        double originalAmount = order.getTotalAmount();
        OrderProcessor validateOrder = o -> {
                    if(o.getItems().isEmpty()) {
                        throw new RuntimeException("Order Empty");
                    }
                    System.out.println("Order Validated");
                    return o;
                };
        OrderProcessor applyCoupon =
                o -> {
                    if("GENAI10".equalsIgnoreCase(o.getCouponCode())) {
                        double discount = o.getTotalAmount() * 0.10;
                        o.setTotalAmount(o.getTotalAmount() - discount);
                        System.out.println("Coupon Applied : GENAI10");
                    }
                    return o;
                };
        OrderProcessor calculateGst = o -> {
            double gst = o.getTotalAmount() * 0.18;
            o.setTotalAmount(o.getTotalAmount() + gst);
                    System.out.println("GST Added : 18%");
                    return o;
                };
        OrderProcessor confirmPayment = o -> {o.setPaymentStatus("SUCCESS");
                    System.out.println("Payment Confirmed");
                    return o;
                };
        OrderProcessor assignDeliveryPartner = o -> {
            o.setDeliveryStatus("ASSIGNED");
                    System.out.println("Delivery Partner Assigned");
                    return o;
                };
        OrderProcessor updateDeliveryStatus = o -> {
                    System.out.println("Delivery Status Updated");
                    return o;
                };
        Order finalOrder = validateOrder.andThen(applyCoupon).andThen(calculateGst).andThen(confirmPayment)
                        .andThen(assignDeliveryPartner).andThen(updateDeliveryStatus).process(order);
        System.out.println();
        System.out.println("Order ID : " + finalOrder.getOrderId());
        System.out.println("Original Amount : " + originalAmount);
        System.out.println("Coupon Applied : " + finalOrder.getCouponCode());
        System.out.println("Final Amount : " + finalOrder.getTotalAmount());
        System.out.println("Payment Status : " + finalOrder.getPaymentStatus());
        System.out.println("Delivery Status : " + finalOrder.getDeliveryStatus());
    }
}