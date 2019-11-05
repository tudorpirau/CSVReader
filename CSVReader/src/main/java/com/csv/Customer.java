package com.csv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "FIRST_NAME")
	 private String firstName;
	 
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "EMAIL")
	private String email;
	 
	@Column(name = "SEX")
	private String sex;
	
	@Column(name = "PICTURE")
	 private String picture;
	 
	@Column(name = "PAYMENT")
	private String payment;
	
	@Column(name = "PRICE")
	 private String price;
	 
	 @Column(name = "VALUE1")
	 private String value1;
	 
	 @Column(name = "VALUE2")
	 private String value2;
	 
	 @Column(name = "LOCATION")
	 private String location;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	 public boolean equals(Object obj){
	  if (this == obj){
	   return true;
	  }
	  Customer customer = (Customer) obj;
	  if (firstName != null ?
	    !firstName.equals(customer.firstName)
	    :customer.firstName != null){
	   return false;
	  }
	  else {
	   return true;
	  } 
	 }
	
	 @Override
	 public String toString() {
	  return "Customer [id=" 
	    + id 
	    + ", name=" 
	    + firstName 
	    + " " 
	    + lastName 
	    + "]";
	 }
}
