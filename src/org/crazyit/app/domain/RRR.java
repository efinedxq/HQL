package org.crazyit.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="r")
public class RRR {
	@EmbeddedId
	private Id id = new Id();
	
	private String rname;
	@ManyToOne
	@JoinColumn(name="aid",insertable=false,updatable=false)
	private AAA aaa;
	@ManyToOne
	@JoinColumn(name="bid",insertable=false,updatable=false)
	private BBB bbb;
    
	
	public RRR(){
    }
    public RRR(String rname){
    	this.rname = rname;
    }
    public RRR(String rname,AAA aaa,BBB bbb){
    	this.rname = rname;
    	this.aaa = aaa;
    	this.bbb = bbb;
    }
    public RRR(String rname, Integer aid, Integer bid){
    	this.rname = rname;
    	this.id = new Id(aid,bid);
    }
    
	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public AAA getAAA() {
		return aaa;
	}

	public void setAAA(AAA aaa) {
		this.aaa = aaa;
	}

	public BBB getBBB() {
		return bbb;
	}

	public void setBBB(BBB bbb) {
		this.bbb = bbb;
	}
	public String toString(){
    	return "<a:"+aaa.toString()+" b:"+bbb.toString()+" rname:"+rname+">";
    }
	@Embeddable
	public static class Id implements Serializable{
		@Column(name="aid")
		protected Integer aid;
		@Column(name="bid")
		protected Integer bid;
		
		public Id(){}
		public Id(Integer aid, Integer bid){
			this.aid = aid;
			this.bid = bid;
		}
		
		public boolean equals(Object o){
			if(o!=null && o instanceof Id){
				Id that = (Id)o;
				return (this.aid.equals(that.aid))&&(this.bid.equals(that.bid));
			}
			return false;
		}
		
		public int hashCode(){
			return this.aid.hashCode() + this.bid.hashCode();
		}
		public Integer getAid() {
			return aid;
		}
		public Integer getBid() {
			return bid;
		}
	}
}
