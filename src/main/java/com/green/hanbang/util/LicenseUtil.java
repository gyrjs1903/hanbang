package com.green.hanbang.util;

import com.green.hanbang.realtor.vo.LicenseImgVO;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.scanner.Constant;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class LicenseUtil {

    public static LicenseImgVO licenseUtil(MultipartFile img){
        LicenseImgVO imgVO = new LicenseImgVO();

        //첨부파일
        String originLicenseFileName = img.getOriginalFilename();

        //첨부될 파일명
        String uuid = UUID.randomUUID().toString();

        //확장자명
        int dotIndex = originLicenseFileName.lastIndexOf(".");
        String extension = originLicenseFileName.substring(dotIndex);
        String attachedLicenseFileName = uuid + extension;

        //파일첨부
        try {
            File file = new File(ConstantVariable.UPLOAD_PATH + attachedLicenseFileName);
            img.transferTo(file);

            imgVO.setOriginLicenseFileName(originLicenseFileName);
            imgVO.setAttachedLicenseFileName(attachedLicenseFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return imgVO;
    }
}
