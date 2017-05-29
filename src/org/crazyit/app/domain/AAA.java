package org.crazyit.app.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="a")
public class AAA {
	@Id
	@Column(name="aid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;
    private String aname;
    @OneToMany(mappedBy="aaa")
	private Set<RRR> rS = new HashSet<>();
    
    public AAA(){
    }
    public AAA(String aname){
    	this.aname = aname;
    }
    public AAA(Integer aid ,String aname){
    	this.aid = aid;
    	this.aname = aname;
    }
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
    public Set<RRR> getrS() {
		return rS;
	}
	public void setrS(Set<RRR> rS) {
		this.rS = rS;
	}
	public String toString(){
    	return "<aid:"+aid+" aname:"+aname+">";
    }
}
