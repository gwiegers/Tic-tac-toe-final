package com.example.tictactoepuppy;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class StepCalculator {
	
	private int[][] grid = new int[3][3];
	private int place;
	
	public int place(int[] array){

		// check if the computer can win
		int k = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				grid[i][j] = array[k];
				k++;
			}
		}
		for (int turn = 2; turn > 0; turn--) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (grid[i][j] == turn) {
						if (i - 1 >= 0 && grid[i - 1][j] == turn) {
							if(i - 2 == 0) {
								place = ((i - 2)* 3) + j;
								return place;
							}
							else if (i - 1 == 0 && grid[i + 1][j] == 0) {
								place = (i + 1) * 3 + j;
								return place;
							}
						}
						if (i - 2 >= 0 && grid[i - 2][j] == turn && grid[i - 1][j] == 0) {
							place = (i - 1) * 3 + j;
							return place;
						}
						if (i + 1 < 3 && grid[i + 1][j] == turn) {
							if(i + 2 == 0 && grid[i + 1][j] == 0) {
								place = (i + 2) * 3 + j;
								return place;
							}
							else (grid[i - 1][j] == 0) {
								place = (i - 1) * 3 + j;
								return place;
							}
						}
						if (i + 2 < 3 && grid[i + 2][j] == turn) {
							place = (i + 1) * 3 + j;
							return place;
						}
						// check vertical
						if (j - 1 >= 0 && grid[j][j - 1] == turn) {
							if(j - 2 == 0) {
								place = i * 3 + (j - 1);
								return place;
							}
							else {
								place = i * 3 + j - 2;
								return place;
							}
						}
						if (j - 2 >= 0 && grid[i][j - 2] == turn) {
							place = i * 3 + j - 1;
						}
						if (j + 1 < 3 && grid[i][j + 1] == turn) {
							if(j + 2 == 0) {
								place = i * 3 + j + 2;
								return place;
							}
							else {
								place = i * 3 + j - 1;
								return place;
							}
						}
						if (j + 2 < 3 && grid[i][j + 2] == turn) {
							place = i * 3 + j + 1;
							return place;
						}
						// check diagonal 							
						if (array[4] == turn) {
							if (array[8] == turn) {
								place = 0;
								return place;
							}
							if (array[6] == turn) {
								place = 2;
								return place;
							}
							if (array[0] == turn) {
								place = 8;
								return place;
							}
							if (array[2] == turn) {
								place = 6;
								return place;
							}
						}
						if ((array[0] == turn && array[8] == turn) || (array[2] == turn && array[6] == turn)) {
							place = 4;
							return place;
						}
					}
				}
			}
		}
		Random rand = new Random();
		place = rand.nextInt(9);
		while (array[place] != 0) {
		place = rand.nextInt(8);
		}
		return place;
	}
}
