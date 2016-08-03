package com.mabao.admin.repository.impl;


import com.mabao.admin.repository.BaseDao;
import com.mabao.admin.util.PageVO;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@SuppressWarnings("unchecked")
public class BaseDaoImpl implements BaseDao {
    @PersistenceContext
    private EntityManager em;

    /**
     * 根据JPQL查询单个对象
     * @param jpql  JPQL查询语句
     * @param args  参数
     * @param <T>   实体或者装箱基础数据类型
     * @return  单个数据对象
     */
    public<T> T findOne(String jpql,Object...args){
        Query query=em.createQuery(jpql);
        if(args!=null&&args.length>0) {
            for (int i = 0; i < args.length; i++) {
                query.setParameter(i+1, args[i]);
            }
        }
        return (T)query.getSingleResult();
    }

    /**
     * 常规JPQL查询，不分页，可以Order By
     * @param jpql JPQL查询语句
     * @param args 参数
     * @param <T> 实体
     * @return 实体集合
     */
    public<T> List<T> findAll(String jpql,Object...args){
        Query query=em.createQuery(jpql);
        if(args!=null&&args.length>0) {
            for (int i = 0; i < args.length; i++) {
                query.setParameter(i+1, args[i]);
            }
        }
        return (List<T>)query.getResultList();
    }

    /**
     * 通过JPQL进行分页查询
     * @param jpql JPQL查询语句
     * @param jpqlCount JPQL查询语句，查询数据总条数
     * @param args  参数
     * @param page  当前页数,0开始
     * @param size  每页条数
     * @param <T>   实体
     * @return      分页对象
     */
    public<T> PageVO<T> findAll(String jpql, String jpqlCount, Object[] args, int page, int size){
        Query queryCount=em.createQuery(jpqlCount);
        if(args!=null&&args.length>0) {
            for (int i = 0; i < args.length; i++) {
                queryCount.setParameter(i+1, args[i]);
            }
        }
        Long count= (Long) queryCount.getSingleResult();
        Query query=em.createQuery(jpql);
        if(args!=null&&args.length>0) {
            for (int i = 0; i < args.length; i++) {
                query.setParameter(i+1, args[i]);
            }
        }
        query.setFirstResult(page*size);
        query.setMaxResults(size);
        List<T> items=(List<T>)query.getResultList();
            PageVO<T> pageVO=new PageVO<>();
            pageVO.setItems(items);
            pageVO.setCurrentPage(page);
            pageVO.setTotalRow(count);
            pageVO.setPageSize(size);
        return pageVO;
    }

    /**
     * 通过SQL语句进行查询，结果超过一条会报错
     * @param sql   SQL语句
     * @param args  参数
     * @param <T>   接收对象，只认基础列，建议使用VO对象
     * @return      单个POJO对象
     */
    public<T> T findOneNative(String sql,Object...args){
        Query query= em.createNativeQuery(sql);
        for(int i=0;i<args.length;i++){
            query.setParameter(i,args[i]);
        }
        return (T)query.getSingleResult();
    }

    /**
     * 通过SQL语句进行查询
     * @param sql   SQL语句
     * @param args  参数
     * @param <T>   接收对象，只认基础列，建议使用VO对象
     * @return      对象集合
     */
    public<T> List<T> findAllNative(String sql,Object...args){
        Query query= em.createNativeQuery(sql);
        for(int i=0;i<args.length;i++){
            query.setParameter(i+1,args[i]);
        }
        return (List<T>)query.getResultList();
    }
}
