import os
import shutil
import uuid
import zipfile

from osgeo import ogr, gdal

from gisserver.common.ParseData import getEncoding, setEncoding


# 解析SHP文件
def parsingShpFile(path):
    mapLayer = {}
    # 设置编码格式
    setEncoding(getEncoding(path))
    # 打开数据
    ds = ogr.Open(path, 0)
    # 获取该数据源中的图层个数，一般shp数据图层只有一个，如果是mdb、dxf等图层就会有多个
    iLayerCount = ds.GetLayerCount()
    # 遍历图层
    for ilayer in range(iLayerCount):
        # 获取图层
        oLayer = ds.GetLayerByIndex(ilayer)
        # 获取数据类型
        geometryType = oLayer.GetGeomType()
        layerName = oLayer.GetName()
        # 判断空间类型
        if geometryType == ogr.wkbPoint:
            geometryType = 'Point'
        elif geometryType == ogr.wkbMultiLineString or geometryType == ogr.wkbLineString:
            geometryType = 'Polyline'
        elif geometryType == ogr.wkbPolygon or geometryType == ogr.wkbMultiPolygon:
            geometryType = 'Polygon'
        else:
            print(geometryType)
        # 获取图层中的属性表表头并输出
        oDefn = oLayer.GetLayerDefn()
        iFieldCount = oDefn.GetFieldCount()
        fields = []
        for iAttr in range(iFieldCount):
            oField = oDefn.GetFieldDefn(iAttr)
            name = oField.GetNameRef().encode('utf-8', 'replace').decode('utf-8')
            fields.append({"name": name, "type": oField.GetFieldTypeName(oField.GetType()),
                           "length": oField.GetWidth()})
        # 输出图层中的要素个数
        total = oLayer.GetFeatureCount()
        oFeature = oLayer.GetNextFeature()
        features = []
        # 下面开始遍历图层中的要素
        while oFeature is not None:
            attributes = {}
            # 获取要素中的属性表内容
            for iField in range(iFieldCount):
                oFieldDefn = oDefn.GetFieldDefn(iField)
                nameRef = str(oFieldDefn.GetNameRef())
                type = oFieldDefn.GetType()
                if type == 0:
                    attributes[nameRef] = str(oFeature.GetField(nameRef))
                elif type == 1:
                    attributes[nameRef] = str(oFeature.GetFieldAsIntegerList(nameRef))
                elif type == 2:
                    attributes[nameRef] = str(oFeature.GetFieldAsDouble(nameRef))
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
            # 获取要素中的几何体
            geometry = oFeature.GetGeometryRef()

            feature["geometry"] = geometry.ExportToJson()
            # 加入到features 中
            features.append(feature)
            # 读取下一个要素
            oFeature = oLayer.GetNextFeature()
        # 封装图层对象
        mapLayer = {"layerName": layerName, "geometryType": geometryType, "fields": fields, "features": features}
    # 返回图层对象
    return mapLayer


# 解析文本文件
def parsingTxtFile(path):
    print("")


# 写入文件
def write2Shape(layer, exp_type):
    global file_prefix, strDriverName, tem_save_path
    #文件名
    layerName = layer["layerName"]
    if layerName == None:
        layerName = 'hndist'

    # 导出类型
    tem_name=str(uuid.uuid1()).replace('-', '')
    if exp_type == 'shp':
        strDriverName = "ESRI Shapefile"
        tem_save_path = os.path.join(os.getcwd(), 'templates/tem',tem_name)
        os.makedirs(tem_save_path)
        path=os.path.join(tem_save_path,layerName+'.shp')

    elif exp_type == 'geojson':
        strDriverName = "GeoJSON"
        path = os.path.join(os.getcwd(), 'templates/tem', str(uuid.uuid1()).replace('-', '') + '.json')
    geometryType = layer["geometryType"]
    if geometryType == None:
        print("%s 空间类型不能为空！\n", geometryType)
        return
    # 支持中文路径
    gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES")
    # 属性表字段支持中文
    gdal.SetConfigOption("SHAPE_ENCODING", "GBK")
    # 注册驱动
    ogr.RegisterAll()
    oDriver = ogr.GetDriverByName(strDriverName)
    if oDriver == None:
        print("%s 驱动不可用！\n", strDriverName)
        return
    # 创建数据源
    oDS = oDriver.CreateDataSource(path)
    if oDS == None:
        print("创建文件【%s】失败！", path)
        return

    # 创建图层，创建一个多边形图层，这里没有指定空间参考，如果需要的话，需要在这里进行指定
    papszLCO = ["ENCODING=GBK"]
    if geometryType == "Polygon":
        oLayer = oDS.CreateLayer(layerName, None, ogr.wkbPolygon, papszLCO)
    elif geometryType == "Polyline":
        oLayer = oDS.CreateLayer(layerName, None, ogr.wkbLineString, papszLCO)
    else:
        oLayer = oDS.CreateLayer(layerName, None, ogr.wkbPoint, papszLCO)
    if oLayer == None:
        print("图层创建失败！\n")
        return
    if layer != None:
        oDefn = oLayer.GetLayerDefn()
        # 遍历字段
        fields = layer['fields']
        if fields != None:
            for field in fields:
                length = field['length']
                if length == None:
                    length = 100
                name = field['name']
                type = field['type']
                if type == "Integer":
                    oFieldID = ogr.FieldDefn(name, ogr.OFTInteger)
                    oLayer.CreateField(oFieldID, 1)
                elif type == "String":
                    oFieldName = ogr.FieldDefn(name, ogr.OFTString)
                    oFieldName.SetWidth(length)
                    oLayer.CreateField(oFieldName, 1)
                elif type == "Real":
                    oFieldName = ogr.FieldDefn(name, ogr.OFTReal)
                    oLayer.CreateField(oFieldName, 1)
        features = layer['features']
        if features != None:
            for feature in features:
                if feature != None:
                    attributes = feature['attributes']
                    if attributes != None:
                        oFeatureTriangle = ogr.Feature(oDefn)
                        for index, k in enumerate(attributes):
                            oFeatureTriangle.SetField(index, attributes[k])
                        geomTriangle = ogr.CreateGeometryFromJson(feature['geometry'])
                        oFeatureTriangle.SetGeometry(geomTriangle)
                        oLayer.CreateFeature(oFeatureTriangle)
    oDS.Destroy()

    #shp文件要进行压缩
    if exp_type == 'shp':
        zip_file = zipfile.ZipFile(tem_save_path+'.zip', 'w')
        zip_file.write(tem_save_path, compress_type=zipfile.ZIP_DEFLATED)
        zip_file.close()
        shutil.rmtree(tem_save_path)

    return {"name": layerName, "path": path if exp_type != 'shp' else tem_save_path+'.zip', "state": True}
# print(parsingShpFile("/Users/agile/Downloads/vectordata/1.矢量数据/data/省级界线.shp"))
# write2Shape("/Users/agile/Desktop/test/test.shp",None)
