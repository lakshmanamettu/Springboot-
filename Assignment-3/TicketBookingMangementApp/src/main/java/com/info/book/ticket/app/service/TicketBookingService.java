package com.info.book.ticket.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.book.ticket.app.dao.TicketBookingDao;
import com.info.book.ticket.app.entities.Ticket;

@Service
public class TicketBookingService {
     
	@Autowired
	private TicketBookingDao ticketBookingDao;
	
	
	public Ticket createTicket(Ticket ticket) {
		return ticketBookingDao.save(ticket);
	}
	public Optional<Ticket> getTicketById(Integer ticketId) {
		return ticketBookingDao.findById(ticketId);
	}
	
	
	public Iterable<Ticket> getallticketsbyId() {
	
		return ticketBookingDao.findAll();
	}
	
}
