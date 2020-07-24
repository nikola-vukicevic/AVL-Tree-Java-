package bsp;

import java.util.*;

public class Stablo {
	public Cvor Koren;
	private int Brojac;
	
	public Stablo() {
		this.Koren  = null;
		this.Brojac = 0;
	}
	
	public Stablo(int Vrednost) {
		this.Koren  = new Cvor(Vrednost);
		this.Brojac = 0;
	}
	
	public void AzuriranjeVisine(Cvor C) {
		
		if (C == null) return;
				
		if(C.Levi == null && C.Desni == null) {
			C.Visina = 1;
			return;
		}
		
		if(C.Levi == null) {
			C.Visina = C.Desni.Visina + 1;
			return;	
		}

		if(C.Desni == null) {
			C.Visina = C.Levi.Visina + 1;
			return;
		}
		
		C.Visina = (C.Levi.Visina >= C.Desni.Visina)? C.Levi.Visina + 1 : C.Desni.Visina + 1;
	}
	
	public void AzuriranjeBalansFaktora(Cvor C) {
		if(C == null) return;
		
		if(C.Levi == null && C.Desni == null) {
			C.BalansFaktor = 0;
			return;
		}
		
		if(C.Levi == null) {
			C.BalansFaktor = -C.Desni.Visina;
			return;
		}
		
		if(C.Desni == null) {
			C.BalansFaktor = C.Levi.Visina;
			return;
		}
		
		C.BalansFaktor = C.Levi.Visina - C.Desni.Visina;
	}
	
	public Cvor Rotacija_LL(Cvor C) {
		Cvor B  = C.Levi;
		C.Levi  = B.Desni;
		B.Desni = C;
		System.out.printf("LL(%d)\n", C.Vrednost);
		AzuriranjeVisine(C);
		AzuriranjeBalansFaktora(C);
		AzuriranjeVisine(B);
		AzuriranjeBalansFaktora(B);
		return B;
	}
	
	public Cvor Rotacija_LD(Cvor C) {
		Cvor P       = new Cvor(0);
		Cvor B       = C.Levi.Desni;
		P.Levi       = B.Levi; 
		P.Desni      = B.Desni;
		B.Levi       = C.Levi;
		B.Desni      = C;
		B.Levi.Desni = P.Levi;
		B.Desni.Levi = P.Desni;
		
		System.out.printf("LD(%d)\n", C.Vrednost);
		AzuriranjeVisine(C);
		AzuriranjeBalansFaktora(C);
		AzuriranjeVisine(B.Levi);
		AzuriranjeBalansFaktora(B.Levi);
		AzuriranjeVisine(B);
		AzuriranjeBalansFaktora(B);
		return B;
	}
	
	public Cvor Rotacija_DD(Cvor C) {
		Cvor B  = C.Desni;
		C.Desni = B.Levi;
		B.Levi  = C;
		System.out.printf("DD(%d)\n", C.Vrednost);
		AzuriranjeVisine(C);
		AzuriranjeBalansFaktora(C);
		AzuriranjeVisine(B);
		AzuriranjeBalansFaktora(B);
		return B;
	}
		
	public Cvor Rotacija_DL(Cvor C) {
		Cvor P       = new Cvor(0);
		Cvor B       = C.Desni.Levi;
		P.Levi       = B.Levi;
		P.Desni      = B.Desni;
		B.Desni      = C.Desni; 
		B.Levi       = C;
		B.Desni.Levi = P.Desni;
		B.Levi.Desni = P.Levi;
		
		System.out.printf("DL(%d)\n", C.Vrednost);
		AzuriranjeVisine(C);
		AzuriranjeBalansFaktora(C);
		AzuriranjeVisine(B.Desni);
		AzuriranjeBalansFaktora(B.Desni);
		AzuriranjeVisine(B);
		AzuriranjeBalansFaktora(B);
		return B;
	}

	public Cvor Dodavanje(Cvor C, int Vrednost) {
		
		// Ako je rekurzija "pronasla" poziciju gde moze biti dodat novi cvor ....
		
		if(C == null) return new Cvor(Vrednost);
		
		// Ako nije, trazimo poziciju za novi cvor ....
		
		if(Vrednost < C.Vrednost)
		{
			C.Levi = Dodavanje(C.Levi, Vrednost);
		}
		else
		{
			if(Vrednost > C.Vrednost)
			{
				C.Desni = Dodavanje(C.Desni, Vrednost);
			}
			else
			{
				return C;
			}
		}
		
		// Posle rekurzije sledi azuriranje Visine i azuriranje Balans Faktora ....
		
		AzuriranjeVisine(C);
		AzuriranjeBalansFaktora(C);
		
		/* ---------------------------------------- */
		/* ROTACIJE U SLUCAJU DISBALANSA            */
		/* ---------------------------------------- */
		
		if(C.BalansFaktor > 1  && Vrednost < C.Levi.Vrednost)  return Rotacija_LL(C);  // LL:
		
		if(C.BalansFaktor > 1  && Vrednost > C.Levi.Vrednost)  return Rotacija_LD(C);  // LD
		
		if(C.BalansFaktor < -1 && Vrednost > C.Desni.Vrednost) return Rotacija_DD(C);  // DD
		
		if(C.BalansFaktor < -1 && Vrednost < C.Desni.Vrednost) return Rotacija_DL(C);  // DL
		
		// Ako nije bilo potrebe za balansiranjem ....
		
		return C; // Samo vratimo pokazivac na dati cvor. 
	}
	
	public int Pretraga(int Vrednost) {
		Cvor C = this.Koren;
		Brojac = 0;		
		
		while(true) {
			Brojac++;
			
			if(Vrednost == C.Vrednost) {
				return Brojac;				
			}
			
			if(Vrednost < C.Vrednost) {
				if(C.Levi == null) {
					return -1;
				}
				else {
					C = C.Levi;
					continue;
				}
			}
			
			if(Vrednost > C.Vrednost) {
				if(C.Desni == null) {
					return -1;
				}
				else {
					C = C.Desni;
					continue;
				}
			}
		}
	}
	
	public void BFS_Ispis() {
		Queue<Cvor> red     = new LinkedList<Cvor>(),
				    sledeci = new LinkedList<Cvor>(), pom;
		boolean reseno = false;
		
		red.add(Koren);
		
		while(!reseno) {
			while(!red.isEmpty()) {
				Cvor C = red.remove();
				System.out.printf("%d(%d %d) ", C.Vrednost, C.Visina, C.BalansFaktor);
				if(C.Levi  != null) sledeci.add(C.Levi);
				if(C.Desni != null) sledeci.add(C.Desni);
			}
			
			System.out.printf("\n");
			
			pom = red;
			red = sledeci;
			sledeci = pom;
			
			reseno = red.isEmpty() && sledeci.isEmpty();
		}
	}
}
