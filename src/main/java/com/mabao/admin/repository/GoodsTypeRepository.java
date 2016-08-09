package com.mabao.admin.repository;


import com.mabao.admin.pojo.GoodsType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsTypeRepository extends BaseRepository<GoodsType>{

    /**
     * 依据标题模糊查询
     * @param title             标题key
     * @param pageable          分页参数
     * @return                  分页goods
     */
    Page<GoodsType> findByTypeNameLike(String title, Pageable pageable);
}
