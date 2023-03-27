import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaSimbolos extends JFrame {
    AnalizadorSintactico Analizador;
	public VentanaSimbolos(AnalizadorSintactico AS) {
		Analizador = AS;
		setSize(700,600);
		setTitle("Simbolos ");
		setLocationRelativeTo(null);
		setResizable(false);
		iniciarComponentes();
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

	private void iniciarComponentes() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("PARTE");
		model.addColumn("NOMBRE");
		model.addColumn("TIPO");
		model.addColumn("PARAMETRO 1");
		model.addColumn("PARAMETRO 2");
		model.addColumn("PARAMETRO 3");
		
		for(Variable item: Analizador.Globales) {
			String[] S = new String[] {"VARIABLE",item.Lexema,item.Tipo};
			model.addRow(S);
		}
		for(Funcion item : Analizador.Funciones) {
			String[] p = new String[3];
			int i = 0;
			for(Variable v : item.Parametros) {
				p[i] = v.Lexema+":"+v.Tipo;
				i++;
			}
			String[] S = {"FUNCION",item.Nombre,item.TipoRetorno,p[0],p[1],p[2]};
			model.addRow(S);
			for(Variable v : item.Variables) {
				String[] V = {"VARIABLE",v.Lexema,v.Tipo};
				model.addRow(V);
			}
		}
		for(Procedimiento item : Analizador.Procedimientos) {
			String[] p = new String[3];
			int i = 0;
			for(Variable v : item.Parametros) {
				p[i] = v.Lexema+":"+v.Tipo;
				i++;
			}
			String[] S = {"PROCEDIMIENTO",item.Nombre,"VOID",p[0],p[1],p[2]};
			model.addRow(S);
			for(Variable v : item.Variables) {
				String[] V = {"VARIABLE",v.Lexema,v.Tipo};
				model.addRow(V);
			}
		}
		{
			String[] S = {"PROCEDIMIENTO","Principal","VOID"};
			for(Variable v : Analizador.Principal.Variables) {
				String[] V = {"VARIABLE",v.Lexema,v.Tipo};
				model.addRow(V);
			}
		}
		
		JTable tabla = new JTable(model);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for(int i = 0; i<=5;i++) {
			tabla.getColumnModel().getColumn(i).setPreferredWidth(150);
		}
		JScrollPane scroll = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(15,45,650,450);
		this.add(scroll);
	}

}
