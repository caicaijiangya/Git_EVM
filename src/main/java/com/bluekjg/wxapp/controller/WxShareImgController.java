package com.bluekjg.wxapp.controller;

import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bluekjg.core.commons.base.BaseController;
import com.bluekjg.core.commons.ffmpeg.FFMpegDto;
import com.bluekjg.core.commons.ffmpeg.FFmpegExecuter;
import com.bluekjg.core.commons.ffmpeg.WaterMarkUtils;
import com.bluekjg.core.commons.utils.DateUtil;
import com.bluekjg.wxapp.model.PagingDto;
import com.bluekjg.wxapp.model.WxBargainGoods;
import com.bluekjg.wxapp.service.IWxBargainService;
import com.bluekjg.wxapp.utils.CommonUtil;
import com.bluekjg.wxapp.utils.PostObjectSample;
import com.bluekjg.wxapp.utils.WxappConfigUtil;

/**
 * @description：分享图
 * @author：pincui.tom
 * @date：2018/3/23 14:51
 */
@Controller
@RequestMapping("/xcx/shareImg")
public class WxShareImgController extends BaseController {
	@Autowired
	private IWxBargainService bargainService;

    /**
     * 砍价分享图
     *
     * @param resource
     * @return
     */
    @RequestMapping("/bargain")
    @ResponseBody
    public Object aes(@Valid PagingDto page,HttpServletResponse response) {
    	Object obj = renderError("The request failed");
        try {
        	WxBargainGoods bargain = bargainService.queryBargainById(page);
    		String input = WxappConfigUtil.SERVICE_FFMPEG_PATH+"bargain_bg.png";
    		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    		String output = WxappConfigUtil.SERVICE_PAGE+"temp"+uuid+".png";
    		
    		String qrCodeFile = WxappConfigUtil.SERVICE_PAGE+"temp" + uuid +"_qrcode.png";
    		CommonUtil.httpsRequestImage(bargain.getQrCode(),qrCodeFile);
    		WaterMarkUtils.getCircleImage(qrCodeFile,qrCodeFile,0.8f);
    		
    		String goodsFile = WxappConfigUtil.SERVICE_PAGE+"temp" + uuid +"_goods.jpg";
    		CommonUtil.httpsRequestImage(bargain.getGoodsImages(),goodsFile);
    		
    		String headImgFile = WxappConfigUtil.SERVICE_PAGE+"temp" + uuid +"_head.png";
    		CommonUtil.httpsRequestImage(bargain.getHeadImgUrl(),headImgFile);
    		WaterMarkUtils.getCircleImage(headImgFile,headImgFile,2f);
    		
    		Graphics2D g = WaterMarkUtils.drawImage(input);
    		FFMpegDto dto = new FFMpegDto();
    		dto.setInputPath(input);
     		dto.setOutputPath(output);
    		//指纹
    		ffmpegImage(60,980,180,180,WxappConfigUtil.SERVICE_FFMPEG_PATH+"fingerprint.png",dto);
    		dto.setInputPath(dto.getOutputPath());
    		//头像
    		ffmpegImage(40,40,100,100,headImgFile,dto);
    		//商品
    		ffmpegImage(75,200,600,524,goodsFile,dto);
    		//二维码
    		ffmpegImage(410,850,300,300,qrCodeFile,dto);
    		
    		//昵称
    		ffmpegText(160,70,30,WxappConfigUtil.SERVICE_FFMPEG_PATH+"msyh.ttc","#555555",bargain.getUserName(),dto);
    		//助力活动
    		ffmpegText(160,130,35,WxappConfigUtil.SERVICE_FFMPEG_PATH+"msyh.ttc","#555555","正在参加助力活动······",dto);
    		
    		//商品名称
    		ffmpegText(60,750,30,WxappConfigUtil.SERVICE_FFMPEG_PATH+"msyh.ttc","#555555",bargain.getGoodsName(),dto);
    		g.setFont(new Font(dto.getFont(), Font.PLAIN, 30));
    		int tempStrWight=g.getFontMetrics().stringWidth(dto.getText()); 
    		int a_y = 750;
    		if(bargain.getGoodsName().length() > 14) {
    			tempStrWight = 0;
    			a_y = 780;
    		}
    		//活动价格
    		ffmpegText(60+tempStrWight,a_y-5,32,WxappConfigUtil.SERVICE_FFMPEG_PATH+"msyh.ttc","#0d93ff",bargain.getActivityPrice().toString(),dto);
    		g.setFont(new Font(dto.getFont(), Font.PLAIN, 32));
    		int tempStrWight0=g.getFontMetrics().stringWidth(dto.getText()); 
    		ffmpegText(60+tempStrWight+tempStrWight0,a_y,30,WxappConfigUtil.SERVICE_FFMPEG_PATH+"msyh.ttc","#555555","元(原价",dto);
    		g.setFont(new Font(dto.getFont(), Font.PLAIN, 30));
    		int tempStrWight1=g.getFontMetrics().stringWidth(dto.getText()); 
    		
    		ffmpegText(60+tempStrWight+tempStrWight0+tempStrWight1,a_y-5,32,WxappConfigUtil.SERVICE_FFMPEG_PATH+"msyh.ttc","#000000",bargain.getGoodsPrice().toString(),dto);
    		g.setFont(new Font(dto.getFont(), Font.PLAIN, 32));
    		int tempStrWight2=g.getFontMetrics().stringWidth(dto.getText()); 
    		ffmpegText(60+tempStrWight+tempStrWight0+tempStrWight1+tempStrWight2,a_y,30,WxappConfigUtil.SERVICE_FFMPEG_PATH+"msyh.ttc","#555555","元)",dto);
    		g.setFont(new Font(dto.getFont(), Font.PLAIN, 30));
    		int tempStrWight3=g.getFontMetrics().stringWidth(dto.getText()); 
    		//横线
    		ffmpegImage(60+tempStrWight+tempStrWight0+tempStrWight1,a_y+15 ,tempStrWight2+tempStrWight3-5,1,WxappConfigUtil.SERVICE_FFMPEG_PATH+"underline.png",dto);
    		
    		//二维码提示文字
    		ffmpegText(60,870,30,WxappConfigUtil.SERVICE_FFMPEG_PATH+"msyh.ttc","#555555","长按识别图中二维码",dto);
    		ffmpegText(60,910,30,WxappConfigUtil.SERVICE_FFMPEG_PATH+"msyh.ttc","#555555","快来助我一臂之力！！！",dto);
    		
    		// 文件上传
    		File inputFile = new File(output);
    		InputStream inputStream = new FileInputStream(inputFile);
    		String imagePath = DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD);
    		Integer result = PostObjectSample.PostObject(imagePath + "/" + uuid+".png", "",inputStream);
			// 返回结果
			if (result > 0) {
				obj = renderSuccess(WxappConfigUtil.ALICLOUD_IMAGE_BASE_URL + imagePath + "/" + uuid+".png");
			}
			new File(qrCodeFile).delete();
			new File(goodsFile).delete();
			new File(headImgFile).delete();
			new File(output).delete();
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
		}
        return obj;
    }
    
    public void ffmpegImage(Integer x,Integer y,Integer width,Integer height,String file,FFMpegDto dto) {
    	String files[] = file.split(":");
 		dto.setIcon(files[0]+"\\\\:"+files[1]);
 		dto.setX(x);
		dto.setY(y);
		dto.setWidth(width);
		dto.setHeight(height);
		FFmpegExecuter.videoTransferImage(dto);
    }
    public void ffmpegText(Integer x,Integer y,Integer size,String font,String color,String text,FFMpegDto dto) {
    	dto.setFont(font);
		String fonts[] = dto.getFont().split(":");
		dto.setFont(fonts[0]+"\\\\:"+fonts[1]);
		dto.setColor(color);
		dto.setX(x);
		dto.setY(y);
		dto.setSize(size);
		dto.setText(text);
		FFmpegExecuter.videoTransferText(dto);
    }
}
