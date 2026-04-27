package org.example.week08.sign.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.example.week08.common.dto.ApiResult;
import org.example.week08.sign.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping(value = "/sign", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor

public class SignController {

    private final SignService signService;

    // 生成签到二维码
    @GetMapping("/createQr")
    public void createQr(HttpServletResponse response, @RequestParam Long activityId) throws IOException {
        // 1. 生成签到码
        String code = signService.generateSignCode(activityId);

        // 2. 拼接扫码地址
        String qrContent = "http://172.20.10.4:8080/sign/scan?code=" + code;

        // 3. 生成二维码
        QrConfig config = new QrConfig(300, 300);
        BufferedImage image = QrCodeUtil.generate(qrContent, config);

        // 4. 输出到浏览器
        response.setContentType("image/png");
        ImageIO.write(image, "png", response.getOutputStream());
    }

    // 扫码签到
    @GetMapping(value = "/scan", produces = "text/html;charset=UTF-8")
    public String scan(@RequestParam String code) {
        if (StrUtil.isBlank(code)) {
            return "<h1 style='color:red;text-align:center;margin-top:30%;'>❌ 签到失败：签到码不能为空</h1>";
        }

        boolean success = signService.doSign(code, 1001L);
        if (success) {
            return "<h1 style='color:green;text-align:center;margin-top:30%;'>✅ 签到成功！</h1>";
        } else {
            return "<h1 style='color:red;text-align:center;margin-top:30%;'>❌ 签到失败：签到码无效或已过期</h1>";
        }
    }
}