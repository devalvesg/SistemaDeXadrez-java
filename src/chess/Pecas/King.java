package chess.Pecas;

import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import chess.Cor;
import chess.chessPartida;
import chess.chessPeca;

public class King extends chessPeca {
	
	private chessPartida chesspartida;

	public King(Tabuleiro tabuleiro, Cor cor, chessPartida chesspartida) {
		super(tabuleiro, cor);
		this.chesspartida = chesspartida;
	}
	
	@Override
	public String toString() {
		return "K";
	}

	private boolean podeMover(Posicao posicao) {
		chessPeca p = (chessPeca)getTabuleiro().pecas(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testeTorreRoque(Posicao posicao) {
		chessPeca p = (chessPeca)getTabuleiro().pecas(posicao);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContagemMovimentos() == 0;
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
		
		//MOVIMENTO ESPECIAL ROQUE
		if(getContagemMovimentos() == 0 && !chesspartida.getCheck()) {
			//Roque do lado do Rei
			Posicao posiT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if(testeTorreRoque(posiT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if(getTabuleiro().pecas(p1) == null && getTabuleiro().pecas(p2) == null) {
					mat[posicao.getLinha()] [posicao.getColuna() + 2] = true;
				}
				
			}
			//Roque do lado da Rainha
			Posicao posiT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			if(testeTorreRoque(posiT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if(getTabuleiro().pecas(p1) == null && getTabuleiro().pecas(p2) == null && getTabuleiro().pecas(p3) == null) {
					mat[posicao.getLinha()] [posicao.getColuna() - 2] = true;
				}
			
			
			}
		}
		
		return mat;
	}
}
