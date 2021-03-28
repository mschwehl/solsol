import java.util.Stack;


public class Brett2 {
	
	
	public static void main(String[] args) {
		
		byte[][] Stellung2 = new byte[][] {
			
			{ 0,0,1,1,1,0,0 } ,
			{ 0,0,1,1,1,0,0 } ,
			{ 1,1,1,1,1,1,1 } ,
			{ 1,1,1,0,1,1,1 } ,
			{ 1,1,1,1,1,1,1 } ,
			{ 0,0,1,1,1,0,0 } ,
			{ 0,0,1,1,1,0,0 } 			
		};
		
		
		byte[][] Stellung1 = new byte[][] {
				
				{ 0,0,0,0,0,0,0 } ,
				{ 0,0,0,0,1,0,0 } ,
				{ 0,0,0,0,1,0,0 } ,
				{ 0,0,0,0,0,1,0 } ,
				{ 0,0,0,0,0,0,0 } ,
				{ 0,0,0,0,0,0,0 } ,
				{ 0,0,0,0,0,0,0 } 	
			};
			

		
		
		Stellung2 s = new Stellung2(Stellung1);
		System.out.println(s.printStellung());
		

		Brett2 b = new Brett2();
		Stellung2 solved =  b.solve(s, 0,0,null);


		System.out.println("SOLVED");
		
		solved.printStellung();
	}

	
	private Stellung2 solve(Stellung2 s, int a, int b, Move r) {

		if (s.isSolved()) {
			return s;
		}
		
//		System.out.println("try " + a + "-" + b + ":" + r);
		
		if (true) {
			

			if (s.isLinksMoeglich(a, b)) {
					s.moveLinks(a,b);
				if (s.isSolved()) {
					return s;
				} 
				return solve(s,0,0, new Move(a,b,  Move.RICHTUNG.links));
			}
			
			if (s.isRechtsMoeglich(a, b)) {
				s.moveRechts(a,b);
				if (s.isSolved()) {
					return s;
				} 			
				return solve(s,0,0,new Move(a,b,  Move.RICHTUNG.rechts));
			}
			
			if (s.isObenMoeglich(a, b)) {
				s.moveOben(a,b);
				if (s.isSolved()) {
					return s;
				} 			
				return solve(s,0,0,new Move(a,b,  Move.RICHTUNG.oben));
			}
			
			if (s.isUntenMoeglich(a, b)) {
				s.moveUnten(a,b);
				if (s.isSolved()) {
					return s;
				} 			
				return solve(s,0,0,new Move(a,b,  Move.RICHTUNG.unten));
			}
			
			
		}

		

		if (r !=null) {
			System.out.println("COMPENSATE " + r.r);
			s.compensate(r);
		}

		if (a < 6) {
			return solve(s, a+1, b,null);
		} else {
			a=0;
		}
		
		return solve(s,a,b+1,null);		

	}


}
