package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    /*
     * @Author sirc_hzr
     * @Description  TODO 文章审核
     * @Date 9:12 2019/3/11
     * @Param
     * @return
     **/

    @Modifying
    @Query(value = "UPDATE tb_article SET state=1 WHERE id=?", nativeQuery = true)
    public void updateState(String id);

    /*
     * @Author sirc_hzr
     * @Description  TODO 增加文章点赞
     * @Date 9:14 2019/3/11
     * @Param
     * @return
     **/

    @Modifying
    @Query(value = "UPDATE tb_article SET thumbup=thumbup+1 WHERE id=?", nativeQuery = true)
    public void addThumbup(String id);
}
