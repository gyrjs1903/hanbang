package com.green.hanbang.util;

import com.green.hanbang.member.vo.MemberImgVO;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class MemberUtil {
    // 파일 첨부 기능 (단일 파일 업로드 - 프로필 사진은 하나만)
    public static MemberImgVO uploadFile(MultipartFile img){
        MemberImgVO memberImgVO = new MemberImgVO();

            // 첨부 파일
            String profileImgName = img.getOriginalFilename();

            // 첨부될 파일명
            String uuid = UUID.randomUUID().toString();

            // 확장자명
            int dotIndex = profileImgName.lastIndexOf(".");
            String extension = profileImgName.substring(dotIndex);
            String attachedProfileImgName = uuid + extension;

            // 파일 첨부
            try {
                File file = new File(ConstantVariable.UPLOAD_PATH + attachedProfileImgName);
                img.transferTo(file);

                memberImgVO.setProfileImgName(profileImgName);
                memberImgVO.setAttachedProfileImgName(attachedProfileImgName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        return memberImgVO;
    }
}