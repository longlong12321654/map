import platform

import jpype
from jpype import *
import os

jpype.startJVM(jpype.getDefaultJVMPath(), "-ea",
                   "-Djava.class.path=%s" % (os.path.join(os.getcwd(), r'gisserver/utils/JarImpot/DwgToDxf.jar')))

def dwg_to_dxf(fp):

    JDClass = JClass("DgToDf")
    jd = JDClass()

    # jprint = java.lang.System.out.println
    # # 调用自定义class中的函数，并输出返回值
    # jprint(jd.say("ok"))

    jd.dwg2dxf(fp)
    dxf_path = fp.replace('.dwg','.dxf')
    # print(dxf_path)
    # 关闭虚拟机
    # jpype.shutdownJVM()
    return dxf_path


if __name__ == "__main__":
    dwg_to_dxf(r"H:\xxx\pygdal\templates\data\TTTQExportCAD.dwg")
