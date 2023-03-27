
public class Token {
	public int id_token;
	public String lexema;
	public int linea;
	public int columna;
	public Token(int i,String l,int col,int li) {
		this.id_token = i;
		this.lexema = l;
		this.linea = li;
		this.columna = col;
	}
}
