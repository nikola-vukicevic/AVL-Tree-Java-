package bsp;

public class Izvrsni {

	public static Stablo Inicijalizacija_1() {
		//int niz[] = {1, 9, 11, 12, 20, 31, 43, 51, 59, 63, 71, 84, 91, 98, 99};
		int niz[] = {51, 9, 1, 12, 20, 31, 43, 98, 59, 63, 99, 84, 91, 71, 11};
		int i, d  = niz.length;
		Stablo S  = new Stablo(niz[0]);
		
		for(i = 1; i < d; i++) {
			S.Koren = S.Dodavanje(S.Koren, niz[i]);
		}
		
		return S;
	}
	
	public static Stablo Inicijalizacija_2() {
		int i, pocetak = 1, kraj = 1000;
		Stablo S = new Stablo(pocetak);
		for(i = pocetak + 1; i <= kraj; i++) {
			S.Koren = S.Dodavanje(S.Koren, i);
		}
		
		return S;
	}
	
	public static void main(String[] args) {
		System.out.printf("ROTACIJE PRI DODAVANJU NOVIH ČVOROVA:\n\n");
		Stablo S1 = Inicijalizacija_1();
		int trazeni  = 98;
		int pretraga = S1.Pretraga(trazeni);
		
		if(pretraga != -1)
		{
			System.out.printf("\nTražena vrednost (%d) pronađena.\n", trazeni);
			System.out.printf("Broj koraka pretrage: %d\n\n--------\n\n", pretraga);
		}
		else
		{
			System.out.printf("\nTražena vrednost (%d) nije pronađena.\n\n", trazeni);
		}
		
		System.out.printf("Struktura stabla:\n\n");
		
		S1.BFS_Ispis();
	}
}
