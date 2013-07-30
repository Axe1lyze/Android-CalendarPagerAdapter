package com.axe1lyze.android.view;

import java.util.Date;

import android.view.View;

public interface CalendarAdapter {
	public CharSequence getMonthTitle(Date date);
	public View getDateVeiw(Date date);
}
