import os
import zipfile


def support_gbk(zip_file: zipfile.ZipFile):
    name_to_info = zip_file.NameToInfo
    for name, info in name_to_info.copy().items():
        real_name = name.encode('cp437').decode('gbk')
        if real_name != name:
            info.filename = real_name
            del name_to_info[name]
            name_to_info[real_name] = info
    return zip_file


def sx(path, target):
    for f in os.listdir(path):
        npath = path + '/' + f
        if os.path.splitext(npath)[1] == target:
            return npath

    return None
