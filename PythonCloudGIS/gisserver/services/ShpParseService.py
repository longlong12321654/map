#coding=utf-8
import json
import os
from osgeo import gdal
from osgeo import ogr
from gisserver.common.ParseData import getPath, getSpaceType,getEncoding,setEncoding

# 为了支持中文路径，请添加下面这句代码
gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES")
# 为了使属性表字段支持中文，请添加下面这句
gdal.SetConfigOption('SHAPE_ENCODING', '')
# 注册所有的驱动
ogr.RegisterAll()

# shp文件解析
def parseshp(path):
    mapLayers = []
    # 设置编码格式
    encoding = getEncoding(path)
    setEncoding(encoding)
    # 获取文件大小
    file_size = os.path.getsize(path)
    # 打开数据
    ds = ogr.Open(path, 0)
    if ds == None:
        return None
    # 获取该数据源中的图层个数，一般shp数据图层只有一个，如果是mdb、dxf等图层就会有多个
    iLayerCount = ds.GetLayerCount()
    # 遍历图层
    for ilayer in range(iLayerCount):
        # 获取图层
        oLayer = ds.GetLayerByIndex(ilayer)
        # 获取地图范围
        layerExtent = oLayer.GetExtent()
        # 获取空间参考
        spatialReference = ""
        if(oLayer.GetSpatialRef()!=None and oLayer.GetSpatialRef().GetAttrValue("AUTHORITY", 0)!=None):
            spatialReference = oLayer.GetSpatialRef().GetAttrValue("AUTHORITY",
                                                                   0) + " " + oLayer.GetSpatialRef().GetAttrValue(
                "AUTHORITY", 1)
        elif oLayer.GetSpatialRef().GetAttrValue("GEOGCS")!=None:
            spatialReference = oLayer.GetSpatialRef().GetAttrValue("GEOGCS")
        # 获取数据类型
        geom_type = oLayer.GetGeomType()
        type = ''
        # 判断空间类型
        if geom_type == ogr.wkbPoint:
            type = 'Point'
        elif geom_type == ogr.wkbMultiLineString or geom_type == ogr.wkbLineString:
            type = 'MultiLineString'
        elif geom_type == ogr.wkbPolygon or geom_type == ogr.wkbMultiPolygon:
            type = 'MultiPolygon'
        else:
            print(geom_type)
        # 定义地图范围
        extent = {'xmin': layerExtent[0], 'ymin': layerExtent[2], 'xmax': layerExtent[1], 'ymax': layerExtent[3]}
        # 获取字段信息
        oDefn = oLayer.GetLayerDefn()
        iFieldCount = oDefn.GetFieldCount()
        # 定义字段数组
        fields = []
        # 遍历字段
        for iAttr in range(iFieldCount):
            # 获取字段对象
            oField = oDefn.GetFieldDefn(iAttr)
            name = oField.GetNameRef().encode('utf-8', 'replace').decode('utf-8')
            # 定义字段对象
            field = {'name': name,'type': oField.GetFieldTypeName(oField.GetType()),'length': oField.GetWidth()}
            # 加入到数组中
            fields.append(field)
        # 定义图层
        layer = {'type': type, 'serviceEngine': 'hndist server', 'extent': extent, 'name': oLayer.GetName(),
                     'spatialReference': spatialReference,'fields': fields,'label': oLayer.GetName(),'mapName': oLayer.GetName(),
                     'path': getPath(path), 'absolutePath': path, 'spaceType': getSpaceType(type), 'fileSize': file_size,'encoding': encoding}
        # 添加到数组
        mapLayers.append(layer)
    # 返回图层对象
    return mapLayers