package sortcomparison;

import static java.lang.Math.*;
import static java.lang.System.*;
import static sbcc.Core.*;

import java.util.*;

public class BasicSorter implements Sorter {

	@Override
	public void insertionSort(String[] data, int fi, int n) {
		String temp;
		for (int i = fi + 1; i < n; i++) {
			temp = data[i];
			int j = i - 1;
			while (j >= fi && temp.compareTo(data[j]) < 0)
				j--;
			arraycopy(data, j + 1, data, j + 2, i - j - 1);
			data[j + 1] = temp;
		}
	}


	@Override
	public void quickSort(String[] data, int fi, int n) {
		if (n > 1) {
			if (n <= 16)
				insertionSort(data, fi, n);
			int pivot = partition(data, fi, n);
			quickSort(data, fi, pivot - fi);
			quickSort(data, pivot + 1, n - pivot - 1 + fi);
		}
	}


	@Override
	public int partition(String[] data, int fi, int n) {
		swap(data, fi, medianThree(data, fi, fi + n - 1));
		String pivot = data[fi];
		int big = fi + 1;
		int small = fi + n - 1;
		while (big < small) {
			while (big < small && data[big].compareTo(pivot) <= 0)
				big++;
			while (small > fi && data[small].compareTo(pivot) > 0)
				small--;
			if (big < small)
				swap(data, big, small);
		}
		if (pivot.compareTo(data[small]) >= 0) {
			swap(data, fi, small);
			return small;
		}
		return fi;
	}


	@Override
	public void mergeSort(String[] data, int fi, int n) {
		if (n <= 16)
			insertionSort(data, fi, n);
		if (n > 1) {
			int size1 = n / 2;
			int size2 = n - size1;
			mergeSort(data, fi, size1);
			mergeSort(data, fi + size1, size2);
			merge(data, fi, size1, size2);
		}
	}


	@Override
	public void merge(String[] data, int fi, int nl, int nr) {
		int l = 0;
		int r = 0;
		String[] temp = new String[nl + nr];
		while (l < nl && r < nr) {
			if (data[fi + l].compareTo(data[fi + nl + r]) <= 0) {
				temp[l + r] = data[fi + l];
				l++;
			} else {
				temp[l + r] = data[fi + nl + r];
				r++;
			}
		}
		while (l < nl) {
			temp[l + r] = data[fi + l];
			l++;
		}
		while (r < nr) {
			temp[l + r] = data[fi + nl + r];
			r++;
		}

		arraycopy(temp, 0, data, fi, nl + nr);
	}


	@Override
	public void heapSort(String[] data) {
		for (int i = 0; i < data.length; i++) {
			int ndx = i;
			while (i != 0 && data[ndx].compareTo(data[(ndx - 1) / 2]) > 0) {
				swap(data, ndx, (ndx - 1) / 2);
				ndx = (ndx - 1) / 2;
			}
		}

		int unsorted = data.length;
		while (unsorted > 1) {
			unsorted--;
			swap(data, 0, unsorted);
			heapify(data);
		}

	}


	@Override
	public void heapify(String[] data) {
		// TODO Auto-generated method stub

	}


	private void swap(String[] data, int a, int b) {
		String temp = data[a];
		data[a] = data[b];
		data[b] = temp;
	}


	private int medianThree(String[] data, int min, int max) {
		Random r = new Random();
		int[] piv = { r.nextInt((max - min) + 1) + min, (min + max) / 2, r.nextInt((max - min) + 1) + min };

		if (data[piv[0]].compareTo(data[piv[1]]) > 0 != data[piv[0]].compareTo(data[piv[2]]) > 0)
			return piv[0];
		else if (data[piv[0]].compareTo(data[piv[1]]) > 0 != data[piv[1]].compareTo(data[piv[2]]) > 0)
			return piv[1];
		else
			return piv[2];
	}


	private void completeTree(int position, Queue<String> nodes, String[] tree) {
		if (position > nodes.size() - 1)
			return;
		completeTree(2 * position + 1, nodes, tree);
		tree[position] = nodes.remove();
		completeTree(2 * position + 2, nodes, tree);
	}
}
