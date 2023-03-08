# shp数据切片
import os
import shutil

from osgeo import ogr

os.environ['PROJ_LIB'] = os.path.join(os.getcwd(),r'gisserver\proj')
def shp_tile(shp_path):
    shp_open = ogr.Open(shp_path, False)
    geo_driver = ogr.GetDriverByName("MVT")
    output_path=shp_path.replace(".shp",".pbf")
    if os.path.exists(output_path):
        shutil.rmtree(output_path)

    # 保存文件
    geo_driver.CopyDataSource(shp_open, output_path)
    shp_open.Destroy()
    return output_path


# shp_tile(r"D:\hndist\vectordata\1.矢量数据\中国\tmp\gis_osm_roads_free_1.shp")