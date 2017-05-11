this project is used encoding of GBK.
总结一下。hibernate的功能真的很强大！
插入操作，创建一个对象然后save()就可以了。
更新、删除也一样，直接调用函数，不用写一条sql、hql语句！直接操作持久化对象，就相当于操作表中的数据。
查询也很简单，可以用get直接得到，也不用写任何数据库查询语句。
关于连接，比如people中有myEvent这个数据项，想要获得某个人的event数据，只需获取到这个人的持久化对象然后再调用get方法即可。就是这么简单方便。

流程：
1、// 使用默认的hibernate.cfg.xml配置文件创建Configuration实例。如果配置文件是abc.xml，则configure("abc.xml")。
   Configuration cfg = new Configuration().configure();
2、// 以Configuration实例来创建SessionFactory实例
   ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	       .applySettings(cfg.getProperties()).build();
   SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
3、//创建session
   Session s = sessionFactory.openSession();
   // 开始事务
   Transaction tx = sess.beginTransaction();
4、//获取持久化对象      
  //get(类名,标识属性)  会直接访问数据库。数据库中没数据时，直接返回null。
  //load(类名,标识属性)  具有延迟加载效果。返回一个代理对象。直到对这个对象进行操作时，才访问数据库。
   People p = (People)s.get(People.class,1);
5、//持久化实体   
  // save()立即插入数据。 persist()适合长事务。
   //先创建一个对象。
   People p = new People();
   ...//做一些初始化，填值。
   s.save(p)  //或者 s.persist(p)
 6、//更新持久化实体
    p.setName("新名字");//此处对p进行了更新。
    s.flush()//即可
 7、//更新托管实体。什么是托管实体，就是p所对应的session已经关闭了,那它就处于托管状态。
    p.setName("新名字");//此处对p进行了更新。
    Session s2 = ....//新建一个Session
    s2.update(p)//即可
8、//删除也很简单
   s.delete(p) 
9、//进行完这些操作之后记得
   //如果定义事务 tx.close();
   s.close();
   sf.close();
   
关于HQL语句查询，get只能获取单条记录，HQL语句适合比较复杂的条件查询。

关于映射关系，比如该项目的N-N关系。
比如有一个People p，想为其添加event关系，直接新建一个event然后调用p.getEvent().add(event),之后save(p)就可以了。关联表都会自动插入数据。
获取也一样，先得到一个People p持久化对象，然后p.getEvent()，就得到一个集合，系统自动生成查询语句从关联表中寻找其值。
 
 
 
 
   
   
   
   
   