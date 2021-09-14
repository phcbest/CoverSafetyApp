package com.xyxy.coversafetyapp.ui.fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xyxy.coversafetyapp.R;
import com.xyxy.coversafetyapp.entity.respond.GetAllCoverErrorBean;
import com.xyxy.coversafetyapp.ui.adapter.RvLogErrorAdapter;
import com.xyxy.coversafetyapp.utils.api.ApiCoverError;
import com.xyxy.coversafetyapp.utils.api.RetrofitNetWork;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WarningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarningFragment extends Fragment {


    private RecyclerView mRvWarning;
    private FloatingActionButton mBtnAddError;
    private ApiCoverError apiCoverError = RetrofitNetWork.getApiCoverError();


    public static WarningFragment newInstance() {
        WarningFragment fragment = new WarningFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_warning, container, false);
        initView(view);
        //适配error的rv
        Observable<GetAllCoverErrorBean> observable = apiCoverError.getAllCoverError(10, 1);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GetAllCoverErrorBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GetAllCoverErrorBean getAllCoverErrorBean) {
                        mRvWarning.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                        mRvWarning.setAdapter(new RvLogErrorAdapter(getAllCoverErrorBean, () -> {

                        }));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(getContext(), "网络请求错误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return view;
    }

    private void initView(View view) {
        mRvWarning = (RecyclerView) view.findViewById(R.id.rv_warning);
        mBtnAddError = (FloatingActionButton) view.findViewById(R.id.btn_add_error);
    }
}