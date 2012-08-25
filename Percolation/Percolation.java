/******************************************************************************
 * Author: William Schwartz
 * Written: 2012-08-25
 *
 * Compilation: $ javac Percolation.java
 * Testing: $ java Percolation
 *
 * Data type to model physical percolation (say of water through concrete).
 ******************************************************************************/

import java.lang.IndexOutOfBoundsException;


public class Percolation {

	private int N; // Length of one side of the grid.
	private boolean[] open;
	private WeightedQuickUnionUF paths;

	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		this.N = N;
		this.open = new boolean[N^2];
		/*	Include two extra spots in the connections list two hold the virtual
			top and virtual bottom spots. Virtual top is 1 to the left of
			the grid's top left spot; virtual bottom is 1 to the right of the
			bottom right spot.
		*/
		this.paths = new WeightedQuickUnionUF(N^2 + 2);
		for (int col = 1; col <= N; col++)
			this.paths.union(indexOf(1, 1) - 1, indexOf(1, col));
		for (int col = 1; col <= N; col++)
			this.paths.union(indexOf(N, N) + 1, indexOf(N, col));
	}

	/* The rest of the API:

	public void open(int i, int j)		// open site (row i, column j) if it is not already
	public boolean isOpen(int i, int j)	// is site (row i, column j) open?
	public boolean isFull(int i, int j)	// is site (row i, column j) full?
	public boolean percolates()			// does the system percolate?
	*/

	/* Convert grid coordinates of the form (x, y) where x,y in {1,...,N}
	to an array index. E.g., indexOf(1,1) == 0; indexOf(N, N) = N^2 - 1.

	Assume the grid is in row-major form.
	*/
	private int indexOf(int row, int col) throws IndexOutOfBoundsException {
		if (row <= 0 || row > this.N || col <= 0 || col >= this.N)
			throw new IndexOutOfBoundsException(
					"(" + row + ", " + col + ") out of bounds" +
					"for " + this.N + "^2 grid.");
		return (row - 1) * this.N + (col - 1);
	}
}
