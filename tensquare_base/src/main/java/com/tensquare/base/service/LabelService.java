package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import com.tensquare.common.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;


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

    private Specification<Label> createSpecification(Label label) {
        return new  Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> list = new ArrayList<>();
                //判断标签名称不为空为其添加条件查询
                if(!StringUtils.isEmpty(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }

                //判断状态不为空为其添加查询条件
                if(!StringUtils.isEmpty(label.getState())) {
                    Predicate predicate = criteriaBuilder.like(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }

                //判断是否推荐不为空为其添加查询条件
                if(!StringUtils.isEmpty(label.getRecommend())) {
                    Predicate predicate = criteriaBuilder.like(root.get("recommend").as(String.class), label.getRecommend());
                    list.add(predicate);
                }

                Predicate[] parrm = new Predicate[list.size()];
                list.toArray(parrm);
                return criteriaBuilder.and(parrm);
            }

        };
    }

    /*
     * @Author sirc_hzr
     * @Description  TODO 根据条件查询
     * @Date 9:17 2019/3/6
     * @Param
     * @return
     **/
    public List<Label> findBySearch(Label label) {
        return labelDao.findAll(createSpecification(label));
    }


    /**
     * @Author sirc_hzr
     * @Description  TODO 根据条件+分页查询标签内容
     * @Date 10:53 2019/3/6
     * @Param [label]
     * @return org.springframework.data.domain.Page
     **/

    public Page findBySearch(Label label, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Specification<Label> specification = createSpecification(label);
        return labelDao.findAll(specification, pageRequest);
    }
}
