package com.bluekjg.core.commons.ffmpeg;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.wxapp.utils.CommonUtil;
import com.bluekjg.wxapp.utils.ImageConvert;
import com.bluekjg.wxapp.utils.PostObjectSample;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

public class WaterMarkUtils {
	public static Logger logger = LogManager.getLogger(WaterMarkUtils.class.getName());
	
	public static Graphics2D drawImage(String bg) {
		Graphics2D g = null;
		try {
			File backFilenew = new File(bg);
	    	Image srcImg = ImageIO.read(backFilenew);
	    	if(srcImg == null) {return null;}
	        Integer srcImgWidth = srcImg.getWidth(null);//获取图片的宽
	        Integer srcImgHeight = srcImg.getHeight(null);//获取图片的高
			BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
	        g = bufImg.createGraphics();
	        // ----------  增加下面的代码使得背景透明  -----------------
	        bufImg = g.getDeviceConfiguration().createCompatibleImage(srcImgWidth,srcImgHeight,Transparency.TRANSLUCENT);
	        g.dispose();
	        g = bufImg.createGraphics();
	        // ----------  背景透明代码结束  -----------------
	        // 设置对线段的锯齿状边缘处理
	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,   
	                RenderingHints.VALUE_INTERPOLATION_BILINEAR);// 设置对线段的锯齿状边缘处理     
	        //g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
	        g.drawImage(srcImg.getScaledInstance(srcImgWidth, srcImgHeight, Image.SCALE_SMOOTH), 0, 0, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return g;
	}
	
    /**
     * 格式转换
     * @param inputPage
     * @param outputPage
     * @return
     */
    public static Image formatConversionImage(String inputPage,String outputPage) {
    	Image srcImg = null;
    	File inputFile = new File(inputPage);
    	File outputFile = new File(outputPage);
    	if(ImageConvert.isImage(inputFile)) {
			// 转换图片格式
        	try {
				ImageConvert.forJpg(inputPage, outputPage);
				srcImg = ImageIO.read(new File(outputPage));
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}finally {
				if(inputFile != null)
					inputFile.delete();
				if(outputFile != null)
					outputFile.delete();
			}
        }
    	return srcImg;
    }
    
    
    
    /** 
     * 旋转照片 
     * @param fullPage 文件主目录
     * @param fullPath 文件子目录
     * @param fullName 文件名
     * @return 
     */  
    public static String rotatePhonePhoto(String fullPage,String fullPath,String fullName, int angel){  
        BufferedImage src;  
        String ret = null;
        try {  
        	File file = new File(fullPage+"/"+fullPath+"/"+fullName);
            src = ImageIO.read(file);  
            int src_width = src.getWidth(null);  
            int src_height = src.getHeight(null);  
              
            int swidth=src_width;  
            int sheight=src_height;  
            int angel_cs = angel/90;
            if(angel_cs != 0 && angel_cs % 2 != 0){  
                swidth = src_height;  
                sheight= src_width;  
            }  
              
            Rectangle rect_des = new Rectangle(new Dimension(swidth,sheight));  
  
            BufferedImage res = new BufferedImage(rect_des.width, rect_des.height,BufferedImage.TYPE_INT_RGB);  
            Graphics2D g2 = res.createGraphics();  
            g2.translate((rect_des.width - src_width) / 2,  
                    (rect_des.height - src_height) / 2);  
            g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2); 
            g2.drawImage(src, null, null);  
            Random random = new Random();
            fullName = random.nextInt(9999)+"_"+fullName;
            
            String filePostfix = fullName.substring(fullName.lastIndexOf("."));
            ImageIO.write(res, filePostfix.substring(1,filePostfix.length()), new File(fullPage+"/"+fullPath+"/"+fullName));  
            //file.delete();
            ret = fullName;
        } catch (IOException e) {  
              
            logger.error(e.getMessage(),e);  
        }    
        return ret;
    }  
    
    
    /** 
     * 旋转照片 
     * @param fullPage 文件主目录
     * @param fullPath 文件子目录
     * @param fullName 文件名
     * @return 
     */  
    public static String rotatePhonePhotoPng(String fullPage,String fullPath,String fullName, int angel){  
        BufferedImage src;  
        String ret = null;
        try {  
        	File file = new File(fullPage+"/"+fullPath+"/"+fullName);
            src = ImageIO.read(file);  
            int src_width = src.getWidth(null);  
            int src_height = src.getHeight(null);  
              
            int swidth=src_width;  
            int sheight=src_height;  
            int angel_cs = angel/-90;
            if(angel_cs != 0 && angel_cs % 2 != 0){  
                swidth = src_height;  
                sheight= src_width;  
            }  
              
            Rectangle rect_des = new Rectangle(new Dimension(swidth,sheight));  
  
            BufferedImage res = new BufferedImage(rect_des.width, rect_des.height,BufferedImage.TYPE_INT_RGB);  
            Graphics2D g2 = res.createGraphics();  
         // ----------  增加下面的代码使得背景透明  -----------------
            res = g2.getDeviceConfiguration().createCompatibleImage(swidth,sheight,Transparency.TRANSLUCENT);
            g2.dispose();
            g2 = res.createGraphics();
            // ----------  背景透明代码结束  -----------------
            g2.translate((rect_des.width - src_width) / 2,  
                    (rect_des.height - src_height) / 2);  
            g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2); 
            g2.drawImage(src, null, null);  
            Random random = new Random();
            fullName = random.nextInt(9999)+"_"+fullName;
            
            String filePostfix = fullName.substring(fullName.lastIndexOf("."));
            ImageIO.write(res, filePostfix.substring(1,filePostfix.length()), new File(fullPage+"/"+fullPath+"/"+fullName));  
            //file.delete();
            ret = fullName;
        } catch (IOException e) {  
              
            logger.error(e.getMessage(),e);  
        }    
        return ret;
    }  
    
    
    /**
     * 裁剪图片方法
     * @param fullPage 文件主目录
     * @param fullPath 文件子目录
     * @param fullName 文件名
     * @param startX 裁剪开始x坐标
     * @param startY 裁剪开始y坐标
     * @param endX 裁剪结束x坐标
     * @param endY 裁剪结束y坐标
     * @return
     */
    public static String cropImage(String fullPage,String fullPath,String fullName, int startX, int startY, int endX, int endY) {
    	String ret = null;
    	try {
    		File file = new File(fullPage+"/"+fullPath+"/"+fullName);
     	   BufferedImage input = ImageIO.read(file);
            int width = input.getWidth();
            int height = input.getHeight();
            if (startX == -1) {
                startX = 0;
            }
            if (startY == -1) {
                startY = 0;
            }
            if (endX == -1) {
                endX = width - 1;
            }
            if (endY == -1) {
                endY = height - 1;
            }
            if(endX > width) {endX = width;}
            if(endY > height) {endY = height;}
            BufferedImage result = new BufferedImage(endX - startX, endY - startY, 4);
            for (int x = startX; x < endX; ++x) {
                for (int y = startY; y < endY; ++y) {
                    int rgb = input.getRGB(x, y);
                    result.setRGB(x - startX, y - startY, rgb);
                }
            }
            Random random = new Random();
            fullName = random.nextInt(9999)+"_"+fullName;
            String filePostfix = fullName.substring(fullName.lastIndexOf("."));
            ImageIO.write(result, filePostfix.substring(1,filePostfix.length()), new File(fullPage+"/"+fullPath+"/"+fullName));
            ret = fullName;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}	
    	 return ret;
     }
    
    
    
    /**
     * 画圆角图片，sourcePath是原图片路径，radius是圆角大小
     * @param sourcePath 原图片地址，不能为空
     * @param destinyPath 目的图片地址，不能为空
     * @param radius，不能为负数，为0或者null表示用图片的宽、高最小值做直径切园(圆角比例值)
     * @return
     */
    public static boolean getCircleImage(String sourcePath,String destinyPath,float diameter){
        if(StringUtils.isEmpty(sourcePath) || StringUtils.isEmpty(destinyPath)){
            return false;
        }
      //生成最终的图片
        boolean rs = false;
        Graphics2D g2 = null;
        try {
        	// 获取原图片
            BufferedImage waterImg = null;
            File file = new File(sourcePath);
            waterImg = ImageIO.read(file);
            
            //半径有设置时，以设置的半径为主
            int width = waterImg.getWidth();
            int height = waterImg.getHeight();
            float getDiameter = (width < height)?width:height;
            if(diameter == 0){
                width = (int) getDiameter;
                height = (int) getDiameter;
            }else {
            	getDiameter = getDiameter*diameter;
            }
            
            //按照要求缩放图片
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            tag.getGraphics().drawImage(waterImg, 0, 0,width, height, null);
            waterImg = tag;
            
            
            tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            g2 = tag.createGraphics();
         // ----------  增加下面的代码使得背景透明  -----------------
            tag = g2.getDeviceConfiguration().createCompatibleImage(width, height,Transparency.TRANSLUCENT);
            g2.dispose();
            g2 = tag.createGraphics();
            // ----------  背景透明代码结束  -----------------
            g2.setComposite(AlphaComposite.Src);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fill(new RoundRectangle2D.Float(0, 0, width, height, getDiameter,getDiameter));
            g2.setComposite(AlphaComposite.SrcIn);
            g2.drawImage(waterImg, 0, 0, null);  
            /*int temp = destinyPath.lastIndexOf(".") + 1;
            rs = ImageIO.write(tag,destinyPath.substring(temp), new File(destinyPath));*/
            rs = ImageIO.write(tag,"png", new File(destinyPath));
        } catch (IOException e) {
            return false;
        }finally{
            if(g2 != null){
                g2.dispose();
            }
        }
        return rs;
        
        
    }
    
    /**
     * 图片旋转90度
     * @param filePage
     * @return
     */
    public static String rotateUploadUrl(String filePage) {
    	String imagePath = DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD);
		String fileName = UUID.randomUUID().toString().replaceAll("-", "");
		String filePostfix = filePage.substring(filePage.lastIndexOf("."));
    	try {
    		CommonUtil.httpsRequestImage(filePage,WxappConfigUtil.SERVICE_PAGE+imagePath+"/"+fileName+filePostfix);
    		String sourceImage = WaterMarkUtils.rotatePhonePhoto(WxappConfigUtil.SERVICE_PAGE,imagePath,fileName+filePostfix, 90);
    		
    		File file = new File(WxappConfigUtil.SERVICE_PAGE+imagePath+"/"+sourceImage);
    		PostObjectSample.PostObject(imagePath + "/" + fileName+filePostfix, null,new FileInputStream(file));
    		file.delete();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return WxappConfigUtil.ALICLOUD_IMAGE_BASE_URL + imagePath + "/" + fileName+filePostfix;
    }
   
}
