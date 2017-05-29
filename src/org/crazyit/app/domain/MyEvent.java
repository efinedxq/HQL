package org.crazyit.app.domain;

import java.util.*;

import javax.persistence.*;
/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
@Entity
@Table(name="event_inf")
public class MyEvent
{
	// �����ʶ����
	@Id @Column(name="event_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	// �����¼����Ƶĳ�Ա����
	private String title;
	// �����¼�����ʱ��ĳ�Ա����
	private Date happenDate;
	// �����MyEventʵ�����������Personʵ��
	@ManyToMany(targetEntity=Person.class , mappedBy="myEvents")
	private Set<Person> actors
		= new HashSet<>();

	public MyEvent () {
	}
	public MyEvent(String title,Date happenDate){
		this.title = title;
		this.happenDate = happenDate;
	}
	// id��setter��getter����
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
	}

	// title��setter��getter����
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTitle()
	{
		return this.title;
	}

	// happenDate��setter��getter����
	public void setHappenDate(Date happenDate)
	{
		this.happenDate = happenDate;
	}
	public Date getHappenDate()
	{
		return this.happenDate;
	}

	// actors��setter��getter����
	public void setActors(Set<Person> actors)
	{
		this.actors = actors;
	}
	public Set<Person> getActors()
	{
		return this.actors;
	}
	@Override
	public String toString(){
		return "<id:"+this.id+" title:"+this.title+" happenDate:"+this.happenDate+">";
	}
}