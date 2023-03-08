import json
import os
import uuid

from osgeo import ogr, gdal

from gisserver.utils.cordinate_transform import VectorTranslate
from gisserver.utils.dwgtodxf import dwg_to_dxf

ogr.RegisterAll()
gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES")
gdal.SetConfigOption("SHAPE_ENCODING", "")
gdal.SetConfigOption("DXF_ENCODING", "ASCII")  # 设置DXF缺省编码

def dwg_to_geojson(file_path):

    #dwg转dxf
    dxf_path=dwg_to_dxf(file_path)

    basename = os.path.split(str(dxf_path))[0]
    filename = os.path.basename(dxf_path).split('.')[0]
    outfile = "{}.geojson".format(basename + '\\' + filename)

    poDS = ogr.Open(dxf_path, False)
    poDriver = ogr.GetDriverByName("GeoJson")
    # 保存文件
    poDriver.CopyDataSource(poDS, outfile)
    poDS.Destroy()

    #投影转换
    geojson_path=VectorTranslate(outfile)

    with open(geojson_path[0],encoding='utf-8-sig') as f:
        gjson=json.load(f)
    #移除生成的临时文件
    os.remove(outfile)
    os.remove(dxf_path)
    return gjson

def blwj(pt):
    g = os.walk(pt)
    for path,dir_list,file_list in g:
        for file_name in file_list:
            print(os.path.join(path,file_name))

if __name__ == '__main__':
    # dwg_to_geojson(r"H:\dd\利民.dxf")
    dxf_path=r"H:\dd\利民.dxf"
    poDS = ogr.Open(r"H:\dd\利民.dxf", False)
    poDriver = ogr.GetDriverByName("GeoJson")
    basename = os.path.split(str(dxf_path))[0]
    filename = os.path.basename(dxf_path).split('.')[0]
    outfile = "{}.geojson".format(basename + '\\' + filename)
    poDriver.CopyDataSource(poDS, outfile)
    poDS.Destroy()

    # blwj(r"H:\新建文件夹")
