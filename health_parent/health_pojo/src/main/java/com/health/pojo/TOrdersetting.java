package com.health.pojo;

import java.io.Serializable;
import java.util.Date;

public class TOrdersetting implements Serializable{
    private Integer id;

    private Date orderdate;

    private Integer number;

    private Integer reservations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getReservations() {
        return reservations;
    }

    public void setReservations(Integer reservations) {
        this.reservations = reservations;
    }

	public TOrdersetting(Integer id, Date orderdate, Integer number,
			Integer reservations) {
		super();
		this.id = id;
		this.orderdate = orderdate;
		this.number = number;
		this.reservations = reservations;
	}
	public TOrdersetting(Date orderdate, Integer number) {
		this.orderdate = orderdate;
		this.number = number;
	}
    
}