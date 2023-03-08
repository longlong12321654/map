#coding=utf-8
import json
from django.conf import settings
from gisserver.services.MapQueryService import mapQuery

# 空间查询
def query(request, path, layerName):
    # 判断是否是GET请求
    if request.method == 'GET':
        datas = request.GET
        isPublishing = datas.get('isPublishing')    # 是否是发布服务
    # 获取真实路径
    path = getRealPath(isPublishing, path)
    # 获取地图服务
    mapService = getMapService(isPublishing, path)
    # 遍历获取图层对象
    for tmplayer in mapService['mapLayerList']:
        if (layerName == tmplayer['name']):
            layer = tmplayer
    path = layer['path']
    return mapQuery(request,path,layer)

# 获取地图服务
def getMapService(isPublishing, path):
    map_file = open(path, 'r', encoding='utf-8')
    try:
        context = None
        lines = map_file.readlines()
        for line in lines:
            if line.find('/******') > -1:
                context = ''
                continue
            elif line.find("******/") > -1:
                break
            if (context != None):
                context = line
    finally:
        map_file.close()
    return json.loads(context)

# 获取真实路径
def getRealPath(isPublishing, path):
    if (isPublishing == None):
        return settings.SERVICES_PATH + "mapserver/" + path
    else:
        return settings.SERVICES_PATH + "cache/" + path

