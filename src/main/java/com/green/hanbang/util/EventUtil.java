package com.green.hanbang.util;

import com.green.hanbang.admin.vo.EventImgVO;
import com.green.hanbang.admin.vo.EventVO;
import com.green.hanbang.realtor.vo.LicenseImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventUtil {

    // 단일 파일 업로드
    public static EventImgVO eventUpload(MultipartFile eventImg){
        EventImgVO eventImgVO = new EventImgVO();


        if (!eventImg.isEmpty()){
            eventImgVO = new EventImgVO();

            //첨부파일
            String eventOriginFileName = eventImg.getOriginalFilename();

            //첨부될 파일명
            String uuid = UUID.randomUUID().toString();

            //확장자명
            int dotIndex = eventOriginFileName.lastIndexOf(".");
            String extension = eventOriginFileName.substring(dotIndex);
            String eventAttachedFileName = uuid + extension;

            //파일첨부
            try {
                File file = new File(ConstantVariable.EVENT_UPLOAD_PATH + eventAttachedFileName);
                eventImg.transferTo(file);

                eventImgVO.setEventOriginFileName(eventOriginFileName);
                eventImgVO.setEventAttachedFileName(eventAttachedFileName);
                eventImgVO.setIsMain("Y");
            } catch (IOException e) {
                throw new RuntimeException("파일 업로드 오류" + e.getMessage());
            }
        }

        return eventImgVO;
    }

    // 다중 파일 업로드
    public static List<EventImgVO> multiEventUpload(MultipartFile[] subEventImgs){
        List<EventImgVO> subImgList = new ArrayList<>();

        for (MultipartFile subEventImg : subEventImgs ){
            EventImgVO eventImgVO = eventUpload(subEventImg);

            if (eventImgVO != null){
                eventImgVO.setIsMain("N");
                subImgList.add(eventImgVO);
            }
        }
        return subImgList;
    }


}
