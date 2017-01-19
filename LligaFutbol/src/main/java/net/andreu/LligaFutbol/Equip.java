package net.andreu.LligaFutbol;

public class Equip {

	private String nom;
	private int victoria;
	private int derrota;
	private int empat;
	private int golsFavor;
	private int golsContra;
	
	public Equip(String nom) {
		this.nom = nom;
		
		victoria = 0;
		derrota = 0;
		empat = 0;
		
		golsFavor = 0;
		golsContra = 0;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getVictoria() {
		return victoria;
	}

	public int getDerrota() {
		return derrota;
	}

	public int getEmpat() {
		return empat;
	}

	public void setResultat(int golsF, int golsC){
		golsFavor += golsF;
		golsContra += golsC;
		if(golsF > golsC){
			victoria++;
		}else if(golsF < golsC){
			derrota++;
		}else if(golsF == golsC){
			empat++;
		}
	}
	
	public int calculaPunts() {
		int punts=0;

		punts+=victoria*3;
		punts+=empat;
		
		return punts;	
	}

	@Override
	public String toString() {
		return nom + ": V=" + victoria + ", D="
				+ derrota + ", E=" + empat + ", GF=" + golsFavor + ", GC=" + golsContra + ", P=" + calculaPunts() ;
	}
	
	
}
