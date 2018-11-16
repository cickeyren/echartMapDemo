package com.mybatis.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * 生成二维码
 * Created by Cickey on 2017/5/27.
 */
public class ZxingTest {
    private static final String CHARSET = "UTF-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸  
    private static final int QRCODE_SIZE = 200;
    // LOGO宽度  
    private static final int WIDTH = 60;
    // LOGO高度  
    private static final int HEIGHT = 60;

    /**
     * user: Rex
     * date: 2016年12月29日  上午12:31:29
     * @param content 二维码内容
     * @param logoImgPath Logo
     * @param needCompress 是否压缩Logo
     * @return 返回二维码图片
     * @throws WriterException
     * @throws IOException
     * BufferedImage
     * TODO 创建二维码图片
     */
    private static BufferedImage createImage(String content, String logoImgPath, boolean needCompress) throws WriterException, IOException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (logoImgPath == null || "".equals(logoImgPath)) {
            return image;
        }

        // 插入图片  
        ZxingTest.insertImage(image, logoImgPath, needCompress);
        return image;
    }

    /**
     * user: Rex
     * date: 2016年12月29日  上午12:30:09
     * @param source 二维码图片
     * @param logoImgPath Logo
     * @param needCompress 是否压缩Logo
     * @throws IOException
     * void
     * TODO 添加Logo
     */
    private static void insertImage(BufferedImage source, String logoImgPath, boolean needCompress) throws IOException{
        File file = new File(logoImgPath);
        if (!file.exists()) {
            return;
        }

        Image src = ImageIO.read(new File(logoImgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO  
            if (width > WIDTH) {
                width = WIDTH;
            }

            if (height > HEIGHT) {
                height = HEIGHT;
            }

            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
            g.dispose();
            src = image;
        }

        // 插入LOGO  
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * user: Rex
     * date: 2016年12月29日  上午12:32:32
     * @param content 二维码内容
     * @param logoImgPath Logo
     * @param destPath 二维码输出路径
     * @param needCompress 是否压缩Logo
     * @throws Exception
     * void
     * TODO 生成带Logo的二维码
     */
    public static void encode(String content, String logoImgPath, String destPath, boolean needCompress) throws Exception {
        BufferedImage image = ZxingTest.createImage(content, logoImgPath, needCompress);
        FileUploadUtil.mkdirs(destPath);
        ImageIO.write(image, FORMAT_NAME, new File(destPath));
    }

    /**
     * user: Rex
     * date: 2016年12月29日  上午12:35:44
     * @param content 二维码内容
     * @param destPath 二维码输出路径
     * @throws Exception
     * void
     * TODO 生成不带Logo的二维码
     */
    public static void encode(String content, String destPath) throws Exception {
        ZxingTest.encode(content, null, destPath, false);
    }

    /**
     * user: Rex
     * date: 2016年12月29日  上午12:36:58
     * @param content 二维码内容
     * @param logoImgPath Logo
     * @param output 输出流
     * @param needCompress 是否压缩Logo
     * @throws Exception
     * void
     * TODO 生成带Logo的二维码，并输出到指定的输出流
     */
    public static void encode(String content, String logoImgPath, OutputStream output, boolean needCompress) throws Exception {
        BufferedImage image = ZxingTest.createImage(content, logoImgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * user: Rex
     * date: 2016年12月29日  上午12:38:02
     * @param content 二维码内容
     * @param output 输出流
     * @throws Exception
     * void
     * TODO 生成不带Logo的二维码，并输出到指定的输出流
     */
    public static void encode(String content, OutputStream output) throws Exception {
        ZxingTest.encode(content, null, output, false);
    }

    /**
     * user: Rex
     * date: 2016年12月29日  上午12:39:10
     * @param file 二维码
     * @return 返回解析得到的二维码内容
     * @throws Exception
     * String
     * TODO 二维码解析
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     * user: Rex
     * date: 2016年12月29日  上午12:39:48
     * @param path 二维码存储位置
     * @return 返回解析得到的二维码内容
     * @throws Exception
     * String
     * TODO 二维码解析
     */
    public static String decode(String path) throws Exception {
        return ZxingTest.decode(new File(path));
    }

    public static void main(String[] args) throws Exception {
        String dir = "D:/1.jpg";
        String content = "321231808290001";
        File file = new File(dir);
        ZxingTest.encode(content, new FileOutputStream(file));
    }
}