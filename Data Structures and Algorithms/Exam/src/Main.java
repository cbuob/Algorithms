import java.util.*;
class Main {
	static class Edge implements Comparable<Edge> {
		int x,y,cost;
		Edge(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(final Edge other) {
			if		(this.cost != other.cost) 	return this.cost < other.cost?1:-1;
			else if	(this.x != other.x)			return this.x < other.x?1:-1;
			else if	(this.y != other.y)			return this.y < other.y?1:-1;
			else return 0;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int c = 1; c <= t; ++c) {
			int N = sc.nextInt();
			int[][] field = new int[N][N];
			for(int i = 0; i < N; ++i) for(int j = 0; j < N; ++j) field[i][j] = sc.nextInt();

			int[][] visited = new int[N][N];
			for(int i = 0; i < N; ++i) for(int j = 0; j < N; ++j) visited[i][j] = -1;
			
			TreeSet<Edge> q = new TreeSet<Edge>();
			int startX = 0, startY = 0;
			for(int i = 0; i < N; ++i) for(int j = 0; j < N; ++j) if(field[i][j] == 0) {
				startX = i; startY = j;
			}
			Edge fakeEdge = new Edge(startX, startY, 0);
			q.add(fakeEdge);

			while(!q.isEmpty()) {
				NavigableSet<Edge> currentNSet = q.descendingSet();

				Edge current = currentNSet.pollFirst();
				int i = current.x;
				int j = current.y;
				if(visited[i][j] == -1) {
					visited[i][j] = current.cost;
					if(i==0||j==0||i==N-1||j==N-1) {
						q.clear();
						System.out.println(current.cost);
					}
					else {
						if(i > 0) {
							Edge e = new Edge(i-1,j,field[i-1][j]+current.cost);
							q.add(e);
						} if(j > 0) {
							Edge e = new Edge(i,j-1,field[i][j-1]+current.cost);
							q.add(e);	
						} if(i < N-1) {
							Edge e = new Edge(i+1,j,field[i+1][j]+current.cost);
							q.add(e);	
						} if(j < N-1) {
							Edge e = new Edge(i,j+1,field[i][j+1]+current.cost);
							q.add(e);
						}
					}
				}
			}
		}

	}
}
