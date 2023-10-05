package com.green.hanbang.room.vo;

import lombok.Data;

import java.util.List;

@Data
public class OptionsVO {
    private String optionCode;
    private String optionName;
    private List<DetailOptionsVO> detailOptionList;
}
