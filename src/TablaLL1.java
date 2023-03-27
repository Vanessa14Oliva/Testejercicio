import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablaLL1 extends JFrame {

	public TablaLL1() {
		setSize(700,600);
		setTitle("Tabla LL1 ");
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
		model.addColumn("NT");
		model.addColumn("Identificador");
		model.addColumn("Entero");
		model.addColumn("Real");
		model.addColumn("Cadena");
		model.addColumn("Caracter");
		model.addColumn("Dos_Puntos");
		model.addColumn("Coma");
		model.addColumn("Pabierto");
		model.addColumn("Pcerrado");
		model.addColumn("Igual");
		model.addColumn("Diferente");
		model.addColumn("Mayor");
		model.addColumn("Menor");
		model.addColumn("MayorIgual");
		model.addColumn("MenorIgual");
		model.addColumn("S.suma");
		model.addColumn("S.resta");
		model.addColumn("S.multiplicacion");
		model.addColumn("S.Division");
		model.addColumn("S.potencia");
		model.addColumn("Pr.entero");
		model.addColumn("Pr.largo");
		model.addColumn("Pr.cadena");
		model.addColumn("Pr.real");
		model.addColumn("Pr.caracter");
		model.addColumn("Pr.inicio");
		model.addColumn("Pr.fin");
		model.addColumn("Pr.funcion");
		model.addColumn("Pr.retornar");
		model.addColumn("Pr,procedimiento");
		model.addColumn("Pr.si");
		model.addColumn("Pr.entonces");
		model.addColumn("Pr.sino");
		model.addColumn("Pr.para");
		model.addColumn("Pr.hasta");
		model.addColumn("Pr.mientras");
		model.addColumn("Pr.hacer");
		model.addColumn("Pr.escribir");
		model.addColumn("Pr.leer");
		model.addColumn("Pr.programa");
		model.addColumn("Pr.principal");
		model.addColumn("Pr.con");
		model.addColumn("Pr.incremento");
		model.addColumn("Pr.decremento");
		String[] S = {"S","S -> PROGRAMA MAIN","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","S -> PROGRAMA MAIN","ERROR","S -> PROGRAMA MAIN",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","S -> PROGRAMA MAIN","ERROR","ERROR",
				"ERROR","ERROR","SINC"};
		String[] PROGRAMA = {"PROGRAMA","PROGRAMA -> DECLARACION PROGRAMA","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","PROGRAMA -> FUNCION PROGRAMA","ERROR","PROGRAMA -> PROCEDIMIENTO PRGRAMA",
				"ERROR","SINC","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","PROGRAMA -> ɛ","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] MAIN = {"MAIN","ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","PROGRAMA -> FUNCION PROGRAMA","ERROR","PROGRAMA -> PROCEDIMIENTO PRGRAMA",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","MAIN -> Pr.programa Pr.Principal Pabierto Pcerrado Pr.inicio SENTENCIAS Pr.fin","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] SENTENCIA = {"SENTENCIA","SENTENCIA -> identificador PRE SENTENCIA","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SENTENCIA -> ɛ","ERROR","SINC","ERROR",
				"SENTENCIA -> CONDICIONAL SENTENCIA","ERROR","ERROR","SENTENCIA -> PARA SENTENCIA","ERROR","SENTENCIA -> MIENTRAS SENTENCIA",
				"SENTENCIA -> HACER SENTENCIA","SENTENCIA -> ESCRIBIR SENTENCIA","SENTENCIA -> LEER SENTENCIA","ERROR","ERROR","SINC",
				"ERROR","ERROR","ERROR"};
		String[] PRE = {"PRE","SINC","ERROR","ERROR","ERROR","ERROR","PRE -> DECLARACION",
				"PRE -> DECLARACION","PRE -> LLAMADA","ERROR","PRE -> ASIGNACION ","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","SINC","ERROR","SINC",
				"SINC","SINC","SINC","SINC","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] DECLARACION = {"DECLARACION","SINC","ERROR","ERROR","ERROR","ERROR","DECLARACION -> LISTA Dos_puntos TIPO",
				"DECLARACION -> LISTA Dos_puntos TIPO","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","SINC","ERROR","SINC",
				"SINC","ERROR","ERROR","SINC","ERROR","SINC",
				"SINC","SINC","SINC","SINC","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] LISTA = {"LISTA","ERROR","ERROR","ERROR","ERROR","ERROR","LISTA -> ɛ",
				"LISTA -> Coma Identificador LISTA","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] TIPO = {"TIPO","SINC","ERROR","ERROR","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","TIPO -> Pr.entero","TIPO -> Pr.largo","TIPO -> Pr.cadena","TIPO -> Pr.real",
				"TIPO -> Pr.caracter","ERROR","SINC","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","SINC","ERROR","SINC",
				"SINC","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		
		String[] CONDICIONAL = {"CONDICIONAL","SINC","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"CONDICIONAL -> Pr.si CONDICION Pr.entonces Pr.inicio SENTENCIA Pr.fin SINO","ERROR","ERROR","SINC","ERROR","SINC",
				"SINC","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		
		String[] SINO  = {"SINO","SINO -> ɛ","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINO -> ɛ","ERROR","ERROR","ERROR",
				"SINO -> ɛ","ERROR","SINO -> Pr.sino Pr.inicio SENTENCIA Pr.fin","SINO -> ɛ","ERROR","SINO -> ɛ",
				"SINO -> ɛ","SINO -> ɛ","SINO -> ɛ","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] CONDICION   = {"CONDICION ","CONDICION -> VALORES OPERADORLOGICO VALORES","CONDICION -> VALORES OPERADORLOGICO VALORES","CONDICION -> VALORES OPERADORLOGICO VALORES","CONDICION -> VALORES OPERADORLOGICO VALORES","CONDICION -> VALORES OPERADORLOGICO VALORES","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","SINC","ERROR","SINC",
				"SINC","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] VALORES   = {"VALORES ","VALORES -> Identificador ","VALORES -> Entero ","VALORES -> Real","VALORES -> Cadena","VALORES -> Caracter","ERROR",
				"SINC","ERROR","SINC","SINC","SINC","SINC",
				"SINC","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","SINC","ERROR","SINC",
				"SINC","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] OPERADORLOGICO   = {"OPERADORLOGICO ","SINC","SINC","SINC","SINC","SINC","ERROR",
				"SINC","ERROR","SINC","OPERADORLOGICO -> Igual","OPERADORLOGICO -> Diferente","OPERADORLOGICO -> Mayor",
				"OPERADORLOGICO -> Menor","OPERADORLOGICO -> MayorIgual","OPERADORLOGICO -> MenorIgual","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] PARA   = {"PARA ","SINC","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","PARA -> Pr.para identificador Igual Entero Pr.hasta CONDICION Pr.con AUMENTO Pr.inicio SENTENCIA Pr.fin","ERROR","SINC",
				"SINC","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] AUMENTO   = {"PARA ","ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","SINC","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"AUMENTO -> Pr.incremento ","AUMENTO -> Pr.Decremento","ERROR"};
		String[] MIENTRAS   = {"MIENTRAS ","SINC","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","SINC","ERROR","MIENTRAS -> Pr.mientras CONDICION Pr.hacer Pr.inicio SENTENCIA Pr.fin",
				"SINC","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] HACER   = {"HACER ","SINC","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","SINC","ERROR","SINC",
				"HACER -> Pr.hacer Pr.inicio SENTENCIA Pr.fin Pr.mientras CONDICION","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] ESCRIBIR   = {"ESCRIBIR ","SINC","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","SINC","ERROR","SINC",
				"SINC","ESCRIBIR -> Pr.escribir Pabierto PARAMETROSE Pcerrado","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] PARAMETROSE   = {"PARAMETROSE ","PARAMETROSE -> VALORES SIG","PARAMETROSE -> VALORES SIG","PARAMETROSE -> VALORES SIG","PARAMETROSE -> VALORES SIG","PARAMETROSE -> VALORES SIG","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] SIG   = {"PARAMETROSE ","ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"SIG -> Coma VALORES ","ERROR","SIG -> ɛ","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] LEER   = {"LEER ","SINC","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","SINC","ERROR","SINC",
				"SINC","SINC","LEER -> Pr.leer Pabierto Identificador P.cerrado","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] ASIGNACION   = {"ASIGNACION ","SINC","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ASIGNACION ->  Igual OPE","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","SINC","ERROR","SINC",
				"SINC","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] OPE   = {"OPE ","OPE-> Identificador F","OPE-> OPE2 ","OPE-> OPE2","OPE-> OPE2","OPE-> OPE2","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","SINC","ERROR","SINC",
				"SINC","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] OPE2   = {"OPE2","SINC","OPE2 -> Entero CONTINUACION ","OPE2 -> Real CONTINUACION","OPE2 -> Cadena","OPE2 -> Carácter","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","SINC","ERROR","SINC",
				"SINC","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] F   = {"F","F -> CONTINUACION","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","F -> Pabierto PARAMETROS_LLA Pcerrado ","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","F -> CONTINUACION","F -> CONTINUACION","F -> CONTINUACION",
				"F -> CONTINUACION","F -> CONTINUACION","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","F -> CONTINUACION","ERROR","ERROR",
				"F -> CONTINUACION","ERROR","ERROR","F -> CONTINUACION","ERROR","F -> CONTINUACION",
				"F -> CONTINUACION","F -> CONTINUACION","F -> CONTINUACION","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] PARAMETROS_LLA   = {"PARAMETROS_LLA","PARAMETROS_LLA -> PARAMETROSE ","PARAMETROS_LLA -> PARAMETROSE","PARAMETROS_LLA -> PARAMETROSE","PARAMETROS_LLA -> PARAMETROSE","PARAMETROS_LLA -> PARAMETROSE","ERROR",
				"ERROR","ERROR","PARAMETROS_LLA -> ɛ","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] CONTINUACION = {"CONTINUACION","SINC","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","CONTINUACION -> OPERADORES OPERANDOS CONTINUACION ","CONTINUACION -> OPERADORES OPERANDOS CONTINUACION ","CONTINUACION -> OPERADORES OPERANDOS CONTINUACION ",
				"CONTINUACION -> OPERADORES OPERANDOS CONTINUACION ","CONTINUACION -> OPERADORES OPERANDOS CONTINUACION ","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","SINC","ERROR","SINC",
				"SINC","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] OPERANDOS = {"OPERANDOS","OPERANDOS -> Identificador ","OPERANDOS -> Entero","OPERANDOS -> Real","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","SINC","SINC","SINC",
				"SINC","SINC","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"SINC","ERROR","ERROR","SINC","ERROR","SINC",
				"SINC","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] OPERADORES = {"OPERADORES","SINC","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","OPERADORES -> S.suma ","OPERADORES -> S.resta","OPERADORES -> S.multiplicacion",
				"OPERADORES -> S.division","OPERADORES -> S.potencia","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] FUNCION = {"FUNCION","SINC","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","FUNCION -> Pr.funcion Identificador Pabierto PARAMETROS Pcerrado Dos_puntos TIPO Pr.inicio SENTENCIA Pr.retornar RETORNO Pr.fin","ERROR","SINC",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","SINC","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] RETORNO = {"RETORNO","RETORNO -> Identificador CONTINUACION","RETORNO -> OPE2 ","RETORNO -> OPE2","RETORNO -> OPE2","RETORNO -> OPE2","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] PARAMETROS = {"PARAMETROS","PARAMETROS -> Identificador Dos_puntos TIPO PSIG ","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","PARAMETROS -> ɛ","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] PSIG = {"PSIG","ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] PROCEDIMIENTO = {"PROCEDIMIENTO","SINC","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","SINC","ERROR","PROCEDIMIENTO -> Pr.procedimiento Identificador Pabierto PARAMETROS Pcerrado Pr.inicio SENTENCIA Pr.fin",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","SINC","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		String[] LLAMADA = {"LLAMADA","SINC","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","LLAMADA -> Pabierto PARAMETROS_LLA Pcerrado","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR","ERROR","ERROR","PROCEDIMIENTO -> Pr.procedimiento Identificador Pabierto PARAMETROS Pcerrado Pr.inicio SENTENCIA Pr.fin",
				"SINC","ERROR","ERROR","SINC","ERROR","SINC",
				"SINC","SINC","SINC","ERROR","ERROR","ERROR",
				"ERROR","ERROR","ERROR"};
		model.addRow(S);
		model.addRow(PROGRAMA);
		model.addRow(MAIN);
		model.addRow(SENTENCIA);
		model.addRow(PRE);
		model.addRow(DECLARACION);
		model.addRow(LISTA);
		model.addRow(TIPO);
		model.addRow(CONDICIONAL);
		model.addRow(SINO);
		model.addRow(CONDICION);
		model.addRow(VALORES);
		model.addRow(OPERADORLOGICO);
		model.addRow(PARA);
		model.addRow(AUMENTO);
		model.addRow(MIENTRAS);
		model.addRow(HACER);
		model.addRow(ESCRIBIR);
		model.addRow(PARAMETROSE);
		model.addRow(SIG);
		model.addRow(LEER);
		model.addRow(ASIGNACION);
		model.addRow(OPE);
		model.addRow(OPE2);
		model.addRow(F);
		model.addRow(PARAMETROS_LLA);
		model.addRow(CONTINUACION);
		model.addRow(OPERANDOS);
		model.addRow(OPERADORES);
		model.addRow(FUNCION);
		model.addRow(RETORNO);
		model.addRow(PARAMETROS);
		model.addRow(PSIG);
		model.addRow(PROCEDIMIENTO);
		model.addRow(LLAMADA);
		JTable tabla = new JTable(model);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for(int i = 0; i<=44;i++) {
			tabla.getColumnModel().getColumn(i).setPreferredWidth(300);
		}
		JScrollPane scroll = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(15,45,650,450);
		this.add(scroll);
	}

}
