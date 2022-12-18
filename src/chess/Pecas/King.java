package chess.Pecas;

import TabuleiroJogo.Tabuleiro;
import chess.Cor;
import chess.chessPeca;

public class King extends chessPeca {

	public King(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()] [getTabuleiro().getColunas()]; 
				return mat;
	}
}
