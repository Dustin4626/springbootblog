package com.dustin.springbootblog.web.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.query.spi.NativeQueryImplementor;
import org.hibernate.transform.Transformers;
import org.springframework.util.Assert;

import com.dustin.springbootblog.model.SysSchedulerFiredList;


public class GenericDao<T, ID extends Serializable>{

	protected Class<T> entityClass;
	
	@PersistenceContext
	EntityManager entityManager;
	
	public GenericDao() {
	}
	
	public void entity(Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	public GenericDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected Class getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
//			logger.debug("T class = " + entityClass.getName());
//			GenericsUtils.getSuperClassGenricType(T.getClass()); 
		}
		return entityClass;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * HQL查詢
	 */
	public List<T> find(String queryString, Object value)  {
		Query  query = entityManager.createQuery(queryString);
		query.setParameter(0, value);
		List<T> find = query.getResultList();
		return find;
	}
	/**
	 * HQL查詢
	 */
	public List<T> find(String queryString, Object[] values) {
		Query  query = entityManager.createQuery(queryString);
		if(values!=null && values.length>0){
			for(int i=0; i<values.length; i++){
				query.setParameter(i, values[i]);
			}
		}
		List<T> find = query.getResultList();
		return find;
	}
	/**
	 * HQL查詢(可使用setParameterList)
	 */
	@SuppressWarnings("rawtypes")
	public List<T> find(String queryString, Map<String, Object> paramsMap) {
		Query query = entityManager.createQuery(queryString);
		if (paramsMap != null && !paramsMap.isEmpty()) {
			paramsMap.entrySet().forEach(e -> {
				if (e.getValue() instanceof Object[]) {
					query.setParameter(e.getKey(), (Object[]) e.getValue());
				} else if (e.getValue() instanceof List) {
					query.setParameter(e.getKey(), (List) e.getValue());
				} else {
					query.setParameter(e.getKey(), e.getValue());
				}
			});
		}
		List<T> find = query.getResultList();
		return find;
	}
	
	/**
	 * 新增或儲存(多筆)
	 */
	public void saveOrUpdateAll(Collection<T> entities){
		if(entities!=null && entities.size()>0){
			for(T t:entities){
				entityManager.persist(t);
			}
		}
	}
	

	/**
	 * SQL查詢
	 */
	public List<T> findNativeQuery(final String sql,final String alies)throws Exception {
		Query sqlQuery = entityManager.createNativeQuery(sql);
//		sqlQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(getEntityClass()));
		((NativeQueryImpl) sqlQuery).addEntity(alies, getEntityClass());
		return sqlQuery.getResultList();
	}
	
	/**
	 * SQL查詢
	 */
	public List<T> findNativeQuery(final String sql, final String alies,final Object...values)throws Exception {
		Query sqlQuery = entityManager.createNativeQuery(sql);
//		sqlQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(getEntityClass()));
		((NativeQueryImpl) sqlQuery).addEntity(alies, getEntityClass());
		if(values != null && values.length > 0){
			for (int i = 0; i < values.length; i++) {
				sqlQuery.setParameter(i, values[i]);
			}
		}
		return sqlQuery.getResultList();
				
	}
//	/**
//	 * Criteria查詢
//	 * @param criterions 查詢條件
//	 * @return
//	 */
//	public List<T> findCriteria(final Criterion... criterions){
//		Criteria criteria = entityManager.createCriteria(getEntityClass());
//		if(criterions != null){
//			for(Criterion c : criterions){
//				criteria.add(c);
//			}
//			return criteria.getResultList();
//		}else{
//			return new ArrayList();
//		}
//	}
//	/**
//	 * Criteria查詢
//	 * @param orderBy 排序欄位
//	 * @param isAsc 排序方式
//	 * @param criterions 查詢條件
//	 * @return
//	 */
//	public List<T> findCriteria(final String orderBy[],final Boolean[] isAsc,final Criterion... criterions){
//		Criteria criteria = entityManager.createCriteria(getEntityClass());
//		if(criterions != null){
//			for(Criterion c : criterions){
//				criteria.add(c);
//			}
//			for (int i = 0; i < orderBy.length; i++) {
//				if (isAsc[i].booleanValue()) {
//					criteria.addOrder(Order.asc(orderBy[i]));
//				} else {
//					criteria.addOrder(Order.desc(orderBy[i]));
//				}
//			}
//			return criteria.getResultList();
//		}else{
//			return new ArrayList<>();
//		}
//	}
	
	/**
	 * 以HQL做刪除或修改
	 * @param hql
	 * @throws Exception
	 */
	public void executeHQL(final String hql)throws Exception{
		Query query = entityManager.createQuery(hql);
		query.executeUpdate();	
	}
	/**
	 * 以HQL做刪除或修改
	 * @param hql
	 * @param values
	 * @throws Exception
	 */
	public int executeHQL(final String hql,final Object...values)throws Exception{
		Query query = entityManager.createQuery(hql);
		for(int i = 0;i < values.length;i++){
			query.setParameter(i, values[i]);
		}
		return query.executeUpdate();
	}
	
	public int executeSQL(final String sql)throws Exception{
		Query query = entityManager.createNativeQuery(sql);
		return query.executeUpdate();
	}
	
	public int executeSQL(final String sql,final Object...values)throws Exception{
		Query query = entityManager.createNativeQuery(sql);
		for(int i = 0;i < values.length;i++){
			query.setParameter(i, values[i]);
		}
		return query.executeUpdate();
	}
	
	/**
	 * 執行Transaction
	 * @param list
	 * @throws Exception
	 */
//	public void executeTransaction(final List<ExecuteObj> list)throws Exception{
//		Session session = entityManager;
//		if(list != null && list.size() > 0){
//			for(ExecuteObj obj : list){
//				int type = obj.getType();
//				switch(type){
//					case ExecuteObj.CREATE:
//						session.save(obj.getExecute());
//						break;
//					case ExecuteObj.UPDATE:
//						session.update(obj.getExecute());
//						break;
//					case ExecuteObj.DELETE:
//						session.delete(obj.getExecute());
//						break;
//					case ExecuteObj.EXECUTE:
//						session.createQuery(obj.getHql()).executeUpdate();
//						break;
//					case ExecuteObj.EXECUTE_ARG:
//						Query query = session.createQuery(obj.getHql());
//						Object[] args = obj.getArgs();
//						for(int i = 0;i < args.length;i++){
//							query.setParameter(i, args[i]);
//						}
//						query.executeUpdate();
//					default:
//						throw new HibernateException("type:"+type+" is not valid");
//				}
//			}
//		}
//	}
//	
//	/**
//	 * 執行Transaction
//	 * @param obj
//	 * @throws Exception
//	 */
//	public void executeTransaction(final TransactionObj obj)throws Exception{
//		obj.process(entityManager);
//	}
	
	/*======  Pagin =======*/

	public String removeSelect(String queryString) {
		Assert.hasText(queryString);
		int beginPos = queryString.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " query string : " + queryString + " must has a keyword 'from'");
		return queryString.substring(beginPos);
	}
	
	public String removeOrders(String queryString) {
		Assert.hasText(queryString);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(queryString);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	/**
	 * HQL查詢(分頁)
	 * @param hql the hql command
	 * @param first the first record
	 * @param max the max record
	 * @return
	 */
	public List<T> find4Pagin(final String hql,final int first,final int max){
		Query query = entityManager.createQuery(hql);
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.getResultList();
	}
	/**
	 * HQL查詢(分頁)
	 * @param hql the hql command
	 * @param first the first record
	 * @param max the max record
	 * @param values the query argument
	 * @return
	 */
	public List<T> find4Pagin(final String hql,final int first,final int max,final Object...values){
		Query query = entityManager.createQuery(hql);
		if(values != null){
			for(int i = 0;i < values.length;i++){
				query.setParameter(i, values[i]);
			}
		}
		query.setFirstResult(first);
		query.setMaxResults(max);
//		ScrollableResults results = query.scroll(ScrollMode.SCROLL_INSENSITIVE);
//		List<T> list = new Arraylist();
//		while (results.next()) {
//			list.add((T)results.get(0));
//		}
//		return list;
		return query.getResultList();
	}
	/**
	 * 取得筆數(HQL)
	 * @param hql
	 * @return
	 */
	public Integer find4PaginTotal(final String hql){	
		String queryStr = "select count(*) " + removeSelect(removeOrders(hql));
		Query  query = entityManager.createQuery(queryStr);
		List countlist = query.getResultList();
		return new Integer(((Long)countlist.get(0)).toString());
	}
	/**
	 * 取得筆數(HQL)
	 * @param hql
	 * @param values
	 * @return
	 */
	public Integer find4PaginTotal(final String hql,final Object...values){	
		String queryStr = "select count(*) " + removeSelect(removeOrders(hql));
		Query  query = entityManager.createQuery(queryStr);
		if(values != null){
			for(int i = 0;i < values.length;i++){
				query.setParameter(i, values[i]);
			}
		}
		List countlist = query.getResultList();
		return new Integer(((Long)countlist.get(0)).toString());
	}
//	/**
//	 * SQL查詢(分頁)
//	 * @param sql
//	 * @param alies
//	 * @param first
//	 * @param max
//	 * @return
//	 * @throws Exception
//	 */
//	public List<T> findNative4Pagin(final String sql,final String alies,final int first,final int max)throws Exception {
//		Query sqlQuery = entityManager.createNativeQuery(sql);
//		sqlQuery.addEntity(alies, getEntityClass());
//		sqlQuery.setFirstResult(first);
//		sqlQuery.setMaxResults(max);
//		return sqlQuery.getResultList();
//	}
	/**
	 * SQL查詢(分頁)
	 * @param sql
	 * @param alies
	 * @param first
	 * @param max
	 * @return
	 * @throws Exception
	 */
	public List<T> findNative4Pagin(final String sql,final String alies,final int first,final int max)throws Exception {
		Query sqlQuery = entityManager.createNativeQuery(sql);
		((NativeQueryImpl) sqlQuery).addEntity(alies, getEntityClass());
		sqlQuery.setFirstResult(first);
		sqlQuery.setMaxResults(max);
		return sqlQuery.getResultList();
	}
	/**
	 * SQL查詢(分頁)
	 * @param sql
	 * @param alies
	 * @param first
	 * @param max
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public List<T> findNative4Pagin(final String sql,final String alies,final int first,final int max,final Object...values)throws Exception {
		Query sqlQuery = entityManager.createNativeQuery(sql);
//		sqlQuery.addEntity(alies, getEntityClass());
		((NativeQueryImpl) sqlQuery).addEntity(alies, getEntityClass());
		if(values != null && values.length > 0){
			for (int i = 0; i < values.length; i++) {
				sqlQuery.setParameter(i, values[i]);
			}
		}
		sqlQuery.setFirstResult(first);
		sqlQuery.setMaxResults(max);
		return sqlQuery.getResultList();
				
	}
	/**
	 * 取得總筆數(SQL)
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public Integer findNative4PaginTotal(final String sql)throws Exception {
		return entityManager.createNativeQuery(sql).getResultList().size();
	}
	/**
	 * 取得總筆數(SQL)
	 * @param sql
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public Integer findNative4PaginTotal(final String sql,final Object...values)throws Exception {
		Query sqlQuery = entityManager.createNativeQuery(sql);
		if(values != null && values.length > 0){
			for (int i = 0; i < values.length; i++) {
				sqlQuery.setParameter(i, values[i]);
			}
		}
		return sqlQuery.getResultList().size();
	}
//	/**
//	 * Criteria查詢(分頁)
//	 * @param orderBy
//	 * @param isAsc
//	 * @param first
//	 * @param max
//	 * @param criterions
//	 * @return
//	 */
//	public List<T> findCriteria4Pagin(final String orderBy[],final Boolean[] isAsc,final int first,final int max,final Criterion... criterions){
//		Criteria criteria = entityManager.createCriteria(getEntityClass());
//		if(criterions != null){
//			for(Criterion c : criterions){
//				criteria.add(c);
//			}
//			for (int i = 0; i < orderBy.length; i++) {
//				if (isAsc[i].booleanValue()) {
//					criteria.addOrder(Order.asc(orderBy[i]));
//				} else {
//					criteria.addOrder(Order.desc(orderBy[i]));
//				}
//			}
//			criteria.setFirstResult(first);
//			criteria.setMaxResults(max);
//			return criteria.getResultList();
//		}else{
//			return new Arraylist();
//		}
//	}
//	/**
//	 * 取得總筆數(Criteria)
//	 * @param criterions
//	 * @return
//	 */
//	public Integer findCriteria4PaginTotal(final Criterion... criterions){
//		Criteria criteria = entityManager.createCriteria(getEntityClass());
//		if(criterions != null){
//			for(Criterion c : criterions){
//				criteria.add(c);
//			}					
//		}
//		return (Integer)criteria.setProjection(Projections.rowCount()).uniqueResult();
//	}
	/**
	 * SQL查詢
	 * @param sql
	 * @return
	 */
	public List findNativeSQL(final String sql){
		Query sqlQuery = entityManager.createNativeQuery(sql);
		return sqlQuery.getResultList();
	}
	
	
	/**
	 * SQL查詢
	 * @param sql
	 * @param values
	 * @return Object[]
	 */
	public List findNativeSQL(final String sql,final Object...values){
		Query sqlQuery = entityManager.createNativeQuery(sql);
		if(values != null && values.length > 0){
			for (int i = 0; i < values.length; i++) {
				sqlQuery.setParameter(i, values[i]);
			}
		}
		return sqlQuery.getResultList();
	}
	/**
	 * SQL查詢回傳是Map
	 * @param sql
	 * @param values
	 * @return
	 */
	public List<Map> findSQLResult(String sql, Object[] values) {
		Query sqlQuery = entityManager.createNativeQuery(sql);
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				sqlQuery.setParameter(i, values[i]);
			}
		}
		// --轉換為 MAP 物件
		NativeQueryImplementor nativeQueryImplementor = sqlQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		return nativeQueryImplementor.getResultList();
	}
	/**
	 * SQL查詢回傳是Map(分頁)
	 * @param sql
	 * @param first
	 * @param max
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> findSQL4Pagin(String sql, int first, int max, Object... values) throws Exception {
		Query sqlQuery = entityManager.createNativeQuery(sql);
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				sqlQuery.setParameter(i, values[i]);
			}
		}
		sqlQuery.setFirstResult(first);
		sqlQuery.setMaxResults(max);
		// --轉換為 MAP 物件
		NativeQueryImplementor nativeQueryImplementor = sqlQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		return nativeQueryImplementor.getResultList();
	}
	
	/**
	 * SQL查詢回傳是Map(sql指令複雜，特殊分頁)
	 * @param sql
	 * @param first
	 * @param max
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> findSQL4Pagin2(String sql, int first,int max,Object...values)throws Exception {
		sql = "select * from (select *, ROW_NUMBER() OVER (ORDER BY ID) as ROWNUM  from ("+sql+") as zzz) as yyy where ROWNUM  between "+(first+1)+" and "+(first+max);		
		Query sqlQuery = entityManager.createNativeQuery(sql);
		if(values != null && values.length > 0){
			for (int i = 0; i < values.length; i++) {
				sqlQuery.setParameter(i, values[i]);
			}
		}
		//--轉換為 MAP 物件
		NativeQueryImplementor nativeQueryImplementor = 
				sqlQuery.unwrap(NativeQueryImpl.class).
				setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		return nativeQueryImplementor.getResultList();
	}
	
	/**
	 * SQL查詢回傳是含 key 的 Map
	 * @param sql
	 * @param values
	 * @return
	 */
	public List<Map<String, Object>> findSQLResult2(String sql, Object[] values) {
		Query sqlQuery = entityManager.createNativeQuery(sql);
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				sqlQuery.setParameter(i, values[i]);
			}
		}
		// --轉換為 MAP 物件
		NativeQueryImplementor nativeQueryImplementor = sqlQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		return nativeQueryImplementor.getResultList();
	}
}
