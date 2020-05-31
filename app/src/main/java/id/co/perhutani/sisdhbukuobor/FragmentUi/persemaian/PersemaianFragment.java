package id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.Adapter.Persemaian.PersemaianMenuAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.VerticalSpaceItemDecoration;
import id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor.DashboardBukuOborFragment;
import id.co.perhutani.sisdhbukuobor.R;

public class PersemaianFragment extends Fragment {

    private PersemaianViewModel mViewModel;
    View event;
    private List<PersemaianViewModel> lsschedule;
    private SQLiteHandler db;
    public static String token;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView schedulerecyclerview;
    private PersemaianMenuAdapter scheduleAdapter;
    private int color = 0;
    private static final int VERTICAL_ITEM_SPACE = 0;

    public static PersemaianFragment newInstance() {
        return new PersemaianFragment();
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        event = inflater.inflate(R.layout.persemaian_fragment, container, false);

        db = new SQLiteHandler(getActivity());
//        try {
//            token = db.getDataKolom(UserSchema.TABLE_NAME, UserSchema.USER_TOKEN);
//        } catch (Exception e) {
//        }
        schedulerecyclerview = event.findViewById(R.id.event_day_recyclerview);
        swipeRefreshLayout = event.findViewById(R.id.swipe_refresh_layout_recycler_view);
        swipeRefreshLayout.setColorSchemeResources(R.color.google_blue, R.color.google_green, R.color.google_red, R.color.google_yellow);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (color > 4) {
                            color = 0;
                        }
                        lsschedule.clear();
                        try {
                            lsschedule.add(new PersemaianViewModel("1","Pasang Batas Persemaian"));
                            lsschedule.add(new PersemaianViewModel("2","Persiapan Lahan"));
                            lsschedule.add(new PersemaianViewModel("3","Persiapan Sarpra"));
                            lsschedule.add(new PersemaianViewModel("4","Pembuatan Bedeng Sapih"));
                            lsschedule.add(new PersemaianViewModel("5","Penaburan Benih"));
                            lsschedule.add(new PersemaianViewModel("6","Over Spin / Penyapihan"));
                            lsschedule.add(new PersemaianViewModel("7","Pemeliharaan"));
                            lsschedule.add(new PersemaianViewModel("8","Seleksi Bibit Siap Tanam"));
                            lsschedule.add(new PersemaianViewModel("9","Mutasi Bibit"));
                            lsschedule.add(new PersemaianViewModel("10","Distribusi Bibit"));
                            scheduleAdapter = new PersemaianMenuAdapter(getActivity(),lsschedule);
                            schedulerecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                            schedulerecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
                            schedulerecyclerview.setAdapter(scheduleAdapter);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        scheduleAdapter.setColor(++color);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);

            }
        });

//        Toolbar toolbar = event.findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_back);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new DashboardBukuOborFragment();
//                FragmentManager frgManager = getFragmentManager();
//                FragmentTransaction ft = frgManager.beginTransaction();
//                ft.replace(R.id.nav_host_fragment, fragment);
//                ft.commit();
//            }
//        });

        lsschedule = new ArrayList<>();
        try {
        lsschedule.add(new PersemaianViewModel("1","Pasang Batas Persemaian"));
        lsschedule.add(new PersemaianViewModel("2","Persiapan Lahan"));
        lsschedule.add(new PersemaianViewModel("3","Persiapan Sarpra"));
        lsschedule.add(new PersemaianViewModel("4","Pembuatan Bedeng Sapih"));
        lsschedule.add(new PersemaianViewModel("5","Penaburan Benih"));
        lsschedule.add(new PersemaianViewModel("6","Over Spin / Penyapihan"));
        lsschedule.add(new PersemaianViewModel("7","Pemeliharaan"));
        lsschedule.add(new PersemaianViewModel("8","Seleksi Bibit Siap Tanam"));
        lsschedule.add(new PersemaianViewModel("9","Mutasi Bibit"));
        lsschedule.add(new PersemaianViewModel("10","Distribusi Bibit"));
        scheduleAdapter = new PersemaianMenuAdapter(getActivity(),lsschedule);
        schedulerecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        schedulerecyclerview.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        schedulerecyclerview.setAdapter(scheduleAdapter);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return event;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(PersemaianViewModel.class);
        // TODO: Use the ViewModel
    }

}
