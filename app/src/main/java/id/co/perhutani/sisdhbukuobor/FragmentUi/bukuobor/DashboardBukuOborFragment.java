package id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.Adapter.DashboardMenuAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.Constant;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.Tools;
import id.co.perhutani.sisdhbukuobor.FragmentUi.dashboard.DashboardViewModel;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.GangguanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PersemaianFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.workorder.WorkOrderFragment;
import id.co.perhutani.sisdhbukuobor.R;

public class DashboardBukuOborFragment extends Fragment {

    RecyclerView recyclerView;
    public DashboardMenuAdapter mAdapter;
    private ProgressBar progressBar;
    private View view;
    private LinearLayout lyt_not_found;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dashboard_buku_obor, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar  = view.findViewById(R.id.progressBar);
        lyt_not_found   = view.findViewById(R.id.lyt_not_found);

        LinearLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), Tools.getGridSpanCount(getActivity()));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        if (getGroupData(getActivity()).size() == 0) {
            lyt_not_found.setVisibility(View.VISIBLE);
        }else{
            lyt_not_found.setVisibility(View.GONE);
        }

        mAdapter = new DashboardMenuAdapter( getActivity(), Constant.getGroupData(getActivity()));
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new DashboardMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, DashboardMenuModel obj, int position) {

//                Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();

                Fragment selectedFragment = null;
                switch (String.valueOf(position)){
                    case "0":
                        selectedFragment = new PersemaianFragment();
                        break;
                    case "1":
                        selectedFragment = new WorkOrderFragment();
                        break;
                    case "2":
                        selectedFragment = new GangguanFragment();
                        break;
                    case "3":
                        selectedFragment = new GangguanFragment();
                        break;
                }

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        selectedFragment).commit();

                //replacing the fragment
                if (selectedFragment != null) {
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    selectedFragment.setEnterTransition(new Slide(Gravity.RIGHT));
                    selectedFragment.setExitTransition(new Slide(Gravity.LEFT));
//                    ft.setCustomAnimations(R.anim.slide_enter_from_left, R.anim.slide_exit_to_right);
                    ft.replace(R.id.nav_host_fragment, selectedFragment);
                    ft.commit();
                }

            }
        });

        return view;
    }

    public static List<DashboardMenuModel> getGroupData(Context ctx)  {
        List<DashboardMenuModel> items = new ArrayList<>();
        String s_name[] = ctx.getResources().getStringArray(R.array.groups_name);
        String s_date[] = ctx.getResources().getStringArray(R.array.groups_date);
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.groups_photos);

        items.add(new DashboardMenuModel(0, s_date[0], s_name[0], "", drw_arr.getResourceId(0,-1)));
        items.add(new DashboardMenuModel(1, s_date[1], s_name[1], "", drw_arr.getResourceId(1,-1)));
        items.add(new DashboardMenuModel(2, s_date[2], s_name[2], "", drw_arr.getResourceId(2,-1)));
        items.add(new DashboardMenuModel(3, s_date[3], s_name[3], "", drw_arr.getResourceId(3,-1)));

        return items;
    }
}
