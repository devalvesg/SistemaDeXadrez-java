package chess;

import TabuleiroJogo.Pecas;
import TabuleiroJogo.Posicao;
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
	
	public ChessPosicao getChessPosicao() {
		return ChessPosicao.fromPosicao(posicao);
	}
	
	protected boolean existePecaDoOponente (Posicao posicao) {
		chessPeca p = (chessPeca)getTabuleiro().pecas(posicao);
		return p != null && p.getCor() != cor;
	}
}
