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
			ImageIcon ii= new ImageIcon("images/rollDicePressedS.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("diceGame").setIcon(ii);
			
		}

		if (e.getSource() == naviMap.get("startGame")) {
			ImageIcon ii= new ImageIcon("images/StartGamePressedS.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("startGame").setIcon(ii);
			
		}
		if(e.getSource()==naviMap.get("endGame")){
			ImageIcon ii= new ImageIcon("images/EndGamePressedS.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("endGame").setIcon(ii);
		}
		if (e.getSource() == naviMap.get("newGame")) {
			ImageIcon ii= new ImageIcon("images/NewGamePressedS.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("newGame").setIcon(ii);
			

		}

		if (e.getSource() == naviMap.get("saveGame")) {
			ImageIcon ii= new ImageIcon("images/SaveGamePressedS.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("saveGame").setIcon(ii);
			

		}

		if (e.getSource() == naviMap.get("loadGame")) {
			ImageIcon ii= new ImageIcon("images/LoadGamePressedS.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("loadGame").setIcon(ii);
			

		}

		if (e.getSource() == naviMap.get("sendGame")) {
			ImageIcon ii= new ImageIcon("images/SendGamePressedS.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("sendGame").setIcon(ii);
			

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == naviMap.get("diceGame")) {
			ImageIcon ii= new ImageIcon("images/rollDiceNormalS.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("diceGame").setIcon(ii);
			
		}

		if (e.getSource() == naviMap.get("startGame")) {
			ImageIcon ii= new ImageIcon("images/StartGameNormalS.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("startGame").setIcon(ii);
			
		}
		if(e.getSource()==naviMap.get("endGame")){
			ImageIcon ii= new ImageIcon("images/EndGameNormalS.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("endGame").setIcon(ii);
		}
		if (e.getSource() == naviMap.get("newGame")) {
			ImageIcon ii= new ImageIcon("images/NewGameNormalS.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("newGame").setIcon(ii);
			

		}

		if (e.getSource() == naviMap.get("saveGame")) {
			ImageIcon ii= new ImageIcon("images/SaveGameNormalS.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("saveGame").setIcon(ii);
			

		}

		if (e.getSource() == naviMap.get("loadGame")) {
			ImageIcon ii= new ImageIcon("images/LoadGameNormalS.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("loadGame").setIcon(ii);
			

		}

		if (e.getSource() == naviMap.get("sendGame")) {
			ImageIcon ii= new ImageIcon("images/SendGameNormalS.png");
			ii.setImage(ii.getImage().getScaledInstance(170,70,  1));
			naviMap.get("sendGame").setIcon(ii);
			

		}

	}

}
