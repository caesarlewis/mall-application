package com.jasonless.mall.api.search.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LiuShiZeng
 * 2021/4/10
 * 写注释吧
 */
@Data
@Document(indexName = "shopsearch",type = "seckillgoodses")
public class SeckillGoodsEs implements Serializable {

    @Id
    private String id;
    private String supId;
    private String skuId;
    @Field(type= FieldType.Text,searchAnalyzer = "ik_smart",analyzer = "ik_smart")
    private String name;
    private String images;
    private Integer price;
    private Integer seckillPrice;
    private Integer num;
    private Integer storeCount;
    private Date createTime;
    @Field(type=FieldType.Keyword)
    private String activityId;
}
