package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /*
     * @Author sirc_hzr
     * @Description  TODO 查询有最新回答的问题
     * @Date 10:51 2019/3/7
     * @Param 
     * @return 
     **/
    
    @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE id=problemid AND labelid = ? ORDER BY replytime DESC", nativeQuery = true)
	public Page<Problem> newlist(String labelid, Pageable pageable);

    /*
     * @Author sirc_hzr
     * @Description  TODO 查询回答数量最多的热门问题
     * @Date 10:52 2019/3/7
     * @Param 
     * @return 
     **/
    
    @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE id=problemid AND labelid = ? ORDER BY reply DESC", nativeQuery = true)
	public Page<Problem> hotlist(String labelid, Pageable pageable);

    /*
     * @Author sirc_hzr
     * @Description  TODO 查询待回答的问题（回答数为0）
     * @Date 10:53 2019/3/7
     * @Param 
     * @return 
     **/
    
     @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE id=problemid AND labelid = ? AND reply = '0' ORDER BY createtime DESC", nativeQuery = true)
	public Page<Problem> waitlist(String labelid, Pageable pageable);


}
