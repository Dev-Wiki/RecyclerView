前一段时间，因为项目需要使用了RecyclerView，为了方便使用还进行封装了，详细参见此处：[RecyclerView中Adapter和ViewHolder的封装 - DevWiki](http://blog.devwiki.net/index.php/2016/05/22/Recycler-View-Adapter-ViewHolder.html)。

那样的封装有几个问题：

1. `ViewHolder`的`setData(M data)`虽然方便设置数据，但是ViewHolder需要知晓数据类型。ViewHolder应该只用作View的缓存，而不应该知晓填充View的数据。

2. `BaseAdapter`无法添加Header和Footer。

3. 点击事件耦合性较高。

基于以上几点，对BaseHolder和BaseAdapter进行修改优化。


<!--more-->


## BaseHolder的优化

相对于旧版的`BaseHolder`：

1. 新版的去除的数据类型的注入，使`ViewHolder`只用来缓存View。
2. 添加`SparseArray`，使之来缓存View。
3. 添加`BaseHolder(View view)`构造器，外部更方便控制View。
4. 保留`getContext()`方法，方便获取Context对象。

新版的BaseHolder代码如下：

```java
/**
 * 基础的ViewHolder</br>
 * ViewHolder只作View的缓存,不关心数据内容
 * Created by DevWiki on 2016/5/17.
 */
public class BaseHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> viewArray;

    /**
     * 构造ViewHolder
     * @param parent 父类容器
     * @param resId 布局资源文件id
     */
    public BaseHolder(ViewGroup parent, @LayoutRes int resId) {
        super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
        viewArray = new SparseArray<>();
    }

    /**
     * 构建ViewHolder
     * @param view 布局View
     */
    public BaseHolder(View view) {
        super(view);
        viewArray = new SparseArray<>();
    }

    /**
     * 获取布局中的View
     * @param viewId view的Id
     * @param <T> View的类型
     * @return view
     */
    protected <T extends View>T getView(@IdRes int viewId){
        View view = viewArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewArray.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取Context实例
     * @return context
     */
    protected Context getContext() {
        return itemView.getContext();
    }
}
```

## Adapter部分的优化

Adapter拆分为两个抽象类：`AbsAdapter`与`BaseAdapter`，其中：
`AbsAdapter`：封装了和ViewHolder和HeaderView，FooterView相关的方法。
`BaseAdapter`：继承AbsAdapter,封装了数据相关的方法。

各自聚焦于不同的方面，方面日后扩展。

`AbsAdapter`的代码如下：

```java
/**
 * RecyclerView.Adapter的扩展,包含headerView/footerView等
 * Created by DevWiki on 2016/7/13.
 */

public abstract class AbsAdapter<M, VH extends BaseHolder> extends RecyclerView.Adapter<BaseHolder> {

    private static final String TAG = "AbsAdapter";

    public static final int VIEW_TYPE_HEADER = 1024;
    public static final int VIEW_TYPE_FOOTER = 1025;

    protected View headerView;
    protected View footerView;

    protected Context context;

    public AbsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public final BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            return new BaseHolder(headerView);
        } else if (viewType == VIEW_TYPE_FOOTER) {
            return new BaseHolder(footerView);
        } else {
            return createCustomViewHolder(parent, viewType);
        }
    }

    /**
     * 创建自定义的ViewHolder
     *
     * @param parent 父类容器
     * @param viewType view类型{@link #getItemViewType(int)}
     * @return ViewHolder
     */
    public abstract VH createCustomViewHolder(ViewGroup parent, int viewType);

    @Override
    public final void onBindViewHolder(BaseHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_HEADER:
            case VIEW_TYPE_FOOTER:
                break;
            default:
                bindCustomViewHolder((VH) holder, position);
                break;
        }
    }

    /**
     * 绑定自定义的ViewHolder
     *
     * @param holder ViewHolder
     * @param position 位置
     */
    public abstract void bindCustomViewHolder(VH holder, int position);

    /**
     * 添加HeaderView
     *
     * @param headerView 顶部View对象
     */
    public void addHeaderView(View headerView) {
        if (headerView == null) {
            Log.w(TAG, "add the header view is null");
            return ;
        }
        this.headerView = headerView;
        notifyDataSetChanged();
    }

    /**
     * 移除HeaderView
     */
    public void removeHeaderView() {
        if (headerView != null) {
            headerView = null;
            notifyDataSetChanged();
        }
    }

    /**
     * 添加FooterView
     *
     * @param footerView View对象
     */
    public void addFooterView(View footerView) {
        if (footerView == null) {
            Log.w(TAG, "add the footer view is null");
            return;
        }
        this.footerView = footerView;
        notifyDataSetChanged();
    }

    /**
     * 移除FooterView
     */
    public void removeFooterView() {
        if (footerView != null) {
            footerView = null;
            notifyDataSetChanged();
        }
    }

    /**
     * 获取附加View的数量,包括HeaderView和FooterView
     *
     * @return 数量
     */
    public int getExtraViewCount() {
        int extraViewCount = 0;
        if (headerView != null) {
            extraViewCount++;
        }
        if (footerView != null) {
            extraViewCount++;
        }
        return extraViewCount;
    }

    /**
     * 获取顶部附加View数量,即HeaderView数量
     * @return 数量
     */
    public int getHeaderExtraViewCount() {
        return headerView == null ? 0 : 1;
    }

    /**
     * 获取底部附加View数量,即FooterView数量
     * @return 数量,0或1
     */
    public int getFooterExtraViewCount() {
        return footerView == null ? 0 : 1;
    }

    @Override
    public abstract long getItemId(int position);
}
```

`BaseAdapter`的代码如下：

```java
/**
 * 基础的Adapter
 *
 * Created by DevWiki on 2016/7/13.
 */

public abstract class BaseAdapter<M, VH extends BaseHolder> extends AbsAdapter<M, VH> {

    private List<M> dataList;

    public BaseAdapter(Context context) {
        super(context);
        this.dataList = new ArrayList<>();
    }

    public BaseAdapter(Context context, List<M> list) {
        super(context);
        this.dataList = new ArrayList<>();
        this.dataList.addAll(list);
    }

    /**
     * 填充数据,此操作会清除原来的数据
     *
     * @param list 要填充的数据
     * @return true:填充成功并调用刷新数据
     */
    public boolean fillList(List<M> list) {
        dataList.clear();
        boolean result = dataList.addAll(list);
        if (result) {
            notifyDataSetChanged();
        }
        return result;
    }

    /**
     * 追加一条数据
     *
     * @param data 要追加的数据
     * @return true:追加成功并刷新界面
     */
    public boolean appendItem(M data) {
        boolean result = dataList.add(data);
        if (result) {
            if (getHeaderExtraViewCount() == 0) {
                notifyItemInserted(dataList.size() - 1);
            } else {
                notifyItemInserted(dataList.size());
            }
        }
        return result;
    }

    /**
     * 追加集合数据
     *
     * @param list 要追加的集合数据
     * @return 追加成功并刷新
     */
    public boolean appendList(List<M> list) {
        boolean result = dataList.addAll(list);
        if (result) {
            notifyDataSetChanged();
        }
        return result;
    }

    /**
     * 在最顶部前置数据
     *
     * @param data 要前置的数据
     */
    public void proposeItem(M data) {
        dataList.add(0, data);
        if (getHeaderExtraViewCount() == 0) {
            notifyItemInserted(0);
        } else {
            notifyItemInserted(getHeaderExtraViewCount());
        }
    }

    /**
     * 在顶部前置数据集合
     *
     * @param list 要前置的数据集合
     */
    public void proposeList(List<M> list) {
        dataList.addAll(0, list);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public final int getItemViewType(int position) {
        if (headerView != null && position == 0) {
            return VIEW_TYPE_HEADER;
        } else if (footerView != null && position == dataList.size() + getHeaderExtraViewCount()) {
            return VIEW_TYPE_FOOTER;
        } else {
            return getCustomViewType(position);
        }
    }

    /**
     * 获取自定义View的类型
     * 
     * @param position 位置
     * @return View的类型
     */
    public abstract int getCustomViewType(int position);

    @Override
    public int getItemCount() {
        return dataList.size() + getExtraViewCount();
    }

    /**
     * 根据位置获取一条数据
     * 
     * @param position View的位置
     * @return 数据
     */
    public M getItem(int position) {
        if (headerView != null && position == 0
                || position >= dataList.size() + getHeaderExtraViewCount()) {
            return null;
        }
        return headerView == null ? dataList.get(position) : dataList.get(position - 1);
    }

    /**
     * 根据ViewHolder获取数据
     *
     * @param holder ViewHolder
     * @return 数据
     */
    public M getItem(VH holder) {
        return getItem(holder.getAdapterPosition());
    }

    public void updateItem(M data) {
        int index = dataList.indexOf(data);
        if (index < 0) {
            return;
        }
        dataList.set(index, data);
        if (headerView == null) {
            notifyItemChanged(index);
        } else {
            notifyItemChanged(index + 1);
        }
    }

    /**
     * 移除一条数据
     *
     * @param position 位置
     */
    public void removeItem(int position) {
        if (headerView == null) {
            dataList.remove(position);
        } else {
            dataList.remove(position - 1);
        }
        notifyItemRemoved(position);
    }

    /**
     * 移除一条数据
     *
     * @param data 要移除的数据
     */
    public void removeItem(M data) {
        int index = dataList.indexOf(data);
        if (index < 0) {
            return;
        }
        dataList.remove(index);
        if (headerView == null) {
            notifyItemRemoved(index);
        } else {
            notifyItemRemoved(index + 1);
        }
    }
}
```

## 点击事件的优化

为了降低点击事件的耦合性，将点击事件从Adapter中移除，使用另外一种方式来实现。使用代码如下：

```java
recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(recyclerView,
        new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Person person = singleAdapter.getItem(position);
                Toast.makeText(SingleActivity.this, "click:" + person,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Person person = singleAdapter.getItem(position);
                Toast.makeText(SingleActivity.this, "Long Click:" + person,
                        Toast.LENGTH_SHORT).show();
            }
        }));
```

使用`RecyclerView`的自带的方法`addOnItemTouchListener()`实现点击和长点击事件。实现代码如下：

```java
/**
 * 点击事件
 * Created by DevWiki on 2016/7/16.
 */

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener clickListener;
    private GestureDetector gestureDetector;

    public interface OnItemClickListener {
        /**
         * 点击时回调
         *
         * @param view 点击的View
         * @param position 点击的位置
         */
        void onItemClick(View view, int position);

        /**
         * 长点击时回调
         *
         * @param view 点击的View
         * @param position 点击的位置
         */
        void onItemLongClick(View view, int position);
    }

    public RecyclerItemClickListener(final RecyclerView recyclerView,
                                     OnItemClickListener listener) {
        this.clickListener = listener;
        gestureDetector = new GestureDetector(recyclerView.getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (childView != null && clickListener != null) {
                            clickListener.onItemLongClick(childView,
                                    recyclerView.getChildAdapterPosition(childView));
                        }
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
```

## 分割线的优化

RecyclerView的分割线不像ListView那样方便，但是RecyclerView也提供了``方法来添加分割线。

分割线代码使用的是SDK中的Sample的代码，在其中添加了``方法，方便设置分割线的形式。

具体的代码参见此处：[DividerDecoration.java](https://github.com/Dev-Wiki/RecyclerView/blob/master/recycler/src/main/java/net/devwiki/recycler/DividerDecoration.java)

## 优化后的使用

优化后使用方式有所改变，具体使用方式如下：

```java
recyclerView = (RecyclerView) findViewById(R.id.single_rv);
layoutManager = new LinearLayoutManager(this);
recyclerView.setLayoutManager(layoutManager);
DividerDecoration decoration = new DividerDecoration(this, DividerDecoration.VERTICAL_LIST);
Drawable drawable = getResources().getDrawable(R.drawable.divider_single);
decoration.setDivider(drawable);
recyclerView.addItemDecoration(decoration);
recyclerView.setAdapter(singleAdapter);

View view = LayoutInflater.from(this).inflate(R.layout.item_single_header, null, false);
singleAdapter.addHeaderView(view);

recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(recyclerView,
        new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Person person = singleAdapter.getItem(position);
                Toast.makeText(SingleActivity.this, "click:" + person,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Person person = singleAdapter.getItem(position);
                Toast.makeText(SingleActivity.this, "Long Click:" + person,
                        Toast.LENGTH_SHORT).show();
            }
        }));
```

以上所有的代码均在GitHub上，具体地址在此：[Dev-Wiki/RecyclerView](https://github.com/Dev-Wiki/RecyclerView)

如果您发现有错误或者有好的想法，欢迎留言讨论或者联系我：[关于 - DevWiki](http://blog.devwiki.net/index.php/about.html)

您也可关注我的公共账号：

![微信公共号](http://7xjhi6.com1.z0.glb.clouddn.com/WeiXin-DevWiki-Common.jpg)
