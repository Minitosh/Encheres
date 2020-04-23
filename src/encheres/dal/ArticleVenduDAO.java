package encheres.dal;

import java.util.List;

import encheres.BusinessException;
import encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {
	public void insert(ArticleVendu articleVendu) throws BusinessException;

	public ArticleVendu selectByNoArticleVendu(int noArticleVendu) throws BusinessException;

	public ArticleVendu selectByNom(String nom) throws BusinessException;

	public List<ArticleVendu> selectAll() throws BusinessException;

	public List<ArticleVendu> selectAllByCategorie(int noCategorie) throws BusinessException;

	public List<ArticleVendu> selectAllByUtilisateur(int noUtilisateur) throws BusinessException;

	public void delete(int noArticleVendu) throws BusinessException;

	public void majPrix(int idArticle, int montant) throws BusinessException;
}
