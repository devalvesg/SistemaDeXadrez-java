package chess.Pecas;

import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import chess.Cor;
import chess.chessPeca;

public class Cavalo extends chessPeca {

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "C";
	}

	private boolean podeMover(Posicao posicao) {
		chessPeca p = (chessPeca)getTabuleiro().pecas(posicao);
		return p == null || p.getCor() != getCor();
	}
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()] [getTabuleiro().getColunas()]; 
		
		Posicao p = new Posicao(0, 0);
		
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()] [p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() -1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()] [p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()] [p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()] [p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()] [p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
		mat[p.getLinha()] [p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()] [p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()] [p.getColuna()] = true;
		}
		
		return mat;
	}
}