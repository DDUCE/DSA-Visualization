package com.tipsandtricks.HelloCard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URLEncoder;
import java.util.StringTokenizer;

public class Quiz extends AppCompatActivity{
        String que="In order traversal of binary search tree will produce −";
        String[] opts={"A - unsorted list","B - reverse of input","C - sorted list","D - none of the above"};
        ListView lv=null;
        int[] ans={1,2};
        static int q;
  //  private static String url = "http://192.168.1.100:9790/MyWebService/webresources/mypackage.quiz/mypackage.quiz1";
   private static String url = "http://192.168.43.182:9790/MyWebService/webresources/mypackage.quiz/mypackage.quiz1";
    static char answer='A';
    static Button[] rs=new Button[4];

    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        rs[0]=(Button)findViewById(R.id.button1);
        rs[1]=(Button)findViewById(R.id.button2);
        rs[2]=(Button)findViewById(R.id.button3);
        rs[3]=(Button)findViewById(R.id.button4);


        q=getIntent().getIntExtra("que_no",1);
        Button b2=(Button)findViewById(R.id.prev);
        if(q==1)
            b2.setEnabled(false);
        else
            b2.setEnabled(true);

        new MyRequester().execute(q);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Quiz.this,Quiz.class);
                intent.putExtra("que_no",q-1);
                startActivity(intent);
            }
        });

        Button b1=(Button)findViewById(R.id.next);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Quiz.this,Quiz.class);
                intent.putExtra("que_no",q+1);
                startActivity(intent);
            }
        });

}

    public void optionClick(View v)
    {
        switch(v.getId())
        {
            case R.id.button1:
                setColors(answer=='A',0);
                break;
            case R.id.button2:
                setColors(answer=='B',1);
                break;
            case R.id.button3:
                setColors(answer=='C',2);
                break;
            case R.id.button4:
                setColors(answer=='D',3);
                break;
        }
    }

    private void setColors(boolean x,int id)
    {
        if(x)
        {
            rs[id].setBackgroundColor(Color.GREEN);
        }
        else
        {
            rs[id].setBackgroundColor(Color.RED);
            rs[answer-'0'].setBackgroundColor(Color.GREEN);
        }
        for(int i=0;i<4;i++)
            rs[i].setClickable(false);


    }
    private class MyRequester extends AsyncTask<Integer,Void,String>
    {
        public String doInBackground(Integer... args)
        {
            WebRequest req=new WebRequest();

            String finalUrl= url + "?id=" + URLEncoder.encode(args[0].toString());

            String str=req.makeWebServiceCall(finalUrl, WebRequest.GET,null);
            return str;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("abc",result);
            StringTokenizer to=new StringTokenizer(result,"$$");
            String str=to.nextToken();

            // Dismiss the progress dialog
            TextView tv=(TextView)findViewById(R.id.question);
            tv.setText(str);



            for(int i=0;i<4;i++)
            {
                str=to.nextToken();
                rs[i].setText(str);
            }

            // checking answer

            str=to.nextToken();
            answer=str.charAt(0);

        }


    }

}

