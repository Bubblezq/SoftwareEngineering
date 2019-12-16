package com.yuyuereading.Presenter.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuyuereading.Model.bean.BookInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.amap.api.mapcore2d.q.i;

public class SearchFromDouban {
    /**
     * 解析豆瓣搜索的数据
     * 工具类
     * 传入值：json字符串
     * 返回值：List<BookInfo>类
     */
    public static List<BookInfo> parsingBookInfos(final String json) throws IOException {
        JSONObject jsonObject = JSON.parseObject(json);
        List<BookInfo> bookInfos = new ArrayList<BookInfo>();
        int count = jsonObject.getInteger( "count");
        int start =jsonObject.getInteger("start");
        //int total =jsonObject.getInteger("total");
        JSONArray books= jsonObject.getJSONArray("books");
        for(i=start;i<count;i++){
            BookInfo bookInfo;
            bookInfo= BookInfoGetFromDouban.parsingBookInfo(books.get(i).toString());
            bookInfos.add(bookInfo);
        }

        return bookInfos;
    }
}
