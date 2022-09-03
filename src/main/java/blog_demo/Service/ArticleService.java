package blog_demo.Service;


import blog_demo.Entity.request.ArticleChangeModel;
import blog_demo.Entity.request.ArticleDeleteModel;
import blog_demo.Entity.request.ArticleUploadModel;
import blog_demo.Entity.request.ArticleViewModel;
import blog_demo.Entity.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	BaseResponse baseResponse = new BaseResponse();
	Integer SUCCESS_STATUS = 1;
	Integer DEFAULT_STATUS = 0;
	public BaseResponse upload(ArticleUploadModel articleUploadModel){//上传文章
		//上传
		String sql = String.format("insert into article_info (ArticleName,ArticleContent,ArticleTitle,ArticleAuthor) values('%s','%s','%s','%s')",
				articleUploadModel.getArticleName(),articleUploadModel.getArticleContent(),articleUploadModel.getArticleTitle(),articleUploadModel.getArticleAuthor());
		try {
			jdbcTemplate.execute(sql);
			baseResponse.setMessage("上传成功");
			baseResponse.setStatus(SUCCESS_STATUS);
		} catch (Exception e) {
			System.out.println(1);
			System.out.println(e);
			baseResponse.setMessage("上传失败");
			baseResponse.setStatus(DEFAULT_STATUS);
		}
		return baseResponse;
	}
	public BaseResponse delete(ArticleDeleteModel articleDeleteModel){//删除文章
		String sql = String.format("delete from article_info where ArticleID='%s'", articleDeleteModel.getArticleID());
		try {
			jdbcTemplate.execute(sql);
			baseResponse.setMessage("删除成功");
			baseResponse.setStatus(SUCCESS_STATUS);
		} catch (Exception e) {
			System.out.println(e);
			baseResponse.setMessage("删除失败");
			baseResponse.setStatus(DEFAULT_STATUS);
		}
		return baseResponse;
	}
	public BaseResponse change(ArticleChangeModel articleChangeModel){//修改文章信息
		BaseResponse baseResponse = new BaseResponse();
		String sql = "";
		if (articleChangeModel.getType().equals("ArticleName")) {
			sql += String.format("update article_info set ArticleName='%s' where ArticleID=%s",
					articleChangeModel.getParam(), articleChangeModel.getArticleID());
			System.out.println(sql);
		} else if (articleChangeModel.getType().equals("ArticleContent")) {
			sql += String.format("update article_info set ArticleContent='%s' where ArticleID=%s",
					articleChangeModel.getParam(), articleChangeModel.getArticleID());
		} else if (articleChangeModel.getType().equals("ArticleTitle")) {
			sql += String.format("update article_info set ArticleTitle='%s' where ArticleID=%s",
					articleChangeModel.getParam(), articleChangeModel.getArticleID());
		} else if (articleChangeModel.getType().equals("ArticleAuthor")) {
			sql += String.format("update article_info set ArticleAuthor='%s' where ArticleID=%s",
					articleChangeModel.getParam(), articleChangeModel.getArticleID());
		}else {
			baseResponse.setData(sql);
		}
		try {
			jdbcTemplate.execute(sql);
			String sql1 = String.format("select *from article_info where ArticleID=%s", articleChangeModel.getArticleID());
			List<Map<String, Object>> res = jdbcTemplate.queryForList(sql1);//查询结果以list类型返回，jdbcTemplate是一个工具类
			
			//List的增强for循环，也叫for each操作，用于遍历列表中的项
			System.out.println("修改后数据：");
			res.forEach((e) -> {
				//e是该List中的一个项，每次循环都对应一个项，如本次循环中，对应着List中的Map类型的项
				System.out.println(e);
			});
			baseResponse.setData(res);
			baseResponse.setMessage("修改成功");
			baseResponse.setStatus(SUCCESS_STATUS);
		} catch (Exception e) {
			baseResponse.setMessage("失败");
			baseResponse.setStatus(DEFAULT_STATUS);
		}
		return baseResponse;
	}
	public BaseResponse view(ArticleViewModel articleViewModel) {//查看文章
		BaseResponse baseResponse = new BaseResponse();
		String sql = String.format("select * from article_info where ArticleID = '%s'", articleViewModel.getArticleID());
		List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);//查询结果以list类型返回，jdbcTemplate是一个工具类
		//List的增强for循环，也叫for each操作，用于遍历列表中的项
		baseResponse.setData(res);
		baseResponse.setMessage("查找成功");
		baseResponse.setStatus(SUCCESS_STATUS);
		return baseResponse;
	}
}
