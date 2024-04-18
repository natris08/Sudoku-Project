package poo.sudoku;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Sudoku extends Backtracking<PuntoDiScelta,Integer>{

	private int[][] tabellaSudoku;
	protected int nrSoluzioni = 0;
	
	
	public Sudoku() {
		tabellaSudoku = new int[9][9];
		for(int i = 0; i<tabellaSudoku.length; i++)
			for(int j = 0; j<tabellaSudoku[0].length; j++)
				tabellaSudoku[i][j] = 0;
	}//COSTRUTTORE (inizializzo, come da richiesta, la matrice direttamente quadrata e con dimensione 9)
	
	private void imposta(int i, int j, int v) {
		tabellaSudoku[i][j] = v;
	}//IMPOSTA
	
	@Override
	protected List<PuntoDiScelta> puntiDiScelta(){
		ArrayList<PuntoDiScelta> ps = new ArrayList<PuntoDiScelta>();
		for(int i = 0; i<tabellaSudoku.length; i++)
			for(int j = 0; j<tabellaSudoku.length; j++)
			{
				PuntoDiScelta p = new PuntoDiScelta(i,j);
				ps.add(p);
			}
		return ps;
	}//PUNTI DI SCELTA
	
	@Override
	protected Collection<Integer> scelte (PuntoDiScelta p){
		List<Integer> s = new ArrayList<Integer>();
		for(int i = 1; i<=9; i++)
			s.add(i);
		return s;
	}//SCELTE
	
	@Override
	protected boolean assegnabile(PuntoDiScelta p, Integer s) {
		if(s<1 || s >9) throw new IllegalStateException();
		int riga = p.getRiga();
		int colonna = p.getColonna();
		
		for(int j = 0; j<tabellaSudoku[0].length; j++) {
			if(tabellaSudoku[riga][j] == s)
				return false;
		}
		for (int i = 0; i<tabellaSudoku.length; i++) {
			if(tabellaSudoku[i][colonna] == s)
				return false;
		}
		if(!esisteInSottogriglia(riga, colonna, s))
			return true;
		return false;
	}//ASSEGNABILE
	
	private boolean esisteInSottogriglia(int riga, int colonna, int v) {
		int indiceRiga = 0;
		int indiceColonna = 0;
		
		if(riga <= 2 && colonna <= 2);
		else if(riga <= 2 && colonna <= 5) {
			indiceColonna = 3;
		}
		else if(riga <= 2 && colonna <= 8) {
			indiceColonna = 6;
		}
		
		else if(riga <= 5 && colonna <= 2) {
			indiceRiga = 3;
		}
		else if(riga <= 5 && colonna <= 5) {
			indiceRiga = 3;
			indiceColonna = 3;
		}
		else if(riga <= 5 && colonna <= 8) {
			indiceRiga = 3;
			indiceColonna = 6;
		}
		
		else if(riga <= 8 && colonna <= 2) {
			indiceRiga = 6;
		}
		else if(riga <= 8 && colonna <= 5) {
			indiceRiga = 6;
			indiceColonna = 3;
		}
		else if(riga <= 8 && colonna <= 8) {
			indiceRiga = 6;
			indiceColonna = 6;
		}
		
		for(int i = indiceRiga; i<(indiceRiga + 3); i++)
			for(int j = indiceColonna; j < (indiceColonna + 3); j++ ) {
				if(tabellaSudoku[i][j] == v)
					return true;
			}
		return false;
	}//VERIFICO SE IL VALORE GIA' ESISTE ALL'INTERNO DI UNA SOTTOGRIGLIA
	
	@Override
	protected void assegna(PuntoDiScelta ps, Integer s) {
		if(s<1 || s >9) throw new IllegalStateException();
		imposta(ps.getRiga(), ps.getColonna(), s);
	}//ASSEGNA

	@Override
	protected void deassegna(PuntoDiScelta ps, Integer s) {
		tabellaSudoku[ps.getRiga()][ps.getColonna()] =  0;
	}//DEASSEGNA

	@Override
	protected void scriviSoluzione( PuntoDiScelta p) {
		nrSoluzioni++;
		System.out.print(nrSoluzioni+": ");
		for (int i=0; i<tabellaSudoku.length; i++) {
			for (int j=0; j<tabellaSudoku.length; j++) {
				System.out.println("< "+i+", "+j+" >: "+tabellaSudoku[i][j]);
			}
		}
	}//SCRIVI SOLUZIONE

	@Override
	protected boolean esisteSoluzione(PuntoDiScelta p) {
		if(p.getRiga() == tabellaSudoku.length-1 && p.getColonna() == tabellaSudoku[0].length-1)
			return true;
		return false;
	}//ESISTE SOLUZIONE

	@Override
	protected boolean ultimaSoluzione( PuntoDiScelta p ) {
		if(nrSoluzioni == 10)
			return true;
		return false;
	}//ULTIMA SOLUZIONE
	
	
	@Override
	protected void risolvi() {
		tentativo(puntiDiScelta(), new PuntoDiScelta(0,0));
	}//RISOLVI
	
	public static void main(String[] args) {
		new Sudoku().risolvi();
	}//MAIN

}//SUDOKU