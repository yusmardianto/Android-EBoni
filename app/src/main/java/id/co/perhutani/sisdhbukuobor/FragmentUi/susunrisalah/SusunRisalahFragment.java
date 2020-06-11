package id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.Adapter.SusunRisalah.FragmentAdapter;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.GangguanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.workorder.WorkOrderFragment;
import id.co.perhutani.sisdhbukuobor.R;


public class SusunRisalahFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    View view_susunrisalah;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_susunrisalah = inflater.inflate(R.layout.fragment_susun_risalah, container, false);

        initViewPager();
        return view_susunrisalah;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initViewPager() {
        mTabLayout = view_susunrisalah.findViewById(R.id.tab_layout_main);
        mViewPager = view_susunrisalah.findViewById(R.id.view_pager_main);

        List<String> titles = new ArrayList<>();
        titles.add("Belum ACC");
        titles.add("Sudah ACC");
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new BelumAccFragment());
        fragments.add(new SudahAccFragment());

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
