import os

from osgeo import gdal, ogr

from gisserver.common.ParseData import getSpaceType

gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES")
# 为了使属性表字段支持中文，请添加下面这句
gdal.SetConfigOption('SHAPE_ENCODING', 'GBK')
# 注册所有的驱动
ogr.RegisterAll()
os.environ['PROJ_LIB'] = r"E:\PythonCloudGIS\gisserver\proj"

#oracle链接
def get_orcl_talbe():
    db_info = 'OCI:ZSTPUSR/ZSTPUSR@192.168.1.119:1521:orcl'
    ds = ogr.Open(db_info, 0)
    print(ds)


# get_orcl_talbe()

def get_geo_table(usr, pwd, dburl,port,dbname,dbtype):

    geodb_data = []

    db_info = 'PG:dbname='+dbname+' host='+dburl+' port='+port+' user='+usr+' password='+pwd
    try:
        ds = ogr.Open(db_info, 0)
        iLayerCount = ds.GetLayerCount()
        for ilayer in range(iLayerCount):
            # 获取图层
            oLayer = ds.GetLayerByIndex(ilayer)
            # 获取地图范围
            layerExtent = oLayer.GetExtent()
            # 获取空间参考
            spatialReference = ""

            try:
                if (oLayer.GetSpatialRef() != None and oLayer.GetSpatialRef().GetAttrValue("AUTHORITY", 0) != None):
                    spatialReference = oLayer.GetSpatialRef().GetAttrValue("AUTHORITY",
                                                                           0) + " " + oLayer.GetSpatialRef().GetAttrValue(
                        "AUTHORITY", 1)
                elif oLayer.GetSpatialRef().GetAttrValue("GEOGCS") != None:
                    spatialReference = oLayer.GetSpatialRef().GetAttrValue("GEOGCS")
                geom_word = 'the_geom'
            except:
                geom_word = 'geom'

            if (oLayer.GetSpatialRef() != None and oLayer.GetSpatialRef().GetAttrValue("AUTHORITY", 0) != None):
                spatialReference = oLayer.GetSpatialRef().GetAttrValue("AUTHORITY",
                                                                       0) + " " + oLayer.GetSpatialRef().GetAttrValue(
                    "AUTHORITY", 1)
            # 获取数据类型
            geom_type = oLayer.GetGeomType()
            type = ''
            # 判断空间类型
            if geom_type == ogr.wkbPoint:
                type = 'POINT'
            elif geom_type == ogr.wkbMultiLineString or geom_type == ogr.wkbLineString:
                type = 'LINE'
            elif geom_type == ogr.wkbPolygon or geom_type == ogr.wkbMultiPolygon:
                type = 'POLYGON'
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
                field = {'name': name, 'type': oField.GetFieldTypeName(oField.GetType()), 'length': oField.GetWidth()}
                # 加入到数组中
                fields.append(field)
            # 定义图层
            if not str(oLayer.GetName()).startswith('tiger.'):
                layer = {'type': dbtype, 'serviceEngine': 'hndist server', 'extent': extent, 'name': oLayer.GetName(),
                         'spatialReference': spatialReference, 'fields': fields, 'label': oLayer.GetName(),
                         'mapName': oLayer.GetName(),
                         'path': db_info.replace('PG:',''), 'absolutePath': db_info.replace('PG:',''), 'spaceType': type, 'fileSize': oLayer.GetFeatureCount(),
                         'encoding': 'utf-8','geom':geom_word}
                geodb_data.append(layer)
        return {'code':200,'data':geodb_data}
    except:
        return {'code': 404, 'data': '数据库连接失败！请检查所填信息'}



