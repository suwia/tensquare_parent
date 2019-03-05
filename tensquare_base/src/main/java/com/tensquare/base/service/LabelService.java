package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import com.tensquare.common.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: sirc_hzr
 * @Date: 2019/3/5 08:41
 * @ClassName: LabelService
 * @Version: 1.0
 * @Description:
 */

@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /*
     * @Author sirc_hzr
     * @Description  TODO 查找所有的标签
     * @Date 8:44 2019/3/5
     * @Param 
     * @return 
     **/
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /*
     * @Author sirc_hzr
     * @Description  TODO 根据id查找标签
     * @Date 8:46 2019/3/5
     * @Param 
     * @return 
     **/
    
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    /*
     * @Author sirc_hzr
     * @Description  TODO 增加新的标签
     * @Date 8:48 2019/3/5
     * @Param 
     * @return 
     **/
    
    public void add(Label label) {
        Long id = idWorker.nextId();
        label.setId(id.toString());
        labelDao.save(label);
    }

    /*
     * @Author sirc_hzr
     * @Description  TODO 更新标签信息
     * @Date 8:51 2019/3/5
     * @Param
     * @return
     **/

    public void update(Label label) {
        labelDao.save(label);
    }

    /*
     * @Author sirc_hzr
     * @Description  TODO 根据id删除标签
     * @Date 8:52 2019/3/5
     * @Param 
     * @return 
     **/
    
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

}
