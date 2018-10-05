package br.com.matheus.chamada;

import android.app.Application;
import android.os.Build;
import android.view.autofill.AutofillManager;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.orhanobut.hawk.Hawk;

import java.util.concurrent.TimeUnit;

import br.com.matheus.chamada.helper.PreferencesHelper;
import br.com.matheus.chamada.service.ChamadaService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mathe on 04/11/2017.
 */

public class ChamadaApplication extends Application {

    private static ChamadaService service;
    private static ChamadaApplication chamadaApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        chamadaApplication = this;

        Hawk.init(this).build();

        configureImageLoader();
        configureRetrofit();
        configureAutoFill();
    }

    public static synchronized ChamadaApplication getInstance() {
        return chamadaApplication;
    }

    public static synchronized ChamadaService getService() {
        return service;
    }

    private void configureImageLoader() {
        DisplayImageOptions options = new DisplayImageOptions
                .Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new FadeInBitmapDisplayer(600))
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .defaultDisplayImageOptions(options)
                .imageDecoder(new BaseImageDecoder(true))
                .memoryCache(new WeakMemoryCache())
                .build();

        ImageLoader.getInstance().init(config);
    }

    private void configureRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient
                .Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("X-Auth-Token",
                                    Hawk.get(PreferencesHelper.ACCESS_TOKEN, ""))
                            .build();
                    return chain.proceed(request);
                })
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(getResources().getString(R.string.serverUrl))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ChamadaService.class);
    }

    private void configureAutoFill() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            AutofillManager autofillManager = getSystemService(AutofillManager.class);
            if (autofillManager != null) {
                autofillManager.disableAutofillServices();
            }
        }
    }
}
