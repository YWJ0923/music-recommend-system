package com.ywj.music_recommend_system.back.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author ywj
 * @date 2021/11/08
 */
@Controller
public class KaptchaController {
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @GetMapping("/kaptcha")
    public void getKaptcha(HttpSession session, HttpServletResponse response) throws Exception {
        String verifyCode = defaultKaptcha.createText();
        session.setAttribute("verifyCode", verifyCode);
        BufferedImage image = defaultKaptcha.createImage(verifyCode);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        byte[] kaptchaOutput = byteArrayOutputStream.toByteArray();
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(kaptchaOutput);
        outputStream.flush();
        outputStream.close();
    }
}
