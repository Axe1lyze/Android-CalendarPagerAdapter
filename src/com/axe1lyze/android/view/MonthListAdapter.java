package com.axe1lyze.android.view;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MonthListAdapter extends ArrayAdapter<Date>{
	
	private Date firstDate;

	public Date getFirstDate() {
		return firstDate;
	}
	public MonthListAdapter(Context context,int i,Date date) {
		this(context,date);
	}
	
	public MonthListAdapter(Context context, Date date) {
		super(context,0);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		firstDate = calendar.getTime();
	}
	
	@Override
	public int getCount() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate);
		return calendar.getActualMaximum(Calendar.DATE) + calendar.get(Calendar.DAY_OF_WEEK)-1;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent){
		Date date = getItem(position);
		TextView textView = (TextView) view;
		if(textView==null)textView = new TextView(getContext());
		if(date!=null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			textView.setText(calendar.get(Calendar.DATE)+"");
			return textView;
		}else {
			textView.setText("");
			return textView;
		}
	}

	@Override
	public Date getItem(int position) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if(position < dayOfWeek){
			return null;
		}else{
			calendar.set(Calendar.DATE,position  - dayOfWeek+1);
			return calendar.getTime();
		}
	}

}
