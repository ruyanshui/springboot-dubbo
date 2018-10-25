package com.gaoxi.controller.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.entity.product.BrandEntity;
import com.gaoxi.entity.product.CategoryEntity;
import com.gaoxi.entity.product.ProdImageEntity;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.facade.Productor.IProductorService;
import com.gaoxi.req.product.*;
import com.gaoxi.rsp.Result;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ProductControllerImpl implements IProductController {

    @Reference(version = "1.0.0")
    private IProductorService productorService;


    @Override
    public Result createProduct(ProdInsertReq prodInsertReq) {
        return productorService.createProduct(prodInsertReq);
    }

    @Override
    public Result<ProdImageEntity> uploadImage(MultipartFile multipartFile) {
        return null;
    }

    @Override
    public Result updateProduct(ProdUpdateReq prodUpdateReq) {
        return productorService.updateProduct(prodUpdateReq);
    }

    @Override
    public Result<List<ProductEntity>> findProducts(ProdQueryReq prodQueryReq) {
        return productorService.findProducts(prodQueryReq);
    }

    @Override
    public Result createCategory(CategoryEntity categoryEntity) {
        return productorService.createCategoty(categoryEntity);
    }

    @Override
    public Result modifyCategory(CategoryEntity categoryEntity) {
        return productorService.modifyCategory(categoryEntity);
    }

    @Override
    public Result deleteCategory(String categoryId) {
        return productorService.deleteCategory(categoryId);
    }

    @Override
    public Result<List<CategoryEntity>> findCategorys(CategoryQueryReq categoryQueryReq) {
        return productorService.findCategorys(categoryQueryReq);
    }

    @Override
    public Result createBrand(BrandInsertReq brandInsertReq) {
        return productorService.createBrand(brandInsertReq);
    }

    @Override
    public Result modifyBrand(BrandInsertReq brandInsertReq) {
        return productorService.modifyBrand(brandInsertReq);
    }

    @Override
    public Result<List<BrandEntity>> findBrands(BrandQueryReq brandQueryReq) {
        return productorService.findBrands(brandQueryReq);
    }
}
