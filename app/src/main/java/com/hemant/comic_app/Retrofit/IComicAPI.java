package com.hemant.comic_app.Retrofit;

import com.hemant.comic_app.Model.Banner;
import com.hemant.comic_app.Model.Comic;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IComicAPI {
    @GET("banner")
    Observable<List<Banner>> getBannerList();

    @GET("comic")
    Observable<List<Comic>> getCommicList();
}
