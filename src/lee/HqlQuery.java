package lee;

import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;

import org.crazyit.app.domain.*;

/**
 * Description: <br/>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> <br/>
 * Copyright (C), 2001-2016, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class HqlQuery {
	public static void main(String[] args) throws Exception {
		HqlQuery mgr = new HqlQuery();
//		mgr.getAllA();
//		mgr.addPerson();
//		 mgr.findBfromA();
//		 mgr.deleteR();
//		 mgr.deleteA();
		mgr.addToR();
		// 调用查询方法
		// mgr.findPersons();
		// // 调用第二个查询方法
		// mgr.findPersonsByHappenDate();
		// // 调用第二个查询方法
		// mgr.findPersonProperty();
	}

	// 第一个查询方法
	private void findPersons() {
		// 获得Hibernate Session
		Session sess = HibernateUtil.currentSession();
		// 开始事务
		Transaction tx = sess.beginTransaction();
		// 以HQL语句创建Query对象.
		// List pl = sess.createQuery("select distinct p from Person p "
		// + "join p.myEvents where title = :eventTitle")
		// // 执行setString()方法为HQL语句的参数赋值
		// .setString("eventTitle" , "很普通的事情")
		// // Query调用list()方法获取查询的全部实例
		// .list();
		List p2 = sess.createQuery("select distinct p from Person p ")
				// Query调用list()方法获取查询的全部实例
				.list();

		// 遍历查询的全部结果
		for (Object ele : p2) {
			Person p = (Person) ele;
			System.out.print(p.toString());
			Set<MyEvent> myEvents = p.getMyEvents();
			System.out.println(myEvents.toString());

		}

		// 提交事务
		tx.commit();
		HibernateUtil.closeSession();
	}

	// 第二个查询方法
	private void findPersonsByHappenDate() throws Exception {
		// 获得Hibernate Session对象
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		// 解析出Date对象
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = sdf.parse("2005-01-01");
		System.out.println("系统开始通过日期查找人" + start);
		// 通过Session的createQuery方法创建Query对象
		List pl = sess
				.createQuery("select distinct p from Person p " + "inner join p.myEvents event where event.happenDate "
						+ "between :firstDate and :endDate")
				// 设置参数
				.setDate("firstDate", start).setDate("endDate", new Date())
				// 返回结果集
				.list();
		// 遍历结果集
		for (Object ele : pl) {
			Person p = (Person) ele;
			System.out.println(p.getName());
		}
		tx.commit();
		HibernateUtil.closeSession();
	}

	// 第三个查询方法：查询属性
	private void findPersonProperty() {
		// 获得Hibernate Session
		Session sess = HibernateUtil.currentSession();
		// 开始事务
		Transaction tx = sess.beginTransaction();
		// 以HQL语句创建Query对象.
		List pl = sess.createQuery("select distinct p.id,  p.name , p.age " + "from Person p join p.myEvents")
				// Query调用list()方法访问查询得到的全部属性
				.list();
		// 遍历查询的全部结果
		for (Object ele : pl) {
			Object[] objs = (Object[]) ele;
			System.out.println(java.util.Arrays.toString(objs));
		}
		// 提交事务
		tx.commit();
		HibernateUtil.closeSession();
	}

	// 从a中获取相关联的b
	private void findBfromA() {
		Integer aid = 1;
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("from AAA where aid=:aid");
		query.setParameter("aid", aid);
		AAA a = (AAA) query.list().get(0);

		Set<RRR> rS = a.getrS();
		List bList = new ArrayList<>();
		for (RRR r : rS) {
			bList.add(r.getBBB());
		}
		System.out.println(bList.toString());
		tx.commit();
		HibernateUtil.closeSession();
	}

	// 向r中增加新关联
	private void addToR() {
		// 错误的方式
		// AAA a = new AAA(1,"a1");
		// BBB b = new BBB(7,"新增b");
		// RRR r = new RRR("新增r");
		// Session sess = HibernateUtil.currentSession();
		// Transaction tx = sess.beginTransaction();
		// r.setAAA(a);
		// r.setBBB(b);
		// sess.save(r);
		// tx.commit();
		// HibernateUtil.closeSession();

		// 错误的方式
		// AAA a = new AAA(7,"新增7");
		// BBB b = new BBB(1,"b1");
		// RRR r = new RRR("新增r2",a,b);
		// Session sess = HibernateUtil.currentSession();
		// Transaction tx = sess.beginTransaction();
		// sess.save(r);
		// tx.commit();
		// HibernateUtil.closeSession();

		// 正确的方式
		RRR r = new RRR("r10", 7, 2);//必须实例化Id类，必须设置aid、bid值，否则报错。
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		Serializable kk = sess.save(r);
		System.out.println("save的返回值是Serializable:"+kk.toString());
		tx.commit();
		HibernateUtil.closeSession();
	}
	//删除 关联 r
	private void deleteR(){
		//可以
//		AAA a = new AAA(1, "a1");
//		BBB b = new BBB(2, "b2");
//		Session sess = HibernateUtil.currentSession();
//		Transaction tx = sess.beginTransaction();
//		Query query = sess.createQuery("from RRR where aaa=:aaa and bbb=:bbb");
//		query.setParameter("aaa", a);
//		query.setParameter("bbb", b);
//		RRR r = (RRR) query.list().get(0);
//		sess.delete(r);
//		tx.commit();
//		HibernateUtil.closeSession();
//		
	   //可以
		RRR r = new RRR("r8", 2, 2);
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		sess.delete(r);
		tx.commit();
		HibernateUtil.closeSession();
	}
	//hql删除操作
	private void deleteA(){
		Integer aid  = 9;
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("delete AAA where aid=:aid");
		query.setParameter("aid", aid);
		Integer res = query.executeUpdate();
		System.out.println("executeUpdate返回值："+res);
		tx.commit();
		HibernateUtil.closeSession();
	}
	
	//1~N添加N时自动添加关联表
	private void addPerson(){
		//不能更新自动插入关联  因为MyEvent有mappedBy属性
//		Integer id  = 1;
//		Person p = new Person("kuun", 23);
//		Session sess = HibernateUtil.currentSession();
//		Transaction tx = sess.beginTransaction();
//		sess.save(p);
//		Query query = sess.createQuery("from MyEvent where id=:id");
//		query.setParameter("id", id);
//		MyEvent myE = (MyEvent) query.list().get(0);
//		myE.getActors().add(p);
//		sess.save(myE);
//		tx.commit();
//		HibernateUtil.closeSession();
		
		//可以
		Integer id  = 1;
		Person p = new Person("jun", 23); //未持久化
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("from MyEvent where id=:id");
		query.setParameter("id", id);
		MyEvent myE = (MyEvent) query.list().get(0); //持久化
		p.getMyEvents().add(myE);
		sess.save(p); //持久化
		tx.commit();
		HibernateUtil.closeSession();
	}
	//获取全部的A
	private void getAllA(){
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("from AAA");
		System.out.println(query.list().toString());
		tx.commit();
		HibernateUtil.closeSession();
	}
	//save()返回值是一个序列化对象，插入的当前行。以1作为起始行。
	//delete() 返回值是void
	//executeUpdate() 没有删除时0，删除1
}