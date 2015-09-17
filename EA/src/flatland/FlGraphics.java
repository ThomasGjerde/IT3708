package flatland;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.CellContent;
import model.Direction;
import model.Parameters;

public class FlGraphics extends JPanel{
	Board currentBoard;
	int scale = 40;
	ChangeListener delaySliderListener;
	public FlGraphics(){
	
		JFrame frame = new JFrame();
		frame.setSize(Parameters.FL_MAPSIZE*scale+20,Parameters.FL_MAPSIZE*scale+40);
		frame.setTitle("Agent");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		//currentBoard = board;
		
		final JSlider delaySlider = new JSlider(JSlider.HORIZONTAL, 0,200,50);
		delaySlider.setPaintTicks(true);
		delaySlider.setMajorTickSpacing(25);
		delaySlider.setMinorTickSpacing(5);
		delaySlider.setPaintLabels(true);
		
		JFrame frame2  = new JFrame();
		frame2.setSize(300,75);
		frame2.setLocation(frame.getWidth(), 0);
		frame2.setTitle("Slider window");
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.add(delaySlider);
		
		delaySliderListener = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				Parameters.DELAY = delaySlider.getValue();
				//System.out.println("Parameters.Delay " + Parameters.DELAY);
				
			}
		};
		
		delaySlider.addChangeListener(delaySliderListener);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0; i<Parameters.FL_MAPSIZE; i++){
			for(int j=0; j<Parameters.FL_MAPSIZE; j++){
				//g.fillRect(x, y, width, height);
				g.setColor(Color.gray);
				g.fillRect(i*scale, j*scale, scale, scale);
				if(currentBoard.getCells()[i][j] == CellContent.AGENT){
					g.setColor(Color.blue);

					//g.fillOval(x, y, width, height);
					g.fillOval(i*scale, j*scale, scale, scale);
					g.setColor(Color.black);
					if(currentBoard.getAgent().getOrientation() == Direction.UP){
						drawArrowLine(g, i*scale +20, j*scale +20, i*scale+20, j*scale+10, 10, 10);
					}else if(currentBoard.getAgent().getOrientation() == Direction.DOWN){
						drawArrowLine(g, i*scale +20, j*scale +10, i*scale+20, j*scale+20, 10, 10);
					}else if(currentBoard.getAgent().getOrientation() == Direction.LEFT){
						drawArrowLine(g, i*scale +20, j*scale +20, i*scale+10, j*scale+20, 10, 10);
					}else if(currentBoard.getAgent().getOrientation() == Direction.RIGHT){
						drawArrowLine(g, i*scale +10, j*scale +20, i*scale+20, j*scale+20, 10, 10);
					}
				}
				if(currentBoard.getCells()[i][j] == CellContent.FOOD){
					g.setColor(Color.red);
					g.fillOval(i*scale, j*scale, scale/2, scale/2);
				}
				if(currentBoard.getCells()[i][j] == CellContent.POISON){
					g.setColor(Color.green);
					g.fillOval(i*scale, j*scale, scale/2, scale/2);
				}
			}
		}
	}
	/**
     * Draw an arrow line betwwen two point 
     * @param g the graphic component
     * @param x1 x-position of first point
     * @param y1 y-position of first point
     * @param x2 x-position of second point
     * @param y2 y-position of second point
     * @param d  the width of the arrow
     * @param h  the height of the arrow
     */
    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h){
       int dx = x2 - x1, dy = y2 - y1;
       double D = Math.sqrt(dx*dx + dy*dy);
       double xm = D - d, xn = xm, ym = h, yn = -h, x;
       double sin = dy/D, cos = dx/D;

       x = xm*cos - ym*sin + x1;
       ym = xm*sin + ym*cos + y1;
       xm = x;

       x = xn*cos - yn*sin + x1;
       yn = xn*sin + yn*cos + y1;
       xn = x;

       int[] xpoints = {x2, (int) xm, (int) xn};
       int[] ypoints = {y2, (int) ym, (int) yn};

       g.drawLine(x1, y1, x2, y2);
       g.fillPolygon(xpoints, ypoints, 3);
    }
	public void setBoard(Board board){
		currentBoard = board;
		try {
			Thread.sleep(Parameters.DELAY);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//drawBoard();
		this.repaint();
	}
	/*
	public void drawBoard(){
		currentBoard.getCells();
		for(int i=0; i<Parameters.FL_MAPSIZE; i++){
			for(int j=0; j<Parameters.FL_MAPSIZE; j++){
				
				//this.repaint();
			}
		}
	}*/
	
}
