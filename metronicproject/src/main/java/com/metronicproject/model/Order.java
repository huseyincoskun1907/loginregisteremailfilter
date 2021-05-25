package com.metronicproject.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    private BigDecimal totalAmount = new BigDecimal(0.00);
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;
    private String shippingAddress;
    private String billingAddress;
    private String paymentMethod;
    private String paymentInfo;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;
    private LocalDateTime orderedDate;
    private LocalDateTime endDate;
    private Boolean usingPoints = false;

    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
    }
    

	public Order(List<OrderItem> orderItems, BigDecimal totalAmount, Buyer buyer, String shippingAddress,
			String billingAddress, String paymentMethod, String paymentInfo, OrderStatus status,
			LocalDateTime orderedDate, LocalDateTime endDate, Boolean usingPoints) {
		super();
		this.orderItems = orderItems;
		this.totalAmount = totalAmount;
		this.buyer = buyer;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.paymentMethod = paymentMethod;
		this.paymentInfo = paymentInfo;
		this.status = status;
		this.orderedDate = orderedDate;
		this.endDate = endDate;
		this.usingPoints = usingPoints;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(LocalDateTime orderedDate) {
		this.orderedDate = orderedDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Boolean getUsingPoints() {
		return usingPoints;
	}

	public void setUsingPoints(Boolean usingPoints) {
		this.usingPoints = usingPoints;
	}
}
