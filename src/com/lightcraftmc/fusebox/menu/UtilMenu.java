package com.lightcraftmc.fusebox.menu;

import java.util.ArrayList;

public class UtilMenu {

	public static int[] getEmptySlots(){
		ArrayList<Integer> ints = new ArrayList<Integer>(); for(int i = 0; i < 9; i++) ints.add(i); for(int i = 45; i <= 53; i++) ints.add(i); int i2 = 0; while(i2 <= 36){ i2 = i2 + 9; if(!ints.contains(i2)){ ints.add(i2); } } int i3 = 8; while(i3 <= 44){ i3 = i3 + 9; if(!ints.contains(i3)){ ints.add(i3); } } int[] i4 = new int[ints.size()]; int i6 = 0; for(@SuppressWarnings("unused") int i5 : ints){ i4[i6] = ints.get(i6).intValue(); i6++; } return i4;
	}

	public static int[] getAllowedSlots(){
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for(int i = 0; i < 9*6; i++){
			if(!isBlocked(i)){
				ints.add(i);
			}
		}

		int[] i4 = new int[ints.size()];
		int i6 = 0;
		for(@SuppressWarnings("unused") int i5 : ints){
			i4[i6] = ints.get(i6).intValue();
			i6++;
		}
		return i4;
	}

	public static boolean isBlocked(int i){
		for(int i2 : getEmptySlots()){
			if(i2 == i){
				return true;
			}
		}
		return false;
	}

}
