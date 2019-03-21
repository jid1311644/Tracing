package servers.webserver;

import java.util.LinkedList;

public class Encrypt {

	private String plainText = "";	//明文
	private String cipherText = "";	//密文
	private String name = "";		//密钥
	private LinkedList<String> table = new LinkedList<String>();

	public Encrypt(String name, String psw) {
		this.name = name;
		this.plainText = psw;
		for(int i = (int) 'A'; i <= (int) 'Z'; i++) {
			table.add((char) i + "");
			table.add((char) (i + 32) + "");
		}
		for(int i = 0; i < 10; i++) {
			table.add(i + "");
		}
		table.add("_");
	}
	
	public boolean isUserName() {	//用户名为标识符的书写规则
		int i = table.indexOf(name.substring(0, 1));
		if(i == -1 || (i >= 52 && i <= 61))
			return false;
		for(i = 1; i < name.length(); i++)
			if(table.indexOf(name.substring(0, 1)) == -1)
				return false;
		return true;
	}
	
	public String Encrypt() {
		int pl = plainText.length();
		int nl = name.length();
		if(pl % nl != 0){
			for(int i = 0; i < nl - (pl % nl); i++)
				plainText += "_";
		}
		pl = plainText.length();
		int n = pl / nl;
		plainText += " ";
		name += " ";
		String[] t = new String[pl];
		int count = 0;
		int i, j;
		for(i = 0; i < n; i++){
			for(j = 0; j < nl; j++)
				t[count++] = new String(plainText.substring(
						i * nl + j, i * nl + (j + 1)) + name.substring(j, j + 1));
		}
		LinkedList<Integer> A = new LinkedList<>();
		for(i = 0; i < nl; i++)
			A.add(table.indexOf(name.substring(i, i + 1)));
		LinkedList<Integer> B = new LinkedList<Integer>();
		for(i = 0; i < nl; i++)
			B.add(A.get(i));
		sort(A);
		for(j = 0; j < nl; j++){
			A.add(B.indexOf(A.get(j)));
			B.set(B.indexOf(A.get(j)), -1);
		}
		for(j = 0; j < nl; j++)
			A.poll();
		for(i = 0; i < n; i++){
			for(j = 0; j < nl; j++){
				cipherText += t[i * nl + A.get(j)].substring(0, 1);
			}
		}
		return cipherText;
	}
	
	public void sort(LinkedList<Integer> a) {
		int n = a.size();
		for(int j = 0; j < n - 1; j++) {
			for(int i = 0; i < n - 1 - j; i++){
				if(a.get(i) > a.get(i+1)) {
					int t = a.get(i);
					a.set(i, a.get(i + 1));
					a.set(i + 1, t);
				}
			}
		}
	}
	
}
