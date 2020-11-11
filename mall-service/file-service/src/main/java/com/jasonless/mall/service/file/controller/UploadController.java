package com.jasonless.mall.service.file.controller;

import com.jasonless.mall.common.util.ResponseResult;
import com.jasonless.mall.service.file.ceph.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jasonless
 * @date 2020/11/11
 */
@RestController
@RequestMapping(value = "/file")
public class UploadController {

    @Autowired
    private FileHandler fileHandler;

    /****
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping(value = "/upload")
    public ResponseResult upload(MultipartFile file) throws IOException {
        //调用
        fileHandler.upload(file.getOriginalFilename(),file.getBytes());
        return ResponseResult.ok();
    }

    /****
     * 文件下载
     * @return
     */
    @GetMapping(value = "/download/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse response) throws IOException {
        //调用
        byte[] buffer = fileHandler.download(filename);
        ServletOutputStream os = response.getOutputStream();
        os.write(buffer);
        os.flush();
        os.close();
    }

}
