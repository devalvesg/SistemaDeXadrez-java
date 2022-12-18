package chess.Pecas;

import TabuleiroJogo.Posicao;
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

	private boolean podeMover(Posicao posicao) {
		chessPeca p = (chessPeca)getTabuleiro().pecas(posicao);
		return p == null || p.getCor() != getCor();
	}
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()] [getTabuleiro().getColunas()]; 
		
		Posicao p = new Posicao(0, 0);
		
		// ACIMA
		p.setValores(posicao.getLinha()-1, posicao.getColuna());
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()] [p.getColuna()] = true;
		}
		// ABAIXO
		p.setValores(posicao.getLinha()+1, posicao.getColuna());
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()] [p.getColuna()] = true;
		}
		// ESQUERDA
		p.setValores(posicao.getLinha(), posicao.getColuna()-1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()] [p.getColuna()] = true;
		}
		// DIREITA
		p.setValores(posicao.getLinha(), posicao.getColuna()+1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()] [p.getColuna()] = true;
		}
		// DIAGONAL ESQUERDA ALTA
		p.setValores(posicao.getLinha() -1, posicao.getColuna()-1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()] [p.getColuna()] = true;
		}
		// DIAGONAL DIREITA ALTA
		p.setValores(posicao.getLinha() -1, posicao.getColuna()+1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
		mat[p.getLinha()] [p.getColuna()] = true;
		}
		// DIAGONAL ESQUERDA BAIXA
		p.setValores(posicao.getLinha() +1, posicao.getColuna()-1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()] [p.getColuna()] = true;
		}
		// DIAGONAL DIREITA BAIXA
		p.setValores(posicao.getLinha() +1, posicao.getColuna()+1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()] [p.getColuna()] = true;
		}
		
		return mat;
	}
}
