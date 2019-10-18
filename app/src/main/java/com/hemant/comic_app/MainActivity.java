package com.hemant.comic_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hemant.comic_app.Adapter.MySliderAdapter;
import com.hemant.comic_app.Common.Common;
import com.hemant.comic_app.Model.Banner;
import com.hemant.comic_app.Retrofit.IComicAPI;
import com.hemant.comic_app.Service.PicasoImageLoadingService;

import java.util.List;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ss.com.bannerslider.Slider;

public class MainActivity extends AppCompatActivity {
    Slider slider;
    IComicAPI iComicAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        iComicAPI =Common.getAPI();
        //init API
        slider = (Slider)findViewById(R.id.banner_slider);
        Slider.init(new PicasoImageLoadingService());
        fetchBanner();

    }
    private void fetchBanner(){
        compositeDisposable.add(iComicAPI.getBannerList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Banner>>() {
            @Override
            public void accept(List<Banner> banners) throws Exception {
                slider.setAdapter(new MySliderAdapter(banners));

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Toast.makeText(MainActivity.this,"Error while Loading Banner",Toast.LENGTH_LONG).show();
            }
        }));

    }
}
