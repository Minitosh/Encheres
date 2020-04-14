package encheres.bo;

import java.io.Serializable;
import java.sql.Date;

public class ArticleVendu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private float miseAPrix;
	private float prixVente;
	private String etatVente;
	
	
	
	public int getNoArticle() {
		return noArticle;
	}



	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}



	public String getNomArticle() {
		return nomArticle;
	}



	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}



	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}



	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}



	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}



	public float getMiseAPrix() {
		return miseAPrix;
	}



	public void setMiseAPrix(float miseAPrix) {
		this.miseAPrix = miseAPrix;
	}



	public float getPrixVente() {
		return prixVente;
	}



	public void setPrixVente(float prixVente) {
		this.prixVente = prixVente;
	}



	public String getEtatVente() {
		return etatVente;
	}



	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	

	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, float miseAPrix, float prixVente, String etatVente) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description + ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix=" + miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente +"]";
	}
	
	
	
}
