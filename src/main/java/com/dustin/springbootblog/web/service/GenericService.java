package com.dustin.springbootblog.web.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dustin.springbootblog.web.dao.GenericDao;



@Service
@Scope("prototype")
public class GenericService<T, ID extends Serializable> {

	@Autowired
	private GenericDao<T,ID> dao;
	public GenericService(){
	}
	
	public GenericService(GenericDao<T,ID> dao){
		this.dao = dao;
	}
	
	
	public GenericService<T,ID> entity(Class entityClass){
		dao.entity(entityClass);
		return this;
	}
	/**
	 * HQL查詢
	 * @param queryString
	 * @param value
	 * @return
	 * @
	 */
	public List<T> find(String queryString, Object value) {
		return dao.find(queryString, value);
	}
	/**
	 * HQL查詢
	 * @param queryString
	 * @param values
	 * @return
	 * @
	 */
	public List<T> find(String queryString, Object[] values) {
		return dao.find(queryString, values);
	}
	/**
	 * HQL查詢
	 * @param queryString
	 * @param paramsMap
	 * @return
	 */
	public List<T> find(String queryString, Map<String, Object> paramsMap) {
		return dao.find(queryString, paramsMap);
	}
	/**
	 * HQL查詢
	 * @param queryString
	 * @return
	 * @
	 */
	public List<T> find(String queryString)  {
		return dao.find(queryString,new Object[0]);
	}
	/**
	 * 新增或儲存(多筆)
	 * @param entities
	 * @
	 */
	public void saveOrUpdateAll(Collection<T> entities) {
		dao.saveOrUpdateAll(entities);
	}
	/**
	 * SQL查詢
	 * @param sql
	 * @param alies
	 * @return
	 * @throws Exception
	 */
	public List<T> findSQL(String sql,String alies)throws Exception {
		return dao.findNativeQuery(sql, alies);
	}
	/**
	 * SQL查詢
	 * @param sql
	 * @param alies
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public List<T> findSQL(String sql, String alies,Object...values)throws Exception {
		return dao.findNativeQuery(sql, alies,values);
	}
//	/**
//	 * Criteria查詢
//	 * @param criterions 查詢條件
//	 * @return
//	 */
//	public List<T> findCriteria(Criterion... criterions){
//		return dao.findCriteria(criterions);
//	}
//	/**
//	 * Criteria查詢
//	 * @param orderBy 排序欄位
//	 * @param isAsc 排序方式
//	 * @param criterions 查詢條件
//	 * @return
//	 */
//	public List<T> findCriteria(String orderBy[],Boolean[] isAsc,Criterion... criterions){
//		return dao.findCriteria(orderBy, isAsc, criterions);
//	}
	/**
	 * 以HQL做刪除或修改
	 * @param hql
	 * @throws Exception
	 */
	public void executeHQL(String hql)throws Exception{
		dao.executeHQL(hql);
	}
	/**
	 * 以HQL做刪除或修改
	 * @param hql
	 * @param values
	 * @throws Exception
	 */
	public int executeHQL(String hql,Object... values)throws Exception{
		return dao.executeHQL(hql,values);
	}
	
	/*==== pagin =======*/
	
	/**
	 * 頁數計算
	 * @param totalRows
	 * @param pageRows
	 * @return
	 */
	public int calculatePageCounts(int totalRows,int pageRows){
		int pageCounts = totalRows % pageRows == 0?(totalRows/pageRows):(totalRows/pageRows + 1);
		if(pageCounts == 0){
			pageCounts = 1;
		}
		return pageCounts;
	}
	
	public void setupPage(Pageable pagin,int totalRows){
//		Pageable pageable = PageRequest.of(0,5);
//		
//		int currentPage = pagin.page;
		int offset = (int) pagin.getOffset();
		
//		int pageRows = pagin.getPageRows();
//		//計算頁數
//		int pageCounts = calculatePageCounts(totalRows,pageRows);
//		if(currentPage > pageCounts){
//			currentPage = pageCounts;			
//		}
//		if(currentPage < 1){
//			currentPage = 1;
//		}
//		pagin.setCurrentPage(currentPage);
//		pagin.setPageCounts(pageCounts);
//		pagin.setTotalRows(totalRows);
	}
	
//	public void setupPage(Page pagin,int totalRows){
//		PageRequest.of(int page, int size, Sort sort);
//		
//		Pageable pageable = pagin.getPageable();
//		int currentPage = pagin.page;
//		int pageRows = pagin.getPageRows();
//		//計算頁數
//		int pageCounts = calculatePageCounts(totalRows,pageRows);
//		if(currentPage > pageCounts){
//			currentPage = pageCounts;			
//		}
//		if(currentPage < 1){
//			currentPage = 1;
//		}
//		pagin.setCurrentPage(currentPage);
//		pagin.setPageCounts(pageCounts);
//		pagin.setTotalRows(totalRows);
//	}
	
	/**
	 * HQL查詢(分頁)
	 * @param hql
	 * @param pagin
	 * @return
	 */
	public List<T> find4Pagin(String hql,Page pagin){
//		//總筆數
//		int totalRows = dao.find4PaginTotal(hql).intValue();
//		setupPage(pagin,totalRows);
//		return dao.find4Pagin(hql, (pagin.getCurrentPage() - 1) * pagin.getPageRows(), pagin.getPageRows());
		return null;
	}
	/**
	 * HQL查詢(分頁)
	 * @param hql 
	 * @param pagin 
	 * @param values 
	 * @return
	 */
	public List<T> find4Pagin(String hql,Page pagin,Object...values){
//		//總筆數
//		int totalRows = dao.find4PaginTotal(hql,values).intValue();
//		setupPage(pagin,totalRows);
//		return dao.find4Pagin(hql, (pagin.getCurrentPage() - 1) * pagin.getPageRows(), pagin.getPageRows(),values);
		return null;
	}
	
	public Page<T> findSQL4Pagin(String sql,Pageable pageable)throws Exception {
		int totalRows = dao.findNative4PaginTotal(sql).intValue();
		setupPage(pageable,totalRows);
		List<T> findNative4PaginList = dao.findNative4Pagin(sql, (int)pageable.getOffset(), pageable.getPageSize());
		return new PageImpl(findNative4PaginList, pageable, totalRows);
	}
	/**
	 * SQL查詢(分頁)
	 * @param sql
	 * @param alies
	 * @param pagin
	 * @return
	 * @throws Exception
	 */
//	public List<T> findSQL4Pagin(String sql,String alies,Page pagin)throws Exception {
////		//總筆數
////		int totalRows = dao.findNative4PaginTotal(sql).intValue();
////		setupPage(pagin,totalRows);
////		return dao.findNative4Pagin(sql, alies, (pagin.getCurrentPage() - 1) * pagin.getPageRows(), pagin.getPageRows());
//		return null;
//	}
	/**
	 * SQL查詢(分頁)
	 * @param sql
	 * @param alies
	 * @param pagin
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public List<T> findSQL4Pagin(String sql,String alies,Page pagin,Object...values)throws Exception {
//		//總筆數
//		int totalRows = dao.findNative4PaginTotal(sql,values).intValue();
//		setupPage(pagin,totalRows);
//		return dao.findNative4Pagin(sql, alies, (pagin.getCurrentPage() - 1) * pagin.getPageRows(), pagin.getPageRows(),values);
		return null;
	}
//	/**
//	 * Criteria查詢(分頁)
//	 * @param pagin
//	 * @param criterions
//	 * @return
//	 */
//	public List<T> findCriteria4Pagin(Pagin pagin,Criterion... criterions){
//		//總筆數
//		int totalRows = dao.findCriteria4PaginTotal(criterions);
//		setupPage(pagin,totalRows);
//		String[] orderBy = (StringUtils.isEmpty(pagin.getCurrentSort())?new String[0]:new String[]{pagin.getCurrentSort().replaceAll("Sort", "")});
//		Boolean[] isAsc = pagin.getSortType().equalsIgnoreCase("asc")?new Boolean[]{Boolean.TRUE}:new Boolean[]{Boolean.FALSE};
//		return dao.findCriteria4Pagin(orderBy, isAsc, (pagin.getCurrentPage() - 1) * pagin.getPageRows(), pagin.getPageRows(), criterions);
//	}
//	/**
//	 * Criteria查詢(分頁)
//	 * @param pagin
//	 * @param orderBy
//	 * @param criterions
//	 * @return
//	 */
//	public List<T> findCriteria4Pagin(Pagin pagin,String[] orderBy,Boolean[] isAsc,Criterion... criterions){
//		//總筆數
//		int totalRows = dao.findCriteria4PaginTotal(criterions);
//		setupPage(pagin,totalRows);
//		return dao.findCriteria4Pagin(orderBy, isAsc, (pagin.getCurrentPage() - 1) * pagin.getPageRows(), pagin.getPageRows(), criterions);
//	}
	
	/**
	 * SQL 查詢
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List findNativeSQL(String sql)throws Exception{
		return dao.findNativeSQL(sql);
	}
	
	public List findNativeSQL(final String sql,final Object...values){
		return dao.findNativeSQL(sql,values);
	}
	/**
	 * SQL查詢
	 * 適用sql中有多個"from"
	 */
	public List<T> findSQL4Pagin2(String sql,String alies,Page pagin)throws Exception {
//		//總筆數
//		List<T> list = dao.findNative4Pagin(sql, alies, (pagin.getCurrentPage() - 1) * pagin.getPageRows(), pagin.getPageRows());
//		setupPage(pagin,dao.findNativeQuery(sql, alies).size());
//		return list;
		return null;
	}
	
	/**
	 * SQL查詢
	 * 適用sql中有多個"from"
	 */
	public List<T> findSQL4Pagin2(String sql,String alies,Page pagin,Object...values)throws Exception {
		//總筆數
//		List<T> list = dao.findNative4Pagin(sql, alies, (pagin.getCurrentPage() - 1) * pagin.getPageRows(), pagin.getPageRows(),values);
//		setupPage(pagin,dao.findNativeQuery(sql, alies, values).size());
//		return list;
		return null;
	}
	/**
	 * SQL查詢 ,可用純 SQL 執行關聯與法查詢， 並將回傳Map資料集合(分頁)
	 * @param sql
	 * @param pagin
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> findSQL4Pagin3(String sql,Page pagin,Object...values)throws Exception {
//		List<Map<String,Object>> list = dao.findSQL4Pagin(sql, (pagin.getCurrentPage() - 1) * pagin.getPageRows(), pagin.getPageRows(), values);
//		setupPage(pagin,dao.findSQLResult(sql, values).size());
//		return list;
		return null;
	}
	/**
	 * SQL查詢 ,可用純 SQL 執行關聯與法查詢， 並將回傳Map資料集合(sql指令複雜，特殊分頁)
	 * @param sql
	 * @param pagin
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> findSQL4Pagin4(String sql,Page pagin,Object...values)throws Exception {
//		List<Map<String,Object>> list = dao.findSQL4Pagin2(sql, (pagin.getCurrentPage() - 1) * pagin.getPageRows(), pagin.getPageRows(), values);	
//		setupPage(pagin,findSQLResult(sql, values).size());
//		return list;
		return null;
	}
	/**
	 * 執行SQL
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int executeSQL(String sql)throws Exception{
		return dao.executeSQL(sql);
	}
	/**
	 * 執行SQL
	 * @param sql
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public int executeSQL(String sql,Object...values)throws Exception{
		return dao.executeSQL(sql, values);
	}
//	/**
//	 * SQL查詢
//	 * @param sql
//	 * @param scalar
//	 * @return
//	 * @throws Exception
//	 */
//	public List findNativeSQL(final String sql,final Map<String,NullableType> scalar)throws Exception{
//		return dao.findNativeSQL(sql,scalar);
//	}
	/**
	 * SQL查詢回傳是Map
	 * @param sql
	 * @param values
	 * @return
	 */
	public List<Map> findSQLResult(String sql ,Object[] values){
		return dao.findSQLResult(sql,values);
	}
	
	/**
	 * SQL查詢回傳是含 key 的 Map
	 * @param sql
	 * @param values
	 * @return
	 */
	public List<Map<String, Object>> findSQLResult2(String sql, Object[] values){
		return dao.findSQLResult2(sql, values);
	}
}
