package com.example.tmt.showorhide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapter adapter;
    private View btnLike, btnShare, btnNext, btnPrev, btnComment;
    private TextView chapterName;
    private ViewGroup header, footer;
    private final static String TAG = MainActivity.class.getSimpleName();

    private final static String[] imageArray = { "http://i.imgur.com/bRzOCig.jpg",
            "http://i.imgur.com/ykPVmnp.jpg", "http://i.imgur.com/RbKOKnG.jpg",
            "http://i.imgur.com/REjrtGp.jpg", "http://i.imgur.com/PT9QjQ9.jpg",
            "http://i.imgur.com/dg5NY1D.jpg", "http://i.imgur.com/Imlqk8j.jpg",
            "http://i.imgur.com/U2IPvXy.jpg", "http://i.imgur.com/0MNYYuG.jpg",
            "http://i.imgur.com/VZhAPel.jpg", "http://i.imgur.com/35jBerW.jpg",
            "http://i.imgur.com/0jHZEfn.jpg", "http://i.imgur.com/81HvMfA.jpg",
            "http://i.imgur.com/nobvCRi.jpg", "http://i.imgur.com/wdT3ZVw.jpg",
            "http://i.imgur.com/DV9MDja.jpg", "http://i.imgur.com/PN2TQnS.jpg" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        listView = (ListView) findViewById(R.id.list_view);
        locateListViewHeaderAndFooter();
        setListViewAdapter();

        //set on scrolling listener for ListView
        listView.setOnScrollListener(onScrollListener());
    }

    // adding header and footer for listview
    private void locateListViewHeaderAndFooter() {
        // declaring elements
        btnLike = findViewById(R.id.btn_like);
        btnComment = findViewById(R.id.btn_comment);
        btnNext =  findViewById(R.id.btn_next);
        btnPrev = findViewById(R.id.btn_prev);
        btnShare = findViewById(R.id.btn_share);
        chapterName = (TextView) findViewById(R.id.chapter_name);
        header = (ViewGroup) findViewById(R.id.layout_header);
        footer = (ViewGroup) findViewById(R.id.layout_footer);
        chapterName.setText("One Piece chap 650");

        // set "demo" events for buttons
        btnLike.setOnClickListener(onButtonClick("You liked it"));
        btnComment.setOnClickListener(onButtonClick("Please enter your comment..."));
        btnNext.setOnClickListener(onButtonClick("Go to Next chap ter"));
        btnPrev.setOnClickListener(onButtonClick("Go to Previous chap ter"));
        btnShare.setOnClickListener(onButtonClick("Thanks for sharing..."));


    }

    private void setListViewAdapter() {
        adapter = new ListViewAdapter(this, R.layout.item_listview, imageArray);
        listView.setAdapter(adapter);
    }

    public AbsListView.OnScrollListener onScrollListener() {
        return new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                if (firstVisibleItem == 0) {
                    // check if we reached the top or bottom of the list
                    View v = listView.getChildAt(0);
                    int offset = (v == null) ? 0 : v.getTop();
                    if (offset == 0) {
                        // reached the top: visible header and footer
                        Log.i(TAG, "top reached");
                        setViewStatus(footer, header, View.GONE);
                    }
                } else if (totalItemCount - visibleItemCount == firstVisibleItem) {
                    View v = listView.getChildAt(totalItemCount - 1);
                    int offset = (v == null) ? 0 : v.getTop();
                    if (offset == 0) {
                        // reached the bottom: visible header and footer
                        Log.i(TAG, "bottom reached!");
                        setViewStatus(footer, header, View.GONE);
                    }
                } else if (totalItemCount - visibleItemCount > firstVisibleItem){
                    // on scrolling
                    setViewStatus(footer, header, View.VISIBLE);
                    Log.i(TAG, "on scroll");
                }
            }
        };
    }

    // handle buttons events
    private View.OnClickListener onButtonClick(final String buttonEventName) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, buttonEventName, Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void setViewStatus(ViewGroup vg1, ViewGroup vg2, int status) {
        vg1.setVisibility(status);
        vg2.setVisibility(status);
    }
}
