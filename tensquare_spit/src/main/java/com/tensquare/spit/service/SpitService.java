package com.tensquare.spit.service;

import com.tensquare.common.utils.IdWorker;
import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: sirc_hzr
 * @Date: 2019/3/25 15:22
 * @ClassName: SpitService
 * @Version: 1.0
 * @Description:
 */
@Service
@Transactional
public class SpitService {
    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    //查询全部
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    //根据主键id查询
    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    //添加
    public void save(Spit spit) {
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date());//发布日
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        if(!StringUtils.isEmpty(spit.getParentid())) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        spitDao.save(spit);
    }

    //修改
    public void update(Spit spit) {
        spitDao.save(spit);
    }

    //删除
    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    //根据父id进行分页查询
    public Page<Spit> findByParentId(String parentId, int page, int size) {
        return spitDao.findByParentid(parentId, new PageRequest(page - 1, size));
    }

    //点赞数加一
    public void thumbup(String spitId) {


        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");

    }
}
