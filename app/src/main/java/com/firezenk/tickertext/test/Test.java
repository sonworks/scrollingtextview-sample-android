/**
 * 	FireZenk's Ticker text <Ticker text for real>
 *  Copyright (C) 2012 Jorge Garrido Oval (aka: FireZenk)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.firezenk.tickertext.test;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils.TruncateAt;
import android.view.ViewGroup.MarginLayoutParams;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firezenk.tickertext.FZTickerText;
import com.firezenk.tickertext.ScrollingTextView;

public class Test extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		FZTickerText ticker1 = (FZTickerText) findViewById(R.id.ticker1);
		
		ArrayList<char[]> phrases = new ArrayList<char[]>();
		phrases.add(new String("Hello!").toCharArray());
//		phrases.add(new String("My name is FireZenk").toCharArray());
//		phrases.add(new String("This is the Firezenk's Ticker Text test").toCharArray());
//		phrases.add(new String("Visit http://firezenk.com for more...").toCharArray());

		String text = "Hello ! ";
//		String text = "Hello ! TextViewのマーキー動作が以下の仕様のため、思ったように動かせませんでした。";
//		String text = "Hello ! TextViewのマーキー動作が以下の仕様のため、思ったように動かせませんでした。フォーカスが当たっていないと動作しない; テキストの長さがViewの幅以上でないと動かない";
		TextView textView = new ScrollingTextView(this, text.getBytes());
		textView.setSingleLine();
		textView.setText(text);
		
		TextView textView2 = new TextView(this);
		textView2.setText(Html.fromHtml("Hello ! 　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　"));
		textView2.setEllipsize(TruncateAt.MARQUEE);
		textView2.setSingleLine(true);
		textView2.setSelected(true);
		
		
		WebView webView = new WebView(this);
		webView.loadDataWithBaseURL("about:blank", 
				"<html><body style='margin-top:0px; margin-left:0px; text-align:center'><marquee behavior='scroll' style='font-family:Marker Felt;font-size:20px; color:#A52A2A;'>Test</marquee></font></body></html>", 
				"text/html", "UTF-8", null);
		LinearLayout layout = (LinearLayout)findViewById(R.id.layout_id);
		layout.addView(textView);
		
		ticker1.ANIMATION_DELAY = 300;
		ticker1.setPhrases(phrases);
		ticker1.animationStart();
	} 
	
}
