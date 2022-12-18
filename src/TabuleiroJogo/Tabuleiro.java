package TabuleiroJogo;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private Pecas [][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new TabuleiroException("Erro ao criar o tabuleiro: necessario que haja 1 linha e 1 coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Pecas[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Pecas pecas(int linhas, int colunas) {
		if(!posicaoExiste(linhas, colunas)) {
			throw new TabuleiroException("Posição não encontrada no tabuleiro");
		}
		return pecas [linhas] [colunas];
	}
	
	public Pecas pecas(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posição não encontrada no tabuleiro");
	}
		return pecas [posicao.getLinha()] [posicao.getColuna()];
	}
	
	public void ColocarPecas(Pecas peca, Posicao posicao) {
		if(TemUmaPeca(posicao)) {
			throw new TabuleiroException("Já existe uma peça na posição " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Pecas removePeca(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posição não encontrada no tabuleiro");
		}
		if (pecas(posicao) == null) {
			return null;
		}
		Pecas aux = pecas(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}
	
	private boolean posicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean TemUmaPeca(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new TabuleiroException("Posição não encontrada no tabuleiro");
		}
		return pecas(posicao) != null;
	}
}
