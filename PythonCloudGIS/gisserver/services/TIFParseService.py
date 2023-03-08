#引用gdal
from osgeo import gdal
from gisserver.common.ParseData import getName, getPath
#引用os
import os

#解析时空文件
def parsetif(path):
    # 打开空间数据
    dataset = gdal.Open(path)
    # 获取文件大小
    file_size = os.path.getsize(path)
    # 图像长度
    width = dataset.RasterXSize
    # 图像宽度
    height = dataset.RasterYSize
    # 读取仿射变换
    geotrans = dataset.GetGeoTransform()
    # 读取投影
    proj = dataset.GetProjection()
    # 获取地图初始范围
    minx = geotrans[0]
    miny = geotrans[3] + width * geotrans[4] + height * geotrans[5]
    maxx = geotrans[0] + width * geotrans[1] + height * geotrans[2]
    maxy = geotrans[3]
    # 定义地图范围
    extent = {'xmin': minx, 'ymin': miny, 'xmax': maxx, 'ymax': maxy}
    # 定义图层
    layer = {'type': 'tif', 'serviceEngine': 'hndist server', 'extent': extent, 'name': getName(path),'label': getName(path),'mapName': getName(path),
             'path': getPath(path), 'absolutePath': path, 'fileSize': file_size, 'spaceType':'RASTER'}
    # 返回初始范围
    return [layer]