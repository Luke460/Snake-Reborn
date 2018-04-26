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
	
	public static boolean veroAl(int percentuale){
		if(percentuale<0||percentuale>100) 
			throw new IllegalArgumentException("La percentuale deve avere un valore da 0 a 100 (estremi inclusi)!");
		int casuale = (int) ((Math.random()*100)+1);
		if(casuale<=percentuale) return true;
		return false;
	}
	
	public static int getNumberFromChar(char c) {
		if(c=='0') return 0;
		if(c=='1') return 1;
		if(c=='2') return 2;
		if(c=='3') return 3;
		if(c=='4') return 4;
		if(c=='5') return 5;
		if(c=='6') return 6;
		if(c=='7') return 7;
		if(c=='8') return 8;
		if(c=='9') return 9;
		if(c=='a') return 10;
		if(c=='b') return 11;
		if(c=='c') return 12;
		if(c=='d') return 13;
		if(c=='e') return 14;
		if(c=='f') return 15;
		if(c=='g') return 16;
		if(c=='h') return 17;
		if(c=='i') return 18;
		if(c=='j') return 19;
		if(c=='k') return 20;
		if(c=='l') return 21;
		if(c=='m') return 22;
		if(c=='n') return 23;
		if(c=='o') return 24;
		if(c=='p') return 25;
		if(c=='q') return 26;
		if(c=='r') return 27;
		if(c=='s') return 28;
		if(c=='t') return 29;
		if(c=='u') return 30;
		if(c=='v') return 31;
		if(c=='w') return 32;
		if(c=='x') return 33;
		if(c=='y') return 34;
		if(c=='z') return 35;
		return -1;
	}
	public static char getCharFromNumber(int c) {
		if(c==0) return '0';
		if(c==1) return '1';
		if(c==2) return '2';
		if(c==3) return '3';
		if(c==4) return '4';
		if(c==5) return '5';
		if(c==6) return '6';
		if(c==7) return '7';
		if(c==8) return '8';
		if(c==9) return '9';
		if(c==10) return 'a';
		if(c==11) return 'b';
		if(c==12) return 'c';
		if(c==13) return 'd';
		if(c==14) return 'e';
		if(c==15) return 'f';
		if(c==16) return 'g';
		if(c==17) return 'h';
		if(c==18) return 'i';
		if(c==19) return 'j';
		if(c==20) return 'k';
		if(c==21) return 'l';
		if(c==22) return 'm';
		if(c==23) return 'n';
		if(c==24) return 'o';
		if(c==25) return 'p';
		if(c==26) return 'q';
		if(c==27) return 'r';
		if(c==28) return 's';
		if(c==29) return 't';
		if(c==30) return 'u';
		if(c==31) return 'v';
		if(c==32) return 'w';
		if(c==33) return 'x';
		if(c==34) return 'y';
		if(c==35) return 'z';
		return 'Z';
	}
}
