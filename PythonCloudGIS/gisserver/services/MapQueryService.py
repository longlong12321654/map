#coding=utf-8
import os

from osgeo import gdal
from osgeo import ogr
from gisserver.common.ParseData import getPath, getSpaceType,getEncoding,setEncoding

# 为了支持中文路径，请添加下面这句代码
gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES")
# 为了使属性表字段支持中文，请添加下面这句
gdal.SetConfigOption('SHAPE_ENCODING', 'GBK')
# 注册所有的驱动
ogr.RegisterAll()
os.environ['PROJ_LIB'] = os.path.abspath('./gisserver/proj')

# shp文件解析
def mapQuery(request, path, layer):

    # 判断是否是GET请求
    global spatialRel
    if request.method == 'GET':
        datas = request.GET
        where = datas.get('where')  # 属性查询条件
        geometry = datas.get('geometry')  # 空间查询集合
        geometryType = datas.get('geometryType')  # 空间类型
        spatialRel = datas.get('spatialRel')  # 空间关系
        outFields = datas.get('outFields')  # 输出字段
        returnGeometry = datas.get('returnGeometry')  # 返回几何图形
        returnCountOnly = datas.get('returnCountOnly')  # 返回总数
        pageNum = datas.get('pageNum')  # 页面返回大小
        pageSize = datas.get('pageSize')  # 页面返回大小
        orderByFields = datas.get('orderByFields')  # 排序字段
        isPublishing = datas.get('isPublishing')  # 是否是发布服务

    print(geometry)
    print(spatialRel)
    mapLayer = {}
    # 设置编码格式
    encoding = getEncoding(path)
    setEncoding(encoding)
    # 打开数据
    ds = ogr.Open(path, 0)
    if ds == None:
        return None
    if pageSize == None:
        pageSize = 10
    if pageNum == None:
        pageNum = 1
    # 获取该数据源中的图层个数，一般shp数据图层只有一个，如果是mdb、dxf等图层就会有多个
    iLayerCount = ds.GetLayerCount()
    # 遍历图层
    for ilayer in range(iLayerCount):
        # 获取图层
        oLayer = ds.GetLayerByIndex(ilayer)
        # 对图层进行初始化，如果对图层进行了过滤操作，执行这句后，之前的过滤全部清空
        oLayer.ResetReading()
        ############ 获取属性字段 ###########
        oDefn = oLayer.GetLayerDefn()
        iFieldCount = oDefn.GetFieldCount()
        fields = []
        for iAttr in range(iFieldCount):
            oField = oDefn.GetFieldDefn(iAttr)
            name = oField.GetNameRef().encode('utf-8', 'replace').decode('utf-8')
            fields.append(
                {"name": name, "type": oField.GetFieldTypeName(oField.GetType()), "length": oField.GetWidth()})
        # 定义wkt
        wkt = None
        # 通过属性表的SQL语句对图层中的要素进行筛选，这部分详细参考SQL查询章节内容
        if (where!=None):
            oLayer.SetAttributeFilter(where)
            # oLayer.SetAttributeFilter("\"学校名称\" = '河南理工大学'")
        if (geometryType!=None and geometry!=None and len(geometry)>0):
            if geometryType == 'Polygon':
                wkt = 'POLYGON (('+ geometry +'))'
            elif geometryType == 'Polyline':
                wkt = 'LINESTRING ('+ geometry +')'
            elif geometryType == 'Point':
                wkt = 'POINT ('+ geometry +')'
            #多点
            elif geometryType=='MultiPoint':
                wkt='MULTIPOINT ('+ geometry +')'
            # 多线
            elif geometryType=='MultiLineString':
                wkt = 'MultiLineString (' + geometry + ')'
            # 多面
            elif geometryType=='MultiPolygon':
                wkt = 'MultiPolygon (' + geometry + ')'
            # 点线面几何体
            elif geometryType=='GeometryCollection':
                wkt = 'GeometryCollection (' + geometry + ')'
        # 通过指定的几何对象对图层中的要素进行筛选
        if wkt!=None:
            geom = ogr.CreateGeometryFromWkt(wkt)
            # 缓冲查询
            if geometryType == 'Point' or geometryType == 'Polyline':
                geom = geom.Buffer(0.00001)
            oLayer.SetSpatialFilter(geom)
            print(geom)
            # if spatialRel == 'Intersects':
            #     oLayer = filter(lambda f: f.GetGeometryRef().Intersects(geom), oLayer)
            # elif spatialRel == 'Disjoint':
            #     oLayer= filter(lambda f: f.GetGeometryRef().Disjoint(geom), oLayer)
            # elif spatialRel == 'Equals':
            #     oLayer = filter(lambda f: f.GetGeometryRef().Equals(geom), oLayer)
            # elif spatialRel == 'Touches':
            #     oLayer = filter(lambda f: f.GetGeometryRef().Touches(geom), oLayer)
            # elif spatialRel == 'Crosses':
            #     oLayer = filter(lambda f: f.GetGeometryRef().Crosses(geom), oLayer)
            # elif spatialRel == 'Within':
            #     oLayer = filter(lambda f: f.GetGeometryRef().Within(geom), oLayer)
            # elif spatialRel == 'Contains':
            #     oLayer = filter(lambda f: f.GetGeometryRef().Contains(geom), oLayer)
            # elif spatialRel == 'Overlaps':
            #     oLayer = filter(lambda f: f.GetGeometryRef().Overlaps(geom), oLayer)
            # elif spatialRel == 'Relates':
            #     oLayer = filter(lambda f: f.GetGeometryRef().Relates(geom), oLayer)
            # elif spatialRel == 'LocateAlong':
            #     oLayer = filter(lambda f: f.GetGeometryRef().LocateAlong(geom), oLayer)
            # elif spatialRel == 'LocateBetween':
            #     oLayer = filter(lambda f: f.GetGeometryRef().LocateBetween(geom), oLayer)
        # 空间关系：Equals，Disjoint，Intersects，Touches，Crosses，Within，Contains，Overlaps，Relate，LocateAlong，LocateBetween
        # queryResults = filter(lambda f: f.GetGeometryRef().Intersects(geom), oLayer)
        # for queryResult in queryResults:
        #     print(queryResult.GetGeometryRef())
        #     print(queryResult.GetField('主管单位'))
        #     print(queryResult.GetFieldCount())
        # 通过指定的四至范围对图层中的要素进行筛选 <minx>, <miny>, <maxx>, <maxy>
        # oLayer.SetSpatialFilterRect(112.7658670077010612,34.8714170864240529,113.4945776583326307,35.3781581243085128)
        # oLayer.SetSpatialFilterRect(112.7658670077010612, 34.8714170864240529, 112.79229973297124445,34.90682629215172739)
        # count = oLayer.GetFeatureCount()
        # 获取图层中的属性表表头并输出
        # print("属性表结构信息：")
        # layer_feature=[]
        # total = len(list(oLayer))
        # pageNum = int(pageNum)
        # pageSize = int(pageSize)
        #
        # xz_res=list(oLayer)[(pageNum-1) * pageSize:] if (pageNum-1) * pageSize<total else list(oLayer)
        # count = 0
        #
        # if total>0:
        #     for oFeature in xz_res:
        #         attributes = {}
        #         # 获取要素中的属性表内容
        #         for iField in range(iFieldCount):
        #             oFieldDefn = oDefn.GetFieldDefn(iField)
        #             # print(str(oFieldDefn.GetType())+":::"+oFieldDefn.GetFieldTypeName(oFieldDefn.GetType()))
        #             nameRef = str(oFieldDefn.GetNameRef())
        #             type = oFieldDefn.GetType()
        #             if type == 0:
        #                 attributes[nameRef] = str(oFeature.GetField(nameRef))
        #             elif type == 1:
        #                 attributes[nameRef] = str(oFeature.GetFieldAsIntegerList(nameRef))
        #             elif type == 2:
        #                 attributes[nameRef] = str(oFeature.GetField(nameRef))
        #             elif type == 3:
        #                 attributes[nameRef] = str(oFeature.GetFieldAsDoubleList(nameRef))
        #             elif type == 4:
        #                 attributes[nameRef] = str(oFeature.GetFieldAsString(nameRef))
        #             elif type == 5:
        #                 attributes[nameRef] = str(oFeature.GetFieldAsStringList(nameRef))
        #             elif type == 12:
        #                 attributes[nameRef] = str(oFeature.GetFieldAsInteger64(nameRef))
        #             elif type == 13:
        #                 attributes[nameRef] = str(oFeature.GetFieldAsInteger64List(nameRef))
        #             else:
        #                 attributes[nameRef] = str(oFeature.GetFieldAsString(nameRef))
        #
        #         # 定义feature
        #         feature = {"attributes": attributes}
        #         # 转换True/false
        #         if (returnGeometry != None):
        #             # 获取要素中的几何体
        #             geometry = oFeature.GetGeometryRef()
        #             feature["geometry"] = geometry.ExportToJson()
        #         # 加入到features 中
        #         layer_feature.append(feature)
        #         # 最大值判断
        #         count = count + 1
        #         if count >= pageSize:
        #             break


        # 输出图层中的要素个数
        total = oLayer.GetFeatureCount()
        pageNum = int(pageNum)
        pageSize = int(pageSize)
        # 设置开始索引
        oLayer.SetNextByIndex((pageNum-1) * pageSize)
        oFeature = oLayer.GetNextFeature()
        count = 0
        features = []
        # 下面开始遍历图层中的要素
        while oFeature is not None:
            attributes = {}
            # 获取要素中的属性表内容
            for iField in range(iFieldCount):
                oFieldDefn = oDefn.GetFieldDefn(iField)
                # print(str(oFieldDefn.GetType())+":::"+oFieldDefn.GetFieldTypeName(oFieldDefn.GetType()))
                nameRef = str(oFieldDefn.GetNameRef())
                type = oFieldDefn.GetType()
                if type == 0:
                    attributes[nameRef] = str(oFeature.GetField(nameRef))
                elif type == 1:
                    attributes[nameRef] = str(oFeature.GetFieldAsIntegerList(nameRef))
                elif type == 2:
                    attributes[nameRef] = str(oFeature.GetField(nameRef))
                elif type == 3:
                    attributes[nameRef] = str(oFeature.GetFieldAsDoubleList(nameRef))
                elif type == 4:
                    attributes[nameRef] = str(oFeature.GetFieldAsString(nameRef))
                elif type == 5:
                    attributes[nameRef] = str(oFeature.GetFieldAsStringList(nameRef))
                elif type == 12:
                    attributes[nameRef] = str(oFeature.GetFieldAsInteger64(nameRef))
                elif type == 13:
                    attributes[nameRef] = str(oFeature.GetFieldAsInteger64List(nameRef))
                else:
                    attributes[nameRef] = str(oFeature.GetFieldAsString(nameRef))

            # 定义feature
            feature = {"attributes": attributes}
            # 转换True/false
            isTrue = returnGeometry == str(True)
            if (returnGeometry != None and isTrue):
                # 获取要素中的几何体
                geometry = oFeature.GetGeometryRef()
                print(geometry)
                feature["geometry"] = geometry.ExportToJson()
            # 加入到features 中
            features.append(feature)
            # 最大值判断
            count = count + 1
            if count >= pageSize:
                break
            # 读取下一个要素
            oFeature = oLayer.GetNextFeature()
        # 封装图层对象
        mapLayer = {"fields": fields, "features": features, "geometryType": layer["spaceType"], "total": total, "pageNum": pageNum, "pageSize": pageSize}
    # 返回图层对象
    return mapLayer