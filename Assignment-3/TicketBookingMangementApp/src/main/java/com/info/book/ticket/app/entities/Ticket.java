package com.info.book.ticket.app.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ticketId")
	private Integer ticketId;
	
	@Column(name="passsengername", nullable=false)
	private String passengername;
	
	@Column(name="booking_date")
	private Date booking_date;
	
	@Column(name="email")
	private String email;
	
	public Integer getTicketId() {
		
		return ticketId;
	}
	
	public void setticketId(Integer ticketId) {
		this.ticketId=ticketId;
	}
	
	public String getpassengername() {
		return passengername;
		
	}
	
	public void setpassengername(String passengername) {
		this.passengername=passengername;
	}
	public Date getbooking_date() {
		return booking_date;
	}
	public void setbooking_date(Date booking_date) {
		this.booking_date=booking_date;	
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email=email;
	}
public Ticket() {
	this.ticketId=ticketId;
    this.passengername=passengername;
	this.booking_date=booking_date;
	this.email=email;
}
}
