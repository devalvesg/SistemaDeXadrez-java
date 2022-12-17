package Aplicacao;


import chess.chessPartida;

public class Programa {

	public static void main(String[] args) {
	
		//Tabuleiro tabuleiro = new Tabuleiro(8, 8);

		chessPartida chesspartida = new chessPartida();
		UI.PrintTabuleiro(chesspartida.getPecas());
	}
}
