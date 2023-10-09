package com.green.hanbang.room.vo;

import lombok.Data;

import java.util.List;

@Data
public class PropertyTypeVO {
    private String propertyTypeCode;
    private String propertyTypeName;
    private List<SubPropertyTypeVO> subPropertyTypeList;

}
