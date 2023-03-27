import java.util.ArrayList;

public class Funcion {
	public String Nombre;
	public ArrayList<Variable> Variables;
	public String TipoRetorno;
	public ArrayList<Variable> Parametros;
	public Object ValorRetorno;
	
	public Funcion() {
		Variables = new ArrayList<Variable>();
		Parametros = new ArrayList<Variable>();
		Nombre = "";
		TipoRetorno = "";
		ValorRetorno = "";
	}
}
