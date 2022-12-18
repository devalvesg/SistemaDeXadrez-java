package chess.Pecas;

import TabuleiroJogo.Posicao;
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
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean [][] mat = new boolean[getTabuleiro().getLinhas()] [getTabuleiro().getColunas()]; 
		Posicao p = new Posicao(0, 0);
		
		//PARA CIMA
		p.setValores(posicao.getLinha()-1, posicao.getColuna());
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if(getTabuleiro().posicaoExiste(p) && existePecaDoOponente(p)) {
			mat [p.getLinha()] [p.getColuna()] = true;			
		}
		
		//ESQUERDA
		p.setValores(posicao.getLinha(), posicao.getColuna()-1);
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if(getTabuleiro().posicaoExiste(p) && existePecaDoOponente(p)) {
			mat [p.getLinha()] [p.getColuna()] = true;			
		}
		
		//DIREITA
		p.setValores(posicao.getLinha()+1, posicao.getColuna());
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if(getTabuleiro().posicaoExiste(p) && existePecaDoOponente(p)) {
			mat [p.getLinha()] [p.getColuna()] = true;			
		}
		
		//PARA BAIXO
		p.setValores(posicao.getLinha()+1, posicao.getColuna());
		while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if(getTabuleiro().posicaoExiste(p) && existePecaDoOponente(p)) {
			mat [p.getLinha()] [p.getColuna()] = true;			
		}
		return mat;
	}
}
