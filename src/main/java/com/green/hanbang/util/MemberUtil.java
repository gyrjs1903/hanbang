package com.green.hanbang.util;

import com.green.hanbang.member.vo.MemberImgVO;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class MemberUtil {
    //파일 업로드 위치
    public static final String UPLOAD_PATH = "D:\\dev\\workspaceSpring\\hanbang\\src\\main\\resources\\static\\img\\member\\profileimg\\";

    // 파일 첨부 기능 (단일 파일 업로드 - 프로필 사진은 하나만)
    public static MemberImgVO uploadFile(MultipartFile img){
        MemberImgVO memberImgVO = null;

        // 첨부 파일을 선택 시
        if(!img.isEmpty()){
            memberImgVO = new MemberImgVO();

            // 첨부 파일
            String originFileName = img.getOriginalFilename();

            // 첨부될 파일명
            String uuid = UUID.randomUUID().toString();
            int dotIndex = originFileName.lastIndexOf(".");
            String extension = originFileName.substring(dotIndex);
            String attachedFileName = uuid + extension;

            // 파일 첨부
            try {
                File file = new File(ConstantVariable.UPLOAD_PATH + attachedFileName);
                img.transferTo(file);

                memberImgVO.setProfileImgName(originFileName);
                memberImgVO.setAttachedProfileImgName(attachedFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return memberImgVO;
    }

}
