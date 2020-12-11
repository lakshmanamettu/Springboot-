package com.info.book.ticket.app;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.info.book.ticket.app.entities.Ticket;
import com.info.book.ticket.app.service.TicketBookingService;

@SpringBootApplication
public class TicketBookingMangementAppApplication {

 
	public static void main(String[] args) {
		ConfigurableApplicationContext applicaioncontext=SpringApplication.run(TicketBookingMangementAppApplication.class, args);
	   
		TicketBookingService ticketBookingService= applicaioncontext.getBean("ticketBookingService",TicketBookingService.class);
	    Ticket ticket=new Ticket();
	    //ticket.setticketId(123);
	    ticket.setbooking_date(new Date());	
	    ticket.setpassengername("lakshman");
	    ticket.setemail("mlk@gmail.com");
	   ticketBookingService.createTicket(ticket);
	}

}
