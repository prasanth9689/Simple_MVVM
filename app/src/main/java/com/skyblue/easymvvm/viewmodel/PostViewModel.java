package com.skyblue.easymvvm.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skyblue.easymvvm.model.PostModel;
import com.skyblue.easymvvm.retrofit.APIClient;
import com.skyblue.easymvvm.retrofit.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    private MutableLiveData<List<PostModel>> mutableLiveData;

    public LiveData<List<PostModel>> getHolidays() {
        if(mutableLiveData==null){
            mutableLiveData = requestPosts();
        }
        return mutableLiveData;
    }

    private final String TAG = getClass().getSimpleName();

    public MutableLiveData<List<PostModel>> requestPosts() {
        final MutableLiveData<List<PostModel>> mutableLiveData = new MutableLiveData<>();

        APIInterface apiService =
                APIClient.getClient().create(APIInterface.class);

        Call<List<PostModel>> call=apiService.getPosts();

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PostModel>> call, @NonNull Response<List<PostModel>> response) {
                Log.e(TAG, "getCurrencyList response="+response );

                if (response.isSuccessful() && response.body()!=null ) {
                    Log.e(TAG, "requestHolidays response.size="+response.body().size() );
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PostModel>> call, @NonNull Throwable t) {
                Log.e(TAG, "getProdList onFailure" + call.toString());
            }
        });

        return mutableLiveData;
    }
}