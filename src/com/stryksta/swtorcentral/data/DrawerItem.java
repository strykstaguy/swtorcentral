package com.stryksta.swtorcentral.data;

public class DrawerItem {
	
	public int title;
	public int iconRes;
	public int counter;
	public boolean isHeader;

	public DrawerItem(int title, int iconRes,boolean header,int counter) {
		this.title = title;
		this.iconRes = iconRes;
		this.isHeader = header;
		this.counter = counter;
	}

	public DrawerItem(int title, int iconRes,boolean header){
		this(title,iconRes,header,0);
	}

	public DrawerItem(int title, int iconRes) {
		this(title,iconRes,false);
	}

}