package com.info.book.ticket.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.info.book.ticket.app.entities.Ticket;
import com.info.book.ticket.app.service.TicketBookingService;

@RestController
@RequestMapping(value="/api/tickets")
public class TicketBookingController {
    @Autowired
	private TicketBookingService ticketBookingService;

	
    @RequestMapping(value="/create",method=RequestMethod.POST)
    public Ticket createTicket(@RequestBody Ticket ticket) {
		return ticketBookingService.createTicket(ticket);
	}
    @GetMapping(value="/ticket/{ticketId}")
    public Optional<Ticket> getTicketById(@PathVariable("ticketId")Integer TicketId) {
    	return ticketBookingService.getTicketById(TicketId);
    	
    }
    @GetMapping(value="/ticket/alltickets")
    public Iterable<Ticket> getallticketsbyId(){
		return ticketBookingService.getallticketsbyId() ;
    	
    }
    @DeleteMapping(value="/ticket/{ticketId}")
    public Optional<Ticket> deleteticket(@PathVariable("ticketId")Integer TicketId) {
    	return ticketBookingService.getTicketById(TicketId);
    }
}
