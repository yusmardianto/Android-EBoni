package id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.Adapter.SusunRisalah.FragmentAdapter;
import id.co.perhutani.sisdhbukuobor.FragmentUi.TallySheet.RekapTS.RekapTSFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor.BukuOborFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.BelumAccFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.SudahAccFragment;
import id.co.perhutani.sisdhbukuobor.R;


public class TallySheetFragment extends Fragment {

    View view_tally_sheet;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_tally_sheet = inflater.inflate(R.layout.fragment_tally_sheet, container, false);

//        Toolbar toolbar = view_tally_sheet.findViewById(R.id.toolbar_tallysheet);
//        toolbar.setNavigationIcon(R.drawable.ic_back);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new BukuOborFragment();
//                FragmentManager frgManager = getFragmentManager();
//                FragmentTransaction ft = frgManager.beginTransaction();
//                ft.replace(R.id.nav_host_fragment, fragment);
//                ft.commit();
//            }
//        });

        initViewPager();

        return view_tally_sheet;
    }

    private void initViewPager() {
        mTabLayout = view_tally_sheet.findViewById(R.id.tab_layout_main_tallysheet);
        mViewPager = view_tally_sheet.findViewById(R.id.view_pager_main_tallysheet);

        List<String> titles = new ArrayList<>();
        titles.add("Tally Sheet");
        titles.add("Rekap Data");
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ListTallySheetFragment());
        fragments.add(new RekapTSFragment());

        mViewPager.setOffscreenPageLimit(2);

        FragmentAdapter mFragmentAdapter = new FragmentAdapter((getActivity()).getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapter);

        mViewPager.addOnPageChangeListener(pageChangeListener);
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}