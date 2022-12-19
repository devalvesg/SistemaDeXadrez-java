package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import TabuleiroJogo.Pecas;
import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import chess.Pecas.Bispo;
import chess.Pecas.King;
import chess.Pecas.Peao;
import chess.Pecas.Torre;

public class chessPartida {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	
	
	private List<Pecas> pecasNoTabuleiro = new ArrayList<>();
	private List<Pecas> pecasCapturadas = new ArrayList<>();
	
	public chessPartida() {
		tabuleiro = new Tabuleiro (8, 8);
		turno = 1;
		jogadorAtual = Cor.WHITE;
		setupInicial();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public boolean getCheck() {
		return check;
	}
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
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
		if(testeCheck(jogadorAtual)) {
			desfazerMovimento(source, target, capturarPeca);
			throw new ChessException("Você não pode se colocar em check");
		}
		
		check = (testeCheck(oponente(jogadorAtual))) ? true : false;
		
		if(testeCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}
		else {
			proximoTurno();
		}
		
		return (chessPeca)capturarPeca;
	}
	
	private Pecas fazerMover(Posicao source, Posicao target) {
		chessPeca p = (chessPeca)tabuleiro.removePeca(source);
		p.increaseContagemMovimentos();
		Pecas capturarPecas = tabuleiro.removePeca(target);
		tabuleiro.ColocarPecas(p, target);
		
		if(capturarPecas != null) {
			pecasNoTabuleiro.remove(capturarPecas);
			pecasCapturadas.add(capturarPecas);
		}
		return capturarPecas;
	}
	
	private void desfazerMovimento(Posicao source, Posicao target, Pecas capPecas) {
		chessPeca p = (chessPeca)tabuleiro.removePeca(target);
		p.decreaseContagemMovimentos();
		tabuleiro.ColocarPecas(p, source);
		
		if (capPecas != null) {
			tabuleiro.ColocarPecas(capPecas, target);
			pecasCapturadas.remove(capPecas);
			pecasNoTabuleiro.add(capPecas);
		}
	}

	private void validateSourcePosicao(Posicao posicao) {
		if (!tabuleiro.TemUmaPeca(posicao)) {
			throw new ChessException("Não existe peça nessa posição");
		}
		if(jogadorAtual != ((chessPeca)tabuleiro.pecas(posicao)).getCor()) {
			throw new ChessException("A peça escolhida não é sua");
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
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}

	private Cor oponente(Cor cor) {
		return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	private chessPeca king (Cor cor) {
		List<Pecas> lista = pecasNoTabuleiro.stream().filter(x -> ((chessPeca)x).getCor() == cor).collect(Collectors.toList());
		for(Pecas p: lista) {
			if(p instanceof King) {
				return (chessPeca)p;
			}
		}
		throw new IllegalStateException("Não existe o rei " + cor + " no tabuleiro");
	}
	
	private boolean testeCheck(Cor cor) {
		Posicao kingPosicao = king(cor).getChessPosicao().toPosicao();
		List<Pecas> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((chessPeca)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for(Pecas p:pecasOponente) {
			boolean [][] mat = p.possiveisMovimentos();
			if(mat[kingPosicao.getLinha()][kingPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testeCheckMate(Cor cor) {
		if (!testeCheck(cor)) {
			return false;
		}
		List<Pecas> lista = pecasNoTabuleiro.stream().filter(x ->((chessPeca)x).getCor() == cor).collect(Collectors.toList());
		for (Pecas p:lista) {
			boolean [][] mat = p.possiveisMovimentos();
			for(int i = 0; i < tabuleiro.getLinhas(); i++) {
				for(int j = 0; j < tabuleiro.getColunas(); j++) {
					if(mat[i][j]) {
						Posicao source = ((chessPeca)p).getChessPosicao().toPosicao();
						Posicao target = new Posicao(i, j);
						Pecas capturarPecas = fazerMover(source, target);
						boolean testCheck = testeCheck(cor);
						desfazerMovimento(source, target, capturarPecas);
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void ColocarNovaPeca(char coluna, int linha, chessPeca peca) {
		tabuleiro.ColocarPecas(peca, new ChessPosicao(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	private void setupInicial() {
		
		ColocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.WHITE));
		ColocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('e', 1, new King(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.WHITE));
        ColocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.WHITE));

        ColocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('e', 8, new King(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.BLACK));
        ColocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.BLACK));
	}
}

