package supporto;

import java.util.Collection;
import java.util.LinkedList;

public class Utility {

	public static Collection<? extends Character> stringaToArray(String stringa) {
		LinkedList<Character> listaCaratteri = new LinkedList<>();
		for(int i = 0; i<stringa.length(); i++){
			listaCaratteri.add(stringa.charAt(i));
		}
		return listaCaratteri;
	}
	
	public static boolean isPari(int numero){
		if((numero%2)!=0) return false;
		return true;
	}

	public static int massimoTra(int a, int b) {
		if(a>b)return a;
		return b;
	}
	
	public static int minimoTra(int a, int b) {
		if(a<b)return a;
		return b;
	}

}
