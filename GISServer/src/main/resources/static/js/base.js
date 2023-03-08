//初始化
function init(map, layers, extent, type) {
     
    //创建三维地图对象
    const viewer = new Cesium.Viewer("cesiumContainer", {
        animation: false, //动画
        timeline: false, //时间轴
        homeButton: false, //home键
        geocoder: false, //地址编码
        baseLayerPicker: false, //图层选择控件
        fullscreenButton: false, //全屏显示
        shouldAnimate: false,
        projectionPicker: false,
        selectionIndicator: false,
        requestRenderMode: false, //营销BIM模型加载
        maximumRenderTimeChange: Infinity,
        infoBox: false, //点击要素之后浮窗
        sceneModePicker: false, //投影方式  三维/二维
        navigationHelpButton: false, //帮助信息
        debugShowFramesPerSecond: false,
        //天地图影像服务（经纬度）
        imageryProvider:new Cesium.WebMapTileServiceImageryProvider({
              url: "http://{s}.tianditu.gov.cn/img_c/wmts?service=wmts&request=GetTile&version=1.0.0" +
              "&LAYER=img&tileMatrixSet=c&TileMatrix={TileMatrix}&TileRow={TileRow}&TileCol={TileCol}" +
              "&style=default&format=tiles&tk=8025b1799722d57a29ce207a6607deb4",
             layer: "tdtImg_c",
             style: "default",
             format: "tiles",
             tileMatrixSetID: "c",
             subdomains:["t0","t1","t2","t3","t4","t5","t6","t7"],
             tilingScheme:new Cesium.GeographicTilingScheme(),
            tileMatrixLabels:["1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19"],
             maximumLevel:18,
             show: true
         })
    });

    // 去除版权信息
    viewer._cesiumWidget._creditContainer.style.display = "none";
    //去除底图
    // viewer.imageryLayers.removeAll();

    //判断是否是三维数据
    if (type == "3DServer") {
        //创建三维对象
        let threetile = viewer.scene.primitives.add(
            new Cesium.Cesium3DTileset({
                url: "/SHMap/cim/rest/services/" + map + "/3DServer",
                maximumScreenSpaceError: 1
            }),
        )
        //开启深度检测
        viewer.scene.globe.depthTestAgainstTerrain = true
        //缩放到指定区域
        viewer.zoomTo(threetile);
        //设置模型高度
        threetile.readyPromise.then(function (tileset) {
            changeHeight(tileset, 500)
        })
    } else if(type == "IotServer"){
        const dataSource = Cesium.GeoJsonDataSource.load("/SHMap/cim/rest/services/" + map + "/IotServer",{markerSymbol:"park"});
        //加载geojson图层到场景内部
        viewer.dataSources.add(dataSource);
        //定位场景摄像头位置
        viewer.zoomTo(dataSource);
    }else {
        //添加图层
        tmpLayer = viewer.imageryLayers.addImageryProvider(
            new Cesium.WebMapServiceImageryProvider({
                url: "/SHMap/cim/rest/services/" + map + "/MapServer?layers=" + layers + "&SERVICE=WMS&VERSION=1.3.0",
                parameters: {
                    service: 'WMS',
                    format: 'image/png',
                    transparent: true,
                }
            })
        )
        //拆分范围
        extent = extent.split(",");
        //视角位置
        viewer.camera.setView({
            destination: Cesium.Rectangle.fromDegrees(
                Number(extent[0]), Number(extent[1]), Number(extent[2]), Number(extent[3])
            ),
        });
    }
}

//更改三维地图高度
function changeHeight(tileset, height) {
    height = Number(height);
    if (isNaN(height)) {
        return;
    }
    let cartographic = Cesium.Cartographic.fromCartesian(
        tileset.boundingSphere.center
    );
    let surface = Cesium.Cartesian3.fromRadians(
        cartographic.longitude,
        cartographic.latitude,
        cartographic.height
    );
    var offset = Cesium.Cartesian3.fromRadians(
        cartographic.longitude,
        cartographic.latitude,
        height
    );
    var translation = Cesium.Cartesian3.subtract(
        offset,
        surface,
        new Cesium.Cartesian3()
    );
    tileset.modelMatrix = Cesium.Matrix4.fromTranslation(translation);
}

//获取请求参数
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURI(r[2]);
    }
    return null;
}

//获取地图
let map = getQueryString("map");
let type = getQueryString("type");

//判断是否是三维服务
if (type == "3DServer" || type=="IotServer") {
    //初始化地图
    init(map, null, null, type);
} else {
    //获取图层
    let layers = getQueryString("layers");
    //获取范围
    let extent = getQueryString("extent");
    
    if (map == null) {
        alert("地图名称为空!");
    } else if (layers == null) {
        alert("图层名称为空!");
    } else if (extent == null) {
        alert("地图范围为空!");
    } else {
        //初始化地图
        init(map, layers, extent);
    }
}
