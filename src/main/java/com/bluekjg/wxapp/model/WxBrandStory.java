package com.bluekjg.wxapp.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.bluekjg.admin.model.BrandStoryDetail;
import com.bluekjg.admin.model.Goods;

import java.io.Serializable;
import java.util.List;
/**
 * <p>
 * 
 * </p>
 *
 * @author Tim
 */
public class WxBrandStory  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 封面图片
     */
    private String coverImg;
    /**
     * 主图
     */
    private String introImg;
    /**
     * 是否置顶
     */
    private Integer isTop;
    /**
     * 是否显示0 显示 1 不显示
     */
    private Integer isShow;
    /**
     * 排序
     */
    private Integer seq;
    /**
     * 备注
     */
    private String note;
    /**
     * 是否删除 0 否 1是
     */
    private Integer isDel;
    /**
     * 创建时间
     */
    private java.util.Date createdTime;
    /**
     * 最后修改时间
     */
    private java.util.Date lastModifiedTime;
    /**
     * 作者
     */
    private String author;
    /**
     * 标签
     */
    private String labelDesc;
    
    /**
     * 品牌描述
     */
    private String brandDesc;
    
    
    /**
     * 简易描述
     */
    private String simpleDesc;
    
    private Integer rid;
    
    /**
     * 获取文章描述
     */
    private List<String> storeDesc;
   
    /**
     * 跳转到指定商品
     */
    private Integer goodsId;
    
    //商品信息
    private WxGoods goodsInfo;
    
    //详情信息
    private List<BrandStoryDetail> storyDetailList;
    
    //浏览量
    private Integer browerNum;
    
    private Integer isZan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getIntroImg() {
        return introImg;
    }

    public void setIntroImg(String introImg) {
        this.introImg = introImg;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public java.util.Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(java.util.Date createdTime) {
        this.createdTime = createdTime;
    }

    public java.util.Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(java.util.Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLabelDesc() {
        return labelDesc;
    }

    public void setLabelDesc(String labelDesc) {
        this.labelDesc = labelDesc;
    }

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public List<String> getStoreDesc() {
		return storeDesc;
	}

	public void setStoreDesc(List<String> storeDesc) {
		this.storeDesc = storeDesc;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getSimpleDesc() {
		return simpleDesc;
	}

	public void setSimpleDesc(String simpleDesc) {
		this.simpleDesc = simpleDesc;
	}

	public List<BrandStoryDetail> getStoryDetailList() {
		return storyDetailList;
	}

	public void setStoryDetailList(List<BrandStoryDetail> storyDetailList) {
		this.storyDetailList = storyDetailList;
	}

	public WxGoods getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(WxGoods goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getBrowerNum() {
		return browerNum;
	}

	public void setBrowerNum(Integer browerNum) {
		this.browerNum = browerNum;
	}

	public Integer getIsZan() {
		return isZan;
	}

	public void setIsZan(Integer isZan) {
		this.isZan = isZan;
	}

}
