package jsc.exam.com.adapter.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jsc.exam.com.adapter.R;
import jsc.exam.com.adapter.bean.CustomBean;
import jsc.exam.com.adapter.utils.CompatResourceUtils;
import jsc.kit.adapter.SimpleAdapter3;
import jsc.kit.adapter.SimpleItemClickListener3;
import jsc.kit.adapter.SpaceItemDecoration;

/**
 * 快速实现复选示例
 * <br>Email:1006368252@qq.com
 * <br>QQ:1006368252
 * create time: 2019/1/3 10:31 Thursday
 *
 * @author jsc
 */
public class CheckableFragment extends BaseFragment {

    SimpleAdapter3<CustomBean> adapter3 = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        recyclerView.addItemDecoration(new SpaceItemDecoration(
                CompatResourceUtils.getDimensionPixelSize(this, R.dimen.space_16),
                CompatResourceUtils.getDimensionPixelSize(this, R.dimen.space_2)
        ));
        adapter3 = new SimpleAdapter3<CustomBean>(R.layout.main_list_item_layout) {
            @Override
            protected void onBindDataViewHolder(@NonNull BaseViewHolder holder, int position, CustomBean dataBean) {
                holder.setText(R.id.tv_label, dataBean.getLabel())
                        .setVisibility(R.id.red_dot_view, dataBean.isSelected() ? View.VISIBLE : View.GONE);
            }
        };
        adapter3.setOnItemClickListener(new SimpleItemClickListener3<CustomBean>() {
            @Override
            public void onDataItemClick(@NonNull View dataItemView, int position, CustomBean dataBean) {
                dataBean.setSelected(!dataBean.isSelected());
                adapter3.notifyItemChanged(position);
            }
        });
        adapter3.bindRecyclerView(recyclerView);
        adapter3.addEmpty(new Object());
        return root;
    }

    @Override
    void onLoadData(Context context) {
        loadNetData();
    }

    private void loadNetData() {
        List<CustomBean> items = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            CustomBean item = new CustomBean();
            item.setLabel("this is " + i);
            items.add(item);
        }
        adapter3.setData(items);
    }
}