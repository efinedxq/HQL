package lee;

import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;

import org.crazyit.app.domain.*;

/**
 * Description: <br/>
 * ��վ: <a href="http://www.crazyit.org">���Java����</a> <br/>
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
//		 mgr.findBfromA();
		 mgr.deleteR();
//		mgr.addToR();
		// ���ò�ѯ����
		// mgr.findPersons();
		// // ���õڶ�����ѯ����
		// mgr.findPersonsByHappenDate();
		// // ���õڶ�����ѯ����
		// mgr.findPersonProperty();
	}

	// ��һ����ѯ����
	private void findPersons() {
		// ���Hibernate Session
		Session sess = HibernateUtil.currentSession();
		// ��ʼ����
		Transaction tx = sess.beginTransaction();
		// ��HQL��䴴��Query����.
		// List pl = sess.createQuery("select distinct p from Person p "
		// + "join p.myEvents where title = :eventTitle")
		// // ִ��setString()����ΪHQL���Ĳ�����ֵ
		// .setString("eventTitle" , "����ͨ������")
		// // Query����list()������ȡ��ѯ��ȫ��ʵ��
		// .list();
		List p2 = sess.createQuery("select distinct p from Person p ")
				// Query����list()������ȡ��ѯ��ȫ��ʵ��
				.list();

		// ������ѯ��ȫ�����
		for (Object ele : p2) {
			Person p = (Person) ele;
			System.out.print(p.toString());
			Set<MyEvent> myEvents = p.getMyEvents();
			System.out.println(myEvents.toString());

		}

		// �ύ����
		tx.commit();
		HibernateUtil.closeSession();
	}

	// �ڶ�����ѯ����
	private void findPersonsByHappenDate() throws Exception {
		// ���Hibernate Session����
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		// ������Date����
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = sdf.parse("2005-01-01");
		System.out.println("ϵͳ��ʼͨ�����ڲ�����" + start);
		// ͨ��Session��createQuery��������Query����
		List pl = sess
				.createQuery("select distinct p from Person p " + "inner join p.myEvents event where event.happenDate "
						+ "between :firstDate and :endDate")
				// ���ò���
				.setDate("firstDate", start).setDate("endDate", new Date())
				// ���ؽ����
				.list();
		// ���������
		for (Object ele : pl) {
			Person p = (Person) ele;
			System.out.println(p.getName());
		}
		tx.commit();
		HibernateUtil.closeSession();
	}

	// ��������ѯ��������ѯ����
	private void findPersonProperty() {
		// ���Hibernate Session
		Session sess = HibernateUtil.currentSession();
		// ��ʼ����
		Transaction tx = sess.beginTransaction();
		// ��HQL��䴴��Query����.
		List pl = sess.createQuery("select distinct p.id,  p.name , p.age " + "from Person p join p.myEvents")
				// Query����list()�������ʲ�ѯ�õ���ȫ������
				.list();
		// ������ѯ��ȫ�����
		for (Object ele : pl) {
			Object[] objs = (Object[]) ele;
			System.out.println(java.util.Arrays.toString(objs));
		}
		// �ύ����
		tx.commit();
		HibernateUtil.closeSession();
	}

	// ��a�л�ȡ�������b
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

	// ��r�������¹���
	private void addToR() {
		// ����ķ�ʽ
		// AAA a = new AAA(1,"a1");
		// BBB b = new BBB(7,"����b");
		// RRR r = new RRR("����r");
		// Session sess = HibernateUtil.currentSession();
		// Transaction tx = sess.beginTransaction();
		// r.setAAA(a);
		// r.setBBB(b);
		// sess.save(r);
		// tx.commit();
		// HibernateUtil.closeSession();

		// ����ķ�ʽ
		// AAA a = new AAA(7,"����7");
		// BBB b = new BBB(1,"b1");
		// RRR r = new RRR("����r2",a,b);
		// Session sess = HibernateUtil.currentSession();
		// Transaction tx = sess.beginTransaction();
		// sess.save(r);
		// tx.commit();
		// HibernateUtil.closeSession();

		// ��ȷ�ķ�ʽ
		RRR r = new RRR("����r", 1, 7);//����ʵ����Id�࣬��������aid��bidֵ�����򱨴�
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		sess.save(r);
		tx.commit();
		HibernateUtil.closeSession();
	}
	//ɾ�� ���� r
	private void deleteR(){
		//����
		AAA a = new AAA(1, "a1");
		BBB b = new BBB(2, "b2");
		Session sess = HibernateUtil.currentSession();
		Transaction tx = sess.beginTransaction();
		Query query = sess.createQuery("from RRR where aaa=:aaa and bbb=:bbb");
		query.setParameter("aaa", a);
		query.setParameter("bbb", b);
		RRR r = (RRR) query.list().get(0);
		sess.delete(r);
		tx.commit();
		HibernateUtil.closeSession();
		
	   //����
//		RRR r = new RRR("����r", 1, 7);
//		Session sess = HibernateUtil.currentSession();
//		Transaction tx = sess.beginTransaction();
//		sess.delete(r);
//		tx.commit();
//		HibernateUtil.closeSession();
	}
}