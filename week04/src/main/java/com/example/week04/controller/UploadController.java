package com.example.week04.controller;

import com.example.week04.common.Result;
import com.example.week04.exception.BusinessException;
import com.example.week04.utils.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/file")
public class UploadController {
    private static final String FILE_URL_PREFIX = "http://localhost:8080/upload/";

    // 单文件上传
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(400, "文件不能为空");
        }
        try {
            String fileName = FileUploadUtil.upload(file);
            String url = FILE_URL_PREFIX + fileName;
            return Result.success(url);
        } catch (IOException e) {
            log.error("文件上传IO异常: {}", e.getMessage(), e);
            throw new BusinessException(500, "文件上传失败: " + e.getMessage());
        } catch (Exception e) {
            log.error("文件上传未知异常: {}", e.getMessage(), e);
            throw new BusinessException(500, "服务器异常，请稍后重试");
        }
    }

    // 批量文件上传
    @PostMapping("/upload/batch")
    public Result<List<String>> uploadBatch(@RequestParam("files") MultipartFile[] files) {
        if (files == null || files.length == 0) {
            throw new BusinessException(400, "文件不能为空");
        }
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            try {
                String fileName = FileUploadUtil.upload(file);
                urls.add(FILE_URL_PREFIX + fileName);
            } catch (IOException e) {
                log.error("批量上传文件失败: {}", file.getOriginalFilename(), e);
                throw new BusinessException(500, "文件上传失败: " + file.getOriginalFilename());
            }
        }
        return Result.success(urls);
    }
}