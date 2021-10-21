package com.xyxy.coversafetyapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xyxy.coversafetyapp.R;
import com.xyxy.coversafetyapp.entity.respond.GetAllCoverErrorBean;

import java.util.List;

public class RvLogErrorAdapter extends RecyclerView.Adapter<RvLogErrorAdapter.ViewHolder> {

    private SyncAdapter syncAdapter;
    private final List<GetAllCoverErrorBean.PageBean.RecordsBean> records;

    public RvLogErrorAdapter(GetAllCoverErrorBean errorBean, SyncAdapter syncAdapter) {
        this.syncAdapter = syncAdapter;
        records = errorBean.getPage().getRecords();
    }

    public interface SyncAdapter {
        void sync(GetAllCoverErrorBean.PageBean.RecordsBean recordsBean);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cover_warning, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetAllCoverErrorBean.PageBean.RecordsBean recordsBean = records.get(position);
        //设置不同状态的下的颜色与字体颜色
        switch (recordsBean.getErrorLevel()) {
            case 1:
                setErrorColorByLevel(holder, 0x126200EE, 0xff6200EE);
                break;
            case 2:
                setErrorColorByLevel(holder, 0x12c56200, 0xffc56200);
                break;
            case 3:
                setErrorColorByLevel(holder, 0x12ff8a80, 0xffff8a80);
                break;
            case 4:
                setErrorColorByLevel(holder, 0x1200695c, 0xff00695c);
                break;
            case 5:
                setErrorColorByLevel(holder, 0x12b71c1c, 0xffb71c1c);
                break;
        }
        holder.mTvErrorInfo.setText(String.format("井盖UID:%s\n错误等级:%d\n报错原因:%s"
                , recordsBean.getCoverUid(), recordsBean.getErrorLevel(), recordsBean.getErrorDepict()));

        holder.mTvErrorInfo.setOnClickListener(v -> syncAdapter.sync(recordsBean));

    }

    private void setErrorColorByLevel(@NonNull ViewHolder holder, int background, int textColor) {
        holder.mTvErrorInfo.setBackgroundColor(background);
        holder.mTvErrorInfo.setTextColor(textColor);
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvErrorInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTvErrorInfo = (TextView) itemView.findViewById(R.id.tv_error_info);

        }
    }
}
