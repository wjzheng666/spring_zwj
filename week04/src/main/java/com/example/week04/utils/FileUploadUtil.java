package com.example.week04.utils;

import com.example.week04.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
public class FileUploadUtil {
    private static final String UPLOAD_DIR = getUploadDir();
    private static final Set<String> ALLOWED_EXTENSIONS;

    static {
        // 初始化上传目录
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new RuntimeException("创建上传目录失败: " + UPLOAD_DIR);
            }
        }

        // 初始化允许的文件类型（Java 8 兼容写法）
        ALLOWED_EXTENSIONS = new HashSet<>();
        ALLOWED_EXTENSIONS.add(".jpg");
        ALLOWED_EXTENSIONS.add(".jpeg");
        ALLOWED_EXTENSIONS.add(".png");
        ALLOWED_EXTENSIONS.add(".gif");
        ALLOWED_EXTENSIONS.add(".bmp");
        ALLOWED_EXTENSIONS.add(".webp");
        ALLOWED_EXTENSIONS.add(".pdf");
        ALLOWED_EXTENSIONS.add(".doc");
        ALLOWED_EXTENSIONS.add(".docx");
        ALLOWED_EXTENSIONS.add(".xls");
        ALLOWED_EXTENSIONS.add(".xlsx");
        ALLOWED_EXTENSIONS.add(".ppt");
        ALLOWED_EXTENSIONS.add(".pptx");
        ALLOWED_EXTENSIONS.add(".txt");
        ALLOWED_EXTENSIONS.add(".md");
        ALLOWED_EXTENSIONS.add(".csv");
        ALLOWED_EXTENSIONS.add(".zip");
        ALLOWED_EXTENSIONS.add(".rar");
        ALLOWED_EXTENSIONS.add(".7z");
        ALLOWED_EXTENSIONS.add(".json");
        ALLOWED_EXTENSIONS.add(".xml");
    }

    private static String getUploadDir() {
        try {
            String baseDir = ResourceUtils.getURL("classpath:").getPath();
            // 处理 Windows 路径编码问题
            if (baseDir.startsWith("/")) {
                baseDir = baseDir.substring(1);
            }
            Path uploadPath = Paths.get(baseDir, "static", "upload");
            Files.createDirectories(uploadPath);
            String uploadDir = uploadPath.toAbsolutePath() + File.separator;
            log.info("上传目录: {}", uploadDir);
            return uploadDir;
        } catch (IOException e) {
            log.error("创建上传目录失败: {}", e.getMessage(), e);
            throw new RuntimeException("创建上传目录失败", e);
        }
    }

    public static String upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new BusinessException(400, "文件名不能为空");
        }

        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(suffix)) {
            throw new BusinessException(400, "不支持的文件类型: " + suffix);
        }

        String fileName = UUID.randomUUID() + suffix;
        File dest = new File(UPLOAD_DIR + fileName);
        file.transferTo(dest);
        return fileName;
    }
}