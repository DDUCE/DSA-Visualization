package com.tipsandtricks.HelloCard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.tipsandtricks.HelloCard.adapters.BaseInflaterAdapter;
import com.tipsandtricks.HelloCard.adapters.CardItemData;
import com.tipsandtricks.HelloCard.adapters.inflaters.CardInflater;

public class HelloCardActivity extends Activity
{
	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hello_card);

		ListView listView=(ListView)findViewById(R.id.list_view);
		//ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.home_items,R.layout.list_item_card);

		String[] arr={"Data Structures","Algorithms","Test your skills"};
		BaseInflaterAdapter<CardItemData> adapter = new BaseInflaterAdapter<CardItemData>(new CardInflater());
		for (int i = 0; i < 3; i++)
		{
			CardItemData data = new CardItemData(arr[i]);
			adapter.addItem(data, false);
		}
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
									long id) {
				Intent intent;
				if(id==0 || id==1) {
					intent = new Intent(HelloCardActivity.this, HelloCardListActivity.class);
					intent.putExtra("data_click", id);
				}
				else
				{
					intent=new Intent(HelloCardActivity.this,Quiz.class);
					intent.putExtra("que_no",1);
				}
				startActivity(intent);
			}
		});

	}
}
