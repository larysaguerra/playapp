package asyral.com.playapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import asyral.com.playapp.R;
import asyral.com.playapp.adapter.AdapterCategory;
import asyral.com.playapp.data.Category;

/**
 * Created by guerra on 11/01/17.
 * FragmentCategory
 */

public class FragmentCategory extends Fragment {

    private ViewGroup viewGroup;
    private RecyclerView recycler;

    public static FragmentCategory newFragmentCategory(String value) {
        FragmentCategory plannerFragment = new FragmentCategory();
        Bundle bundle = new Bundle();
        bundle.putString("key", value);
        plannerFragment.setArguments(bundle);
        plannerFragment.setRetainInstance(true);
        return plannerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_category, container, false);
        findViews();
        initVariables();
        return viewGroup;
    }

    private void findViews() {
        recycler = (RecyclerView) viewGroup.findViewById(R.id.recycler_category);
    }

    private void initVariables() {
        ArrayList<Category> categoryArray = new ArrayList<>();
        String[] type = getActivity().getResources().getStringArray(R.array.category_type);
        String[] name = getActivity().getResources().getStringArray(R.array.category_name);
        String[] icon = getActivity().getResources().getStringArray(R.array.category_icon);
        for (int i = 0; i < type.length; i++) {
            categoryArray.add(new Category(type[i], name[i], icon[i]));
        }

        AdapterCategory adapterCategory = new AdapterCategory();
        if (getResources().getBoolean(R.bool.is_tablet)) {
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2,
                    LinearLayoutManager.VERTICAL, false);
            recycler.setLayoutManager(layoutManager);
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.VERTICAL, false);
            recycler.setLayoutManager(layoutManager);
        }
        recycler.setAdapter(adapterCategory);
        adapterCategory.addData(categoryArray);
    }

}
