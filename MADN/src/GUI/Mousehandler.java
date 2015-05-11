package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Mousehandler implements MouseListener{
	
	
	private HashMap<String, JButton> naviMap = null;
	
	public Mousehandler(HashMap<String, JButton> naviMap){
		if (naviMap!=null)
			this.naviMap= naviMap;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == naviMap.get("diceGame")) {

//			naviMap.get("diceGame").setIcon(arg0);
		}

		if (e.getSource() == naviMap.get("startGame")) {
		}
		if (e.getSource() == naviMap.get("newGame")) {

		}

		if (e.getSource() == naviMap.get("saveGame")) {

		}

		if (e.getSource() == naviMap.get("loadGame")) {

		}

		if (e.getSource() == naviMap.get("sendGame")) {

		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == naviMap.get("diceGame")) {

		}

		if (e.getSource() == naviMap.get("startGame")) {
		}
		if (e.getSource() == naviMap.get("newGame")) {

		}

		if (e.getSource() == naviMap.get("saveGame")) {

		}

		if (e.getSource() == naviMap.get("loadGame")) {

		}

		if (e.getSource() == naviMap.get("sendGame")) {

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == naviMap.get("diceGame")) {
			ImageIcon ii= new ImageIcon("images/rollDicePressed.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("diceGame").setIcon(ii);
			
		}

		if (e.getSource() == naviMap.get("startGame")) {
			ImageIcon ii= new ImageIcon("images/StartGamePressed.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("startGame").setIcon(ii);
			
		}
		if(e.getSource()==naviMap.get("endGame")){
			ImageIcon ii= new ImageIcon("images/EndGamePressed.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("endGame").setIcon(ii);
		}
		if (e.getSource() == naviMap.get("newGame")) {
			ImageIcon ii= new ImageIcon("images/NewGamePressed.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("newGame").setIcon(ii);
			

		}

		if (e.getSource() == naviMap.get("saveGame")) {
			ImageIcon ii= new ImageIcon("images/SaveGamePressed.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("saveGame").setIcon(ii);
			

		}

		if (e.getSource() == naviMap.get("loadGame")) {
			ImageIcon ii= new ImageIcon("images/LoadGamePressed.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("loadGame").setIcon(ii);
			

		}

		if (e.getSource() == naviMap.get("sendGame")) {
			ImageIcon ii= new ImageIcon("images/SendGamePressed.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("sendGame").setIcon(ii);
			

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == naviMap.get("diceGame")) {
			ImageIcon ii= new ImageIcon("images/rollDiceNormal.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("diceGame").setIcon(ii);
			
		}

		if (e.getSource() == naviMap.get("startGame")) {
			ImageIcon ii= new ImageIcon("images/StartGameNormal.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("startGame").setIcon(ii);
			
		}
		if(e.getSource()==naviMap.get("endGame")){
			ImageIcon ii= new ImageIcon("images/EndGameNormal.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("endGame").setIcon(ii);
		}
		if (e.getSource() == naviMap.get("newGame")) {
			ImageIcon ii= new ImageIcon("images/NewGameNormal.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("newGame").setIcon(ii);
			

		}

		if (e.getSource() == naviMap.get("saveGame")) {
			ImageIcon ii= new ImageIcon("images/SaveGameNormal.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("saveGame").setIcon(ii);
			

		}

		if (e.getSource() == naviMap.get("loadGame")) {
			ImageIcon ii= new ImageIcon("images/LoadGameNormal.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("loadGame").setIcon(ii);
			

		}

		if (e.getSource() == naviMap.get("sendGame")) {
			ImageIcon ii= new ImageIcon("images/SendGameNormal.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("sendGame").setIcon(ii);
			

		}

	}

}
