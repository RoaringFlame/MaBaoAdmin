package com.mabao.admin.repository;

import com.is.controller.vo.PageVO;

import java.util.List;

public interface BaseDao {
    /**
     * 根据JPQL查询单个对象
     * @param jpql  JPQL查询语句
     * @param args  参数
     * @param <T>   实体或者装箱基础数据类型
     * @return  单个数据对象
     */
    public<T> T findOne(String jpql, Object... args);

    /**
     * 常规JPQL查询，不分页，可以Order By
     * @param jpql JPQL查询语句
     * @param args 参数
     * @param <T> 实体
     * @return 实体集合
     */
    public<T> List<T> findAll(String jpql, Object... args);

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
    public<T> PageVO<T> findAll(String jpql, String jpqlCount, Object[] args, int page, int size);

    /**
     * 通过SQL语句进行查询，结果超过一条会报错
     * @param sql   SQL语句
     * @param args  参数
     * @param <T>   接收对象，只认基础列，建议使用VO对象
     * @return      单个POJO对象
     */
    public<T> T findOneNative(String sql, Object... args);

    /**
     * 通过SQL语句进行查询
     * @param sql   SQL语句
     * @param args  参数
     * @param <T>   接收对象，只认基础列，建议使用VO对象
     * @return      对象集合
     */
    public<T> List<T> findAllNative(String sql, Object... args);
}
