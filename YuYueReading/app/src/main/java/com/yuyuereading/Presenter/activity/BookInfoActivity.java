package com.yuyuereading.Presenter.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.willy.ratingbar.ScaleRatingBar;
import com.yuyuereading.Model.bean.BookComment;
import com.yuyuereading.Model.bean.BookInfo;
import com.yuyuereading.Presenter.adapter.CommentListAdapter;
import com.yuyuereading.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class BookInfoActivity extends AppCompatActivity {

    private Context mContext = BookInfoActivity.this;

    private RecyclerView recyclerView;

    private BookComment[] bookComments = {new BookComment("https://img3.doubanio.com/lpic/s29418322.jpg","芳华","8.1","《芳华》涵盖了严歌苓的青春与成长期，她在四十余年后回望这段经历，笔端蕴含了饱满的情感。青春荷尔蒙冲动下的少男少女的懵懂激情，由激情犯下的过错，由过错生出的懊悔，还有那个特殊的时代背景，种种，构成了《芳华》对一段历史、一群人以及潮流更替、境遇变迁的复杂感怀。","2017-4-1"),
            new BookComment("https://img3.doubanio.com/lpic/s29418322.jpg","芳华","8.1","《芳华》涵盖了严歌苓的青春与成长期，她在四十余年后回望这段经历，笔端蕴含了饱满的情感。青春荷尔蒙冲动下的少男少女的懵懂激情，由激情犯下的过错，由过错生出的懊悔，还有那个特殊的时代背景，种种，构成了《芳华》对一段历史、一群人以及潮流更替、境遇变迁的复杂感怀。","2017-4-1"),
            new BookComment("https://img3.doubanio.com/lpic/s29418322.jpg","芳华","8.1","《芳华》涵盖了严歌苓的青春与成长期，她在四十余年后回望这段经历，笔端蕴含了饱满的情感。青春荷尔蒙冲动下的少男少女的懵懂激情，由激情犯下的过错，由过错生出的懊悔，还有那个特殊的时代背景，种种，构成了《芳华》对一段历史、一群人以及潮流更替、境遇变迁的复杂感怀。","2017-4-1"),
            new BookComment("https://img3.doubanio.com/lpic/s29418322.jpg","芳华","8.1","《芳华》涵盖了严歌苓的青春与成长期，她在四十余年后回望这段经历，笔端蕴含了饱满的情感。青春荷尔蒙冲动下的少男少女的懵懂激情，由激情犯下的过错，由过错生出的懊悔，还有那个特殊的时代背景，种种，构成了《芳华》对一段历史、一群人以及潮流更替、境遇变迁的复杂感怀。","2017-4-1")};

    private List<BookComment> bookCommentList = new ArrayList<>();

    String book_ISBN,book_score;

    Button wantRead, reading, havaRead, returnButton;

    TextView bookName,bookScore, bookWriter,bookPressName,bookPressData,bookISBN,book_summary,title;

    ScaleRatingBar bookRating;

    ImageView bookImage;

    ScrollView scrollView;

    LinearLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        initView();
        //从上一页面获取图书信息类，来填充控件
        getBookInfo();
        //点击事件
        onClick();
        mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);
        //获取评论信息
        initList();
        addDate();
    }

    @SuppressLint("SetTextI18n")
    private void getBookInfo() {
        Intent intent = this.getIntent();
        BookInfo bookInfo = (BookInfo) intent.getSerializableExtra("bookInfo");
        book_ISBN = String.valueOf(bookInfo.getBook_isbn13());
        bookName.setText(bookInfo.getBook_name());
        title.setText(bookInfo.getBook_name());
        bookWriter.setText(bookInfo.getBook_author());
        bookPressName.setText(bookInfo.getBook_publisher());
        bookPressData.setText(bookInfo.getBook_publish_date());
        bookISBN.setText("ISBN:"+ bookInfo.getBook_isbn13());
        Glide.with(mContext)
                .load(bookInfo.getBook_image())
                .into(bookImage);
        book_score = bookInfo.getBook_rating();
        bookScore.setText(bookInfo.getBook_rating());
        bookRating.setRating(Float.parseFloat(bookInfo.getBook_rating())/2);
        book_summary.setText(bookInfo.getBook_summary());
    }

    private void initView() {
        wantRead = findViewById(R.id.book_info_wantRead);
        reading = findViewById(R.id.book_info_reading);
        havaRead = findViewById(R.id.book_info_haveRead);
        returnButton = findViewById(R.id.book_info_return);
        bookName = findViewById(R.id.bookName);
        bookScore = findViewById(R.id.bookScore);
        bookWriter = findViewById(R.id.bookWriter);
        bookPressName = findViewById(R.id.bookPressName);
        bookPressData = findViewById(R.id.bookPressData);
        bookISBN = findViewById(R.id.bookISBN);
        bookRating = findViewById(R.id.bookRating);
        bookImage = findViewById(R.id.bookImage);
        book_summary = findViewById(R.id.book_summary);
        title = findViewById(R.id.title);
        scrollView = findViewById(R.id.scrollView);
        recyclerView = findViewById(R.id.recycler_comment);
        recyclerView.setNestedScrollingEnabled(false);
    }

    //获取评论信息
    private void initList() {
        bookCommentList.clear();
        for (int i = 0; i < 50;i++) {
            Random random = new Random();
            int index = random.nextInt(bookComments.length);
            bookCommentList.add(bookComments[index]);
        }
    }

    //向评论adapter中添加数据
    private void addDate() {
        CommentListAdapter adapter = new CommentListAdapter(bookCommentList);
        recyclerView.setAdapter(adapter);

    }


    //监听事件
    private void onClick() {

        //scrollView从顶部显示
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //直接置顶，瞬间回到顶部，没有滚动过程
                scrollView.scrollTo(0,0);
            }
        });

        wantRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WantReadActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("bookISBN", book_ISBN);
                bundle.putString("bookScore", book_score);
                intent.putExtra("id_score",bundle);
                startActivity(intent);
            }
        });
        reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReadingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("bookISBN", book_ISBN);
                bundle.putString("bookScore", book_score);
                intent.putExtra("id_score",bundle);
                startActivity(intent);
            }
        });
        havaRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SeenActivity.class);
                startActivity(intent);
            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
    }


    public void onBackPressed() {
        finish();
    }

}