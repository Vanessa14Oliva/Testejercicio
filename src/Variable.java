
public class Variable {
	public String Lexema;
	public Object Valor;
	public String Tipo;
	
	public Variable(){
		Valor = new Object();
		Lexema = "";
		Tipo = "";
	}
	
	public Variable(Object v,String l, String t){
		Valor = new Object();
		Valor = v;
		Lexema = l;
		Tipo = t;
	}
}
