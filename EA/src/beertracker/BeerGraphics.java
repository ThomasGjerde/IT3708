package beertracker;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.BeerCellContent;
import model.Parameters;

public class BeerGraphics extends JPanel{
	BeerBoard currentBoard;
	int scale = 40;	
	public BeerGraphics(){
	
		JFrame frame = new JFrame();
		frame.setSize(Parameters.BT_SIZE_X*scale,Parameters.BT_SIZE_Y*scale);
		frame.setTitle("Agent");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		//currentBoard = board;
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0; i<Parameters.BT_SIZE_X; i++){
			for(int j=0; j<Parameters.BT_SIZE_Y; j++){
				g.setColor(Color.gray);
				g.fillRect(i*scale, j*scale, scale, scale);
				if(currentBoard.getCells()[i][j] == BeerCellContent.SENSOR){
					g.setColor(Color.blue);
					g.fillOval(i*scale, j*scale, scale, scale);
				}
				if(currentBoard.getCells()[i][j] == BeerCellContent.BLOCK){
					g.setColor(Color.red);
					g.fillOval(i*scale, j*scale, scale/2, scale/2);
				}
			}
		}
	}
	
	public void setBoard(BeerBoard board){
		currentBoard = board;
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//drawBoard();
		this.repaint();
	}
	
}
