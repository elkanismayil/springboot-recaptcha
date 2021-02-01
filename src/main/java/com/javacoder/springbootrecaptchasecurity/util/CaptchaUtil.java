package com.javacoder.springbootrecaptchasecurity.util;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;
import com.javacoder.springbootrecaptchasecurity.entity.User;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class CaptchaUtil {
    // create captcha object
    public static Captcha createCaptcha(int width, int height) {
        return new Captcha.Builder(width, height)
                .addBackground(new GradiatedBackgroundProducer())
                .addText(new DefaultTextProducer(), new DefaultWordRenderer())
                .addNoise(new CurvedLineNoiseProducer())
                .build();
    }

    // converting to binary string
    public static String encodeCaptcha(Captcha captcha) {
        String image = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(), "jpg", outputStream);
            byte[] bytes = Base64.getEncoder().encode(outputStream.toByteArray());
            image = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static void getCaptcha(User user) {
        Captcha captcha = createCaptcha(240, 70);
        user.setHiddenCaptcha(captcha.getAnswer());
        user.setCaptcha("");
        user.setRealCaptcha(encodeCaptcha(captcha));
    }
}
