import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

public class Principal extends JFrame
{
	TextArea Areatexto;
	TextArea AreaErrores;
	String Path = "";
	AnalizadorLexico analizador = new AnalizadorLexico();
	JPanel Superior;
	JPanel Inferor;
	AnalizadorSintactico Sintactico;
	public Principal()
	{
		setSize(700,700);
		setTitle("Proyecto Compiladores");
		setLocationRelativeTo(null);
		setResizable(false);
		iniciarComponentes();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(32,178,170));
		Superior = new JPanel();
		Superior.setBounds(0, 0, 700, 40);
		Superior.setBackground(new Color(20,128,128));
		this.add(Superior);
		
		Inferor = new JPanel();
		Inferor.setBounds(0, 40, 700, 40);
		Inferor.setBackground(new Color(32,178,170));
		this.add(Inferor);
	}
	
	private void iniciarComponentes() {
		this.setLayout(null);
		//boton abrir un archivo
		JButton Abrir = new JButton();
		Abrir.setText("Abrir");
		Abrir.setBounds(20, 10, 100, 20);
		//Agregar Evento
		ActionListener clickButton = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Abrir();
			}
		};
		Abrir.addActionListener(clickButton);
		this.getContentPane().add(Abrir);
		
		//boton abrir un archivo
		JButton Nuevo = new JButton();
		Nuevo.setText("Nuevo");
		Nuevo.setBounds(130, 10, 100, 20);
		//Agregar Evento
		ActionListener CrearNuevo = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				CrearNuevo();
			}
		};
		Nuevo.addActionListener(CrearNuevo);
		this.getContentPane().add(Nuevo);
		
		
		//boton guardar el archivo
		JButton guarda = new JButton();
		guarda.setText("Guardar");
		guarda.setBounds(280, 10, 100, 20);
		this.getContentPane().add(guarda);
		//Agregar Evento
		ActionListener Guardar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Guardar();
			}
		};
		guarda.addActionListener(Guardar);
		
		//boton Compilar
		JButton compilar = new JButton();
		compilar.setText("Compilar");
		compilar.setBounds(390, 10, 100, 20);
		//Agregar Evento
		ActionListener Compilar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Compilar();
			}
		};
		compilar.addActionListener(Compilar);
		this.getContentPane().add(compilar);
		
		//boton tokens
		JButton tokens = new JButton();
		tokens.setText("Tokens");
		tokens.setBounds(20, 50, 100, 20);
		//Agregar Evento
		ActionListener Tokens = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				MostrarTokens();
			}
		};
		tokens.addActionListener(Tokens);
		this.getContentPane().add(tokens);
		
		//boton LL1
		JButton ll1 = new JButton();
		ll1.setText("Tabla LL1");
		ll1.setBounds(130, 50, 100, 20);
		//Agregar Evento
		ActionListener Ll1 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				MostrarLL1();
			}
		};
		ll1.addActionListener(Ll1);
		this.getContentPane().add(ll1);
		
		//boton de simbolos
		
		JButton Simbolos = new JButton();
		Simbolos.setText("Simbolos");
		Simbolos.setBounds(280, 50, 100, 20);
		//Agregar Evento
		ActionListener simbolos = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				MostrarSimbolos();
			}
		};
		Simbolos.addActionListener(simbolos);
		this.getContentPane().add(Simbolos);
		
		
		//area para editar el codigo
	    Areatexto = new TextArea();
		JScrollPane scroll = new JScrollPane(Areatexto,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		Areatexto.setBounds(20, 80, 650, 400);
		this.getContentPane().add(Areatexto);
		
		JLabel l = new JLabel();
		l.setText("Errores encontrados:");
		l.setBounds(40, 480, 650, 20);
		this.add(l);
		//area que muestra los errores o resultados de la compilacion
		AreaErrores = new TextArea();
		scroll = new JScrollPane(AreaErrores,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		AreaErrores.setBounds(20, 500, 650, 150);
		this.getContentPane().add(AreaErrores);
	}
	
	private void Abrir() {
		
		JFileChooser selectorArchivos = new JFileChooser();
		selectorArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos", "CMM");
		selectorArchivos.setFileFilter(filter);
		// indica cual fue la accion de usuario sobre el jfilechooser
		int resultado = selectorArchivos.showOpenDialog(this);
		
		File archivo = selectorArchivos.getSelectedFile(); // obtiene el archivo seleccionado

		// muestra error si es inválido
		if ((archivo == null) || (archivo.getName().equals(""))) {
		 JOptionPane.showMessageDialog(this, "Nombre de archivo inválido", "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE);
		 return;
		} // fin de if

		//Areatexto.setText(archivo.getAbsolutePath());
		Scanner scn;
		try {
			Areatexto.setText("");
			Path = archivo.getAbsolutePath();
			scn = new Scanner(archivo);
			int pos =0;
			while (scn.hasNextLine()) {
				Areatexto.append(scn.nextLine() + "\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	private void Guardar() {
		try {
			if(!Path.isEmpty())
				guardarFichero(Areatexto.getText(),"", Path);
			else {
				JFileChooser guardar = new JFileChooser();
			    guardar.showSaveDialog(null);
			    guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			    File archivo = guardar.getSelectedFile();
			    String ruta = guardar.getCurrentDirectory().toString();
			
			    String nombre = guardar.getSelectedFile().getName();
			    if(nombre.isEmpty())
			    	nombre = "codigo";
			    guardarFichero(Areatexto.getText(), nombre + ".CMM", ruta);
			}
		}catch(Exception e) {
			
		}
	}
	
	private void guardarFichero(String cadena, String nombre, String ruta){
		File archivo;
		if(!nombre.isEmpty()) {
			archivo = new File(ruta+"/"+nombre);
			Path = ruta+"/"+nombre;
		}
		else
			archivo = new File(ruta.toString());
	    FileWriter escribir;
	    try {

	        escribir = new FileWriter(archivo);
	        escribir.write("");
	        escribir.write(cadena);
	        escribir.close();

	    } catch (FileNotFoundException ex) {
	        JOptionPane.showMessageDialog(null, "Error al guardar, ponga nombre al archivo");
	    } catch (IOException ex) {
	        JOptionPane.showMessageDialog(null, "Error al guardar, en la salida");
	    }
	}
	
	private void CrearNuevo() {
		if(!Areatexto.getText().isEmpty())
			Guardar();
		Path = "";
		Areatexto.setText(" ");
	}
	
	//este metodo debera llamar a todos los analizadores
	private void Compilar() {
		Guardar();
		analizador.iniciarAnalisis(Path);
		Sintactico = new AnalizadorSintactico(analizador.Tokens);
		Sintactico.iniciarAnalisis();
		AreaErrores.setText("");
		if(analizador.errores.size() != 0) {
			AreaErrores.append("Errores Lexicos"+"\n");
			for(int i = 0;i<analizador.errores.size();i++) {
				AreaErrores.append(analizador.errores.get(i).toString()+"\n");
			}
		}
		if(Sintactico.Errores.size() !=0) {
			AreaErrores.append("Errores Sintactico"+"\n");
			for(int i = 0;i<Sintactico.Errores.size();i++) {
				AreaErrores.append(Sintactico.Errores.get(i).toString()+"\n");
			}
		}
		if(Sintactico.ErroresSemanticos.size() !=0) {
			AreaErrores.append("Errores Semanticos"+"\n");
			for(int i = 0;i<Sintactico.ErroresSemanticos.size();i++) {
				AreaErrores.append(Sintactico.ErroresSemanticos.get(i).toString()+"\n");
			}
		}
	}
	
	private void MostrarTokens() {
		VentanaTokens ventana = new VentanaTokens(analizador.mostrarTabla());
		ventana.show();
				
	}
	private void MostrarLL1() {
		TablaLL1 ventana = new TablaLL1();
		ventana.show();
	}
	private void MostrarSimbolos() {
		VentanaSimbolos ventana = new VentanaSimbolos(Sintactico);
		ventana.show();
	}
}
