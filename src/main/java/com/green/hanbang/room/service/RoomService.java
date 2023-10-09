package com.green.hanbang.room.service;


import com.green.hanbang.room.vo.OptionsVO;
import com.green.hanbang.room.vo.PropertyTypeVO;

import java.util.List;

public interface RoomService {
    //option select
    public List<OptionsVO> selectOptions();
    //select property
    public List<PropertyTypeVO> selectProperty();

}
