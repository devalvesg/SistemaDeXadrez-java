package chess;

import TabuleiroJogo.Tabuleiro;

public class chessPartida {

	private Tabuleiro tabuleiro;
	
	public chessPartida() {
		tabuleiro = new Tabuleiro (8, 8);
	}
	
	public chessPeca[][] getPecas(){
		chessPeca [] [] mat = new chessPeca[tabuleiro.getLinhas()] [tabuleiro.getColunas()];
		for(int i = 0; i <tabuleiro.getLinhas(); i++) {
			for(int j = 0; j <tabuleiro.getColunas(); j++) {
				mat[i][j] = (chessPeca) tabuleiro.pecas(i, j);
			}
		}
		return mat;
	}
	
}
