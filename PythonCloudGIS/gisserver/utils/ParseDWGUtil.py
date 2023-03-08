from osgeo import ogr, gdal

# 打开数据
path = "/Users/agile/Downloads/TTTQ_ExportCAD.DWG"
ds = ogr.Open(path, 0)
if ds == None:
    print("error")
else:
    print(ds)