# 引用gdal包
from osgeo import gdal
from functools import partial

# 引用消息发送
from gisserver.controller.WebsocketController import sedClientMsg

# 重采样方法
pszResamplings = [
    {'最近邻法': 'NEAREST'},
    {'取平均法': 'AVERAGE'},
    {'双线性插值': 'BILINEAR'},
    {'立方插值': 'CUBIC'},
    {'高斯插值': 'GAUSS'},
    {'兰佐斯插值': 'LANCZOS'}
]

# 重采样：抽样率
panOverviewList = [2, 4, 8, 16, 32, 64, 128]

# 返回重采样当前支持方法
def getResampling():
    return pszResamplings

# 建立影像金字塔
def buildOverviews(path, resampling):
    # 如果为选择金字塔方法则设置默认方法
    if resampling == None:
        resampling = "NEAREST"
    # else:
    #     # 遍历获取当前处理方法
    #     for pszResampling in pszResamplings:
    #         tmp_resampling = pszResampling.get(resampling)
    #         if tmp_resampling!=None:
    #             break
    #如果为获取到数据则抛出异常
    if resampling == None:
        raise Exception("无效的金字塔建立方法:" + resampling)
    # 打开空间数据
    dataset = gdal.Open(path)
    # 调用构建金字塔接口(最近邻nearest)
    dataset.BuildOverviews(resampling, panOverviewList, partial(progressFunc, path))
    # 回收对象
    dataset = None
    return {"name": path, "type": "dataprocessing", "progress": 100}

# 返回进度
def progressFunc(path,dfComplete, pszMessage, pProgressArg):
    sedClientMsg({"name": path, "type": "dataprocessing", "progress": int(dfComplete*100)})
