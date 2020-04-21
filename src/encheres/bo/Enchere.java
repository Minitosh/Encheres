package encheres.bo;

import java.io.Serializable;
import java.sql.Date;

public class Enchere implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date dateEnchere;
	private int montantEnchere;
	private int noUtilisateur;
	private int noArticle;
	
	public Date getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public float getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public Enchere() {
		
	}
	
	public Enchere(Date dateEnchere, int montantEnchere, int noUtilisateur, int noArticle) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
	}
	
	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", noUtilisateur=" + noUtilisateur + ", noArticle=" + noArticle + "]";
	}
	
	
	
	
}
