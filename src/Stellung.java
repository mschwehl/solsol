


public class Stellung {

	Stellung vorgaenger = null;
	String richtung = "";
	
	byte[][] layout = new byte[][] {
			
			{ 0,0,1,1,1,0,0 } ,
			{ 0,0,1,1,1,0,0 } ,
			{ 1,1,1,1,1,1,1 } ,
			{ 1,1,1,1,1,1,1 } ,
			{ 1,1,1,1,1,1,1 } ,
			{ 0,0,1,1,1,0,0 } ,
			{ 0,0,1,1,1,0,0 } 			
		};
		
	int ix = 0, iy = 0;
	
	int count = 0;
	
	public void setFocus(int x, int y) {
		ix = x;
		iy = y;
	}
	
	public byte[][] root = new byte[7][7];
	
	public Stellung(byte[][] root) {
		this.root = root;
		this.count = 0;
		for (int x=0 ; x < root.length ; x++) {
			for (int y=0 ; y < root.length ; y++) {
					if (root[x][y] > 0) {
						count++;
					};
			}
			
		}	
		
	}
	
	public Stellung getClone() {
		Stellung s = new Stellung(clone());
		return s;
	}


	protected byte[][] clone() {
		
		count=0;
		byte[][] tmp = new byte[7][7];
		
		for (int x=0 ; x < root.length ; x++) {
			for (int y=0 ; y < root.length ; y++) {
				tmp[x][y] = root[x][y];
				
				if (tmp[x][y] > 0) {
					count ++;
				}
				
			}	
		} 
		return tmp;
		
	}
	
	
	public boolean isSolved() {

		if (count > 1) {
			return false;
		}
		
		int countLocal = 0;
		
		for (int x=0 ; x < root.length ; x++) {
			for (int y=0 ; y < root.length ; y++) {
				if (root[x][y] > 0) { 
					countLocal ++; 
				}
				
				if (countLocal > 1) {
					return false;
				}
			}
		}
		
		System.out.println("" + ix  + ":" +  iy + " -> " + richtung);
		System.out.println(this);

		System.out.println("---------------------------------------");
		
		while(vorgaenger != null) {
			System.out.println("" + vorgaenger.ix  + ":" +  vorgaenger.iy + " -> " + vorgaenger.richtung);
			System.out.println(vorgaenger);
			vorgaenger = vorgaenger.vorgaenger;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return printStellung().toString();
	}
	
	public StringBuffer printStellung() {
		
		StringBuffer sb = new StringBuffer();
		
		String zeichen = "";
		
		for (int x=0 ; x < root.length ; x++) {
			
			for (int y=0 ; y < root.length ; y++) {

					zeichen = ((x == ix && y == iy) ? "X" : "0");
					
					if (root[x][y] > 0) {
						sb.append(" ").append(zeichen).append(" ");
					} else {
						sb.append("   ");
					}
				
				
			}
			
			sb.append("\n");
		}

		
		sb.append("\n");
		
		return sb;
	}
	

	
	public boolean isObenMoeglich(int x, int y) {
		if (root[x][y] == 0 ){
			return false;
		}
		
		if (!isInLayout(x, y) || !isInLayout(x + 2, y)) {
			return false;
		}
		

		
		if ( y < 5 && root[x+2][y] == 0 && root[x+1][y] == 1) {
//			System.out.println("unten:  " + x + "|" + y);
			return true;
		}
		
		return false;
		
	}
	
	public boolean isUntenMoeglich(int x, int y) {
		
		if (root[x][y] == 0 ){
			return false;
		}

		if (!isInLayout(x, y)  || !isInLayout(x-2, y))  {
			return false;
		}
		


		
		if ( y > 1 && root[x-2][y] == 0 && root[x-1][y] == 1) {
//			System.out.println("oben:  " + x + "|" + y);
			return true;
		}
		
		return false;
		
	}
	
	public boolean isRechtsMoeglich(int x, int y) {
		
		if (root[x][y] == 0 ){
			return false;
		}
		
		if (!isInLayout(x, y)  || !isInLayout(x,y+2)) {
			return false;
		}
		


		
		if ( x < 5 && root[x][y+2] == 0 && root[x][y+1] == 1) {
//			System.out.println("rechts:  " + x + "|" + y);
			return true;
		}
		
		return false;
		
	}
	
	public boolean isLinksMoeglich(int x, int y) {
		
		if (root[x][y] == 0 ){
			return false;
		}
		
		if (!isInLayout(x, y)  || !isInLayout(x, y-2) ) {
			return false;
		}
		


		if ( x > 1  && root[x][y-2] == 0 && root[x][y-1] == 1) {
//			System.out.println("links:  " + x + "|" + y);
			return true;
		}
		
		return false;
		
	}

	public Stellung moveLinks(int x, int y) {
		Stellung t = getClone();
		t.root[x][y]   = 0;
		t.root[x][y-2] = 0;
		t.root[x][y-1] = 1;
		
		richtung = "links";
		setFocus(x, y);
		t.vorgaenger = this;
		t.count--;
		return t;
	}

	public Stellung moveRechts(int x, int y) {
		Stellung t = getClone();
		t.root[x][y]   = 0;
		t.root[x][y+1] = 0;
		t.root[x][y+2] = 1;

		
		richtung = "rechts";
		setFocus(x, y);
		t.vorgaenger = this;
		t.count--;
		return t;
	}

	public Stellung moveUnten(int x, int y) {
		Stellung t = getClone();
		t.root[x][y]   = 0;
		t.root[x-1][y] = 0;
		t.root[x-2][y] = 1;
		
		richtung = "unten";
		setFocus(x, y);
		t.vorgaenger = this;
		t.count--;
		
		return t;
	}

	public Stellung moveOben(int x, int y) {
		Stellung t = getClone();
		t.root[x][y]   = 0;
		t.root[x+1][y] = 0;
		t.root[x+2][y] = 1;
		
		richtung = "oben";
		setFocus(x, y);
		t.vorgaenger = this;
		t.count--;
		
		return t;
	}
	
	public boolean isInLayout(int x, int y) {
		
		if (x < 0 || x > layout.length - 1) {
			return false;
		}
		
		if (y < 0 || y > layout.length - 1) {
			return false;
		}
		
		return layout[x][y] > 0;
		
	}

	public boolean isEmpty(int a, int b) {
		return root[a][b] == 0;
	}



}
