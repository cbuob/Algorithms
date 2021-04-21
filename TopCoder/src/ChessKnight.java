
public class ChessKnight {
	public double probability(int x, int y, int n) {
		return prob(x, y, 0, 1, n);

	}

	private double prob(int x, int y, int n, int p, int end) {
		if (x < 1 || x > 8 || y < 1 || y > 8) {
			return 0;
		} else if (n == end) {
			return 1;
		} else {
			return (double) 1 / 8 * prob(x - 2, y - 1, n + 1, p, end)
					+ (double) 1 / 8 * prob(x + 2, y - 1, n + 1, p, end)
					+ (double) 1 / 8 * prob(x + 2, y + 1, n + 1, p, end)
					+ (double) 1 / 8 * prob(x - 2, y + 1, n + 1, p, end)
					+ (double) 1 / 8 * prob(x - 1, y - 2, n + 1, p, end)
					+ (double) 1 / 8 * prob(x + 1, y - 2, n + 1, p, end)
					+ (double) 1 / 8 * prob(x + 1, y + 2, n + 1, p, end)
					+ (double) 1 / 8 * prob(x - 1, y + 2, n + 1, p, end);
		}
	}
}
