package com.jieli.stream.p016dv.running2.p017ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jieli.stream.p016dv.running2.C1438R;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseFragment;

/* JADX INFO: loaded from: classes.dex */
public class GenericFragment extends BaseFragment {
    private TextView textView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(C1438R.layout.fragment_generic, viewGroup, false);
        this.textView = (TextView) viewInflate.findViewById(C1438R.id.generic_text_view);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getActivity() == null) {
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }
}
