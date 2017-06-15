package com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.base;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.interfaces.OnLoadMoreListener;
import com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.util.Util;
import com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.viewholder.RecyclerViewCommonViewHolder;

import java.util.ArrayList;
import java.util.List;

//isAddFooter和isOpenLoadMore不能同时为true  footerView有两种，一种是用于自动加载 另一种是尾部 它们不会同时存在

/**
 * @author dragon
 * @date 2017/6/14 15:04
 * @editor
 * @date
 * @describe
 */
public abstract class RecyclerViewBaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER_VIEW = 0;
    public static final int TYPE_COMMON_VIEW = 1;
    public static final int TYPE_FOOTER_VIEW = 2;
    protected Context context;
    private OnLoadMoreListener onLoadMoreListener;//加载更多回调

    private View loadingView; //加载中
    private View loadFailedView; //加载失败
    private View loadEndView; //加载完成
    private LinearLayout footerLayout;//加载布局

    protected List<T> dataList;//数据源

    private boolean isAutoLoadMore = true;//是否自动加载，当数据不满一屏幕会自动加载

    private boolean isAddHeader;//是否根据Item type添加头部
    private boolean isAddFooter;//是否根据Item type添加尾部

    private boolean isOpenLoadMore;//是否开启加载更多


    protected abstract int getViewType(int position, T data);

    public RecyclerViewBaseAdapter(Context context) {
        this.context = context;
        dataList = new ArrayList<>();
    }

    /**
     * StaggeredGridLayoutManager模式时，FooterView可占据一行
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isFooterView(holder.getLayoutPosition())) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    /**
     * GridLayoutManager模式时， FooterView可占据一行，判断RecyclerView是否到达底部
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isFooterView(position)) {
                        return gridManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
        startLoadMore(recyclerView, layoutManager);
    }

    @Override
    public int getItemCount() {
        if (dataList.isEmpty()) {
            return 0;
        }
        return dataList.size() + getFooterViewCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooterView(position)) {
            return TYPE_FOOTER_VIEW;
        }
        return getViewType(position, dataList.get(position));
    }

    @Override
    public RecyclerViewCommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewCommonViewHolder viewHolder = null;
        switch (viewType) {
            case TYPE_FOOTER_VIEW:
                if (footerLayout == null) {
                    getFooterLayout();
                }
                viewHolder = RecyclerViewCommonViewHolder.create(footerLayout);
                break;
        }
        return viewHolder;
    }

    private void getFooterLayout() {
        footerLayout = new LinearLayout(context);
//        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(500, 500);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        footerLayout.setLayoutParams(layoutParams);
    }

    /**
     * 是否是FooterView
     *
     * @param position
     * @return
     */
    private boolean isFooterView(int position) {
        return (isOpenLoadMore || isAddFooter) && position == getItemCount() - 1;
    }

    protected boolean isCommonItemView(int viewType) {
        return viewType != TYPE_FOOTER_VIEW;
    }

    /**
     * 判断列表是否滑动到底部
     *
     * @param recyclerView
     * @param layoutManager
     */
    private void startLoadMore(RecyclerView recyclerView, final RecyclerView.LayoutManager layoutManager) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisibleItemPosition = findLastVisibleItemPosition(layoutManager);
                    int i = lastVisibleItemPosition + 1;
                    int itemCount = getItemCount();
                    if (!isAutoLoadMore && i == itemCount) {
                        scrollLoadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = findLastVisibleItemPosition(layoutManager);
                int i = lastVisibleItemPosition + 1;
                int itemCount = getItemCount();
                if (isAutoLoadMore && i == itemCount) {
                    scrollLoadMore();
                } else if (isAutoLoadMore) {
                    isAutoLoadMore = false;
                }
            }
        });
    }

    /**
     * 到达底部开始加载更多
     */
    private void scrollLoadMore() {
        if (isOpenLoadMore && footerLayout != null && loadingView != null && footerLayout.getChildAt(0) == loadingView) {//只有最后一个是加载状态才可以
            if (onLoadMoreListener != null) {
                onLoadMoreListener.onLoadMore(false);
            }
        }
    }

    private int findLastVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
            return Util.findMax(lastVisibleItemPositions);
        }
        return -1;
    }

    /**
     * 清空footer view
     */
    public void removeFooterView() {
        footerLayout.removeAllViews();
    }

    /**
     * 添加新的footer view
     *
     * @param footerView
     */
    private void addFooterView(View footerView) {
        if (footerView == null) {
            return;
        }
        if (footerLayout == null) {
            getFooterLayout();
        }
        removeFooterView();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        footerLayout.addView(footerView, params);
    }

    /**
     * 加载更多的数据
     *
     * @param datas
     */
    public void setData(List<T> datas) {
        int size = dataList.size();
        dataList.addAll(datas);
        notifyDataSetChanged();
        //todo dragon 2017/6/15 16:49
        // notifyItemInserted(size);   //用这个方法会导致数据出错，暂时还没找到原因  使用notifyDataSetChanged不会报错 但是没有动画效果
        /*if (size != dataList.size()) {
            notifyItemRangeChanged(size, dataList.size() - size);
        }*/
    }

    public List<T> getData() {
        return dataList;
    }

    public void addItem(int position) {
        // dataList.add(position,"新加项");
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
        if (position != dataList.size()) {//更新防止position错乱
            notifyItemRangeChanged(position, dataList.size() - position);
        }
    }

    public void updateItem(int position, T updateData) {
        dataList.set(position, updateData);
        notifyItemChanged(position);
    }

    /**
     * 根据positiond得到data
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        if (dataList.isEmpty()) {
            return null;
        }
        return dataList.get(position);
    }

    /**
     * 设置是否添加头部
     */
    public void setAddHeader(boolean addHeader) {
        isAddHeader = addHeader;
        if (isAddHeader) {
            dataList.add(null);//让数据源第一条为null来添加头部
        }
    }

    /**
     * 设置是否添加尾部
     */
    public void setAddFooter(boolean addFooter) {
        isAddFooter = addFooter;
    }

    /**
     * 设置是否开启加载更多
     */
    public void setOpenLoadMore(boolean openLoadMore) {
        isOpenLoadMore = openLoadMore;
    }

    /**
     * 初始化加载中布局
     */
    public void setLoadingView(View loadingView) {
        this.loadingView = loadingView;
        addFooterView(this.loadingView);
    }

    public void setLoadingView(int loadingId) {
        setLoadingView(Util.inflate(context, loadingId));
    }

    public void setFooterView(int footerViewId) {
        setFooterView(Util.inflate(context, footerViewId));
    }

    public void setFooterView(View footerView) {
        addFooterView(footerView);
    }

    /**
     * 初始化加载失败布局
     *
     * @param loadFailedView
     */
    public void setLoadFailedView(View loadFailedView) {
        if (loadFailedView == null) {
            return;
        }
        this.loadFailedView = loadFailedView;
        addFooterView(this.loadFailedView);
        this.loadFailedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFooterView(loadingView);
                if (onLoadMoreListener != null) {
                    onLoadMoreListener.onLoadMore(true);
                }
            }
        });
    }

    public void setLoadFailedView(int loadFailedId) {
        setLoadFailedView(Util.inflate(context, loadFailedId));
    }

    /**
     * 初始化加载完成布局
     *
     * @param loadEndView
     */
    public void setLoadEndView(View loadEndView) {
        this.loadEndView = loadEndView;
        addFooterView(this.loadEndView);
    }

    public void setLoadEndView(int loadEndId) {
        setLoadEndView(Util.inflate(context, loadEndId));
    }

    /**
     * 返回foot数量
     *
     * @return
     */
    public int getFooterViewCount() {
        return (isOpenLoadMore || isAddFooter) && !dataList.isEmpty() ? 1 : 0;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        onLoadMoreListener = loadMoreListener;
    }

}
