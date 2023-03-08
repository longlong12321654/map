# 路由
from django.urls import path, re_path
from gisserver import views

# from rest_framework_swagger.views import get_swagger_view
# schema_view = get_swagger_view(title='数慧CIM - MapServer')

# from rest_framework_swagger.views import get_swagger_view
# schema_view = get_swagger_view(title='数慧CIM - MapServer')


urlpatterns = [
    # 首页
    path('', views.index),
    #数据库连接
    path('p_cim/rest/engineering/getDbDataList', views.get_geodb_data),
    path('p_cim/rest/engineering/getSpatialDataList', views.getSpatialDataList),
    path('p_cim/rest/services/<str:folder>/<str:serverName>/MapServer/<str:layerName>/query',
         views.getFolderSpatialSearch),
    path('p_cim/rest/services/<str:serverName>/MapServer/<str:layerName>/query', views.getSpatialSearch),
    path('p_cim/rest/services/dataprocessing/buildPyramids', views.buildPyramids),
    path('p_cim/rest/services/dataprocessing/getResamplings', views.getResamplings),
    path('p_cim/rest/services/dataprocessing/rocessingProgress', views.rocessingProgress),
    path('p_cim/rest/services/dataprocessing/parsedata', views.parsedata),
    path('p_cim/rest/services/dataprocessing/writeShape', views.writeShape),
    path('p_cim/rest/services/dataprocessing/cad', views.cad),  # CAD文件加载
    path('p_cim/rest/services/dataprocessing/shptiles', views.shp_to_tiles),  # 矢量切片

]
