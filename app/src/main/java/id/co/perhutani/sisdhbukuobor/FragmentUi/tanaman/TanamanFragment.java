package id.co.perhutani.sisdhbukuobor.FragmentUi.tanaman;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.co.perhutani.sisdhbukuobor.Adapter.TanamanMenuAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.R;

public class TanamanFragment extends Fragment {

    private TanamanViewModel mViewModel;
    private List<TanamanViewModel> lsschedule;
    private SQLiteHandler db;
    public static String token;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView schedulerecyclerview;
    private TanamanMenuAdapter scheduleAdapter;
    private int color = 0;
    private static final int VERTICAL_ITEM_SPACE = 0;

    public static TanamanFragment newInstance() {
        return new TanamanFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tanaman_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TanamanViewModel.class);
        // TODO: Use the ViewModel
    }

}
