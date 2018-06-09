package com.example.thomas.lovetravel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnPolylineClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine.TransitStep;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.example.thomas.lovetravel.overlayutil.PoiOverlay;
import com.example.thomas.lovetravel.overlayutil.TransitRouteOverlay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MapActivity extends ActionBarActivity implements BaiduMap.OnPolylineClickListener {
    private TextureMapView mMapView =null;
    private BaiduMap bdMap;
    private EditText tvSearch;

    private LocationClient locationClient;
    private BDLocationListener locationListener;
    private BDNotifyListener notifyListener;
    private double mCurrentLongitude ;// 精度
    private double mCurrentLantitude ;// 维度
    private float radius;// 定位精度半径，单位是米
    private String addrStr;// 反地理编码
    private String province;// 省份信息
    private String city;// 城市信息
    private String district;// 区县信息
    private float direction;// 手机方向信息

    private int locType;

    PoiSearch poiSearch ;//检索类

    /***
     * 是否是第一次定位
     */
    private volatile boolean isFristLocation = true;
    //	private LocationMode mCurrentMode = LocationMode .NORMAL;
    private MyLocationConfiguration.LocationMode currentMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.map);

        //获取地图控件引用
        mMapView = (TextureMapView) findViewById(R.id.bdmapView);
        bdMap =mMapView.getMap();

        //		currentMode = MyLocationConfiguration.LocationMode.COMPASS;
        currentMode = MyLocationConfiguration.LocationMode.NORMAL;

        init();
    }

    LocationClientOption locOption;
    /**
     * 定义定位初识化的方法
     */
    private void init() {
        bdMap.setMyLocationEnabled(true);
        // 1. 初始化LocationClient类
        locationClient = new LocationClient(getApplicationContext());
        // 2. 声明LocationListener类
        locationListener = new MyLocationListener();
        // 3. 注册监听函数
        locationClient.registerLocationListener(locationListener);
        // 4. 设置参数
        locOption = new LocationClientOption();
        locOption.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
        locOption.setCoorType("bd09ll");// 设置定位结果类型
        locOption.setScanSpan(1000);// 设置发起定位请求的间隔时间,ms
        locOption.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        locOption.setNeedDeviceDirect(true);// 设置返回结果包含手机的方向
        locationClient.setLocOption(locOption);
        // 6. 开启/关闭 定位SDK
        locationClient.start();

        //获取poi检索实例
        poiSearch = PoiSearch.newInstance();
        //绑定点击事件侦听
        findViewById(R.id.im_search).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "点击了一次搜索", Toast.LENGTH_SHORT).show();
                citySearch(1);

            }
        });
        tvSearch =(EditText)findViewById(R.id.tv_search);

        bdMap.setOnPolylineClickListener(this);
//		bdMap.seton
    }

    LatLng locLatLng;
    /**实现了定位结果回调的事件监听器类*/
    class MyLocationListener implements BDLocationListener {
        // 异步返回的定位结果
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null)
                return;

            locType = location.getLocType();//获取定位的locType
            //			Toast.makeText(MainActivity.this, "当前定位的返回值是："+locType, Toast.LENGTH_SHORT).show();
            mCurrentLongitude = location.getLongitude();//获取经纬度
            mCurrentLantitude = location.getLatitude();
            if (location.hasRadius()) {// 判断是否有定位精度半径
                radius = location.getRadius();
            }
            if (locType == BDLocation.TypeGpsLocation) {//gps定位
                Toast.makeText(
                        MapActivity.this,
                        "当前速度是：" + location.getSpeed() + "~~定位使用卫星数量："
                                + location.getSatelliteNumber(),
                        Toast.LENGTH_SHORT).show();
            } else if (locType == BDLocation.TypeNetWorkLocation) {//使用网络定位
                addrStr = location.getAddrStr();// 获取反地理编码(文字描述的地址)
                //				Toast.makeText(MainActivity.this, addrStr,
                //						Toast.LENGTH_SHORT).show();
            }//获取定位返回的其他信息
            direction = location.getDirection();// 获取手机方向，【0~360°】,手机上面正面朝北为0°
            province = location.getProvince();// 省份
            city = location.getCity();// 城市
            district = location.getDistrict();// 区县
            //输出信息
            //			Toast.makeText(MainActivity.this,
            //					province + "~" + city + "~" + district, Toast.LENGTH_SHORT)
            //					.show();
            // 构造定位数据  显示定位的地方，这些以全局变量，在回调结果的返回过程中被赋值
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(radius)//
                    .direction(direction)// 方向
                    .latitude(mCurrentLantitude)//
                    .longitude(mCurrentLongitude)//
                    .build();
            // 设置定位数据
            bdMap.setMyLocationData(locData);
            // 第一次定位时，将地图位置移动到当前位置
            if (isFristLocation)
            {
                isFristLocation = false;
                //更新地图状态，依据当前定位的地点
                locLatLng = new LatLng(mCurrentLantitude, mCurrentLongitude);
                //更新状态的详细设定，可以设定级别
                Log.v("tag","定位信息");
                MapStatus.Builder mSBuilder = new MapStatus.Builder();
                MapStatus mapStatus =mSBuilder.zoom(17.0f).target(locLatLng).build();
                MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(mapStatus);
                bdMap.animateMapStatus(msu);
            }
            //			locationClient.unRegisterLocationListener(locationListener);
        }

        //

    }

    /**
     * 地图移动到我的位置,此处可以重新发定位请求，然后定位；
     * 直接拿最近一次经纬度，如果长时间没有定位成功，可能会显示效果不好
     */
    private void center2myLoc()
    {
        LatLng ll = new LatLng(mCurrentLantitude, mCurrentLongitude);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        bdMap.animateMapStatus(u);
    }

    //按钮点击事件，移动到中心位置
    public void onclick_move(View view){
        Toast.makeText(MapActivity.this, "移动到所在地", Toast.LENGTH_SHORT).show();
        center2myLoc();
    }


    /**
     *搜索结果处理的监听器类
     *
     * */
    public String poiName;//到这里去poi地点的名字
    OnGetPoiSearchResultListener searchListener = new OnGetPoiSearchResultListener() {

        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            Log.v("tag", "执行了结果搜索,poi搜索侦听处理");
            // TODO 结果处理
            if(poiResult==null
                    || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND){//未找到结果
                Toast.makeText(MapActivity.this, "未找到结果", Toast.LENGTH_SHORT).show();
                return;//方法终止
            }
            if(poiResult.error == SearchResult.ERRORNO.NO_ERROR){//检索返回正常结果
                bdMap.clear();//清除地图中其他的东西初始化
                MyPoiOverlay poiOverlay= new  MyPoiOverlay(bdMap);
                //				bdMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng, arg1)))
                poiOverlay.setData(poiResult);
                bdMap.setOnMarkerClickListener(poiOverlay);//传递给poi内部的click方法处理
                poiOverlay.addToMap();
                poiOverlay.zoomToSpan();
                StringBuilder sb = new StringBuilder("查询结果：\n");
                List<PoiInfo> list =poiResult.getAllPoi();
                for(int i =0;i<list.size();i++)
                    sb.append("第"+(i+1)+"个poi： "+list.get(i).name.toString()+"\n");
                //获取查询的相关信息
                int totalPage = poiResult.getTotalPageNum();
                /**
                 Toast.makeText(AtyMap.this, "总共查到"+poiResult.getTotalPoiNum()+"个兴趣点，分为"+totalPage+"页"
                 +"当前页"+poiResult.getCurrentPageNum()+"\n内容："+sb.toString(), Toast.LENGTH_LONG).show();
                 */
            }

        }

        LatLng poiInfoPosition;

        //详情结果处理，把当前搜索的poi的地点的详情类传递过来进行处理
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            if (poiDetailResult.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(MapActivity.this, "抱歉，未找到结果",
                        Toast.LENGTH_SHORT).show();
            } else {// 正常返回结果的时候，此处可以获得很多相关信息
                //获取地点的名称，地址信息，关键看详情类有哪些方法和属性了
                Toast.makeText(
                        MapActivity.this,
                        poiDetailResult.getName() + ": "
                                + poiDetailResult.getAddress(),
                        Toast.LENGTH_LONG).show();

                //弹出InfoWindow
                //创建InfoWindow展示的view
                Button button = new Button(getApplicationContext());
                button.setText("到这里去");
                button.setTextColor(Color.BLACK);
                button.setBackgroundResource(R.drawable.popup);
                //定义用于显示该InfoWindow的坐标点
                poiInfoPosition = poiDetailResult.getLocation();
                //获取点击的地点名
                poiName = poiDetailResult.getName();
                //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                //顺便构建点击事件
                BitmapDescriptor bitmap= BitmapDescriptorFactory.fromView(button);
                InfoWindow mInfoWindow = new InfoWindow(bitmap, poiInfoPosition, -47,new InfoWindow.OnInfoWindowClickListener() {

                    @Override
                    public void onInfoWindowClick() {
                        //infowindow的点击事件，进行线路查询
                        //设置起始点
                        PlanNode stNode = PlanNode.withLocation(locLatLng);
//						PlanNode enNode = PlanNode.withCityNameAndPlaceName("郑州市", tvSearch.getText().toString());
                        //出发地点、结束地点
//						PlanNode stNode = PlanNode.withCityNameAndPlaceName("郑州市","郑大工学院");
                        PlanNode enNode = PlanNode.withLocation(poiInfoPosition);
                        RoutePlanSearch search = RoutePlanSearch.newInstance();
                        search.setOnGetRoutePlanResultListener(routePlanlistener);
                        //创建检索监听器
                        bdMap.clear();
                        search.transitSearch((new TransitRoutePlanOption())
                                .from(stNode).city(city).to(enNode));
//								.from(stNode).city("郑州市").to(enNode));
                        //只能在当前城市进行线路检索
                    }
                });
                //缩放显示地图，更新地图状态
                //更新地图状态，依据当前定位的地点
                //更新状态的详细设定，可以设定级别
                MapStatus.Builder mSBuilder = new MapStatus.Builder();
                MapStatus mapStatus =mSBuilder.zoom(19.0f).target(poiInfoPosition).build();
                MapStatusUpdate msu = MapStatusUpdateFactory.newMapStatus(mapStatus);
                bdMap.animateMapStatus(msu);
                //显示InfoWindow
                bdMap.showInfoWindow(mInfoWindow);


            }
        }
    };

    /**
     * poi级别的地图覆盖物
     * */
    class MyPoiOverlay extends PoiOverlay {
        //		构造方法
        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int arg0) {
            super.onPoiClick(arg0);
            PoiInfo poiInfo = getPoiResult().getAllPoi().get(arg0);
            // 检索poi详细信息
            //需要一个查询接口类的引用
            poiSearch.searchPoiDetail(new PoiDetailSearchOption()//传入匿名的详情参数类
                    .poiUid(poiInfo.uid));
            return true;
        }
    }



    /**
     *发动城市内检索
     */
    private void citySearch(int page) {
        // 设置检索参数
        PoiCitySearchOption citySearchOption = new PoiCitySearchOption();
        //		citySearchOption.city(ed_searchBar.getText().toString());// 城市
        //		citySearchOption.keyword(editSearchKeyEt.getText().toString());// 关键字
        citySearchOption.city(city);// 城市
//		citySearchOption.city("郑州市");// 城市
        Log.v("tag", "城市信息:"+city);
        citySearchOption.keyword(tvSearch.getText().toString());// 关键字
        Log.v("tag", "搜索关键字:"+tvSearch.getText().toString());
        //		citySearchOption.pageCapacity(15);// 默认每页10条
        citySearchOption.pageNum(page);// 分页编号
        poiSearch.setOnGetPoiSearchResultListener(searchListener);
        // 发起检索请求
        poiSearch.searchInCity(citySearchOption);
        Log.v("tag", "城市检索发起");
    }

    /**关于线路规划相关的结果
     *
     * */

    ArrayList<String> steptMsg;
    //设置线路规划结果事件侦听器
    OnGetRoutePlanResultListener routePlanlistener = new OnGetRoutePlanResultListener() {
        public void onGetWalkingRouteResult(WalkingRouteResult result) {
            //
        }
        public void onGetTransitRouteResult(TransitRouteResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(MapActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
            }
            if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                result.getSuggestAddrInfo();
                return;
            }
            //搜索有结果
            if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                steptMsg  =new ArrayList<String>();
                List<TransitStep> stepts =result.getRouteLines().get(0).getAllStep();
                int count =1;
                String previous_instruction = null;
                for(TransitStep step :stepts){
                    String str;
                    String instructions =step.getInstructions();
                    int distance =step.getDistance();
                    String entranceTitle =step.getEntrance().getTitle();
                    String exitTitle =step.getExit().getTitle();
                    String steptType = step.getStepType().name();

                    if (count==1){//第一次
                        previous_instruction = instructions;
                        str =String.format(count+"、从本地出发，%s",instructions);
                    }else{
                        if (entranceTitle!=null){//入口非空的时候
                            previous_instruction = instructions;
                            str =String.format(count+"、从%s出发，%s ", entranceTitle,instructions);
                        }else {
                            //入口为空则截取上一次的建议
                            String[] msg = previous_instruction.split(",");
                            //取最后一个分号的结束内容，也就是到达的站点
                            String startLoc = msg[msg.length - 1];
                            str = String.format(count + "、从%s出发，%s ", startLoc, instructions);
                            previous_instruction = instructions;
                            Log.v("输出出发站点信息",startLoc);
                        }
                    }

//					String str =String.format("输出信息：路段类型：%s \n instruction:%s entranceTitle:%s exitTitle:%s distance:%s", steptType,instructions,entranceTitle,exitTitle,distance+"");
                    Log.v("tag", str);;
                    steptMsg.add(str);
                    System.out.println(str);
                    count++;
                }
                TransitRouteOverlay overlay = new MyTransitRouteOverlay(bdMap);
                bdMap.setOnMarkerClickListener(overlay);
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();
                //					overlay.getTerminalMarker();
            }
        }
        public void onGetDrivingRouteResult(DrivingRouteResult result) {
            //
        }
        @Override
        public void onGetBikingRouteResult(BikingRouteResult arg0) {
            // TODO Auto-generated method stub

        }
    };


    /**
     * 线路覆盖物
     *
     * */
    class MyTransitRouteOverlay extends TransitRouteOverlay {

        public MyTransitRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
            // TODO Auto-generated constructor stub

        }

        //从写线路点击事件
/**		@Override
public boolean onPolylineClick(Polyline polyline) {
// TODO Auto-generated method stub
List<LatLng> linePoints = polyline.getPoints();
int size =	polyline.getPoints().size();
LatLng endPoint = linePoints.get(size-1);
//				super.onPolylineClick(polyline);
Toast.makeText(MainActivity.this, "点击线路事件", Toast.LENGTH_LONG).show();
//创建InfoWindow展示的view
Button button = new Button(getApplicationContext());
button.setText("查看线路详情");
button.setTextColor(Color.BLACK);
button.setBackgroundResource(R.drawable.popup);
//定义用于显示该InfoWindow的坐标点
//				 poiInfoPosition = poiDetailResult.getLocation();
//创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
//顺便构建点击事件
BitmapDescriptor bitmap= BitmapDescriptorFactory.fromView(button);
InfoWindow mInfoWindow = new InfoWindow(bitmap, endPoint, -47,new InfoWindow.OnInfoWindowClickListener() {

@Override
public void onInfoWindowClick() {
// TODO Auto-generated method stub
Toast.makeText(MainActivity.this, "点击线路详情信息", Toast.LENGTH_LONG).show();
}
});

return true;
}*/
    }



    @Override
    protected void onStart()
    {	super.onStart();
        bdMap.setMyLocationEnabled(true);
        if (!locationClient.isStarted())
        {
            locationClient.start();
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        bdMap.setMyLocationEnabled(false);
        locationClient.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMapView.onDestroy();
        locationClient.unRegisterLocationListener(locationListener);
        locationClient.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();

    }

    /**
     * 线路点击事件处理，不是在覆盖物transLineoverLay进行设置事件处理，而是在bdmap上设置
     *
     * */
    @Override
    public boolean onPolylineClick(Polyline polyline) {
        // TODO Auto-generated method stub

        List<LatLng> linePoints = polyline.getPoints();
        int size =	polyline.getPoints().size();
        LatLng endPoint = linePoints.get(size-1);
        //super.onPolylineClick(polyline);
//		Toast.makeText(AtyMap.this, "点击线路事件", Toast.LENGTH_LONG).show();
        ListView list = (ListView)getLayoutInflater().inflate(R.layout.stepts_list,null );
        list.setBackgroundColor(Color.DKGRAY);
//		LinearLayout root =(LinearLayout)getLayoutInflater().inflate(R.layout.stepts_msg,null );
//		TextView tv = (TextView)root.findViewById(R.id.tv_stept);
        ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, steptMsg);
//				new ArrayAdapter<String>(getApplicationContext(),t, steptMsg);
        list.setAdapter(adapter);//绑定adapter
        new AlertDialog.Builder(this).setTitle("到"+poiName+"线路详情").setView(list).setPositiveButton("保存线路", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MapActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MapActivity.this,"点击取消",Toast.LENGTH_SHORT).show();
            }
        }).show();

        return false;
    }
}
