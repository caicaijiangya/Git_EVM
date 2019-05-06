package com.bluekjg.admin.service.impl;

import com.bluekjg.admin.mapper.DictMapper;
import com.bluekjg.admin.model.DictData;
import com.bluekjg.admin.model.ParameterData;
import com.bluekjg.admin.service.IDictService;
import com.bluekjg.core.commons.result.PageInfo;
import com.bluekjg.core.commons.result.Tree;
import com.bluekjg.core.commons.utils.ConstantUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据字典表 服务实现类
 * </p>
 *
 * @author Max
 * @since 2018-03-30
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, DictData> implements IDictService {
	
	@Autowired 
    private DictMapper dictMapper;
	
	@Override
	public void queryDictList(PageInfo pageInfo, DictData dict) {
		Page<DictData> page = new Page<DictData>(pageInfo.getNowpage(), pageInfo.getSize());
		List<DictData> list = dictMapper.queryDictList(page, dict);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}

	@Override
	public void queryParameterList(PageInfo pageInfo, ParameterData param) {
		Page<ParameterData> page = new Page<ParameterData>(pageInfo.getNowpage(), pageInfo.getSize());
		List<ParameterData> list = dictMapper.queryParameterList(page, param);
		pageInfo.setRows(list);
		pageInfo.setTotal(page.getTotal());
	}
	
	@Override
	public boolean updateParam(ParameterData param) {
		return dictMapper.updateParam(param);
	}

	@Override
	public boolean addParam(ParameterData param) {
		return dictMapper.addParam(param);
	}

	@Override
	public ParameterData queryParameterById(Integer id) {
		return dictMapper.queryParameterById(id);
	}
	
	@Override
    public List<Tree> selectTree(String params) {
        List<DictData> dictList = dictMapper.queryDictByCode(params);
        List<Tree> trees = new ArrayList<Tree>();
        if (dictList == null) {
            return trees;
        }
        for (DictData dict : dictList) {
            Tree tree = new Tree();
            tree.setId(dict.getId());
            tree.setText(dict.getDictValue());
            tree.setPid(null);
            tree.setIconCls("glyphicon-globe icon-gray");
            tree.setState("open");
            trees.add(tree);
        }
        return trees;
    }

	@Override
	public Integer countDictNum(DictData wxappDict) {
		return dictMapper.countDictNum(wxappDict);
	}

	@Override
	public DictData queryDictByCode(String code) {
		List<DictData> list = dictMapper.queryDictByCode(code);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<DictData> queryDictByCodeList(String code) {
		// TODO Auto-generated method stub
		return dictMapper.queryDictByCode(code);
	}
}
