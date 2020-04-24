package encheres;

public enum EtatVente {
	EN_COURS("en cours"), TERMINE("termine");

	private String value;

	private EtatVente(String s) {
		this.value = s;
	}

	public String getValue() {
		return this.value;
	}

}
