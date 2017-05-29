package org.crazyit.app.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "b")
public class BBB {
	@Id
	@Column(name = "bid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bid;
	private String bname;
	@OneToMany(mappedBy = "bbb")
	private Set<RRR> rS = new HashSet<>();

	public BBB(){
    }
    public BBB(String bname){
    	this.bname = bname;
    }
    public BBB(Integer bid ,String bname){
    	this.bid = bid;
    	this.bname = bname;
    }
	
	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String toString() {
		return "<bid:" + bid + " bname:" + bname + ">";
	}
}
