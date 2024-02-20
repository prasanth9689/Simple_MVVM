package com.skyblue.easymvvm.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.skyblue.easymvvm.R;
import com.skyblue.easymvvm.adapter.PostAdapter;
import com.skyblue.easymvvm.databinding.ActivityMainBinding;
import com.skyblue.easymvvm.model.PostModel;
import com.skyblue.easymvvm.utils.MyApplication;
import com.skyblue.easymvvm.viewmodel.PostViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private  final Context context = this;
    private final String TAG = getClass().getSimpleName();
    private PostAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        adapter = new PostAdapter();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        binding.recyclerView.setAdapter(adapter);
        PostViewModel postViewModel = new PostViewModel();

        if(MyApplication.getInstance().isNetworkAvailable()) {
            binding.progressBar.setVisibility(View.VISIBLE);

            postViewModel.getHolidays().observe(this, postModelList -> {
                if (postModelList != null && !postModelList.isEmpty()) {
                    Log.e(TAG, "observe onChanged()=" + postModelList.size());
                    binding.progressBar.setVisibility(View.GONE);
                    adapter.addHolidayList(postModelList);
                    adapter.notifyDataSetChanged();
                }
            });
        }else{
            Toast.makeText(context, "No Network Available", Toast.LENGTH_LONG).show();
        }
    }
}