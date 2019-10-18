package com.hemant.comic_app.Common;

import com.hemant.comic_app.Retrofit.IComicAPI;
import com.hemant.comic_app.Retrofit.RetrofitClient;

import retrofit2.Retrofit;

public class Common {
    public static IComicAPI getAPI(){
        return RetrofitClient.getInstance().create(IComicAPI.class);
    }
}
