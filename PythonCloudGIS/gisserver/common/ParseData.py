# 引用os
import os
from osgeo import gdal

# 获取名称
def getName(name):
    return os.path.basename(name).replace(".tif","").replace(".shp","").replace(".glb","").replace(".gltf","").replace(".IoT","")

# 获取路径
def getPath(name):
    return name

# 设置编码格式
def setEncoding(encoding):
    if encoding!=None:
        gdal.SetConfigOption("SHAPE_ENCODING", encoding)
    else:
        gdal.SetConfigOption('SHAPE_ENCODING', 'GBK')

# 获取编码格式
def getEncoding(path):
    cpgPath = path.replace('.shp', '.cpg')
    if (os.path.exists(cpgPath)):
        try:
            cpgPath_object = open(cpgPath, 'r')
            encoding = cpgPath_object.read()  # 结果为str类型
            return encoding
        except:
            return None
        # finally:
        #     cpgPath_object.close()

# 获取空间类型
def getSpaceType(type):
    if type == 'MultiPolygon':
        return 'POLYGON'
    elif type == 'MultiLineString':
        return 'LINE'
    elif type == 'Point':
        return 'POINT'
    elif type == 'tif':
        return 'RASTER'
    return None