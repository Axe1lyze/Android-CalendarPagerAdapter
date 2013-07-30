package com.axe1lyze.android.supportv4.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.axe1lyze.android.view.CalendarAdapter;
import com.axe1lyze.android.view.MonthListAdapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

public class CalendarPagerAdapter extends PagerAdapter{
	
	private static final SimpleDateFormat MONTH_TITLE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM",Locale.getDefault());
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd",Locale.getDefault());
	
	private Date date = new Date();
	private Context context;
	private Context getContext(){return context;}
	private CalendarAdapter calendarAdapter;
	public CalendarPagerAdapter(Context context,CalendarAdapter calendarAdapter) {
		this.context = context;
		this.calendarAdapter = calendarAdapter;
	}
	

	public CharSequence getPageTitle(int position) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, position-Integer.MAX_VALUE/2);
		Date date = calendar.getTime();
		return calendarAdapter!=null?
			calendarAdapter.getMonthTitle(date):
			MONTH_TITLE_DATE_FORMAT.format(date);
	}
	
	public View getDateView(Date date){
		if(date == null){
			return new View(getContext());
		}else{
			TextView tv = new TextView(getContext());
			tv.setText(DATE_FORMAT.format(date));
			return tv;
		}
	}
	
	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}
	
	@Override
	public Object instantiateItem(View collection, int position) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, position-Integer.MAX_VALUE/2);
		
		GridView monthGridView = new GridView(getContext());
		monthGridView.setNumColumns(7);
		monthGridView.setAdapter(new MonthListAdapter(getContext(), calendar.getTime()){
			public View getView(int position, View view, ViewGroup parent) {
				return calendarAdapter!=null?
					calendarAdapter.getDateVeiw(getItem(position)):
					super.getView(position, view, parent);
			}
		});
		((ViewPager)collection).addView(monthGridView,0);
		return monthGridView;
	}
	
	@Override
	public void destroyItem(View collection, int position, Object view) {
		((ViewGroup) collection).removeView((View)view);
	}
	
	
	
	
}
