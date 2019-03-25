package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: sirc_hzr
 * @Date: 2019/3/25 15:21
 * @ClassName: SpitDao
 * @Version: 1.0
 * @Description:
 */
public interface SpitDao extends MongoRepository<Spit, String> {
    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
