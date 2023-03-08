# -*- coding: UTF-8 -*-
#引用gdal包
import json

from osgeo import gdal
# 引用解析库
from gisserver.services.TIFParseService import parsetif
from gisserver.services.ShpParseService import parseshp
from gisserver.common.ParseData import getName,getPath
# 引用OS包
import os
os.environ['PROJ_LIB'] = os.path.join(os.getcwd(),'gisserver\proj')
#时空文件解析
def analysis(request, path):
    # 开启异常
    gdal.UseExceptions()
    # 定义图层对象
    mapLayers = []
    # 判断当前路径类型
    if(os.path.isfile(path)):
        maplayer = getSpatialFile(path)
        mapLayers.append(maplayer)
    elif path.endswith(".gdb"):
        return parseshp(path)
    else:
        dirs = os.listdir(path)
        # 遍历所有文件和文件夹
        for file in dirs:
            if path[-1] == '/':
                # 获取图层对象
                maplayer = getSpatialFile(path+file)
            else:
                # 获取图层对象
                maplayer = getSpatialFile(path + '/' + file)

            if(maplayer != None):
                # 遍历图层
                for layer in maplayer:
                    mapLayers.append(layer)
    return mapLayers

# 解析时空文件
def getSpatialFile(path):
    os.environ['PROJ_LIB'] = os.path.join(os.getcwd(), 'gisserver\proj')
    if (os.path.isfile(path)):
        if(path.endswith(".tif")):
            # 返回解析tif
            return parsetif(path)
        elif(path.endswith(".shp")):
            return parseshp(path)
        elif (path.endswith(".glb")):
            # 获取文件大小
            file_size = os.path.getsize(path)
            # 组合图层结果
            layer = {'type': 'glb', 'serviceEngine': 'glb', 'name': getName(path),
                     'label': getName(path), 'mapName': getName(path),
                     'path': getPath(path), 'absolutePath': path, 'fileSize': file_size}
            return [layer]
        elif (path.endswith(".gltf")):
            # 获取文件大小
            file_size = os.path.getsize(path)
            # 组合图层结果
            layer = {'type': 'gltf', 'serviceEngine': 'gltf', 'name': getName(path),
                     'label': getName(path), 'mapName': getName(path),
                     'path': getPath(path), 'absolutePath': path, 'fileSize': file_size}
            return [layer]
        elif (path.endswith(".json") and os.path.basename(path)=='tileset.json'):
            tileset = open(path, "r")
            if tileset != None:
                tilesetjson = json.load(tileset)
                if tilesetjson!= None:
                    path = os.path.dirname(path)
                    height = 0
                    box = []
                    if tilesetjson['root']!=None and tilesetjson['root']['boundingVolume']!=None and tilesetjson['root']['boundingVolume']['box']!=None:
                        box = tilesetjson['root']['boundingVolume']['box']
                        if box!=None:
                            height = box[11]

                    layer = {'type': 'json', 'serviceEngine': '3dtiles', 'name': getName(path),
                             'label': getName(path), 'mapName': getName(path), 'height': height,'version': tilesetjson['asset']['version'], 'box': box,
                             'path': getPath(path), 'absolutePath': path}

                    return [layer]
            return None
        elif (path.endswith(".IoT")):
            layer = {'type': 'iot', 'serviceEngine': 'iot', 'name': getName(path),
                     'label': getName(path), 'mapName': getName(path),
                     'path': getPath(path), 'absolutePath': path}
            return [layer]
        else:
            return None
    else:
        if 'Tile_' in path:
            return None
        else:
            return [{'name': os.path.basename(path), 'type': 'directory', 'path': path, 'absolutePath': path,'label': os.path.basename(path), 'mapName': os.path.basename(path)}]