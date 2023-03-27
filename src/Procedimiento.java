import java.util.ArrayList;

public class Procedimiento {
	public String Nombre;
	public ArrayList<Variable> Variables;
	public ArrayList<Variable> Parametros;
	
	public Procedimiento() {
		Variables = new ArrayList<Variable>();
		Parametros = new ArrayList<Variable>();
		Nombre = "";
	}
}

