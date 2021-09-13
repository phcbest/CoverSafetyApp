package com.xyxy.coversafetyapp.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.xyxy.coversafetyapp.R;
import com.xyxy.coversafetyapp.entity.respond.AllCoverLogBean;
import com.xyxy.coversafetyapp.ui.adapter.RvLogAdapter;
import com.xyxy.coversafetyapp.utils.api.ApiCoverLog;
import com.xyxy.coversafetyapp.utils.api.RetrofitNetWork;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 井盖日志界面
 */
public class NoteFragment extends Fragment {
    private static final String TAG = "NoteFragment";

    private RecyclerView mRvCoverLog;
    private ApiCoverLog apiCoverLog = RetrofitNetWork.getApiCoverLog();

    public static NoteFragment newInstance() {
        NoteFragment fragment = new NoteFragment();
        return fragment;
    }

    public static Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        initView(view);
        bundle = savedInstanceState;
        //
        Observable<AllCoverLogBean> ob = apiCoverLog.selectAllLog(10, 1);
        setAdapter(ob);

        return view;
    }

    private void setAdapter(Observable<AllCoverLogBean> ob) {
        ob.subscribeOn(Schedulers.io())//子线程中进行http访问.
                .observeOn(AndroidSchedulers.mainThread())//主线程中处理返回接口
                .subscribe(new Observer<AllCoverLogBean>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AllCoverLogBean allCoverLogBean) {
                        //适配listView
                        Log.i(TAG, "onNext: " + new Gson().toJson(allCoverLogBean));
                        if (allCoverLogBean != null) {
                            mRvCoverLog.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                            mRvCoverLog.setAdapter(new RvLogAdapter(allCoverLogBean, new RvLogAdapter.UpdateAdapter() {
                                @Override
                                public void upAdapter() {
                                    setAdapter(ob);
                                }
                            }));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(View view) {
        mRvCoverLog = (RecyclerView) view.findViewById(R.id.rv_cover_log);
    }
}