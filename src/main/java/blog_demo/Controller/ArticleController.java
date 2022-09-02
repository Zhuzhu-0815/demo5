package blog_demo.Controller;

import blog_demo.Entity.request.ArticleChangeModel;
import blog_demo.Entity.request.ArticleDeleteModel;
import blog_demo.Entity.request.ArticleUploadModel;
import blog_demo.Entity.request.ArticleViewModel;
import blog_demo.Entity.response.BaseResponse;
import blog_demo.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aaa")
public class ArticleController {
	@Autowired
	ArticleService articleService;
	@RequestMapping("/bbb")//上传文章
	public BaseResponse Upload(@RequestBody ArticleUploadModel articleUploadModel){
		return articleService.upload(articleUploadModel);
	}
	@RequestMapping("/ccc")//删除文章
	public BaseResponse Delete(@RequestBody ArticleDeleteModel articleDeleteModel){
		return articleService.delete(articleDeleteModel);
	}
	@RequestMapping("/ddd")//修改文章
	public BaseResponse Change(@RequestBody ArticleChangeModel articleChangeModel){
		return articleService.change(articleChangeModel);
	}
	@RequestMapping("/eee")//查看文章
	public BaseResponse View(@RequestBody ArticleViewModel articleViewModel){
		return articleService.view(articleViewModel);
	}
}
