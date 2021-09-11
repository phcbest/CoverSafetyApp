package com.xyxy.coversafetyapp.ui.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.xyxy.coversafetyapp.R;
import com.xyxy.coversafetyapp.entity.request.CoverInfoEntity;
import com.xyxy.coversafetyapp.entity.respond.AllCoverBean;
import com.xyxy.coversafetyapp.utils.api.ApiCoverInfo;
import com.xyxy.coversafetyapp.utils.api.RetrofitNetWork;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment implements LocationSource {

    private static final String TAG = "SettingFragment";

    private MapView mMapviewSetting;
    private AMap aMap;

    private OnLocationChangedListener listener;
    private AMapLocationClient client;
    private NestedScrollView mBottomSheet;
    private CircleImageView mIvCoverPortrait;
    private TextView mTvCoverInfo;
    private ImageView mIvCoverShare;
    private EditText mEtRoad;
    private MaterialButton mBtnSettingSubmit;
    private ApiCoverInfo apiCoverInfo;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        initView(view);
        mMapviewSetting.onCreate(savedInstanceState);
        aMap = mMapviewSetting.getMap();
        UiSettings uiSettings = aMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(false);

        //获得定位权限
        aMap.setLocationSource(this);
        getLocationPermission();
        //获得位置
        getLocation();
        //显示井盖
        showCover();
        return view;
    }


    private void showCover() {
        apiCoverInfo = RetrofitNetWork.getApiCoverInfo();
        Call<AllCoverBean> allCoverBeanCall = apiCoverInfo.allCover(10, 1);
        allCoverBeanCall.enqueue(new Callback<AllCoverBean>() {
            @Override
            public void onResponse(Call<AllCoverBean> call, Response<AllCoverBean> response) {
                //在地图上标出点
                if (response.body() != null && response.body().getPage() != null) {
                    aMap.clear();
                    for (AllCoverBean.PageBean.ListBean listBean : response.body().getPage().getList()) {
                        String[] split = listBean.getCoverLongLat().split("-");
                        Bitmap bitmap = BitmapFactory
                                .decodeResource(getResources(), R.mipmap.cover);
                        BitmapDescriptor descriptor = BitmapDescriptorFactory
                                .fromBitmap(bitmap);
                        aMap.addMarker(new MarkerOptions()
                                .title(new Gson().toJson(listBean))
                                .position(new LatLng(Double.parseDouble(split[1]), Double.parseDouble(split[0])))
                                .icon(descriptor));
                    }
                } else {
                    Toast.makeText(getContext(), "网络请求出现问题，请检查网络", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllCoverBean> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(getContext(), "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
        //井盖的点击事件
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i(TAG, "onMarkerClick: " + marker.getTitle());
                //屏幕中心移过去
                moveCamera(marker.getPosition().longitude, marker.getPosition().latitude);
                bottomSheetChange(marker.getTitle(), marker.getPosition().longitude, marker.getPosition().latitude);
                return true;
            }
        });
    }

    private void bottomSheetChange(String title, double longitude, double latitude) {
        AllCoverBean.PageBean.ListBean listBean = new Gson().fromJson(title, AllCoverBean.PageBean.ListBean.class);
        BottomSheetBehavior<NestedScrollView> behavior = BottomSheetBehavior.from(mBottomSheet);
        //拉起底部栏的时候的回调
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.i(TAG, "onStateChanged: ");
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i(TAG, "onSlide: ");
            }
        });

        //设置弹出dialog的ui与事件
        SpannableString sp = new SpannableString(String.format(
                "井盖编号:\n%s\n" +
                        "经度:%f\n" +
                        "纬度:%f\n" +
                        "倾斜度:%d",
                listBean.getUid(), longitude, latitude, listBean.getCoverSensorStatus()));
        sp.setSpan(new AbsoluteSizeSpan(10, true), 42, sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //请求修改街道信息
        mBtnSettingSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoverInfoEntity coverInfoEntity = new CoverInfoEntity();
                coverInfoEntity.setUid(listBean.getUid());
                coverInfoEntity.setCoverRoad(mEtRoad.getText().toString());
                apiCoverInfo.updateCoverInfo(coverInfoEntity)
                        .enqueue(new Callback<Map<String, Object>>() {
                            @Override
                            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                                if (response.body() != null) {
                                    Map<String, Object> body = response.body();
                                    if (body.get("msg").equals("success")) {
                                        Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                                        //缩回去
                                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                                        //刷新所有的井盖
                                        showCover();
                                        //获得自己位置
                                        getLocation();
                                    } else {
                                        Toast.makeText(getContext(), "请求出错:" + body.get("reason"), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getContext(), "请求出错", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<Map<String, Object>> call, Throwable t) {

                            }
                        });

            }
        });


        mTvCoverInfo.setText(sp);
        mEtRoad.setText(listBean.getCoverRoad());
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        aMap.setOnMapClickListener(latLng -> behavior.setState(BottomSheetBehavior.STATE_COLLAPSED));
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(), "没有定位权限，请同意定位权限", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i(TAG, "进入权限请求回调");
        if (requestCode == 10) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(getContext(), "未同意定位权限请求", Toast.LENGTH_SHORT).show();
//                getLocationPermission();
            }
        }
    }


    private void getLocation() {
        moveCamera(114.99968262848277, 27.855947370214256);
        client = new AMapLocationClient(getContext());
        AMapLocationClientOption option = new AMapLocationClientOption();
        client.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                double longitude = aMapLocation.getLongitude();
                double latitude = aMapLocation.getLatitude();
                Log.i(TAG, String.format("经度%s，维度%s", longitude, latitude));
                if (longitude != 0 && latitude != 0) {
                    moveCamera(longitude, latitude);
                    aMap.setMyLocationEnabled(true);
                    client.stopLocation();
                    listener.onLocationChanged(aMapLocation);
                }
            }
        });
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setInterval(2000);
        client.setLocationOption(option);
        client.stopLocation();
        client.startLocation();
    }

    private void moveCamera(double longitude, double latitude) {
        aMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                new CameraPosition.Builder()
                        .target(new LatLng(latitude, longitude))
                        .zoom(14)
                        .build()));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapviewSetting.onDestroy();
        client.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapviewSetting.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapviewSetting.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapviewSetting.onSaveInstanceState(outState);
    }

    private void initView(View view) {
        mMapviewSetting = (MapView) view.findViewById(R.id.mapview_setting);
        mBottomSheet = (NestedScrollView) view.findViewById(R.id.bottom_sheet);
        mIvCoverPortrait = (CircleImageView) view.findViewById(R.id.iv_cover_portrait);
        mTvCoverInfo = (TextView) view.findViewById(R.id.tv_cover_info);
        mIvCoverShare = (ImageView) view.findViewById(R.id.iv_cover_share);
        mEtRoad = (EditText) view.findViewById(R.id.et_road);
        mBtnSettingSubmit = (MaterialButton) view.findViewById(R.id.btn_setting_submit);
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        listener = onLocationChangedListener;

    }

    @Override
    public void deactivate() {

    }
}