package chess;

import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import chess.Pecas.King;
import chess.Pecas.Torre;

public class chessPartida {

	private Tabuleiro tabuleiro;
	
	public chessPartida() {
		tabuleiro = new Tabuleiro (8, 8);
		setupInicial();
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
	
	private void setupInicial() {
		tabuleiro.ColocarPecas(new Torre(tabuleiro, Cor.WHITE), new Posicao(2, 1) );
		tabuleiro.ColocarPecas(new King(tabuleiro, Cor.BLACK), new Posicao(0, 4) );
		tabuleiro.ColocarPecas(new King(tabuleiro, Cor.WHITE), new Posicao(7, 4) );
	}
}
