import json

from osgeo import gdal, ogr
from pyproj import CRS
from pyproj import Transformer

import os
import uuid

from osgeo import gdal, ogr, osr

# 为了支持中文路径，请添加下面这句代码
gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES")
# 为了使属性表字段支持中文，请添加下面这句
gdal.SetConfigOption('SHAPE_ENCODING', 'GBK')
# 注册所有的驱动
ogr.RegisterAll()
gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES")


# os.environ['PROJ_LIB'] = os.path.abspath('./gisserver/proj')


# 单个投影转换
def marcator(x, y):
    lonlat = {}
    # CGCS2000_3_Degree_GK_Zone_40 的wkid
    crs_CGCS2000 = CRS.from_epsg(3857)

    crs_WGS84 = CRS.from_epsg(4326)
    from_crs = crs_CGCS2000
    to_crs = crs_WGS84
    transformer = Transformer.from_crs(from_crs, to_crs)
    # 即为转换后的坐标，也可以分别使⽤数组
    new_x, new_y = transformer.transform(x, y)

    return [new_x, new_y]


def VectorTranslate(
        shapeFilePath,
        format="GeoJSON",
        accessMode=None,
        selectFields=None,
        dim="XYZ",
):
    """
        转换矢量文件，包括坐标系，名称，格式，字段，类型，纬度等。
        :param shapeFilePath: 要转换的矢量文件
        :param saveFolderPath: 生成矢量文件保存目录
        :param format: 矢量文件格式，强烈建议不要使用ESRI Shapefile格式。
        :param accessMode:None代表creation,'update','append','overwrite'
        :param dstSrsESPG: 目标坐标系EPSG代码，4326是wgs84地理坐标系
        :param selectFields: 需要保留的字段列表如果都保留，则为None
        :param geometryType: 几何类型,"POLYGON","POINT"。。。
        :param dim: 新矢量文件坐标纬度,建议查阅官方API。
        :return:
	"""
    # 本地投影加载
    os.environ['PROJ_LIB'] = os.path.join(os.getcwd(), 'gisserver\proj')
    data = ogr.Open(shapeFilePath)
    layer = data.GetLayer()

    iLayerCount = data.GetLayerCount()
    cordinate = '500000'
    scale_factor='1.0'
    latitude_of_origin='0.0'
    for ilayer in range(iLayerCount):
        # 获取图层
        oLayer = data.GetLayerByIndex(ilayer)
        # 获取地图范围
        layerExtent = oLayer.GetExtent()
        cordinate=str(max(layerExtent))
        if min(layerExtent)<500000:
            scale_factor='10'
            latitude_of_origin='35.0'
        print(layerExtent)

    print(latitude_of_origin)
    layerName = layer.GetName()
    data.Destroy()

    srcSRS = osr.SpatialReference()
    srcSRS.ImportFromWkt('PROJCS["CGCS_2000_3_Degree_GK_Zone_38",GEOGCS["GCS_China_Geodetic_Coordinate_System_2000",'
                         'DATUM["D_China_2000",SPHEROID["CGCS2000",6378137.0,0.0]],PRIMEM["Greenwich",0.0],'
                         'UNIT["Degree",0.0174532925199433]],PROJECTION["Gauss_Kruger"],PARAMETER["False_Easting",'
                         +cordinate+'],PARAMETER["False_Northing",0.0],PARAMETER["Central_Meridian",114.0],'
                         'PARAMETER["Scale_Factor",'+scale_factor+'],PARAMETER["Latitude_Of_Origin",'+latitude_of_origin+'],UNIT["Meter",1.0]]')

    dstsrs = osr.SpatialReference()
    dstsrs.ImportFromEPSG(4326)

    lastpath = os.path.abspath(os.path.join(shapeFilePath, ".."))
    destDataName = str(uuid.uuid1()) + ".geojson"
    destDataPath = os.path.join(lastpath, destDataName)
    options = gdal.VectorTranslateOptions(
        format=format,
        accessMode=accessMode,
        srcSRS=srcSRS,
        dstSRS=dstsrs,
        reproject=True,
        skipFailures=True,
        emptyStrAsNull=True,
        forceNullable=True,

        selectFields=selectFields,
        layerName=layerName,
        # resolveDomains=True,
        dim=dim
    )
    gdal.VectorTranslate(
        destDataPath,
        srcDS=shapeFilePath,
        options=options
    )
    # os.remove(shapeFilePath)
    return [destDataPath, shapeFilePath]


# VectorTranslate(r'H:/dd\利民.geojson')

if __name__ == '__main__':
    # VectorTranslate(r"H:\txcl\利民.geojson")
    # print(marcator(3906012.698094999883324,489121.346742999972776))

    # crs_CGCS2000 =CRS.from_epsg(4479)    #meter#这两个都不行
    # crs_CGCS2000=CRS.from_epsg(4490)    #degree#这两个都不行

    os.environ['PROJ_LIB'] = r"H:\xxx\pygis\gisserver\proj"

    crs_CGCS2000 = CRS.from_wkt('PROJCS["CGCS_2000_3_Degree_GK_Zone_38",GEOGCS['
                                '"GCS_China_Geodetic_Coordinate_System_2000",DATUM["D_China_2000",'
                                'SPHEROID["CGCS2000",6378137.0,88.0]],PRIMEM["Greenwich",0.0],UNIT["Degree",'
                                '0.0174532925199433]],PROJECTION["Gauss_Kruger"],PARAMETER["False_Easting",1000000.0],'
                                'PARAMETER["False_Northing",0.0],PARAMETER["Central_Meridian",114.0],'
                                'PARAMETER["Scale_Factor",10],PARAMETER["Latitude_Of_Origin",35.0],UNIT["Meter",'
                                '1.0]]')  # degree
    crs_WGS84 = CRS.from_epsg(4326)
    from_crs = crs_CGCS2000
    to_crs = crs_WGS84
    transformer = Transformer.from_crs(from_crs, to_crs)
    new_x, new_y = transformer.transform(931880.094315743306652, 73944.490485179427196)  # new_x,new_y即为转换后的坐标，也可以分别使用数组
    print(new_x)
    print(new_y)

    # ds = ogr.Open(r"H:\txcl\利民.geojson", 0)
    # iLayerCount = ds.GetLayerCount()
    # for ilayer in range(iLayerCount):
    #     # 获取图层
    #     oLayer = ds.GetLayerByIndex(ilayer)
    #     # 获取地图范围
    #     layerExtent = oLayer.GetExtent()
    #     print(layerExtent)
    #     if 500000 < max(layerExtent) < 38500000:
    #         print(123)
