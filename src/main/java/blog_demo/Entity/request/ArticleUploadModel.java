package blog_demo.Entity.request;

import lombok.Data;

@Data
public class ArticleUploadModel {
	public String ArticleName;
	public String ArticleContent;
	public String ArticleTitle;
	public String ArticleAuthor;
}
