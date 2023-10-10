package com.green.hanbang.util;

import com.green.hanbang.room.vo.RoomIMGVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomUtil {
    //파일 첨부 기능(단일 파일 업로드)
    public static RoomIMGVO uploadFile(MultipartFile img){
        RoomIMGVO roomIMGVO = null;

        //첨부파일을 선택했다면...
        if(!img.isEmpty()){
            roomIMGVO = new RoomIMGVO();

            //첨부파일
            String originFileName = img.getOriginalFilename();

            //첨부될 파일명
            String uuid = UUID.randomUUID().toString();
            //가장 빨리 만나는 자바.jpg
            int dotIndex = originFileName.lastIndexOf(".");
            String extension = originFileName.substring(dotIndex);
            String attachedFileName = uuid + extension;

            //파일 첨부
            try {
                File file = new File(ConstantVariable.UPLOAD_PATH + attachedFileName);
                img.transferTo(file);

                roomIMGVO.setOriginFileName(originFileName);
                roomIMGVO.setAttachedFileName(attachedFileName);
                roomIMGVO.setIsMain("Y");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return roomIMGVO;
    }

    //다중 파일 업로드
    public static List<RoomIMGVO> multiFileUpload(MultipartFile[] imgs){
        List<RoomIMGVO> roomIMGList = new ArrayList<>();

        for(MultipartFile img : imgs){
            RoomIMGVO vo = uploadFile(img);

            if(vo != null){
                vo.setIsMain("N");
                roomIMGList.add(vo);
            }
        }
        return roomIMGList;
    }
}
