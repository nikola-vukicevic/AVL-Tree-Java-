package bsp;

public class Cvor {
	public int  Vrednost;
	public Cvor Levi,
	            Desni;
	public int  VisinaLevi,
	            VisinaDesni,
	            Visina,
	            BalansFaktor;
	
	public Cvor(int Vrednost) {
		this.Vrednost     = Vrednost;
		this.Levi         = null;
		this.Desni        = null;
		this.Visina       = 1;
		this.BalansFaktor = 0;
	}
}
