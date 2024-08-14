package com.blog.mapper;

import com.blog.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.pojo.SaveArticle;
import com.blog.pojo.SelectArticle;
import com.blog.pojo.ShowArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 86155
* @description 针对表【article(文章)】的数据库操作Mapper
* @createDate 2024-08-09 11:05:18
* @Entity com.blog.pojo.Article
*/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<SelectArticle> load();

    List<SelectArticle> selectByUserId(Long id);

    SaveArticle selectByArticleId(Long id);

    ShowArticle showArticle(Long id);

    Integer countComment(Long id);

    Integer deleteByArticleId(Long id);


    int updateArticle(com.blog.entity.Article article);

    int saveArticle(com.blog.entity.Article article);

}




