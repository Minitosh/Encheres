package encheres.dal;

import encheres.BusinessException;
import encheres.bo.Retrait;

public interface RetraitDAO {

	public void insert(Retrait retrait) throws BusinessException;
	public Retrait select(int noArticle) throws BusinessException;
	public void delete(int noArticle) throws BusinessException;
}
