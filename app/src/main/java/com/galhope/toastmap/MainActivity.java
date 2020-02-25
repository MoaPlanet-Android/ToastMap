package com.galhope.toastmap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    // 출발
    EditText etStartLat, etStartLng;

    // 도착
    EditText etEndLat, etEndLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        lookup();
    }

    private void initView() {
        etStartLat = findViewById(R.id.et_start_lat);
        etStartLng = findViewById(R.id.et_start_lng);

        etStartLat.setText("37.479902");
        etStartLng.setText("126.882960");

        etEndLat = findViewById(R.id.et_end_lat);
        etEndLng = findViewById(R.id.et_end_lng);

        etEndLat.setText("37.481251");
        etEndLng.setText("126.889416");

        Button btnLookup = findViewById(R.id.btn_lookup);
        btnLookup.setOnClickListener(v -> lookup());

    }

    /**
     * 거리 조회
     */
    private void lookup() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-maps.cloud.toast.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        NetworkApi service = retrofit.create(NetworkApi.class);

        ReqModel reqModel = new ReqModel();
        reqModel.setStartX(etStartLng.getText().toString());
        reqModel.setStartY(etStartLat.getText().toString());
        reqModel.setEndX(etEndLng.getText().toString());
        reqModel.setEndY(etEndLat.getText().toString());

        service.getNavigation(
                reqModel
        ).enqueue(new Callback<ResModel>() {
            @Override
            public void onResponse(Call<ResModel> call, Response<ResModel> response) {
                Log.d(TAG, "데이터 결과 : " + new Gson().toJson(response.body()));

                TextView tvDistance = findViewById(R.id.tv_distance);
                tvDistance.setText("거리 : " + response.body().reoutModel.dataModel.distance + "m");

                int time = response.body().reoutModel.dataModel.time;
                int second = time % 60;
                int hour = time / 60;
                int minute = hour % 60;
                hour = hour / 60;

                TextView tvTime = findViewById(R.id.tv_time);
                tvTime.setText("걸리는 시간 ==> " + hour + ":" + minute + ":" + second);
            }

            @Override
            public void onFailure(Call<ResModel> call, Throwable t) {

            }
        });
    }
}
