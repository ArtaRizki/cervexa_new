package com.generalplus.GoPlusDrone.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
/* JADX INFO: loaded from: classes.dex */
public class FileSelectFragment extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(com.weioa.KmedHealthIndonesia.R.layout.activity_fileselect, viewGroup, false);
        if (bundle == null) {
            FragmentTransaction fragmentTransactionBeginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.replace(com.weioa.KmedHealthIndonesia.R.id.sample_content_fragment, TabFragment.newInstance());
            fragmentTransactionBeginTransaction.commit();
        }
        return viewInflate;
    }
}
