package com.bluekjg.wxapp.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.image.ImageProducer;
import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiException;
import com.sun.jimi.core.JimiWriter;
import com.sun.jimi.core.options.JPGOptions;

public class ImageConvert {
	public static Logger logger = LogManager.getLogger(ImageConvert.class.getName());
	/**
	 * 测试函数
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// 图片文件夹路径
		String pathName = "F:/test";

		// 存放文件名的集合
		ArrayList<String> fileNameList = getFileName(pathName);

		// 遍历文件名集合
		Iterator<String> iter = fileNameList.iterator();

		while (iter.hasNext()) {

			String fileName = iter.next();

			File file = new File(pathName + "/" + fileName);

			// 判断是否为图片
			boolean result = isImage(file);

			if (result) {

				// 获取图片的格式
				String format = getExtension(file);

				System.out.println("图片格式为:" + format);

				// 将图片格式转换为JPEG
				if (format != "JPEG") {

					// 需要修改的文件路径
					String input = pathName + "/" + fileName;

					// 将文件路径改为.jpg
					String[] stringSplit = fileName.split("\\.");
					String output = pathName + "/" + stringSplit[0] + ".jpg";

					// 转换图片格式
					forJpg(input, output);

				}
			}
		}
	}

	/**
	 * 获取文件夹内所有的文件名
	 * 
	 * @param pathName
	 */
	public static ArrayList<String> getFileName(String pathName) {

		// 存放遍历出来的文件名字
		ArrayList<String> nameList = new ArrayList<String>();

		File dirFile = new File(pathName);

		// 获取此目录下的所有文件名与目录名
		String[] fileList = dirFile.list();

		for (int i = 0; i < fileList.length; i++) {

			// 遍历文件目录
			String string = fileList[i];

			File file = new File(dirFile.getPath(), string);

			String name = file.getName();

			nameList.add(name);
		}

		return nameList;
	}

	/**
	 * 获取图片格式函数
	 * 
	 * @param file
	 * @return
	 */
	public static String getExtension(File file) {
		// 图片格式
		String format = "";

		ImageInputStream iis = null;

		try {

			iis = ImageIO.createImageInputStream(file);

			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);

			if (iter.hasNext()) {

				format = iter.next().getFormatName();
			}

		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		} finally {
			if (iis != null) {
				try {
					iis.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}

		return format;

	}

	/**
	 * 判断是否为图片函数
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isImage(File resFile) {

		ImageInputStream iis = null;

		try {

			iis = ImageIO.createImageInputStream(resFile);

			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);

			// 文件是不是图片
			if (iter.hasNext()) {

				return true;

			}

		} catch (IOException e) {

			logger.error(e.getMessage(),e);

		} finally {
			if (iis != null) {
				try {
					iis.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}
		return false;
	}

	/**
	 * 将图片格式转换为JPG格式
	 * 
	 * @param input
	 * @param output
	 * @throws IOException
	 */
	public static void forJpg(String input, String output) throws IOException {

		try {
			JPGOptions options = new JPGOptions();
			options.setQuality(72);

			ImageProducer image = Jimi.getImageProducer(input);
			JimiWriter writer = Jimi.createJimiWriter(output);

			writer.setSource(image);
			writer.setOptions(options);
			writer.putImage(output);

			// 转换后删除原文件
			File f = new File(input);
			//f.delete();
		} catch (JimiException e) {
			System.err.println("Error: " + e);
			logger.error(e.getMessage(),e);
		}

	}
}
