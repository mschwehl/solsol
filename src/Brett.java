import java.util.Stack;


public class Brett {
	
	
	public static void main(String[] args) {
		
		byte[][] stellung1 = new byte[][] {
			
			{ 0,0,1,1,1,0,0 } ,
			{ 0,0,1,1,1,0,0 } ,
			{ 1,1,1,1,1,1,1 } ,
			{ 1,1,1,0,1,1,1 } ,
			{ 1,1,1,1,1,1,1 } ,
			{ 0,0,1,1,1,0,0 } ,
			{ 0,0,1,1,1,0,0 } 			
		};
		
		byte[][] stellung = new byte[][] {
				
				{ 0,0,0,0,0,0,0 } ,
				{ 0,0,1,1,0,0,0 } ,
				{ 0,0,0,0,1,0,0 } ,
				{ 0,0,0,0,0,1,0 } ,
				{ 0,0,0,0,0,0,0 } ,
				{ 0,0,0,0,0,0,0 } ,
				{ 0,0,0,0,0,0,0 } 			
			};
		
		
		
		Stellung s = new Stellung(stellung1);
		s.printStellung();

		Brett b = new Brett();
//		Stellung solved =  b.solve(s, 0,0);

		Stellung solved =  b.solveIterativ(s);

		System.out.println("SOLVED");
		
		solved.printStellung();
	}

	Stack <Stellung> stellungStack = new Stack();

	long count =0;
	
	private Stellung solveIterativ(Stellung t) {
		
		stellungStack.add(t);
		
		Stellung s = null;
		
		do  {
			
			if (stellungStack.isEmpty()) {
				throw new RuntimeException("Stellung nicht lösbar: # " + count);
			}
			
			s = stellungStack.pop();
			count ++;
			
			if (count % 500000 == 0) {
				System.out.println(count + "(" + stellungStack.size() + ")" ); 
				System.out.println(s);
			}
			
			
			for (int a=0; a < 7; a++) {
				for ( int b=0; b < 7; b++){

			
					if (s.isEmpty(a,b)) {
						continue;
					}
					
			if (s.isLinksMoeglich(a, b)) {
				Stellung n1 = s.moveLinks(a,b);
				if (n1.isSolved()) {
					return n1;
				} 
				stellungStack.push(n1);

				
			}
			
			if (s.isRechtsMoeglich(a, b)) {
				Stellung n1 = s.moveRechts(a,b);
				if (n1.isSolved()) {
					return n1;
				} 			
				stellungStack.push(n1);
			}
			
			if (s.isObenMoeglich(a, b)) {
				Stellung n1 = s.moveOben(a,b);
				if (n1.isSolved()) {
					return n1;
				} 			
				stellungStack.push(n1);
			}
			
			if (s.isUntenMoeglich(a, b)) {
				Stellung n1 = s.moveUnten(a,b);
				if (n1.isSolved()) {
					return n1;
				} 			
				stellungStack.push(n1);
			}
			
				}
			}
			
			
		} while (! s.isSolved() );
		
		return s;

		
	}
	
	private Stellung solve(Stellung s, int a, int b) {

		if (s.isSolved()) {
			return s;
		}
		
//		s.setFocus(a, b);
		
		if (Math.random() > 0.9) {
//			System.out.println(s.printStellung());
		}
		
//		

		if (s.isLinksMoeglich(a, b)) {
			Stellung n1 = s.moveLinks(a,b);
			if (n1.isSolved()) {
				return n1;
			} 
			return solve(n1,0,0);
			
		}
		
		if (s.isRechtsMoeglich(a, b)) {
			Stellung n1 = s.moveRechts(a,b);
			if (n1.isSolved()) {
				return n1;
			} 			
			return solve(n1,0,0);
		}
		
		if (s.isObenMoeglich(a, b)) {
			Stellung n1 = s.moveOben(a,b);
			if (n1.isSolved()) {
				return n1;
			} 			
			return solve(n1,0,0);
		}
		
		if (s.isUntenMoeglich(a, b)) {
			Stellung n1 = s.moveUnten(a,b);
			if (n1.isSolved()) {
				return n1;
			} 			
			return solve(n1,0,0);
		}
		
		


		if (a < 6) {
			return solve(s, a+1, b);
		} else {
			a=0;
		}
		
		return solve(s,a,b+1);		

	}

	
}
