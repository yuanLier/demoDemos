package com.demo.demePrettierCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 这是
 * 好不容易偷来的
 * 更好看的验证码
 * 嘻嘻
 */

public class DemoPrettierCode {
    // 指定默认字符源（去掉了O/0;I/1）
    public static final String VERIFY_CODES = "23456789ABCDEFGHIJKMNPQRSTUVWXYZ";
    private static Random random = new Random();

    public static void main(String[] args) throws IOException {
        // 指定验证码中字符个数
        String verifyCode = generateVerifyCode(4);

        // 指定生成的文件路径
        File file = new File("C://Users/yuanyiwen/Desktop/" + verifyCode + ".jpg");

        // 输出验证码图片文件
        outputImage(200,80,file,verifyCode);

        // 控制台打印验证码值
        System.out.println(verifyCode);
    }

    /**
     * 使用默认字符源生成验证码值
     * @param verifySize 验证码字符个数
     * @return
     */
    public static String generateVerifyCode(int verifySize) {
        // 默认为默认字符源
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    /**
     * 使用指定字符源生成验证码值
     * @param verifySize 验证码个数
     * @param sources 指定的字符源
     * @return 返回验证码值
     */
    public static String generateVerifyCode(int verifySize, String sources) {
        // 若指定字符源为空，使用默认字符源
        if(sources == null || sources.length() == 0) {
            sources = VERIFY_CODES;
        }

        StringBuilder verifyCode = new StringBuilder(verifySize);
        for(int i = 0; i < verifySize; i++) {
            // 在字符源中随机挑选字符并依次拼接起来
            verifyCode.append(sources.charAt(random.nextInt(sources.length()-1)));
        }

        // 返回验证码值
        return verifyCode.toString();
    }

    /**
     * 画验证码图片
     * @param imgWidth 图片宽度
     * @param imgHeight 图片高度
     * @param stream 输出流
     * @param verifyCode 验证码值
     */
    public static void outputImage(int imgWidth, int imgHeight, OutputStream stream, String verifyCode) {
        // 生成BufferedImage对象（选用RGB(红绿蓝调色)模式）
        BufferedImage bufferedImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        // 使用该对象创造一个画笔
        Graphics2D g = bufferedImage.createGraphics();
        // 开启抗锯齿属性
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        // 随机选五个颜色
        Color[] colors = new Color[5];
        // 设置颜色的可选范围
        Color[] colorSpaces = new Color[] {
                Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY,
                Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW
        };
        // fraction 分数
        float[] fractions = new float[colors.length];
        for(int i = 0; i < colors.length; i++) {
            // 从可选范围中指定一个颜色
            colors[i] = colorSpaces[random.nextInt(colorSpaces.length)];
            // 为这个颜色指定一个偏移量（？）
            fractions[i] = random.nextFloat();
        }

        // 设置边框
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, imgWidth, imgHeight);

        // 设置背景色
        Color bgColor = getRandColor(200,250);
        g.setColor(bgColor);
        // 给它一个留一点边框，这样扭起来之后比较好看
        // 备注 ：这个参数不要乱动 玄学调图
        g.fillRect(0, 2, imgWidth, imgHeight - 4);

        // 绘制干扰线（对没错配色是偷来的 躺
        g.setColor(getRandColor(160,200));
        for(int i = 0; i < 20; i++) {
            int x = random.nextInt(imgWidth - 1);
            int y = random.nextInt(imgHeight - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g.drawLine(x, y, x + xl + 40, y + yl + 20);
        }

        // 对没错叕是偷来的
        float yawpRate = 0.05f;
        int area = (int) (yawpRate * imgWidth * imgHeight);
        for(int i = 0; i < area; i++) {
            int x = random.nextInt(imgWidth);
            int y = random.nextInt(imgHeight);
            int rgb = getRandomIntColor();
            bufferedImage.setRGB(x, y, rgb);
        }

        // 使图片扭曲（颜色跟背景颜色保持一致）
        shear(g, imgWidth, imgHeight, bgColor);

        // 配色依然是偷的
        g.setColor(getRandColor(100, 160));
        // 设置字体
        int fontSize = imgHeight - 4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        g.setFont(font);
        // 获取各个字符
        char[] chars = verifyCode.toCharArray();
        // 后面的看不懂了 嘤
        for(int i = 0; i < verifyCode.length(); i++){
            // 让字符们歪起来
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * random.nextDouble() * (random.nextBoolean() ? 1 : -1), (imgWidth / verifyCode.length()) * i + fontSize/2, imgHeight/2);
            g.setTransform(affine);
            // 然后就可以开始画字符啦
            g.drawChars(chars, i, 1, ((imgWidth-10) / verifyCode.length()) * i + 5, imgHeight/2 + fontSize/2 - 10);
        }

        // 全部画完之后，这根画笔就被安排上了
        g.dispose();

        try {
            // 最后将BufferedImage对象写入ImageIO流中
            ImageIO.write(bufferedImage, "jpg", stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将图片写入指定文件
     * @param imgWight 图片宽度
     * @param imgHeight 图片高度
     * @param file 指定文件
     * @param verifyCode 验证码值
     * @throws IOException
     */
    public static void outputImage(int imgWight, int imgHeight, File file, String verifyCode) throws IOException{
        try{
            FileOutputStream outputStream = new FileOutputStream(file);
            // 将画好的验证码写入输出流
            outputImage(imgWight, imgHeight, outputStream, verifyCode);
            // 关闭流
            outputStream.close();
        } catch(IOException e){
            throw e;
        }
    }


    /************************** 好了好了后面就都看不懂了 ************************/

    /********** 配色三连 *********/

    private static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }


    /************* 扭曲三连 *************/

    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }

    }

}
