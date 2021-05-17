package test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


public class Solution {
	
	public int question2(int[] nums, int target){
	    int res = -1;
	    if(nums == null || nums.length == 0) return -1;
	    int left = 0;
	    int right = nums.length - 1;
	    while(left < right - 1){
	      int mid = left + (right - left) / 2;
	      if(nums[mid] <= target){
	        left = mid;
	      } else {
	        right = mid;
	      }
	    }
	    if(nums[right] == target){
	      res = right;
	      return res;
	    }
	    if(nums[left] == target){
	      res = left;
	    }
	    
	    return res;
	  }
	
	public int lis(int[] nums) {
		int res = 0;
		int n = nums.length;
		int[] lis = new int[n];
		
		for(int i = 0; i < n; i++) {
			lis[i] = 1;
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < i; j++) {
				if(nums[i] > nums[j] && lis[i] < lis[j] + 1) {
					lis[i] = lis[j] + 1;
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			if(res < lis[i]) {
				res = lis[i];
			}
		}
		
		return res;
	}
	
	public boolean question3(int[] nums) {
		int len = lis(nums);
		return (nums.length - len <= 1);
	}
 
	  public static void main(String args[]) throws IOException {
		  Solution s = new Solution();
			int[] input = new int[] {-2, -2, 0, 0, 1, 1, 1, 2, 3};          
	        
	        System.out.println(s.question2(input, 5));
	      
	  }
}



