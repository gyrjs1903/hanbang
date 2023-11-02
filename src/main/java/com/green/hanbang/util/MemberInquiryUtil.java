package com.green.hanbang.util;

import com.green.hanbang.member.vo.MemberInquiryImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemberInquiryUtil {
    // 파일 첨부 기능 (단일)
    public static MemberInquiryImgVO inquiryUploadFile(MultipartFile img) {
        MemberInquiryImgVO memberInquiryImgVO = null;

        // 첨부파일 선택
        if (!img.isEmpty()) {
            memberInquiryImgVO = new MemberInquiryImgVO();
            // 첨부 파일명
            String memberInquiryImgName = img.getOriginalFilename();
            // 첨부될 파일명
            String uuid = UUID.randomUUID().toString();
            // 확장자명
            int dotIndex = memberInquiryImgName.lastIndexOf(".");
            String extension = memberInquiryImgName.substring(dotIndex);
            String memberAttachedInquiryImgName = uuid + extension;
            // 파일 첨부
            try {
                File file = new File(ConstantVariable.INQUIRY_UPLOAD_PATH + memberAttachedInquiryImgName);
                img.transferTo(file);
                memberInquiryImgVO.setMemberInquiryImgName(memberInquiryImgName);
                memberInquiryImgVO.setMemberAttachedInquiryImgName(memberAttachedInquiryImgName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return memberInquiryImgVO;
    }

    // 다중 파일 업로드
    public static List<MemberInquiryImgVO> inquiryMultiUpload(MultipartFile[] imgs){
        List<MemberInquiryImgVO> memberInquiryImgList = new ArrayList<>();

        for(MultipartFile img : imgs){
            MemberInquiryImgVO inquiryImg = inquiryUploadFile(img);

            if(inquiryImg != null){
                memberInquiryImgList.add(inquiryImg);
            }
        }
        return memberInquiryImgList;
    }

}
