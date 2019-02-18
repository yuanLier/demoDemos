package com.demo.demoCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * 模拟二维码
 *
 * 基本思路 ：
 *      1、首先需要一个画图的方法
 *      2、还需要一个把画出来的图转化为图片文件的方法
 *      3、最后生成图片，并返回验证码的值
 */

public class DemoCode {
    public static void main(String[] args) {
        int numOfChars = 4;
        String suffix = "jpg";
        String picName = "C://Users/yuanyiwen/Desktop/img";
        String code = generateImg(numOfChars, picName, suffix);
        System.out.println(code);
    }

    /**
     * 绘制验证码图片
     * @param bufferedImage 获取一个空的BufferedImage对象，用来装载验证码图片
     * @param numOfChars 验证码中字符个数
     * @param imgWidth 图片宽度
     * @param imgHeight 图片高度
     * @return
     */
    private static String drawImg(BufferedImage bufferedImage, int numOfChars, int imgWidth, int imgHeight) {
        // 首先创建一支画笔
        Graphics2D g = bufferedImage.createGraphics();

        // 画出底框那个长条条
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0 , imgWidth, imgHeight);

        // 设置字体
        Font font = new Font("Futura",Font.PLAIN, imgHeight-5);
        g.setFont(font);

        // 绘制边框
        g.setColor(Color.GRAY);
        g.drawRect(0,0,imgWidth-1,imgHeight-1);

        // 随机绘制干扰线
        Random random = new Random();
        for(int i = 0; i < 16; i++) {
            int x = random.nextInt(imgWidth);
            int y = random.nextInt(imgHeight);
            int xPlus = random.nextInt(14);
            int yPlus = random.nextInt(14);
            g.drawLine(x,y,(x +xPlus),(y + yPlus));
        }


        int charX = 15, charY = 25;

        // 记录验证码
        StringBuffer code = new StringBuffer();

        // 设置字符（这里去掉了容易混淆的O和0）
        char[] codeChars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };

        String randomChar;
        for(int i = 0, red, green, blue; i < numOfChars; i++) {
            // 随机选择字符
            randomChar = String.valueOf(codeChars[random.nextInt(codeChars.length)]);

            // 随机设置颜色
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));

            // 绘制字符
            g.drawString(randomChar, (i+1) * charX, charY);

            // 将随机产生的各个字符拼接起来
            code.append(randomChar);
        }

        return code.toString();
    }

    /**
     * 将图片写入文件流
     * @param bufferedImage 获取已经装载了验证码的BufferedImage对象
     * @param fileName 文件名
     * @param suffix 图片后缀名
     */
    private static void outPutImg(BufferedImage bufferedImage, String fileName, String suffix) {

        // 创建一个新文件（在文件名中加入当前时间戳，避免重名）
        File file = new File(fileName + System.currentTimeMillis() + "." + suffix);

        try {
            // 创建输出流
            FileOutputStream outputStream = new FileOutputStream(file);
            // 将图片写入流中
            ImageIO.write(bufferedImage, suffix, outputStream);
            // 关闭流
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成图片，返回验证码值
     * @param numOfChars 验证码中字符个数
     * @param picName 文件名
     * @param suffix 图片后缀名
     * @return 返回验证码值
     */
    private static String generateImg(int numOfChars, String picName, String suffix) {
        // 设置图片的长和宽
        int imgWidth = numOfChars * 20;
        int imgHeight = 30;

        // 创建BufferedImage对象
        BufferedImage bufferedImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_3BYTE_BGR);

        // 获取验证码值，并将画出来的验证码写入图片流
        String code = drawImg(bufferedImage, numOfChars, imgWidth, imgHeight);

        // 判断图片名（若为空，则以验证码值命名）
        picName = (picName == null)? code : picName;

        // 将图片写入文件流
        outPutImg(bufferedImage, picName, suffix);

        return code;
    }
}
