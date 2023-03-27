import java.util.ArrayList;

public class AnalizadorSintactico {
	//lista de tokens, estos se deberan de ir a traer al analizador lexico 
		ArrayList<Token> ListaTokens;
		public ArrayList<String> Errores;
		public ArrayList<String> ErroresSemanticos;
		int Token;
		int guia = 0;
		
		public boolean error = false;
		public ArrayList<Variable> Globales;
		public ArrayList<Funcion> Funciones;
		public ArrayList<Procedimiento> Procedimientos;
		public Procedimiento Principal;
		private ArrayList<Variable> Temporales;
		private Procedimiento Ptemporal;
		private Funcion Ftemporal;
		private Variable Vtemporal;
		
		//booleanas para saber dentro de que estamos leyendo
		private boolean f = false;
		private boolean p = false;
		private boolean m = false;
		
		//variable para llevar el control de los parametros
		private int num = 0;
		private int tip = 0;
		
		//variable para llevar control del tipo
		String tipo = "";
		//variable con lexema anterior, esto es ya que hay una parte que no puede ser predecida
		private String retardo = "";
		
		//boolean global = false;
		//0 = principal   1= procedimiento  2= funcion 
		int estatus = 0;
		Token current;
		//al inicializar el analizador se debe de pasar como parametros los tokens del analiador lexico
		public AnalizadorSintactico(ArrayList<Token> Lista) {
			ListaTokens = new ArrayList<Token>();
			Errores = new ArrayList<String>();
			Globales = new ArrayList<Variable>();
			Temporales = new ArrayList<Variable>();
			ErroresSemanticos = new ArrayList<String>();
			Procedimientos = new ArrayList<Procedimiento>();
			Funciones = new ArrayList<Funcion>();
			Principal = new Procedimiento();
			Principal.Nombre = "Principal";
			this.ListaTokens = Lista;
		}
		
		//este metodo es para movernos en la lista de tokens enviadas por el analizador lexico
		public void SiguienteToken() {
			try {
				current = ListaTokens.get(guia);
				Token = ListaTokens.get(guia).id_token;
				guia++;
			}
			catch(Exception ex) {
				
			}
		}
		
		//busca si existe un procedimiento insertado antes
		private boolean ExisteProcedimiento(String lexema) {
			boolean busqueda = false;
			for(Procedimiento item : Procedimientos) {
				if(lexema.equals(item.Nombre)) {
					busqueda = true;
					break;
				}
			}
			for(Funcion item : Funciones) {
				if(lexema.equals(item.Nombre)) {
					busqueda = true;
					break;
				}
			}
			return busqueda;
		}
		
		//busca si existe ya una funcion insertada
		private boolean ExisteFuncion(String lexema) {
			boolean busqueda = false;
			for(Funcion item : Funciones) {
				if(lexema.equals(item.Nombre)) {
					busqueda = true;
					break;
				}
			}
			for(Procedimiento item : Procedimientos) {
				if(lexema.equals(item.Nombre)) {
					busqueda = true;
					break;
				}
			}
			return busqueda;
		}
		
		private boolean ExisteTS(String lexema) {
			boolean busqueda = false;
			for(Variable item : Globales) {
				if(lexema.equals(item.Lexema)) {
					busqueda = true;
					break;
				}
			}
			
			if(f) {
				for(Variable item : Ftemporal.Variables) {
					if(lexema.equals(item.Lexema)) {
						busqueda = true;
						break;
					}
				}
				for(Variable item : Ftemporal.Parametros) {
					if(lexema.equals(item.Lexema)) {
						busqueda = true;
						break;
					}
				}
			}
			
			if(p) {
				for(Variable item : Ptemporal.Variables) {
					if(lexema.equals(item.Lexema)) {
						busqueda = true;
						break;
					}
				}
				for(Variable item : Ptemporal.Parametros) {
					if(lexema.equals(item.Lexema)) {
						busqueda = true;
						break;
					}
				}
			}
			
			if(m) {
				for(Variable item : Principal.Variables) {
					if(lexema.equals(item.Lexema)) {
						busqueda = true;
						break;
					}
				}
			}
			return busqueda;
		}
		
		private String BuscarTipo(String lexema) {
			String busqueda = " ";
			for(Variable item : Globales) {
				if(lexema.equals(item.Lexema)) {
					busqueda = item.Tipo;
					break;
				}
			}
			
			if(f) {
				for(Variable item : Ftemporal.Variables) {
					if(lexema.equals(item.Lexema)) {
						busqueda = item.Tipo;
						break;
					}
				}
				for(Variable item : Ftemporal.Parametros) {
					if(lexema.equals(item.Lexema)) {
						busqueda = item.Tipo;
						break;
					}
				}
			}
			
			if(p) {
				for(Variable item : Ptemporal.Variables) {
					if(lexema.equals(item.Lexema)) {
						busqueda = item.Tipo;
						break;
					}
				}
				for(Variable item : Ptemporal.Parametros) {
					if(lexema.equals(item.Lexema)) {
						busqueda = item.Tipo;
						break;
					}
				}
			}
			
			if(m) {
				for(Variable item : Principal.Variables) {
					if(lexema.equals(item.Lexema)) {
						busqueda = item.Tipo;
						break;
					}
				}
			}
			return busqueda;
		}
		
		//esta funcion es la que arranca el analisis sintactico para empezar a evaluar la estructura
		public void iniciarAnalisis() {
			//esto se hace para que token tenga un valor inicial
			SiguienteToken();
			S();	
		}
		
		private void S() {
			//simulacion de la produccion S -> PROGRAMA MAIN
			if(Token == 1 || Token == 108 || Token == 110 || Token == 120) {
				
				try {
					PROGRAMA();
				}
				catch(Exception ex) {
					String e = "Se esperaba Metodo Principal ";
					Errores.add(e);
				}
				try {
					m=true;
					MAIN();
					m = false;
					
				}
				catch(Exception ex) {
					String e = "Se esperaba fin en principal ";
					Errores.add(e);
				}
			}
			else {
				String e = "Se esperaba Metodo Principal ";
				Errores.add(e);
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					SiguienteToken();
					if(Token == 0) {
						error = false;
					}
				}
			}
		}
		
		private void PROGRAMA() {
			//simulacion de la produccion S -> DECLARACION PROGRAMA
			if(Token == 1) {
				Temporales = new ArrayList<Variable>();
				if(ExisteTS(current.lexema)) {
					ErroresSemanticos.add("variable "+current.lexema+" Ya esta declarada Linea "+current.linea);
				}
				else {
					Temporales.add(new Variable(null,current.lexema,""));
				}
				SiguienteToken();
				DECLARACION();
				//regla semantica
				Globales.addAll(Temporales);
				Temporales = new ArrayList<Variable>();
				PROGRAMA();
			}
			else {
				//simulacion de la produccion S -> FUNCION PROGRAMA
				if(Token == 108) {
					f = true;
					FUNCION();
					f = false;
					PROGRAMA();
				}
				else {
					//simulacion de la produccion S -> PROCEDIMIENTO PROGRAMA
					if(Token == 110) {
						p = true;
						PROCEDIMIENTO();
						p = false;
						PROGRAMA();
					}
					else {
						//simulacion de la produccion PROGRAMA -> e
						if(Token == 120) {
							//sentencia nula
						}
						else {
							/*error = true;
							String e = "Se esperaba Metodo Principal en linea: "+ListaTokens.get(guia).linea;
							Errores.add(e);*/
						}
					}
				}
			}
			
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 120) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void MAIN() {
			estatus = 0;
			//simulacion de produccion MAIN -> Pr.programa Pr.Principal Pabierto Pcerrado Pr.inicio SENTENCIA Pr.fin
			if(Token == 120) {
				SiguienteToken();
				if(Token == 121) {
					SiguienteToken();
					if(Token == 8) {
						SiguienteToken();
						if(Token == 9) {
							SiguienteToken();
							if(Token == 106) {
								SiguienteToken();
								SENTENCIA();
								if(Token == 107) {
									SiguienteToken();
								}
								else {
									error = true;
									String e = "Se esperaba Fin en linea: "+ListaTokens.get(guia).linea;
									Errores.add(e);
								}
							}
							else {
								error = true;
								String e = "Se esperaba Inicio en linea: "+ListaTokens.get(guia).linea;
								Errores.add(e);
								
							}
						}
						else {
							error = true;
							String e = "Se esperaba Parentesis cerrado en linea: "+ListaTokens.get(guia).linea;
							Errores.add(e);
						}
					}
					else {
						error = true;
						String e = "Se esperaba Parentesis abierto en linea: "+ListaTokens.get(guia).linea;
						Errores.add(e);
					}
				}
				else {
					error = true;
					String e = "Se esperaba Palabra Principal en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			else {
				error = true;
				String e = "Se esperaba Inicio en linea: "+ListaTokens.get(guia).linea;
				Errores.add(e);
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 0) {
						error = false;
					}
					else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void SENTENCIA() {
			//simulacion de produccion SENTENCIA -> CONDICIONAL SENTENCIA
			if(Token == 111) {
				CONDICIONAL();
				SENTENCIA();
			}
			else {
				//simulacion de produccion SENTENCIA -> PARA SENTENCIA
				if(Token == 114) {
					PARA();
					SENTENCIA();
				}
				else {
					//simulacion de produccion SENTENCIA -> MIENTRAS SENTENCIA
					if(Token == 116) {
						MIENTRAS();
						SENTENCIA();
					}
					else {
						//simulacion de produccion SENTENCIA -> HACER SENTENCIA
						if(Token == 117) {
							HACER();
							SENTENCIA();
						}
						else {
							//simulacion de la produccion SENTENCIA -> LEER SENTENCIA
							if(Token == 119) {
								LEER();
								SENTENCIA();
							}
							else {
								//simulacion de produccion SENTENCIA -> ESCRIBIR SENTENCIA
								if(Token == 118) {
									ESCRIBIR();
									SENTENCIA();
								}
								else {
									//simulacion de producion SENTENCIA -> identificador PRE SENTENCIA
									if(Token == 1) {
										retardo = current.lexema;
										SiguienteToken();
										PRE();
										retardo = "";
										SENTENCIA();
									}
									else {
										//simulacion de produccion SENTENCIA -> e
										if(Token == 107 || Token == 109) {
											//sentencia nula
										}
										else {
											error = true;
											String e = "Se esperaba FIN en linea: "+ListaTokens.get(guia).linea;
											Errores.add(e);
										}
									}
								}
							}
						}
					}
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 107 || Token == 109) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void PRE() {
			//simulacion de produccion PRE -> ASIGNACION
			if(Token == 10) {
				if(!ExisteTS(retardo)) {
					ErroresSemanticos.add("Error no existe una variable "+retardo+" Linea "+current.linea);
				}
				ASIGNACION();
			}
			else {
				//simulacion de produccion PRE -> LLAMADA
				if(Token == 8) {
					if(!ExisteFuncion(retardo)) {
						ErroresSemanticos.add("Error no existe una funcion o procedimiento "+retardo+" Linea "+current.linea);
					}
					LLAMADA();
				}
				else {
					//simulacion de produccion PRE -> DECLARACION
					if(Token == 7 || Token == 6) {
						Temporales = new ArrayList<Variable>();
						if(ExisteTS(retardo)) {
							ErroresSemanticos.add("Error ya existe una variable "+retardo+" Linea "+current.linea);
						}
						else {
							Temporales.add(new Variable(null,retardo,""));
						}
						DECLARACION();
						if(f)
							Ftemporal.Variables.addAll(Temporales);
						else if(p)
							Ptemporal.Variables.addAll(Temporales);
						else if(m)
							Principal.Variables.addAll(Temporales);
						Temporales = new ArrayList<Variable>();
					}
					else {
						error = true;
						String e = "Se esperaba simbolo extra en linea: "+ListaTokens.get(guia).linea;
						Errores.add(e);
					}
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107 ||Token ==120) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void DECLARACION() {
			//simulacion de produccion DECLARACION -> LISTA dos_puntos TIPO
			if(Token == 7 || Token == 6) {
				LISTA();
				if(Token == 6) {
					SiguienteToken();
					TIPO();
					//el tipo se agrega a toda las variables 
				}
				else {
					error = true;
					String e = "Se esperaba Dos Puntos en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107 ||Token ==120 || Token == 108 || Token == 110) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void LISTA() {
			//simulacion de produccion LISTA -> Coma Identificador LISTA
			if(Token == 7) {
				SiguienteToken();
				if(Token == 1) {
					//regla semantica
					if(ExisteTS(current.lexema)) {
						ErroresSemanticos.add("Error variable "+current.lexema+" ya existe Linea "+current.linea);
					}
					else {
						Temporales.add(new Variable(null,current.lexema,""));
					}
					SiguienteToken();
					LISTA();
				}
				else {
					error = true;
					String e = "Se esperaba Identificador en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			else {
				//simulacion de produccion LISTA -> ɛ
				if(Token == 6) {
					//sentencia nula
				}
				else {
					error = true;
					String e = "Se esperaba Coma en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 6) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void TIPO() {
			//simulacion de produccion TIPO -> Pr.entero
			if(Token == 101) {
				//Regla semantica
				tip = 1;
				for(Variable item : Temporales) {
					item.Tipo = "Entero";
				}
				/*if(p)
					Ptemporal.Parametros.get(Ptemporal.Parametros.size() - 1).Tipo = "Entero";
				else
					if(f)
						Ftemporal.Parametros.get(Ftemporal.Parametros.size() -1).Tipo = "Entero";*/
				SiguienteToken();
			}
			else {
				//simulacion de produccion TIPO -> Pr.largo
				if(Token == 102) {
					tip = 2;
					for(Variable item : Temporales) {
						item.Tipo = "Largo";
					}
					/*if(p)
						Ptemporal.Parametros.get(Ptemporal.Parametros.size() - 1).Tipo = "Largo";
					else
						if(f)
							Ftemporal.Parametros.get(Ftemporal.Parametros.size() -1).Tipo = "Largo";*/
					SiguienteToken();
				}
				else {
					//simulacion de produccion TIPO -> Pr.cadena
					if(Token == 103) {
						tip = 3;
						for(Variable item : Temporales) {
							item.Tipo = "Cadena";
						}
						/*if(p)
							Ptemporal.Parametros.get(Ptemporal.Parametros.size() - 1).Tipo = "Cadena";
						else
							if(f)
								Ftemporal.Parametros.get(Ftemporal.Parametros.size() -1).Tipo = "Cadena";*/
						SiguienteToken();
					}
					else {
						//simulacion de produccion TIPO -> Pr.real
						if(Token == 104) {
							tip = 4;
							for(Variable item : Temporales) {
								item.Tipo = "Real";
							}
							/*if(p)
								Ptemporal.Parametros.get(Ptemporal.Parametros.size() - 1).Tipo = "Real";
							else
								if(f)
									Ftemporal.Parametros.get(Ftemporal.Parametros.size() -1).Tipo = "Real";*/
							SiguienteToken();
						}
						else {
							//simulacion de produccion TIPO -> Pr.caracter
							if(Token == 105) {
								tip = 5;
								for(Variable item : Temporales) {
									item.Tipo = "Caracter";
								}
								/*if(p)
									Ptemporal.Parametros.get(Ptemporal.Parametros.size() - 1).Tipo = "Caracter";
								else
									if(f)
										Ftemporal.Parametros.get(Ftemporal.Parametros.size() -1).Tipo = "Caracter";*/
								SiguienteToken();
							}
							else {
								tip = 0;
								error = true;
								String e = "Se esperaba Tipo valido en linea: "+ListaTokens.get(guia).linea;
								Errores.add(e);
							}
						}
					}
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107 ||Token ==106 || Token == 7) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void CONDICIONAL() {
			//simulacion de la produccion CONDICIONAL -> Pr.si CONDICION Pr.entonces Pr.inicio SENTENCIA Pr.fin SINO
			if(Token == 111) {
				SiguienteToken();
				CONDICION();
				if(Token == 112) {
					SiguienteToken();
					if(Token == 106) {
						SiguienteToken();
						SENTENCIA();
						if(Token == 107) {
							SiguienteToken();
							SINO();
						}
						else {
							error = true;
							String e = "Se esperaba Fin en linea: "+ListaTokens.get(guia).linea;
							Errores.add(e);
						}
					}
					else {
						error = true;
						String e = "Se esperaba Inicio en linea: "+ListaTokens.get(guia).linea;
						Errores.add(e);
					}
				}
				else {
					error = true;
					String e = "Se esperaba Entonces en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			else {
				error = true;
				String e = "Se esperaba Si en linea: "+ListaTokens.get(guia).linea;
				Errores.add(e);
			}
			
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void SINO() {
			//simulacion de produccion SINO -> Pr.sino Pr.inicio SENTENCIA Pr.fin
			if(Token == 113) {
				SiguienteToken();
				if(Token == 106) {
					SiguienteToken();
					SENTENCIA();
					if(Token == 107) {
						SiguienteToken();
					}
				}
			}
			else {
				//simulacion de produccion SINO -> e
				if(Token == 1 || Token == 111 || Token == 114 
						|| Token == 116 || Token == 117 || Token == 119
						|| Token == 118 || Token == 107) {
					//sentencia nula
				}
				else {
					error = true;
					String e = "Se esperaba sino en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		//sujeta a posibles cambios
		private void CONDICION() {
			//simulacion de produccion CONDICION -> VALORES OPERADORLOGICO VALORES
			if(Token == 1 || Token == 2 || Token == 3 || Token == 4 || Token == 5) {
				VALORES();
				OPERADORLOGICO();
				VALORES();
			}
			else {
				error = true;
				String e = "Se esperaba Valor en linea: "+ListaTokens.get(guia).linea;
				Errores.add(e);
			}
			
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107 || Token == 122 || Token == 112) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void VALORES() {
			//simulacion de la procuccion VALORES -> Indentificador
			if(Token == 1) {
				if(!ExisteTS(current.lexema)) {
					ErroresSemanticos.add("Error no existe una variable "+current.lexema+" Linea "+current.linea);
				}
				SiguienteToken();
			}
			else {
				//simulacion de la procuccion VALORES -> Entero
				if(Token == 2) {
					SiguienteToken();
				}
				else {
					//simulacion de la procuccion VALORES -> Real
					if(Token == 3) {
						SiguienteToken();
					}
					else {
						//simulacion de la procuccion VALORES -> Cadena
						if(Token == 4) {
							SiguienteToken();
						}
						else {
							//simulacion de la procuccion VALORES -> Caracter
							if(Token == 5) {
								SiguienteToken();
							}
							else {
								error = true;
								String e = "Se esperaba Valor Valido en linea: "+ListaTokens.get(guia).linea;
								Errores.add(e);
							}
						}
					}
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 10 || Token ==11 || Token ==12 || Token ==13 || Token ==14 || Token ==15
							|| Token ==1 || Token ==112 || Token ==122 || Token ==117 || Token ==111
							|| Token ==114 || Token ==116 || Token ==119 || Token ==118 || Token ==7
							|| Token == 7 || Token ==9) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void OPERADORLOGICO() {
			//simulacion de la procuccion OPERADORLOGICO -> Igual
			if(Token == 10) {
				SiguienteToken();
			}
			else {
				//simulacion de la procuccion OPERADORLOGICO -> Diferente
				if(Token == 11) {
					SiguienteToken();
				}
				else {
					//simulacion de la procuccion OPERADORLOGICO -> Mayor
					if(Token == 12) {
						SiguienteToken();
					}
					else {
						//simulacion de la procuccion OPERADORLOGICO -> Menor
						if(Token == 13) {
							SiguienteToken();
						}
						else {
							//simulacion de la procuccion OPERADORLOGICO -> MayorIgual
							if(Token == 14) {
								SiguienteToken();
							}
							else {
								//simulacion de la procuccion OPERADORLOGICO -> MenorIgual
								if(Token == 15) {
									SiguienteToken();
								}
								else {
									error = true;
									String e = "Se esperaba Operador Valido en linea: "+ListaTokens.get(guia).linea;
									Errores.add(e);
								}
							}
						}
					}
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 || Token == 2 || Token == 3 || Token == 4|| Token == 5) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void PARA() {
			//simulacion de la produccion PARA -> Pr.para identificador Igual Entero Pr.hasta CONDICION Pr.con 	AUMENTO Pr.inicio SENTENCIA Pr.fin
			if(Token == 114) {
				SiguienteToken();
				if(Token == 1) {
					SiguienteToken();
					if(Token == 10) {
						SiguienteToken();
						if(Token == 2) {
							SiguienteToken();
							if(Token == 115) {
								SiguienteToken();
								CONDICION();
								if(Token == 122) {
									SiguienteToken();
									AUMENTO();
									if(Token == 106) {
										SiguienteToken();
										SENTENCIA();
										if(Token == 107) {
											SiguienteToken();
										}
										else {
											error = true;
											String e = "Se esperaba Fin en linea: "+ListaTokens.get(guia).linea;
											Errores.add(e);
										}
									}
									else {
										error = true;
										String e = "Se esperaba Inicio en linea: "+ListaTokens.get(guia).linea;
										Errores.add(e);
									}
								}
								else {
									error = true;
									String e = "Se esperaba Con en linea: "+ListaTokens.get(guia).linea;
									Errores.add(e);
								}
							}
							else {
								error = true;
								String e = "Se esperaba Hasta en linea: "+ListaTokens.get(guia).linea;
								Errores.add(e);
							}
						}
						else {
							error = true;
							String e = "Se esperaba Valor Entero en linea: "+ListaTokens.get(guia).linea;
							Errores.add(e);
						}
					}
					else {
						error = true;
						String e = "Se esperaba Igual en linea: "+ListaTokens.get(guia).linea;
						Errores.add(e);
					}
				}
				else {
					error = true;
					String e = "Se esperaba Identificador en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			else {
				error = true;
				String e = "Se esperaba Para en linea: "+ListaTokens.get(guia).linea;
				Errores.add(e);
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void AUMENTO() {
			//simulacion de la produccion AUMENTO -> Pr.incremento
			if(Token == 123) {
				SiguienteToken();
			}
			else {
				//Simulacion de la produccion AUMENTO -> Pr.decremento
				if(Token == 124) {
					SiguienteToken();
				}
				else {
					error = true;
					String e = "Se esperaba Incremento o Decremento en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				/*while(error) {
					if(Token == 106) {
						error = false;
					}else {
						SiguienteToken();
					}
				}*/
				SiguienteToken();
				error = false;
			}
		}
		
		private void MIENTRAS() {
			//simulacion de la produccion MIENTRAS -> Pr.mientras CONDICION Pr.hacer Pr.inicio SENTENCIA Pr.fin
			if(Token == 116) {
				SiguienteToken();
				CONDICION();
				if(Token == 117) {
					SiguienteToken();
					if(Token == 106) {
						SiguienteToken();
						SENTENCIA();
						if(Token == 107) {
							SiguienteToken();
						}
						else {
							error = true;
							String e = "Se esperaba Fin en linea: "+ListaTokens.get(guia).linea;
							Errores.add(e);
						}
					}
					else {
						error = true;
						String e = "Se esperaba Inicio en linea: "+ListaTokens.get(guia).linea;
						Errores.add(e);
					}
				}
				else {
					error = true;
					String e = "Se esperaba Hacer en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			else {
				error = true;
				String e = "Se esperaba Mientras en linea: "+ListaTokens.get(guia).linea;
				Errores.add(e);
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void HACER() {
			//simulacion de la produccion HACER -> Pr.hacer Pr.inicio SENTENCIA Pr.fin Pr.mientras CONDICION
			if(Token == 117) {
				SiguienteToken();
				if(Token == 106) {
					SiguienteToken();
					SENTENCIA();
					if(Token == 107) {
						SiguienteToken();
						if(Token == 116) {
							SiguienteToken();
							CONDICION();
						}
						else {
							error = true;
							String e = "Se esperaba Mientras en linea: "+ListaTokens.get(guia).linea;
							Errores.add(e);
						}
					}
					else {
						error = true;
						String e = "Se esperaba Fin en linea: "+ListaTokens.get(guia).linea;
						Errores.add(e);
					}
				}
				else {
					error = true;
					String e = "Se esperaba Inicio en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			else {
				error = true;
				String e = "Se esperaba Hacer en linea: "+ListaTokens.get(guia).linea;
				Errores.add(e);
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void ESCRIBIR() {
			//simulacion de la produccion ESCRIBIR -> Pr.escribir Pabierto PARAMETROSE Pcerrado
			if(Token == 118) {
				SiguienteToken();
				if(Token == 8) {
					SiguienteToken();
					num = 0;
					PARAMETROSE();
					if(num > 3)
						ErroresSemanticos.add("El Escribir posee mas de 3 parametros Linea "+current.linea);
					if(Token == 9) {
						SiguienteToken();
					}
					else {
						error = true;
						String e = "Se esperaba Parentesis Cerrado en linea: "+ListaTokens.get(guia).linea;
						Errores.add(e);
					}
				}
				else {
					error = true;
					String e = "Se esperaba Parentesis Abierto en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			else {
				error = true;
				String e = "Se esperaba Escribir en linea: "+ListaTokens.get(guia).linea;
				Errores.add(e);
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void PARAMETROSE() {
			//simulacion de produccion PARAMETROSE -> VALORES SIG
			if(Token == 1 || Token == 2 || Token == 3 || Token == 4 || Token == 5) {
				//if(p || f)
				num++;
				VALORES();
				SIG();
			}
			else {
				error = true;
				String e = "Se esperaba un Valor en linea: "+ListaTokens.get(guia).linea;
				Errores.add(e);
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 9) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void SIG() {
			//simulacion de la produccion SIG -> Coma VALORES
			if(Token == 7) {
				SiguienteToken();
				//if(p || f)
				num++;
				VALORES();
				SIG();
			}
			else {
				if(Token == 9) {
					//sentencia nula
				}
				else {
					error = true;
					String e = "Se esperaba Coma en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 6) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void LEER() {
			//simulacion de produccion LEER -> Pr.leer Pabierto Identificador P.cerrado
			if(Token == 119) {
				SiguienteToken();
				if(Token == 8) {
					SiguienteToken();
					if(Token == 1) {
						if(!ExisteTS(current.lexema)) {
							ErroresSemanticos.add("Error no existe una variable "+current.lexema+" Linea "+current.linea);
						}
						SiguienteToken();
						if(Token == 9) {
							SiguienteToken();
						}
						else {
							error = true;
							String e = "Se esperaba Parentesis cerrado en linea: "+ListaTokens.get(guia).linea;
							Errores.add(e);
						}
					}
					else {
						error = true;
						String e = "Se esperaba Identificador en linea: "+ListaTokens.get(guia).linea;
						Errores.add(e);
					}
				}
				else {
					error = true;
					String e = "Se esperaba Parentesis Abierto en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			else {
				error = true;
				String e = "Se esperaba Leer en linea: "+ListaTokens.get(guia).linea;
				Errores.add(e);
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void ASIGNACION() {
			//simulacion de produccion ASIGNACION -> Identificador OPE
			if(Token == 10) {
				SiguienteToken();
				OPE();
			}
			else {
				error = true;
				String e = "Se esperaba Identificador en linea: "+ListaTokens.get(guia).linea;
				Errores.add(e);
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void OPE() {
			//simulacion de produccion OPE -> OPE2
			if(Token == 2 || Token == 3 || Token == 4 ||Token == 5) {
				OPE2();
			}
			else{
				if(Token == 1) {
					SiguienteToken();
					F();
				}
				else {
					error = true;
					String e = "Se esperaba Valor para ser asignado en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void OPE2() {
			//simulacion de produccion OPE2 -> Entero CONTINUCACION
			if(Token == 2) {
				SiguienteToken();
				CONTINUACION();
			}
			else {
				//simulacion de produccion OPE2 -> Real CONTINUCACION
				if(Token == 3) {
					SiguienteToken();
					CONTINUACION();
				}
				else {
					//simulacion de produccion OPE2 -> Cadena
					if(Token == 4) {
						SiguienteToken();
					}
					else {
						//simulacion de produccion OPE2 -> Caracter
						if(Token == 5) {
							SiguienteToken();
						}
						else {
							error = true;
							String e = "Se esperaba un Valor para asignar en linea: "+ListaTokens.get(guia).linea;
							Errores.add(e);
						}
					}
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void F() {
			//simulacion de la produccion F -> Pabierto PARAMETROS_LLA Pcerrado 
			if(Token == 8) {
				SiguienteToken();
				p = true;
				PARAMETROS_LLA();
				p = false;
				if(Token == 9) {
					SiguienteToken();
				}
				else {
					error = true;
					String e = "Se esperaba Parentesis cerrado en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			else {
				//simulacion de produccion F -> CONTINUACION
				if(Token == 16 || Token == 17 || Token == 18 || Token == 19 || Token == 20) {
					CONTINUACION();
				}
				else {
					error = true;
					String e = "Se esperaba Parentesis Abierto u operador en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void PARAMETROS_LLA() {
			//simulacion de produccion PARAMETROS_LLA -> PARAMETROSE
			if(Token == 1 || Token == 2 || Token == 3 || Token == 4 || Token == 5) {
				PARAMETROSE();
			}
			else {
				//simulacion de produccion PARAMETROS_LLA -> e
				if(Token == 9) {
					//sentencia nula
				}
				else {
					error = true;
					String e = "Se esperaba parametros o cierre en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 9) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void CONTINUACION() {
			//simulacion de produccion CONTINUACION -> OPERADORES OPERANDOS CONTINUACION 
			if(Token == 16 || Token == 17 || Token == 18 || Token == 19 || Token == 20) {
				OPERADORES();
				OPERANDOS();
				CONTINUACION();
			}
			else {
				//simulacion de produccion CONTINUACION -> ɛ
				if(Token == 1 || Token == 111 || Token == 114 
						|| Token == 116 || Token == 117 || Token == 119
						|| Token == 118 || Token == 107) {
					//sentencia nula
				}
				else {
					error = true;
					String e = "error de sentencia en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void OPERANDOS() {
			//simulacion de produccion OPERANDOS -> Identificador
			if(Token == 1) {
				if(!ExisteTS(current.lexema)) {
					ErroresSemanticos.add("Error no existe una variable "+current.lexema+" Linea "+current.linea);
				}
				SiguienteToken();
			}
			else {
				//simulacion del produccion OPERANDOS -> Entero
				if(Token == 2) {
					SiguienteToken();
				}
				else {
					//simulacion de produccion OPERANDOS -> REAL 
					if(Token == 3) {
						SiguienteToken();
					}
					else {
						error = true;
						String e = "Se esperaba Operando Valido en linea: "+ListaTokens.get(guia).linea;
						Errores.add(e);
					}
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107 || Token == 16 || Token ==17 || Token ==18
							|| Token == 19 || Token == 20) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void OPERADORES() {
			//simulacion de produccion OPERADORES -> S.suma
			if(Token == 16) {
				SiguienteToken();
			}
			else {
				//simulacion de produccion OPERADORES -> S.resta
				if(Token == 17) {
					SiguienteToken();
				}
				else {
					//simulacion de produccion OPERADORES -> S.multiplicacion
					if(Token == 18) {
						SiguienteToken();
					}
					else {
						//simulacion de produccion OPERADORES -> S.division
						if(Token == 19) {
							SiguienteToken();
						}
						else {
							//simulacion de produccion OPERADORES -> S.potencia
							if(Token == 20) {
								SiguienteToken();
							}
							else {
								error = true;
								String e = "Se esperaba Operador Valido en linea: "+ListaTokens.get(guia).linea;
								Errores.add(e);
							}
						}
					}
				}
			}
			
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==2 || Token == 3) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void FUNCION() {
			//simulacion de produccion FUNCION -> Pr.funcion Identificador Pabierto PARAMETROS Pcerrado Dos_puntos TIPO Pr.inicio SENTENCIA Pr.retornar RETORNO Pr.fin
			if(Token == 108) {
				Ftemporal = new Funcion();
				estatus = 2;
				String retornoT = "";
				SiguienteToken();
				if(Token == 1) {
					//accion semantica
					Ftemporal.Nombre = current.lexema;
					SiguienteToken();
					if(Token == 8) {
						SiguienteToken();
						PARAMETROS();
						if(Token == 9) {
							SiguienteToken();
							if(Token == 6) {
								SiguienteToken();
								TIPO();
								{
									switch(tip){
									case 1:
										retornoT = "Entero";
										break;
									case 2:
										retornoT = "Largo";
										break;
									case 3:
										retornoT = "Cadena";
										break;
									case 4:
										retornoT = "Real";
										break;
									case 5:
										retornoT = "Caracter";
										break;
									}
								}
								//accion semantica
								if(ExisteFuncion(Ftemporal.Nombre)) {
									ErroresSemanticos.add("Funcion "+Ftemporal.Nombre+" Ya fue declarado Linea "+current.linea);
								}
								else {
									//la funcion se agrega abajo despues del token 107
								}
								if(Token == 106) {
									SiguienteToken();
									SENTENCIA();
									if(Token == 109) {
										SiguienteToken();
										RETORNO();
										{
											String t = "";
											switch(tip){
											    case -1:
											    	if(!ExisteTS(retardo)) {
											    		ErroresSemanticos.add("Variable a retornar "+retardo+" no existe Linea "+current.linea);
											    	}
											    	else {
											    		t = BuscarTipo(retardo);
											    	}
											    	break;
												case 1:
													t = "Entero";
													break;
												case 2:
													t = "Largo";
													break;
												case 3:
													t = "Cadena";
													break;
												case 4:
													t = "Real";
													break;
												case 5:
													t = "Caracter";
													break;
											}
											
											if(!t.equals(retornoT)) {
												if(t.equals("Largo") && (retornoT.equals("Entero") || retornoT.equals("Largo"))) {
													
												}
												else {
													ErroresSemanticos.add("El tipo de retorno no coincide con el declarado Linea"+current.linea);
												}
											}
											else {
												ErroresSemanticos.add("El tipo de retorno no coincide con el declarado Linea"+current.linea);
											}
										}
										if(Token == 107) {
											if(Ptemporal.Parametros.size() > 3)
												ErroresSemanticos.add("El procedimiento "+Ptemporal.Nombre+" tiene mas de 3 parametros");
											Ftemporal.TipoRetorno = retornoT;
											Funciones.add(Ftemporal);
											SiguienteToken();
										}
										else {
											error = true;
											String e = "Se esperaba Fin en linea: "+ListaTokens.get(guia).linea;
											Errores.add(e);
										}
									}
									else {
										error = true;
										String e = "Se esperaba Retornar en linea: "+ListaTokens.get(guia).linea;
										Errores.add(e);
									}
								}
								else {
									error = true;
									String e = "Se esperaba Inicio en linea: "+ListaTokens.get(guia).linea;
									Errores.add(e);
								}
							}
							else {
								error = true;
								String e = "Se esperaba Dos_puntos en linea: "+ListaTokens.get(guia).linea;
								Errores.add(e);
							}
						}
						else {
							error = true;
							String e = "Se esperaba Parentesis cerrado en linea: "+ListaTokens.get(guia).linea;
							Errores.add(e);
						}
					}
					else {
						error = true;
						String e = "Se esperaba Parentesis Abierto en linea: "+ListaTokens.get(guia).linea;
						Errores.add(e);
					}
				}
				else {
					error = true;
					String e = "Se esperaba Identificador en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			else {
				error = true;
				String e = "Se esperaba Palabra Funcion en linea: "+ListaTokens.get(guia).linea;
				Errores.add(e);
			}
			
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==108 || Token == 110 || Token == 120) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void RETORNO() {
			//simulacion de produccion RETORNO -> OPE2
			if(Token == 2 || Token == 3| Token == 4 || Token == 5) {
				tip = Token;
				OPE2();
			}
			else {
				//simulacion de produccion RETORNO -> Identificador CONTINUACION
				if(Token == 1) {
					retardo = current.lexema;
					tip = -1;
					SiguienteToken();
					CONTINUACION();
				}
				else {
					error = true;
					String e = "Se esperaba valor Valido a retornar en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token ==107) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void PARAMETROS() {
			//simulacion de produccion PARAMETROS -> Identificador Dos_puntos TIPO PSIG 
			if(Token == 1) {
				String l = current.lexema;
				
				SiguienteToken();
				if(Token == 6) {
					SiguienteToken();
					
					TIPO();
					{
						String t = "";
						switch(tip){
							case 1:
								t = "Entero";
								break;
							case 2:
								t = "Largo";
								break;
							case 3:
								t = "Cadena";
								break;
							case 4:
								t = "Real";
								break;
							case 5:
								t = "Caracter";
								break;
						}
						if(p || f) {
							if(ExisteTS(current.lexema)) {
								ErroresSemanticos.add("Error en parametro, ya existe una variable declarada con nombre "+current.lexema+" Linea "+current.linea);
							}
							else {
								if(p)
									Ptemporal.Parametros.add(new Variable(null, l,t));
								else
									if(f)
										Ftemporal.Parametros.add(new Variable(null, l,t));
							}
						}
						
						/*if(p)
							Ptemporal.Parametros.get(Ptemporal.Parametros.size() -1 ).Tipo = t;
						else
							if(f)
								Ftemporal.Parametros.get(Ftemporal.Parametros.size() -1 ).Tipo = t;*/
					}
					PSIG();
					
				}
				else {
					error = true;
					String e = "Se esperaba Dos_Puntos en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			else {
				//simulacion de produccion PARAMETROS -> ɛ
				if(Token == 9) {
					//sentencia nula
				}
				else {
					error = true;
					String e = "Se esperaba Parentesis Cerrado en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 9) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void PSIG() {
			//simulacion de produccion PSIG -> Coma PARAMETROS
			if(Token == 7) {
				SiguienteToken();
				PARAMETROS();
			}
			else {
				//simulacion de produccion PSIG -> ɛ
				if(Token == 9) {
					//sentencia nula
				}
				else {
					error = true;
					String e = "Se esperaba Coma en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 9) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void PROCEDIMIENTO() {
			//simulacion de produccion PROCEDIMIENTO -> Pr.procedimiento Identificador Pabierto PARAMETROS Pcerrado Pr.inicio SENTENCIA Pr.fin
			if(Token == 110) {
				Ptemporal = new Procedimiento();
				SiguienteToken();
				if(Token == 1) {
					//accion semantica
					Ptemporal.Nombre = current.lexema;
					SiguienteToken();
					if(Token == 8) {
						SiguienteToken();
						PARAMETROS();
						//accion semantica
						if(ExisteProcedimiento(Ptemporal.Nombre)) {
							ErroresSemanticos.add("Error el procedimiento "+Ptemporal.Nombre+" Ya existe declarado Linea "+current.linea);
						}
						else {
							Procedimientos.add(Ptemporal);
						}
						if(Token == 9) {
							SiguienteToken();
							if(Token == 106) {
								SiguienteToken();
								SENTENCIA();
								if(Token == 107) {
									if(Ptemporal.Parametros.size() > 3)
										ErroresSemanticos.add("El procedimiento "+Ptemporal.Nombre+" tiene mas de 3 parametros");
									SiguienteToken();
								}
								else {
									error = true;
									String e = "Se esperaba Fin en linea: "+ListaTokens.get(guia).linea;
									Errores.add(e);
								}
							}
							else {
								error = true;
								String e = "Se esperaba Inicio en linea: "+ListaTokens.get(guia).linea;
								Errores.add(e);
							}
						}
						else {
							error = true;
							String e = "Se esperaba Parentesis cerrado en linea: "+ListaTokens.get(guia).linea;
							Errores.add(e);
						}
					}
					else {
						error = true;
						String e = "Se esperaba Parentesis Abierto en linea: "+ListaTokens.get(guia).linea;
						Errores.add(e);
					}
				}
				else {
					error = true;
					String e = "Se esperaba Identificador en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			else {
				error = true;
				String e = "Se esperaba Palabra Procedimiento en linea: "+ListaTokens.get(guia).linea;
				Errores.add(e);
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==108 || Token == 110 || Token == 120) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
		
		private void LLAMADA() {
			//simulacion de produccion LLAMADA -> Pabierto PARAMETROS_LLA Pcerrado
			if(Token == 8) {
				for(Procedimiento item : Procedimientos) {
					if(item.Nombre.equals(retardo)) {
						Ptemporal = item;
						p = true;
					}
				}
				
				for(Funcion item : Funciones) {
					if(item.Nombre.equals(retardo)) {
						Ftemporal = item;
						f = true;
					}
				}
				num = 0;
				SiguienteToken();
				PARAMETROS_LLA();
				if(Ptemporal.Parametros.size() != num && p)
					ErroresSemanticos.add("El numero de parametros de "+Ptemporal.Nombre+" no coincide con el declarado Linea"+current.linea);
				else
					if(Ftemporal.Parametros.size() != num && f)
						ErroresSemanticos.add("El numero de parametros de "+Ftemporal.Nombre+" no coincide con el declarado Linea"+current.linea);
				p = false;
				f = false;
				if(Token == 9) {
					SiguienteToken();
				}
				else {
					error = true;
					String e = "Se esperaba Parentesis cerrado en linea: "+ListaTokens.get(guia).linea;
					Errores.add(e);
				}
			}
			else {
				error = true;
				String e = "Se esperaba Parentesis abierto en linea: "+ListaTokens.get(guia).linea;
				Errores.add(e);
			}
			//implementacion de recuperacion de errores
			if(error) {
				while(error) {
					if(Token == 1 ||Token ==111 ||Token ==114 ||Token ==116 ||Token ==117 ||Token ==119
							||Token ==118 ||Token ==107) {
						error = false;
					}else {
						SiguienteToken();
					}
				}
			}
		}
}
