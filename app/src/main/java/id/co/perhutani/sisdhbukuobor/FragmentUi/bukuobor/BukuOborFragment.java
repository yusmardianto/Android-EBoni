package id.co.perhutani.sisdhbukuobor.FragmentUi.bukuobor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import id.co.perhutani.sisdhbukuobor.Adapter.SusunRisalah.FragmentAdapter;
import id.co.perhutani.sisdhbukuobor.ExtentionClass.SQLiteHandler;
import id.co.perhutani.sisdhbukuobor.FragmentUi.MonitoringKlsHtnPenampakan.MonitoringKlsHtnFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.gangguan.ListGangguanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.identifikasitenurial.ListIdentifikasiTenurialFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.interaksimdh.ListInteraksiMDHFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.laporanpalbatas.ListPelaporanpalFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pemantauansatwa.ListPemantauansatwaFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.pengelolaanhutan.ListPengelolaanHutanFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.persemaian.PersemaianFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.perubahankelas.ListPerubahanKelasFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.registerpcp.ListRegisterpcpFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.BelumAccFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.SudahAccFragment;
import id.co.perhutani.sisdhbukuobor.FragmentUi.susunrisalah.SusunRisalahFragment;
import id.co.perhutani.sisdhbukuobor.R;

public class BukuOborFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private SQLiteHandler db;
    View root;

    public static BukuOborFragment newInstance() {
        return new BukuOborFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
//        return inflater.inflate(R.layout.buku_obor_fragment, container, false);


        root = inflater.inflate(R.layout.buku_obor_fragment, container, false);

        initViewPager();

        return root;
    }

    private void initViewPager() {
        mTabLayout = root.findViewById(R.id.tab_layout_bukuobor);
        mViewPager = root.findViewById(R.id.view_pager_bukuobor);

        List<String> titles = new ArrayList<>();
        titles.add("Pencatatan");
        titles.add("Pengelolaan Hutan");
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new PencatatanFragment());
        fragments.add(new PengelolaanHutanFragment());

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
