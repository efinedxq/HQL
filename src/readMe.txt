this project is used encoding of GBK.
�ܽ�һ�¡�hibernate�Ĺ�����ĺ�ǿ��
�������������һ������Ȼ��save()�Ϳ����ˡ�
���¡�ɾ��Ҳһ����ֱ�ӵ��ú���������дһ��sql��hql��䣡ֱ�Ӳ����־û����󣬾��൱�ڲ������е����ݡ�
��ѯҲ�ܼ򵥣�������getֱ�ӵõ���Ҳ����д�κ����ݿ��ѯ��䡣
�������ӣ�����people����myEvent����������Ҫ���ĳ���˵�event���ݣ�ֻ���ȡ������˵ĳ־û�����Ȼ���ٵ���get�������ɡ�������ô�򵥷��㡣

���̣�
1��// ʹ��Ĭ�ϵ�hibernate.cfg.xml�����ļ�����Configurationʵ������������ļ���abc.xml����configure("abc.xml")��
   Configuration cfg = new Configuration().configure();
2��// ��Configurationʵ��������SessionFactoryʵ��
   ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	       .applySettings(cfg.getProperties()).build();
   SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
3��//����session
   Session s = sessionFactory.openSession();
   // ��ʼ����
   Transaction tx = sess.beginTransaction();
4��//��ȡ�־û�����      
  //get(����,��ʶ����)  ��ֱ�ӷ������ݿ⡣���ݿ���û����ʱ��ֱ�ӷ���null��
  //load(����,��ʶ����)  �����ӳټ���Ч��������һ���������ֱ�������������в���ʱ���ŷ������ݿ⡣
   People p = (People)s.get(People.class,1);
5��//�־û�ʵ��   
  // save()�����������ݡ� persist()�ʺϳ�����
   //�ȴ���һ������
   People p = new People();
   ...//��һЩ��ʼ������ֵ��
   s.save(p)  //���� s.persist(p)
 6��//���³־û�ʵ��
    p.setName("������");//�˴���p�����˸��¡�
    s.flush()//����
 7��//�����й�ʵ�塣ʲô���й�ʵ�壬����p����Ӧ��session�Ѿ��ر���,�����ʹ����й�״̬��
    p.setName("������");//�˴���p�����˸��¡�
    Session s2 = ....//�½�һ��Session
    s2.update(p)//����
8��//ɾ��Ҳ�ܼ�
   s.delete(p) 
9��//��������Щ����֮��ǵ�
   //����������� tx.close();
   s.close();
   sf.close();
   
����HQL����ѯ��getֻ�ܻ�ȡ������¼��HQL����ʺϱȽϸ��ӵ�������ѯ��

����ӳ���ϵ���������Ŀ��N-N��ϵ��
������һ��People p����Ϊ�����event��ϵ��ֱ���½�һ��eventȻ�����p.getEvent().add(event),֮��save(p)�Ϳ����ˡ����������Զ��������ݡ�
��ȡҲһ�����ȵõ�һ��People p�־û�����Ȼ��p.getEvent()���͵õ�һ�����ϣ�ϵͳ�Զ����ɲ�ѯ���ӹ�������Ѱ����ֵ��
 
 
 
 
   
   
   
   
   