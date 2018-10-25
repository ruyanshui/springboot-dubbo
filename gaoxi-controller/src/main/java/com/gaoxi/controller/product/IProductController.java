package com.gaoxi.controller.product;

import com.gaoxi.annotation.Login;
import com.gaoxi.annotation.Permission;
import com.gaoxi.entity.product.BrandEntity;
import com.gaoxi.entity.product.CategoryEntity;
import com.gaoxi.entity.product.ProdImageEntity;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.req.product.*;
import com.gaoxi.rsp.Result;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductController {


    /**
     * 创建产品
     * @param prodInsertReq
     * @return
     */
    @PostMapping("product")
    @Login
    @Permission("product:create")
    Result createProduct(ProdInsertReq prodInsertReq);

    /**
     * 上传图片，需要定时清除没有被引用的图片
     * @param multipartFile
     * @return
     */
    @PostMapping("image")
    @Login
    @Permission("image:upload")
    Result<ProdImageEntity> uploadImage(MultipartFile multipartFile);

    /**
     * 修改产品
     * @param prodUpdateReq
     * @return
     */
    @PutMapping("product")
    @Login
    @Permission("product:update")
    Result updateProduct(ProdUpdateReq prodUpdateReq);

    /**
     * 查询产品
     * @param prodQueryReq
     * @return
     */
    @GetMapping("product")
    Result<List<ProductEntity>> findProducts(ProdQueryReq prodQueryReq);

    /**
     * 创建产品类别
     * @param categoryEntity
     * @return
     */
    @PostMapping("category")
    @Login
    @Permission("category: create")
    Result createCategory(CategoryEntity categoryEntity);

    /**
     * 修改产品类别
     * PS：只能修改：类别名称、排序，且id必填
     * @param categoryEntity 待修改类别
     * @return 是否修改成
     */
    @PutMapping("category")
    @Login
    @Permission("category:update")
    Result modifyCategory(CategoryEntity categoryEntity);

    /**
     * 删除类别
     * PS：只有当该类别下没有产品时才允许删除
     * @param categoryId 待删除类别的id
     * @return 删除结果
     */
    @DeleteMapping("category/{categoryId}")
    @Login
    @Permission("category:delete")
    Result deleteCategory(String categoryId);

    /**
     * 查询类别
     * @param categoryQueryReq 类别查询请求
     * @return 类别查询结果
     */
    @GetMapping("category")
    @Login
    @Permission("category:query")
    Result<List<CategoryEntity>> findCategorys(CategoryQueryReq categoryQueryReq);

    /**
     * 创建品牌
     * @param brandInsertReq 品牌参数(企业id即可)
     * @return 是否创建成功
     */
    @PostMapping("brand")
    @Login
    @Permission("brand:create")
    Result createBrand(BrandInsertReq brandInsertReq);

    /**
     * 修改品牌
     * @param brandInsertReq 待修改品牌(品牌id必填)
     * @return 是否修改成功
     */
    @PutMapping("brand")
    @Login
    @Permission("brand:update")
    Result modifyBrand(BrandInsertReq brandInsertReq);

    /**
     * 查询品牌
     * @param brandQueryReq 品牌查询请求
     * @return 品牌查询结果
     */
    @GetMapping("brand")
    @Login
    @Permission("brand:query")
    Result<List<BrandEntity>> findBrands(BrandQueryReq brandQueryReq);

}
