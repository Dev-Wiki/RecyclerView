# RecyclerView支持类

该支持库包含以下内容:

- [BaseHolder](https://github.com/Dev-Wiki/RecyclerView/blob/master/recycler/src/main/java/net/devwiki/recycler/BaseHolder.java) : ViewHolder的支持类
- [BaseAdapter](https://github.com/Dev-Wiki/RecyclerView/blob/master/recycler/src/main/java/net/devwiki/recycler/BaseAdapter.java) : Adapter的支持类
- [ItemClickSupport](https://github.com/Dev-Wiki/RecyclerView/blob/master/recycler/src/main/java/net/devwiki/recycler/ItemClickSupport.java) : 点击事件支持类

该库可以方便使用RecyclerView实现列表,并且实现添加HeaderView和FooterView.

具体使用方法如下:

```
public class MyAdapter extends BaseAdapter<Person, MyHolder> {

    public MyAdapter(Context context) {
        super(context);
    }

    static class MyHolder extends BaseHolder {

        // views

        MyHolder(ViewGroup parent, @LayoutRes int resId) {
            super(parent, resId);
            //get views
        }
    }
}
```

更多使用示例参见:

1. [简单的列表](https://github.com/Dev-Wiki/RecyclerView/tree/master/app/src/main/java/net/devwiki/recyclerview/single)
2. [多View类型列表](https://github.com/Dev-Wiki/RecyclerView/tree/master/app/src/main/java/net/devwiki/recyclerview/multi)
3. [有关联的多View类型](https://github.com/Dev-Wiki/RecyclerView/tree/master/app/src/main/java/net/devwiki/recyclerview/chat)
