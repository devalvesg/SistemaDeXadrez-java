package chess;

import TabuleiroJogo.Pecas;
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
	
	public boolean [][] possiveisMovimentos(ChessPosicao sourcePosicao){
		Posicao posicao = sourcePosicao.toPosicao();
		validateSourcePosicao(posicao);
		return tabuleiro.pecas(posicao).possiveisMovimentos();
	}

	public chessPeca perfomeChessMove(ChessPosicao sourcePosicao, ChessPosicao targetPosicao) {
		Posicao source = sourcePosicao.toPosicao();
		Posicao target = targetPosicao.toPosicao();
		validateSourcePosicao(source);
		validateTargetPosicao(source, target);
		Pecas capturarPeca = fazerMover(source, target);
		return (chessPeca)capturarPeca;
	}
	
	private Pecas fazerMover(Posicao source, Posicao target) {
		Pecas p = tabuleiro.removePeca(source);
		Pecas capturarPecas = tabuleiro.removePeca(target);
		tabuleiro.ColocarPecas(p, target);
		return capturarPecas;
	}

	private void validateSourcePosicao(Posicao posicao) {
		if (!tabuleiro.TemUmaPeca(posicao)) {
			throw new ChessException("Não existe peça nessa posição");
		}
		if(!tabuleiro.pecas(posicao).esseMovimentoEPossivel()) {
			throw new ChessException("Não existe movimentos possiveis para essa peça");
		}
	}
	
	private void validateTargetPosicao(Posicao source, Posicao target) {
		if(!tabuleiro.pecas(source).possiveisMovimentos(target)) {
		throw new ChessException("A peça escolhida não pode se mover para posição de destino");
		}
	}

	private void ColocarNovaPeca(char coluna, int linha, chessPeca peca) {
		tabuleiro.ColocarPecas(peca, new ChessPosicao(coluna, linha).toPosicao());
	}
	
	private void setupInicial() {
		ColocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('d', 1, new King(tabuleiro, Cor.WHITE));

        ColocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('d', 8, new King(tabuleiro, Cor.BLACK));
	}
}

