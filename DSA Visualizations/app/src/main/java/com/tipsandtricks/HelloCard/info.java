package com.tipsandtricks.HelloCard;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Bundle b=getIntent().getExtras();

        long x=getIntent().getLongExtra("data_click",0);
        boolean flag=getIntent().getBooleanExtra("data_click2",true);
        String fname=null;

        WebView v=(WebView)findViewById(R.id.webView);
        if(flag==true)
        {
            if (x > 0) {
                fname="ComparisonSort.html";

            }
            else if (x == 0) {
                fname="HeapSort.html";
            }
            else{
                fname="Stack.html";
            }

        }
        else
        {
            if (x == 0)
            {
                fname="Stack.html";
            }
            else if (x == 1)
            {
                fname="Queue.html";
            }
            else if (x == 2)
            {
                fname="LinkedList.html";
            }
            else if (x == 3)
            {
                fname="Avl.html";
            }
            else if (x == 4)
            {
                fname="BST.html";
            }
            else if (x == 5)
            {
                fname="Redblack.html";
            }
            else if (x == 6)
            {
                fname="btree.html";
            }
            else if (x == 7)
            {
                fname="Heap.html";
            }
        }
        this.setTitle(fname.substring(0,fname.length()-5));

        String a1=fname;
        if(fname=="ComparisonSort.html")
        {
            if (x == 5)
            {
                a1="BubbleSort.html";
            }
            else if (x == 1)
            {
                a1="SelectionSort.html";
            }
            else if (x == 2)
            {
               a1="InsertionSort.html";
            }
            else if (x == 3)
            {
                a1="MergeSort.html";
            }
            else if (x == 4)
            {
                a1="QuickSort.html";
            }
            else if(x==6)
            {
                a1="ShellSort.html";
            }
            else {
                a1="HeapSort.html";
            }

        }
        v.loadUrl("file:///android_asset/"+a1);
        final String a=fname;
        Button bu=(Button) findViewById(R.id.button);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(info.this,MyAnimation.class);
                i.putExtra("data_click",a);
                startActivity(i);
            }
        });

    }
}
