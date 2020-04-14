package encheres.bo;

import java.io.Serializable;
import java.util.Date;

public class Enchere implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date dateEnchere;
	private float montantEnchere;
	
	public Date getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public float getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(float montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	
	public Enchere(Date dateEnchere, float montantEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	
	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + "]";
	}
	
	
	
	
}
