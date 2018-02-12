package com.praire.fire.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.base.BaseDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyp on 11/16/15.
 */
public class DownloadDialogFragment extends BaseDialogFragment {



    @BindView(R.id.status_tv)
    TextView statusTv;
    @BindView(R.id.transfer_percent_tv)
    TextView transferPercentTv;
    @BindView(R.id.download_progress)
    ProgressBar downloadProgress;
    @BindView(R.id.data_size_info_tv)
    TextView dataSizeInfoTv;
    @BindView(R.id.retry_tv)
    TextView retryTv;
    @BindView(R.id.retry_v)
    RelativeLayout retryView;
    @BindView(R.id.cancel_tv)
    TextView cancelTv;

    private DownloadDialogFragmentListener mListener;

    public static DownloadDialogFragment newInstance(DownloadDialogFragmentListener listener) {
        DownloadDialogFragment fragment = new DownloadDialogFragment();
        fragment.mListener = listener;
        fragment.setCancelable(false);
        return fragment;
    }
    @Override
    protected void injectViews(View view) {
        ButterKnife.bind(this, view);
    }



    public void updateProgress(final int progress, final int transferDataSize,final int dataSize) {
        downloadProgress.setProgress(progress);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                transferPercentTv.setText("已完成" + progress + "%");

                dataSizeInfoTv.setText(transferDataSize + "MB/" + dataSize + "MB");
            }
        });
    }

    public void retryViewHide() {
        retryView.setVisibility(View.GONE);
    }

    public void returnViewShow() {
        retryView.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_dialog_download;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.retry_tv, R.id.cancel_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.retry_tv:
                mListener.onRetryClick(DownloadDialogFragment.this);
                break;
            case R.id.cancel_tv:
                mListener.setCancelClick(DownloadDialogFragment.this);
                break;
        }
    }


    public interface DownloadDialogFragmentListener {

        void onRetryClick(DialogFragment dialog);

        void setCancelClick(DialogFragment dialog);
    }

}
