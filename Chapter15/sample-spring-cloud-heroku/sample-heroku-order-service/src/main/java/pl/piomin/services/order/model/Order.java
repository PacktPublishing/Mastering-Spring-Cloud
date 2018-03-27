package pl.piomin.services.order.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {

	@Id
	private String id;
	private OrderStatus status;
	private int price;
	private String customerId;
	private String accountId;
	private List<String> productIds;

	public Order() {

	}	
	
	public Order(String id, OrderStatus status, int price, String customerId, String accountId, List<String> productIds) {
		this.id = id;
		this.status = status;
		this.price = price;
		this.customerId = customerId;
		this.accountId = accountId;
		this.productIds = productIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public List<String> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<String> productIds) {
		this.productIds = productIds;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", price=" + price + ", customerId=" + customerId
				+ ", accountId=" + accountId + ", productIds=" + productIds + "]";
	}

}
