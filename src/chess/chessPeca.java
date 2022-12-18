package chess;

import TabuleiroJogo.Pecas;
import TabuleiroJogo.Tabuleiro;

public abstract class chessPeca extends Pecas{

	private Cor cor;

	public chessPeca(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
}
