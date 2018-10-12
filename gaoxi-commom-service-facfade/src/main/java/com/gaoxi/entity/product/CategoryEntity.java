package com.gaoxi.entity.product;

import java.io.Serializable;

/**
 * 产品分类的实体类
 */
public class CategoryEntity implements Serializable{

    private static final long serialVersionUID = -1636030710011551197L;

    private String id;

    // 产品分类
    private String category;

    // 父分类id,一级分类的parendId为空
    private String parentId;

    // 排序
    private int sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", parentId='" + parentId + '\'' +
                ", sort=" + sort +
                '}';
    }
}
