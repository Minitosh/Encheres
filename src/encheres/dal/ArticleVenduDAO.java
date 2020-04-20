package encheres.dal;

import encheres.BusinessException;
import encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {
	public void insert(ArticleVendu articleVendu) throws BusinessException;
	public ArticleVendu select(String nom) throws BusinessException;
	public ArticleVendu select(int noCategorie) throws BusinessException;
	public void delete(int noArticleVendu) throws BusinessException;
}
