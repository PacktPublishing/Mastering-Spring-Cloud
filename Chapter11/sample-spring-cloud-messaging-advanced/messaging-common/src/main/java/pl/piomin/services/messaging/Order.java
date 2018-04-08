package pl.piomin.services.messaging;

import java.util.List;

public class Order {

	private Long id;
	private OrderStatus status;
	private int price;
	private Long customerId;
	private Long accountId;
	private List<Long> productIds;

	public Order() {
		
	}
	
	public Order(OrderStatus status, Long customerId, List<Long> productIds) {
		this.status = status;
		this.customerId = customerId;
		this.productIds = productIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public List<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}

}
