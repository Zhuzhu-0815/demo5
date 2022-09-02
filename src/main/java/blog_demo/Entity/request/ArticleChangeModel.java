package blog_demo.Entity.request;

import lombok.Data;

@Data
public class ArticleChangeModel {
	private String type;
	private Integer articleID;
	private String param;
}
