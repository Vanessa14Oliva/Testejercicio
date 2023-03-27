import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

public class VentanaTokens extends JFrame{
	public VentanaTokens(String lista) {
		setSize(700,600);
		setTitle("Lista de Tokens ");
		setLocationRelativeTo(null);
		setResizable(false);
		iniciarComponentes(lista);
		this.setLayout(null);
		JPanel Superior = new JPanel();
		Superior.setBounds(0, 0, 700, 40);
		Superior.setBackground(new Color(20,128,128));
		this.add(Superior);
		
		JPanel Inferor = new JPanel();
		Inferor.setBounds(0, 40, 700, 660);
		Inferor.setBackground(new Color(32,178,170));
		this.add(Inferor);
	}
	
	public void iniciarComponentes(String lista) {
		TextArea text = new TextArea();
		text.setText(lista);
		text.setBounds(10, 40, 650, 520);
		this.add(text);
		
		JLabel id = new JLabel();
		id.setText("ID");
		id.setBounds(10, 20, 20, 20);
		id.setForeground(Color.white);
		this.add(id);
		
		JLabel Token = new JLabel();
		Token.setText("Token");
		Token.setBounds(30, 20, 100, 20);
		Token.setForeground(Color.white);
		this.add(Token);
		
		JLabel Patron = new JLabel();
		Patron.setText("Patron");
		Patron.setForeground(Color.white);
		Patron.setBounds(250, 20, 100, 20);
		this.add(Patron);
		
		JLabel Cantidad = new JLabel();
		Cantidad.setText("Cantidad");
		Cantidad.setForeground(Color.white);
		Cantidad.setBounds(350, 20, 100, 20);
		this.add(Cantidad);
	}
}
