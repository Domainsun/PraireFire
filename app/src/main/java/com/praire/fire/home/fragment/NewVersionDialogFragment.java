package com.praire.fire.home.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.base.BaseDialogFragment;




public class NewVersionDialogFragment extends BaseDialogFragment implements View.OnClickListener{

    TextView updateContentTv,oktv,cancel;

    private NewVersionDialogListener mListener;
    String content;
    public static NewVersionDialogFragment newInstance(NewVersionDialogListener listener, String desc) {
        NewVersionDialogFragment fragment = new NewVersionDialogFragment();
        fragment.setCancelable(false);
        fragment.mListener = listener;
        fragment.content = desc;
        return fragment;
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_dialog_new_version;
    }

    @Override
    protected void injectViews(View view) {
        updateContentTv = view.findViewById(R.id.update_content_tv);
        updateContentTv.setText(Html.fromHtml(content));
        oktv = view.findViewById(R.id.ok_tv);
        cancel = view.findViewById(R.id.cancel_tv);
        oktv.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_tv:
                mListener.onDialogPositiveClick(NewVersionDialogFragment.this);
                break;
            case R.id.cancel_tv:
                mListener.setNegativeClick(NewVersionDialogFragment.this);
                break;
            default:
                break;
        }
    }



    public interface NewVersionDialogListener {

        void onDialogPositiveClick(DialogFragment dialog);

        void setNegativeClick(DialogFragment dialog);
    }
}
