package com.bluekjg.admin.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 模块管理表
 * </p>
 *
 * @author Tim
 * @since 2018-09-14
 */
@TableName("t_evm_index_model")
public class IndexModel extends Model<IndexModel> {

    private static final long serialVersionUID = 1L;

	@TableId(value="ID", type= IdType.AUTO)
	private Integer id;
    /**
     * 模块名称
     */
	@TableField("MODEL_NAME")
	private String modelName;
    /**
     * 模块图标
     */
	@TableField("MODEL_IMG")
	private String modelImg;
    /**
     * 管理身份图标
     */
	@TableField("MODEL_STORE_IMG")
	private String modelStoreImg;
    /**
     * 跳转地址
     */
	@TableField("PAGE_URL")
	private String pageUrl;
    /**
     * 管理员跳转地址
     */
	@TableField("PAGE_STORE_URL")
	private String pageStoreUrl;
    /**
     * 类型(0消费者 1店员 默认0)
     */
	@TableField("TYPE")
	private Integer type;
    /**
     * 排序
     */
	@TableField("SEQ")
	private Integer seq;
    /**
     * 创建时间
     */
	@TableField("CREATED_TIME")
	private Date createdTime;
    /**
     * 是否删除 : 0-否 , 1-是
     */
	@TableField("IS_DEL")
	private Integer isDel;
    /**
     * 最后更改时间
     */
	@TableField("LAST_MODIFIED_TIME")
	private Date lastModifiedTime;
    /**
     * 备注
     */
	@TableField("NOTE")
	private String note;
	//openID
	@TableField(exist=false)
	private String openId;
	//所选择的权限模块ID集合(字符串形式)
	@TableField(exist=false)
	private String modelIds;
	@TableField(exist=false)
	private Integer modelId;
	@TableField(exist=false)
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getModelIds() {
		return modelIds;
	}

	public void setModelIds(String modelIds) {
		this.modelIds = modelIds;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelImg() {
		return modelImg;
	}

	public void setModelImg(String modelImg) {
		this.modelImg = modelImg;
	}

	public String getModelStoreImg() {
		return modelStoreImg;
	}

	public void setModelStoreImg(String modelStoreImg) {
		this.modelStoreImg = modelStoreImg;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getPageStoreUrl() {
		return pageStoreUrl;
	}

	public void setPageStoreUrl(String pageStoreUrl) {
		this.pageStoreUrl = pageStoreUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
