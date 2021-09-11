package com.xyxy.coversafetyapp.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.xyxy.coversafetyapp.R;
import com.xyxy.coversafetyapp.entity.respond.AllCoverLogBean;
import com.xyxy.coversafetyapp.entity.respond.GetCoverByUidBean;
import com.xyxy.coversafetyapp.ui.fragment.NoteFragment;
import com.xyxy.coversafetyapp.utils.CodeUtils;
import com.xyxy.coversafetyapp.utils.api.ApiCoverInfo;
import com.xyxy.coversafetyapp.utils.api.RetrofitNetWork;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RvLogAdapter extends RecyclerView.Adapter<RvLogAdapter.ViewHolder> {

    private final List<AllCoverLogBean.PageBean.RecordsBean> records;
    private ApiCoverInfo apiCoverInfo = RetrofitNetWork.getApiCoverInfo();

    public RvLogAdapter(AllCoverLogBean allCoverLogBean) {
        records = allCoverLogBean.getPage().getRecords();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cover_log, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String coverUid = records.get(position).getCoverUid();
        Observable<GetCoverByUidBean> coverByUid = apiCoverInfo.getCoverByUid(coverUid);
        coverByUid.observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mMaterialCardView = (MaterialCardView) itemView.findViewById(R.id.materialCardView);
            mMapviewLog = (TextureMapView) itemView.findViewById(R.id.mapview_log);
            mIvLogFavorite = (ImageView) itemView.findViewById(R.id.iv_log_favorite);

        }
    }
}
