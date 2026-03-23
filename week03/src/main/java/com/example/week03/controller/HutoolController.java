package com.example.week03.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hutool")
public class HutoolController {

    // 4.1 生成无横杠 UUID
    @GetMapping("/id")
    public String generateId() {
        return IdUtil.fastSimpleUUID();
    }

    // 4.2 MD5 加密接口
    @GetMapping("/md5")
    public String md5(@RequestParam String text) {
        return SecureUtil.md5(text);
    }

    // 4.3 文件上传接口
    @PostMapping("/upload")
    public Map<String, String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        // 生成唯一文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = FileUtil.extName(originalFilename);
        String newFileName = IdUtil.fastSimpleUUID() + "." + suffix;

        // 保存路径（可修改为你自己的路径）
        String uploadPath = "D:/download/";
        File destFile = FileUtil.file(uploadPath + newFileName);

        // 写入文件
        FileUtil.writeFromStream(file.getInputStream(), destFile);

        // 返回结果
        Map<String, String> result = new HashMap<>();
        result.put("fileName", newFileName);
        result.put("savePath", uploadPath + newFileName);
        return result;
    }
}