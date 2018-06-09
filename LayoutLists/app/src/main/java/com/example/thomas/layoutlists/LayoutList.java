package com.example.thomas.layoutlists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class LayoutList {
    public static class Layout{
        Integer id;
        String name;
        String content;
        public Layout(int id,String name,String content){
            this.id=id;
            this.name=name;
            this.content=content;
        }
        @Override
        public String toString(){
            return name;
        }
    }
    public static List<Layout> items=new ArrayList<Layout>();
    public static Map<Integer,Layout> item=new HashMap<Integer, Layout>();
    static{
        addItem(new Layout(1,"线性布局","线性布局是将放入其中的组件按照垂直或水平方向来布局，也就是控制放入其中的组件横向排列或纵向排列。"+
                "在线性布局中，每一行（针对垂直排列）或每一列（针对水平排列）中只能放一个组件。"+
                "并且Android的线性布局不会换行，当组件一个挨着一个排列到窗体的边缘后，剩下的组件将不会被显示出来。"));
        addItem(new Layout(2,"表格布局","表格布局与常见的表格类似，它以行、列的形式来管理放入其中的UI组件。"+"表格布局使用<TableLayout>标记定义，在表格布局中，可以添加多个<TableRow>标记，"+
                "每个<TableRow>标记占用一行，由于<TableRow>标记也是容器，所以在该标记中还可添加其他组件，"+
                "在<TableRow>标记中，每添加一个组件，表格就会增加一列。在表格布局中，列可以被隐藏，"+
                "也可以被设置为伸展的，从而填充可利用的屏幕空间，也可以设置为强制收缩，直到表格匹配屏幕大小。"));
        addItem(new Layout(3,"帧布局","在帧布局管理器中，每加入一个组件，都将创建一个空白的区域，通常称为一帧，"+
                "这些帧都会根据gravity属性执行自动对齐。默认情况下，帧布局是从屏幕的左上角（0,0）坐标点开始布局，"+"多个组件层叠排序，后面的组件覆盖前面的组件。"));
        addItem(new Layout(4,"相对布局","相对布局是指按照组件之间的相对位置来进行布局，如某个组件在另一个组件的左边、右边、上面或下面等。"));
    }
    public static void addItem(Layout layout){
        items.add(layout);
        item.put(layout.id,layout);
    }
}
