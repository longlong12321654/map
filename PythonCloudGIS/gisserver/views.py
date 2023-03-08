# coding=utf-8
import json

# 引用基础包
from django.http import HttpResponse, JsonResponse, StreamingHttpResponse
from django.shortcuts import render
from django.views.decorators.csrf import csrf_exempt
# 引用websocket
from dwebsocket import require_websocket

# 获取重采样方法,创建影像金字塔
from gisserver.controller.DataProcessingController import getResampling, buildOverviews
# 引用分析包
from gisserver.controller.ParseDataController import analysis
# 引用空间查询
from gisserver.controller.QueryController import query
from gisserver.controller.WebsocketController import *
from gisserver.services.CadParse import dwg_to_geojson
from gisserver.utils.ShapeUtil import parsingShpFile, write2Shape
from gisserver.utils.gdal_db import get_geo_table

# 欢迎页
welcome = '欢迎使用河南数慧城市信息模型（CIM）基础平台!'


# 首页
def index(request):
    return HttpResponse(welcome)


# 空间查询(含文件夹)
@csrf_exempt
def getFolderSpatialSearch(request, folder, serverName, layerName):
    # 组合地图地址
    path = folder + "/" + serverName + ".map"
    # 调用空间查询并返回

    rdata = {"code": 200, "data": query(request, path, layerName)}

    # 转换为JSON
    strJson = json.dumps(rdata, ensure_ascii=False)
    response = HttpResponse(strJson, content_type='application/json')
    # 返回处理结果
    return response


# 空间查询（不含文件夹）
@csrf_exempt
def getSpatialSearch(request, serverName, layerName):
    # 组合地图地址
    path = serverName + ".map"
    # 调用空间查询并返回
    try:
        rdata = {"code": 200, "data": query(request, path, layerName)}
    except(ValueError, ArithmeticError):
        rdata = {"code": 500, "msg": "请求参数为空，请检查"}
    # 转换为JSON
    strJson = json.dumps(rdata, ensure_ascii=False)
    response = HttpResponse(strJson, content_type='application/json')
    # 返回处理结果
    return response


# 解析数据库空间类型数据
def get_geodb_data(request):
    db_type=request.GET.get('dbtype')
    if db_type=='postgres':
        db_usr=request.GET.get('usr')
        pwd=request.GET.get('pwd')
        dburl=request.GET.get('dburl')
        port=request.GET.get('port')
        dbname=request.GET.get('dbname')
        return JsonResponse(get_geo_table(db_usr, pwd, dburl,port,dbname,db_type))
    else:
        return JsonResponse({"code": 200, 'data': "数据库类型错误"})

# 获取空间数据
@csrf_exempt
def getSpatialDataList(request):
    # 获取body
    postBody = request.body
    # 判断必须参数是否为空
    if (postBody == None or len(postBody) < 2):
        return HttpResponse(welcome)
    # 转换为JSON
    json_result = json.loads(postBody)
    # 获取路径
    path = json_result['path']
    print(path)
    try:
        # 获取范围
        extent = analysis(request, path)
        rdata = {"code": 200, "data": extent}
    except(ValueError, ArithmeticError):
        rdata = {"code": 500, "msg": "请求参数为空，请检查"}
    # 转换为JSON
    print(rdata)
    strJson = json.dumps(rdata, ensure_ascii=False)
    response = HttpResponse(strJson, content_type='application/json')
    # 返回处理结果
    return response


# 获取重采样当前支持方法
@csrf_exempt
def getResamplings(request):
    rdata = {"code": 200, "data": getResampling()}
    # 转换为JSON
    strJson = json.dumps(rdata, ensure_ascii=False)
    response = HttpResponse(strJson, content_type='application/json')
    # 返回处理结果
    return response


# 创建影像金字塔
@csrf_exempt
def buildPyramids(request):
    # 判断是否是GET请求
    if request.method == 'GET':
        datas = request.GET
        path = datas.get('path')
        resampling = datas.get('resampling')
    # 判断路径是否为空
    if path != None:
        # 调用影像金字塔
        try:
            rdata = {"code": 200, "data": buildOverviews(path, resampling)}
        except Exception as e:
            msg = e.args[0]
            rdata = {"code": 500, "msg": msg}
    else:
        rdata = {"code": 500, "msg": "请求参数为空，请检查"}
    # 转换为JSON
    strJson = json.dumps(rdata, ensure_ascii=False)
    # 封装返回对象
    response = HttpResponse(strJson, content_type='application/json')
    # 返回处理结果
    return response


# 解析数据
@csrf_exempt
def parsedata(request):
    req_data = json.loads(request.body)
    fpath = req_data['path']  # 服务器

    if fpath.endswith(".shp"):  # shp文件
        rdata = {"code": 200, "data": parsingShpFile(fpath)}
    else:
        rdata = {"code": 200, "data": None}
    # 转换为JSON
    strJson = json.dumps(rdata, ensure_ascii=False)
    # 封装返回对象
    response = HttpResponse(strJson, content_type='application/json')
    # 返回处理结果
    return response


# 写入空间数据
@csrf_exempt
def writeShape(request):
    # 获取body
    postBody = request.body
    # 判断必须参数是否为空
    if (postBody == None or len(postBody) < 2):
        return HttpResponse(welcome)
    # 转换为JSON
    json_result = json.loads(postBody)
    # 导出文件的类型：shp/geojson/gdb/kml
    exp_type = json_result['dclx']
    layer = json_result['layer']

    res = write2Shape(layer, exp_type)
    ly_name = res['name']
    ly_path = res['path']
    fil = open(ly_path, 'rb')
    response = StreamingHttpResponse(fil)
    response['Content-Type'] = 'application/octet-stream'
    response['Content-Disposition'] = 'attachment;filename="{}"'.format(
        ly_name + "." + exp_type if exp_type != 'shp' else 'zip')
    return response


# 处理进度
@require_websocket
def rocessingProgress(request):
    websocket(request)


# # 发送消息
# def sedMsg(request):
#     sedClientMsg(request)
#     # 封装返回对象
#     response = HttpResponse("", content_type='application/json')
#     # 返回处理结果
#     return response

# 全局403、404、500错误自定义页面显示
def permission_denied(request, exception=403):
    return render(request, 'static/common/403.html')


def page_not_found(request, exception=404):
    return render(request, 'static/common/404.html')


def page_error(request):
    return render(request, 'static/common/500.html')

# cad 解析
@csrf_exempt
def cad(request):
    fpath = json.loads(request.body)['path']  # 服务器
    res = dwg_to_geojson(fpath)
    return JsonResponse({"code": 200, 'data': res})


# 矢量数据切片
def shp_to_tiles(request):
    return JsonResponse({"code": 200, 'data': 'ok'})
