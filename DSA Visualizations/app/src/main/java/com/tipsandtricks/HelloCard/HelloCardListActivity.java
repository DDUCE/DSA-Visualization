package com.tipsandtricks.HelloCard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.tipsandtricks.HelloCard.adapters.BaseInflaterAdapter;
import com.tipsandtricks.HelloCard.adapters.CardItemData;
import com.tipsandtricks.HelloCard.adapters.inflaters.CardInflater;

public class HelloCardListActivity extends Activity
{
	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);



		ListView list = (ListView)findViewById(R.id.list_view);

		list.addHeaderView(new View(this));
		list.addFooterView(new View(this));

		BaseInflaterAdapter<CardItemData> adapter = new BaseInflaterAdapter<CardItemData>(new CardInflater());

		String[] arr=null;

		Bundle b=getIntent().getExtras();
		final long x=getIntent().getLongExtra("data_click",0);
		if(x==0)
		{
				String[] str={"STACK","QUEUE","LINKED LIST","AVL TREE","BINARY SEARCH TREE","RED BLACK TREE","B TREE","HEAP"};
				arr=str;
				this.setTitle("Data Structures");
		}
		else {
			this.setTitle("Algorithms");
			String[] str={"HEAP SORT","SELECTION SORT","INSERTION SORT","MERGE SORT","QUICK SORT","BUBBLE SORT","SHELL SORT"};
			arr=str;
		}

		for (int i = 0; i < arr.length; i++)
		{
			CardItemData data = new CardItemData(arr[i]);
			adapter.addItem(data, false);
		}

		list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
				boolean flag=true;
                Intent intent = new Intent(HelloCardListActivity.this, info.class);
                intent.putExtra("data_click",id);

				if(x==0)
				{
					flag=false;
				}
				else {
					flag=true;
				}
				intent.putExtra("data_click2", flag);
					startActivity(intent);
            }
        });

	}
}
