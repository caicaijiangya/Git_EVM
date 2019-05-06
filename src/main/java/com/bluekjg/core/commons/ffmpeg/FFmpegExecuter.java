package com.bluekjg.core.commons.ffmpeg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bluekjg.wxapp.utils.WxappConfigUtil;

public class FFmpegExecuter {
	public static Logger  logger = LogManager.getLogger(FFmpegExecuter.class.getName());
	/**
	 * @ FFMpegCardDto dto 参数传递对象<br>
	 * dto中包含的参数<br>
	 * (必填)2.InputPath:原视频路径<br>
	 * (必填)3.OutputPath:转换后视频输出路径<br>
	 * (必填)4.icon:水印地址
	 * (必填)5.x:水印横坐标
	 * (必填)6.y:水印竖坐标
	 * (必填)7.width:水印宽度
	 * (必填)8.height:水印高度
	 */
	public static void videoTransferImage(FFMpegDto dto) {
		List<String> cmd = new ArrayList<String>();
		cmd.add(WxappConfigUtil.SERVICE_FFMPEG_PATH+"ffmpeg.exe");
		cmd.add("-y");
		cmd.add("-i");
		cmd.add(dto.getInputPath());
		if (null != dto.getIcon()) {// logo和水印效果差不多,没有研究区别是啥
			String logo = dto.getIcon();
			cmd.add("-vf");
			Integer xaxis = dto.getX();
			Integer yaxis = dto.getY();
			xaxis = xaxis != null ? xaxis : 0;
			yaxis = yaxis != null ? yaxis : 0;
			String logoString = "movie=" + logo + ",scale= "+dto.getWidth()+":"+dto.getHeight()+"[watermask]; [in] [watermask]overlay=x=" + xaxis + ":y=" + yaxis + "[out]";
			cmd.add(logoString);
		}
		cmd.add(dto.getOutputPath());
		FFmpegExecuter.exec(cmd);
	}

	/**
	 * @ FFMpegCardDto dto 参数传递对象<br>
	 * dto中包含的参数<br>
	 * (必填)2.InputPath:原视频路径<br>
	 * (必填)3.OutputPath:转换后视频输出路径<br>
	 * (必填)4.text:水印文字
	 * (必填)5.color:水印文字颜色
	 * (必填)6.font:水印文字字体
	 * (必填)7.size:水印文字大小
	 * (必填)8.x:水印横坐标
	 * (必填)9.y:水印竖坐标
	 */
	public static void videoTransferText(FFMpegDto dto) {
		List<String> cmd = new ArrayList<String>();
		cmd.add(WxappConfigUtil.SERVICE_FFMPEG_PATH+"ffmpeg.exe");
		cmd.add("-y");
		cmd.add("-i");
		cmd.add(dto.getInputPath());
		if (null != dto.getText()) {// logo和水印效果差不多,没有研究区别是啥
			String text = dto.getText();
			cmd.add("-vf");
			Integer xaxis = dto.getX();
			Integer yaxis = dto.getY();
			xaxis = xaxis != null ? xaxis : 0;
			yaxis = yaxis != null ? yaxis : 0;
			if(dto.getYbl() == null) {dto.setYbl(1);}
			double ybl = dto.getYbl();
			String logoString = "drawtext=fontfile=" + dto.getFont() + ": text='" + text
					+ "':x=("+xaxis+"):y=((("+dto.getSize()+"-th)/"+ybl+")+"+yaxis+"):fontsize="+dto.getSize()+":fontcolor=" + dto.getColor();
			cmd.add(logoString);
		}
		cmd.add(dto.getOutputPath());
		FFmpegExecuter.exec(cmd);
	}

	/**
	 * 判断返回内容通过正则表达式判断
	 * 
	 * @param str
	 * @return
	 */
	public static String dealString(String str) {
		Matcher m = java.util.regex.Pattern.compile("^frame=.*").matcher(str);
		String msg = "";
		while (m.find()) {
			msg = m.group();
		}
		return msg;
	}

	/**
	 * 如果返回不是null的值就是成功(值为转换用时单位:秒)
	 * 
	 * @param instr
	 * @return
	 */
	public static String returnSecond(String instr) {
		String returnValue = null;
		if (null != instr) {
			String[] a = instr.split("\\.");
			String[] b = a[0].split(":");
			int returnNumber = 0;
			if (null != instr && b[0].length() != 0) {
				returnNumber = Integer.valueOf(b[0]) * 60 * 60 + Integer.valueOf(b[1]) * 60 + Integer.valueOf(b[2]);
				returnValue = String.valueOf(returnNumber);
			} else {
				returnValue = null;
			}
		}
		return returnValue;
	}

	/**
	 * 程序执行CMD
	 * 
	 * @param cmd
	 * @return
	 */
	public static void exec(List<String> cmd) {
		Process proc = null;
		BufferedReader stdout = null;
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(cmd);
			builder.redirectErrorStream(true);
			proc = builder.start();
			stdout = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line;
			while ((line = stdout.readLine()) != null) {
				dealString(line);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			try {
				proc.waitFor();
				stdout.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
	}
}
