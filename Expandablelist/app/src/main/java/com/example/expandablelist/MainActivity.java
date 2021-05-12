package com.example.expandablelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.AbstractList;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private ExpandableListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

        ArrayList<myGroup> DataList = new ArrayList<myGroup>();
        listView = (ExpandableListView)findViewById(R.id.mylist);
        myGroup temp = new myGroup("한글");
        temp.child.add("ㄱ");
        temp.child.add("ㄴ");
        temp.child.add("ㄷ");
        DataList.add(temp);
        temp = new myGroup("영어");
        temp.child.add("a");
        temp.child.add("b");
        temp.child.add("c");
        DataList.add(temp);
        temp = new myGroup("숫자");
        temp.child.add("1");
        temp.child.add("2");
        temp.child.add("3");
        DataList.add(temp);

        ExpandAdapter adapter = new ExpandAdapter(getApplicationContext(),R.layout.group_row,R.layout.child_row,DataList);
        listView.setIndicatorBounds(width-50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView.setAdapter(adapter);
    }
}