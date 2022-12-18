package Aplicacao;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessPosicao;
import chess.chessPartida;
import chess.chessPeca;

public class Programa {

	public static void main(String[] args) {
	
		//Tabuleiro tabuleiro = new Tabuleiro(8, 8);
		Scanner entrada = new Scanner(System.in);
		chessPartida chesspartida = new chessPartida();
		List<chessPeca> captured = new ArrayList<>();
		
		while(true) {
			try {
				UI.clearScreen();
				UI.PrintPartida(chesspartida, captured);
				System.out.println();
				System.out.print("Origem: ");
				ChessPosicao source = UI.readChessPosicao(entrada);
				
				boolean [][] possiveisMovimentos = chesspartida.possiveisMovimentos(source);
				UI.clearScreen();
				UI.PrintTabuleiro(chesspartida.getPecas(), possiveisMovimentos);
				
				System.out.println();
				System.out.print("Destino: ");
				ChessPosicao target = UI.readChessPosicao(entrada);
				
				chessPeca capturarPeca = chesspartida.perfomeChessMove(source, target);
				if(capturarPeca != null) {
					captured.add(capturarPeca);
				}
			}
			catch(ChessException e) {
				System.out.println(e.getMessage());
				entrada.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				entrada.nextLine();
			}
		}
	}
}
