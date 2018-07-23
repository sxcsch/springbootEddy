package cn.eddy.service.impl;

import cn.eddy.dao.DaoSupport;
import cn.eddy.service.CodeService;
import cn.eddy.utils.JBDC.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/7/23.
 */
@Service
public class CodeServiceImpl implements CodeService {
    @Resource(name = "daoSupport")
    private DaoSupport dao;
    @Override
    public String getRandomStr(int i) {
        /*
		* 通过id获取数据
		*/
        PageData pd = new PageData();
        pd.put("id", i);
        try {
            return (String)dao.findForObject("CodeMapper.findById", pd);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
