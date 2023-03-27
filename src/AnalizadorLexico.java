import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class AnalizadorLexico {
	public int token;
	char c;
	int error;
	String lexema;
	int estado;
	String Line[];
	String Palabra;
	char sig;
	int token1 = 0,token2 = 0,token3 = 0,token4 = 0,token5 = 0,token6 = 0,token7 = 0, token8= 0,
			token9 = 0, token10=0,token11=0,token12=0,token13=0,token14=0,token15=0,token16=0,token17=0,token18=0,token19=0,token20=0,
			token101 = 0,token102 = 0,token104 = 0,token103 = 0,token105=0,token106=0,token107=0,token108 = 0,
			token109 = 0,token110=0,token111=0,token112=0,token113=0,token114=0,token115=0,token116=0,token117=0,token118=0,token119=0,token120=0,
			token121=0,token122=0,token123=0,token124=0;
	ArrayList<String> errores;
	ArrayList<Token> Tokens;
	public void resetTokens() {
		token1 = 0;token2 = 0;token3 = 0;token4 = 0;token5 = 0;token6 = 0;token7 = 0; token8= 0;
				token9 = 0; token10=0;token11=0;token12=0;token13=0;token14=0;token15=0;token16=0;token17=0;token18=0;token19=0;token20=0;
				token101 = 0;token102 = 0;token104 = 0;token103 = 0;token105=0;token106=0;token107=0;token108 = 0;
				token109 = 0;token110=0;token111=0;token112=0;token113=0;token114=0;token115=0;token116=0;token117=0;token118=0;token119=0;token120=0;
				token121=0;token122=0;token123=0;token124=0;
	}
	public void iniciarAnalisis(String Path) {
		errores = new ArrayList<String>();
		Tokens = new ArrayList<Token>();
		resetTokens();
		Scanner reader = null;
		try {
			reader = new Scanner(new File(Path));
		}catch(FileNotFoundException ex) {
			//System.out.println("Ocurrio un Error en el Archivo");
			return;
		}
		if(reader == null) {
			//System.out.println("No existe en archivo que se quiere leer");
			return;
		}
		int numeroLinea = 0;
		boolean comentarios2 = false;
		while(reader.hasNextLine()) {
			Line = reader.nextLine().trim().split(" ");
			String salida = "";
			int npalabra = 0;
			boolean comillas = false;
			boolean comentarios1 = false;
			boolean simples = false;
			String cadena = "";
			String Linea = "";
			int a = 0;
			//pruebas2
			if(!Line[0].isEmpty()) {
				if(Line[0].charAt(0) == '\t')
					Line[0] = Line[0].substring(1);
			}
			for(int i = 0; i<Line.length;i++) {
				/*if(Line[0].isEmpty() && Line.length == 1)
					break;*/
				Palabra = Line[i];
				int guia = 0;
				cadena = "";
				while(guia < Palabra.length()) {
					if(Palabra.charAt(guia) == '"' && !comentarios2 && !simples) {
						if(!comillas)
							comillas = true;
						else
							comillas = false;
						cadena += Palabra.charAt(guia);
						guia++;
					}else if(Palabra.charAt(guia) == '\'' && !comentarios2 && !comillas) {
						if(!simples)
							simples = true;
						else
							simples = false;
						cadena += Palabra.charAt(guia);
						guia++;
					}else if(Palabra.charAt(guia) == '/' && !comentarios2 && !comillas&& !simples) {
						if(Palabra.charAt(guia+1) == '/') {
							comentarios1 = true;
							break;
						}
						else if(Palabra.charAt(guia+1) == '*') {
							comentarios2 = true;
							guia++;
							guia++;
							//comentario linea completa
						}
						else {
							cadena += Palabra.charAt(guia);
							guia++;
							//no es comentario
						}
					}else if(comentarios2) {
						if(Palabra.charAt(guia) == '*') {
							if(Palabra.charAt(guia+1) == '/') {
								comentarios2 = false;
								guia++;
							}
						}
						guia++;
					}else if(Palabra.charAt(guia) != '\t'){
						cadena+=Palabra.charAt(guia);
						guia++;
					}
					else {
						cadena+=" ";
						guia++;
					}
				}
				if(comentarios1)
					break;
				if(!cadena.isEmpty())
					Linea+=cadena+" ";
				
			}
			
			Palabra = Linea;
			numeroLinea++;
			while(!Palabra.isEmpty()) {
				int t = obtenerToken(npalabra,numeroLinea);
				if(t != 0) {
					salida += " " + t;
					npalabra++;
				}
					
			}
			//System.out.println(salida);
			
		}
		Tokens.add(new Token(0,"vacio",0,0));
	}
	
	public String mostrarTabla() {
		String Tabla="";
		Tabla += ("1) Identificador				L(L | D)+		"+token1+"\n");
		Tabla += ("2) Entero				D+		"+token2+"\n");
		Tabla += ("3) Real					D+ '.' D+		"+token3+"\n");
		Tabla += ("4) Cadena				'”'(L | D)+'“'	"+token4+"\n");
		Tabla += ("5) Caracter				'‘' L '‘'		"+token5+"\n");
		Tabla += ("6) Dos_puntos				':'		"+token6+"\n");
		Tabla += ("7) Coma				','		"+token7+"\n");
		Tabla += ("8) Pabierto				'('		"+token8+"\n");
		Tabla += ("9) Pcerrado				')'		"+token9+"\n");
		Tabla += ("10) Igual				'='		"+token10+"\n");
		Tabla += ("11) Diferente				'<''>'		"+token11+"\n");
		Tabla += ("12) Mayor				'>'		"+token12+"\n");
		Tabla += ("13) Menor				'<'		"+token13+"\n");
		Tabla += ("14) MayorIgual				'>''='		"+token14+"\n");
		Tabla += ("15) MenorIgual				'<''='		"+token15+"\n");
		Tabla += ("16) S.mas				'+'		"+token16+"\n");
		Tabla += ("17) S.menos				'-'		"+token17+"\n");
		Tabla += ("18) S.multiplicacion			'*'		"+token18+"\n");
		Tabla += ("19) S.division				'/'		"+token19+"\n");
		Tabla += ("20) S.potencia				'^'		"+token20+"\n");
		Tabla += ("101) Pr.entero				'Entero'		"+token101+"\n");
		Tabla += ("102) Pr.largo				'Largo'		"+token102+"\n");
		Tabla += ("103) Pr.cadena				'Cadena'	"+token103+"\n");
		Tabla += ("104) Pr.real				'Real'		"+token104+"\n");
		Tabla += ("105) Pr.caracter				'Caracter'	"+token105+"\n");
		Tabla += ("106) Pr.inicio				'Inicio'		"+token106+"\n");
		Tabla += ("107) Pr.fin				'Fin'		"+token107+"\n");
		Tabla += ("108) Pr.funcion				'Funcion'	"+token108+"\n");
		Tabla += ("109) Pr.retornar				'Retornar'	"+token109+"\n");
		Tabla += ("110) Pr.Procedimiento			'Procedimiento'	"+token110+"\n");
		Tabla += ("111) Pr.si				'Si'		"+token111+"\n");
		Tabla += ("112) Pr.entonces				'Entonces'	"+token112+"\n");
		Tabla += ("113) Pr.sino				'Sino'		"+token113+"\n");
		Tabla += ("114) Pr.para				'Para'		"+token114+"\n");
		Tabla += ("115) Pr.hasta				'Hasta'		"+token115+"\n");
		Tabla += ("116) Pr.mientras				'Mientras'	"+token116+"\n");
		Tabla += ("117) Pr.hacer				'Hacer'		"+token117+"\n");
		Tabla += ("118) Pr.escribir				'Escribir'		"+token118+"\n");
		Tabla += ("119) Pr.leer				'Leer'		"+token119+"\n");
		Tabla += ("120) Pr.programa			'Programa'	"+token120+"\n");
		Tabla += ("121) Pr.principal				'Principal'	"+token121+"\n");
		Tabla += ("122) Pr.con				'Con'		"+token122+"\n");
		Tabla += ("123) Pr.incremento			'Incremento'	"+token123+"\n");
		Tabla += ("124) Pr.decremento			'Decremento'	"+token124+"\n");
	
		return Tabla;
	}
	
	
	private boolean letra(char c) {
		String Letras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
		if(Letras.indexOf(c) == -1) {
			return false;
		}
		return true;
	}
	
	private boolean digito(char c) {
		String Digito = "0123456789";
		if(Digito.indexOf(c) == -1) {
			return false;
		}
		return true;
	}
	
	private int getPalabra(String lexema) {
		switch(lexema) {
		case "Entero":
			token101++;
			return 101;
		case "Largo":
			token102++;
			return 102;
		case "Cadena":
			token103++;
			return 103;
		case "Real":
			token104++;
			return 104;
		case "Caracter":
			token105++;
			return 105;
		case "Inicio":
			token106++;
			return 106;
		case "Fin":
			token107++;
			return 107;
		case "Funcion":
			token108++;
			return 108;
		case "Retornar":
			token109++;
			return 109;
		case "Procedimiento":
			token110++;
			return 110;
		case "Si":
			token111++;
			return 111;
		case "Entonces":
			token112++;
			return 112;
		case "Sino":
			token113++;
			return 113;
		case "Para":
			token114++;
			return 114;
		case "Hasta":
			token115++;
			return 115;
		case "Mientras":
			token116++;
			return 116;
		case "Hacer":
			token117++;
			return 117;
		case "Escribir":
			token118++;
			return 118;
		case "Leer":
			token119++;
			return 119;
		case "Programa":
			token120++;
			return 120;
		case "Principal":
			token121++;
			return 121;
		case "Con":
			token122++;
			return 122;
		case "Incremento":
			token123++;
			return 123;
		case "Decremento":
			token124++;
			return 124;
		default:
			return 1;
		}
	}
	private int obtenerToken(int numeroPalabra,int numeroLinea) {
		token = 0;
		error = 0;
		lexema = "";
		estado = 0;
		while(token == 0 && error == 0) {
			if (!Palabra.isEmpty()) {		
				c = Palabra.charAt(0);
				Palabra = Palabra.substring(1);
			}
			   switch(estado) {
			   		case 0:
			   			if(letra(c)) {
			   				estado = 1;
			   			}else if(digito(c)){
			   				estado = 2;
			   			}else if(c == '"') {
			   				estado = 3 ;
			   			}else if(c == '\'') {
			   				estado = 15;		
			   			}else if(c == ':') {
			   				token = 6;
			   				token6++;
			   			}else if(c == ',') {
			   				token = 7;
			   				token7++;
			   			}else if(c == '(') {
			   				token = 8;
			   				token8++;
			   			}else if(c == ')') {
			   				token = 9;
			   				token9++;
			   			}else if(c == '=') {
			   				token = 10;
			   				token10++;
			   			}else if(c == '<') {
			   				estado = 4;
			   			}else if(c == '>') {
			   				estado = 5;
			   			}else if (c == '+') {
			   				token = 16;
			   				token16++;
			   			}else if(c == '-') {
			   				token = 17;
			   				token17++;
			   			}else if(c == '*') {
			   				token = 18;
			   				token18++;
			   			}else if(c == '/') {
			   				token = 19;
			   				token19++;
			   			}else if(c == '^') {
			   				token = 20;
			   				token20++;
			   			}else if(c == ' ') {
			   				return 0;
			   			}else {
			   				error = 1;
			   			}
						lexema += c;
			   			break;
			   		case 1://
			   			if(letra(c) || digito(c)) {
			   				estado = 1;
			   				if(Palabra.isEmpty()){
				   				token = 1;
			   				}
							lexema += c;
			   			}else{
			   				 token = 1;
			   				 Palabra = c + Palabra;
			   			}
			   			break;
			   		case 2:
			   			if(digito(c)) {
			   				estado = 2;
			   				if(Palabra.isEmpty()){
				   				token = 2;
				   				token2++;
				   			}
							lexema += c;
			   			}else if(c=='.') {
			   				estado = 17;
			   				lexema +=c;
			   			}else{
			   				token = 2;
			   				token2++;
			   				Palabra = c + Palabra;
			   			}
			   			break;
			   			
			   		case 17://
			   			if(digito(c)) {
			   				estado = 23;
							lexema += c;
			   			}else{
			   				error = 1;
			   				Palabra = c + Palabra;
			   			}
			   			break;
			   			
			   		case 23://
			   			if(digito(c)) {
			   				estado = 23;
			   				if(Palabra.isEmpty()){
				   				token = 3;
				   				token3++;
				   			}
							lexema += c;
			   			}else{
			   				token = 3;
			   				token3++;
			   				Palabra = c + Palabra;
			   			}
			   			break;
			   		case 3://
			   			if(c!='"') {
			   				estado = 18;
			   				if(Palabra.isEmpty()){
				   				error = 1;
			   				}
							lexema += c;
			   			}else{
			   				error = 1;
			   				lexema += c;
			   			}
			   			break;
			   		case 18:
			   			if(c!='"') {
			   				estado = 18;
			   				if(Palabra.isEmpty()){
				   				error = 1;
			   				}
							lexema += c;
			   			}else{
			   				token = 4;
			   				token4++;
			   				lexema += c;
			   			}
			   			break;
			   		case 15:
			   			if(c=='\'') {
			   				error = 1;
			   			}
			   			else {
			   				estado = 22;
			   			}
			   			lexema += c;
			   			break;
			   		case 22:
			   			if(c == '\'') {
			   				token = 5;
			   				token5++;
			   				lexema += c;
			   			}
			   			else {
			   				while(c != '\'') {
			   					lexema += c;
			   					c = Palabra.charAt(0);
			   					Palabra = Palabra.substring(1);
			   				}
			   				lexema += c;
			   				error = 1;
			   			}
			   			break;
			   		case 4:
			   			if(c == '>') {
			   				token = 11;
			   				token11++;
			   			}
			   			else if(c == '=') {
			   				token = 15;
			   				token15++;
			   			}
			   			else {
			   				token = 13;
			   				token13++;
			   				Palabra = c+Palabra;
			   			}
			   			break;
			   		case 5:
			   			if(c == '=') {
			   				token = 14;
			   				token14++;
			   			}
			   			else {
			   				token = 12;
			   				token12++;
			   				Palabra = c+Palabra;
			   			}
			   			break;
			   		default:
			   			error = 1;
			   			break;
			   }
		}
		//lexema = lexema.substring(1);
		if(error == 1) {
			token = -1;
			errores.add("Error en lexema "+(numeroPalabra+1)+" en linea "+numeroLinea+" Lexema invalido: "+lexema);
		}
		else if(token == 1) {
			token = getPalabra(lexema);
		}
		// Muestra o retorna Token  cuando se dio un error en el lexema
		if(token == 1)
			token1++;
		Tokens.add(new Token(token,lexema,numeroPalabra,numeroLinea));
		return token;
	}
}

