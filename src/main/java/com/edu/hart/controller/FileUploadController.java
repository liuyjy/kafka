package com.edu.hart.controller;

import com.edu.hart.entity.FastDfsFile;
import com.edu.hart.entity.exception.FileException;
import com.edu.hart.util.FastDFSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 测试文件上传
 * <p>
 *
 * @author yongzhen
 * @date 2018/12/14-14:36
 */
@RestController
@RequestMapping("file")
public class FileUploadController {

    private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    /**
     * 文件上传
     *
     * @param multipartFile 文件对象
     */
    @RequestMapping(value = "/fileUpload")
    public FastDfsFile fastDFSUpload(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty() || multipartFile.getSize() == 0) {
            throw new FileException("请选择上传文件!");
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String expandName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        logger.info("文件名:" + originalFilename + ";拓展名:" + expandName);
        byte[] bytes = null;
        try {
            bytes = multipartFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath = FastDFSUtil.uploadFile(bytes, expandName);
        FastDfsFile fastDfsFile = new FastDfsFile();
        fastDfsFile.setFileName(originalFilename);
        fastDfsFile.setRelativeFilepath(filePath);
        return fastDfsFile;
    }
}
