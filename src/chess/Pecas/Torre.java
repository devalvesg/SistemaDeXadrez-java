package chess.Pecas;

import TabuleiroJogo.Tabuleiro;
import chess.Cor;
import chess.chessPeca;

public class Torre extends chessPeca{

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "T";
	}

	
}