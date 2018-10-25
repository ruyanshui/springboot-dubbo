package com.gaoxi.product.dao;

import com.gaoxi.entity.product.BrandEntity;
import com.gaoxi.req.product.BrandInsertReq;
import com.gaoxi.req.product.BrandQueryReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IBrandDAO {

    /**
     * 新增品牌
     * @param brandInsertReq
     * @return
     */
    int createBrand(BrandInsertReq brandInsertReq);

    /**
     * 增量更新品牌
     * @param brandInsertReq
     */
    int updateBrand(BrandInsertReq brandInsertReq);


    /**
     * 查询所有品牌（支持分页）
     * @param brandQueryReq
     * @return
     */
    List<BrandEntity> findBrands(BrandQueryReq brandQueryReq);
}
