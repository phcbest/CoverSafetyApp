package com.xyxy.coversafetyapp.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.xyxy.coversafetyapp.R;
import com.xyxy.coversafetyapp.entity.respond.AllCoverLogBean;
import com.xyxy.coversafetyapp.entity.respond.GetCoverByUidBean;
import com.xyxy.coversafetyapp.ui.fragment.NoteFragment;
import com.xyxy.coversafetyapp.utils.CodeUtils;
import com.xyxy.coversafetyapp.utils.api.ApiCoverInfo;
import com.xyxy.coversafetyapp.utils.api.ApiCoverLog;
import com.xyxy.coversafetyapp.utils.api.RetrofitNetWork;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RvLogAdapter extends RecyclerView.Adapter<RvLogAdapter.ViewHolder> {

    private final List<AllCoverLogBean.PageBean.RecordsBean> records;

    private UpdateAdapter updateAdapter;

    private static final String TAG = "RvLogAdapter";
    private ApiCoverInfo apiCoverInfo = RetrofitNetWork.getApiCoverInfo();
    private ApiCoverLog apiCoverLog = RetrofitNetWork.getApiCoverLog();

    public RvLogAdapter(List<AllCoverLogBean.PageBean.RecordsBean> records, UpdateAdapter updateAdapter) {
        this.records = records;
        this.updateAdapter = updateAdapter;
    }

    public RvLogAdapter(AllCoverLogBean allCoverLogBean, UpdateAdapter updateAdapter) {
        records = allCoverLogBean.getPage().getRecords();
        this.updateAdapter = updateAdapter;
    }

    public interface UpdateAdapter {
        void upAdapter();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cover_log, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AllCoverLogBean.PageBean.RecordsBean recordsBean = records.get(position);
        String coverUid = recordsBean.getCoverUid();
        Observable<GetCoverByUidBean> coverByUid = apiCoverInfo.getCoverByUid(coverUid);

        //地图点击事件
        holder.mMapviewLog.getMap().setOnMapClickListener(latLng -> {
            AlertDialog alertDialog = new MaterialAlertDialogBuilder(holder.itemView.getContext())
                    .setTitle("井盖警告信息")
                    .setMessage(String.format("井盖:\n%s\nlog:\n%s", coverUid, recordsBean.getLog()))
                    .setNegativeButton("删除日志", (dialog, which) -> {
                        Integer[] ints = {recordsBean.getId()};
                        Observable<Map<String, Object>> mapObservable = apiCoverLog.deleteLogById(ints);
                        mapObservable.subscribeOn(Schedulers.io())//发布者运行的线程
                                .observeOn(AndroidSchedulers.mainThread())//订阅者运行的线程
                                .subscribe(new Observer<Map<String, Object>>() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onNext(@NonNull Map<String, Object> stringObjectMap) {
                                        if (Objects.equals(stringObjectMap.get("msg"), "success")) {
                                            Toast.makeText(holder.itemView.getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                                            //重新适配适配器
                                            updateAdapter.upAdapter();
                                        } else {
                                            Toast.makeText(holder.itemView.getContext(), "删除失败", Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        Toast.makeText(holder.itemView.getContext(), "网络不通畅", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });


                    })
                    .setPositiveButton("确认", (dialog, which) -> {
                        dialog.cancel();
                    })
                    .create();
            alertDialog.show();
        });
        holder.mIvLogFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: like");
            }
        });

        coverByUid.subscribeOn(Schedulers.io())//发布者
                .observeOn(AndroidSchedulers.mainThread())//订阅者
                .subscribe(new Observer<GetCoverByUidBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GetCoverByUidBean getCoverByUidBean) {


                        //地图部分
                        holder.mMapviewLog.onCreate(NoteFragment.bundle);
                        AMap map = holder.mMapviewLog.getMap();
                        map.getUiSettings().setAllGesturesEnabled(false);
                        map.getUiSettings().setZoomControlsEnabled(false);
                        double[] longLat = CodeUtils.isLongLat(getCoverByUidBean.getCoverInfo().getCoverLongLat());

                        Bitmap bitmap = BitmapFactory
                                .decodeResource(holder.itemView.getResources(), R.mipmap.cover);
                        BitmapDescriptor descriptor = BitmapDescriptorFactory
                                .fromBitmap(bitmap);
                        map.addMarker(new MarkerOptions()
                                .position(new LatLng(longLat[1], longLat[0]))
                                .icon(descriptor));
                        map.moveCamera(CameraUpdateFactory.newCameraPosition(
                                new CameraPosition.Builder()
                                        .target(new LatLng(longLat[1], longLat[0]))
                                        .zoom(14)
                                        .build()));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(holder.itemView.getContext(), "请求失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialCardView mMaterialCardView;
        private TextureMapView mMapviewLog;
        private ImageView mIvLogFavorite;
        private FrameLayout mFrameLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mMaterialCardView = (MaterialCardView) itemView.findViewById(R.id.materialCardView);
            mMapviewLog = (TextureMapView) itemView.findViewById(R.id.mapview_log);
            mIvLogFavorite = (ImageView) itemView.findViewById(R.id.iv_log_favorite);
            mFrameLayout = (FrameLayout) itemView.findViewById(R.id.fl_log_item);

        }
    }
}
