# RecyclerView支持类

该支持库包含以下内容:

- [BaseHolder](https://github.com/Dev-Wiki/RecyclerView/blob/master/recycler/src/main/java/net/devwiki/recycler/BaseHolder.java) : ViewHolder的支持类
- [BaseAdapter](https://github.com/Dev-Wiki/RecyclerView/blob/master/recycler/src/main/java/net/devwiki/recycler/BaseAdapter.java) : Adapter的支持类
- [ItemClickSupport](https://github.com/Dev-Wiki/RecyclerView/blob/master/recycler/src/main/java/net/devwiki/recycler/ItemClickSupport.java) : 点击事件支持类

具体使用方法如下:

```
public class MyAdapter extends BaseAdapter<Person, MyHolder> {


    static class MyHolder extends BaseHolder {
    
        MyHolder(ViewGroup parent, @LayoutRes int resId) {
        super(parent, resId);
    }
}
```

1. 简单的列表
2. 多View类型列表
3. 有关联的多View类型
