package com.example.thomas.myfriend;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ExpandableListAdapter adapter=new BaseExpandableListAdapter() {
            int []images1=new int[]{
              R.drawable.image1,
              R.drawable.image2
            };
            int[][]images2=new int[][]{
                    { R.drawable.image6, R.drawable.image7, R.drawable.image8, R.drawable.image9},
                    { R.drawable.image10, R.drawable.image11, R.drawable.image12}
            };
            private String[] group=new String[]{
                    "我的亲人","我的同学"
            };
            private String[][] person=new String[][]{
                    {"哥哥1 one", "哥哥2 two", "哥哥3 three", "哥哥4 four"
            },
                    {"小胖 胖","小美 美","小丽 丽" }
            };
            private  String[][] dec=new String[][]{
                    {"哥哥1有钱","哥哥2有势","哥哥3有权","哥哥4一无所有"},
                    {"小胖喜欢玩","小美喜欢美","小丽喜欢逛街"}
            };
            @Override
            public int getGroupCount() {
                return group.length;
            }
            @Override
            public int getChildrenCount(int groupPosition) {
                return person[groupPosition].length;
            }
            @Override
            public Object getGroup(int groupPosition) {
                return group[groupPosition];
            }
            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return person[groupPosition][childPosition];
            }
            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }
            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup friend) {
                LinearLayout f=new LinearLayout(MainActivity.this);
                f.setOrientation(LinearLayout.HORIZONTAL);
                ImageView ima=new ImageView(MainActivity.this);
                ima.setImageResource(images1[groupPosition]);
                f.addView(ima);
                TextView textView=new TextView(MainActivity.this);
                textView.setText(getGroup(groupPosition).toString());
                f.addView(textView);
                return f;
            }
            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convewview, ViewGroup friend) {
                LinearLayout g=(LinearLayout)getLayoutInflater().inflate(R.layout.simple,null);
                ImageView header=(ImageView)g.findViewById(R.id.header);
                header.setImageResource(images2[groupPosition][childPosition]);
                TextView name=(TextView)g.findViewById(R.id.name);
                TextView des=(TextView)g.findViewById(R.id.des);
                name.setText(person[groupPosition][childPosition]);
                des.setText(dec[groupPosition][childPosition]);
                return g;
            }
            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {

                return true;
            }
            @Override
            public boolean hasStableIds() {

                return true;
            }
        };
        ExpandableListView expandableListView=(ExpandableListView) findViewById(R.id.list);
        expandableListView.setAdapter(adapter);


    }
}