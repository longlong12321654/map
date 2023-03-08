"""
WSGI config for PythonCloudGIS project.

It exposes the WSGI callable as a module-level variable named ``application``.

For more information on this file, see
https://docs.djangoproject.com/en/4.1/howto/deployment/wsgi/
"""

import os
import sys
PROJECT_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.insert(0, PROJECT_DIR)  # 项目加入导包路径
virtualenv_dir = os.path.join(PROJECT_DIR, 'venv', 'Lib', 'site-packages')  # 虚拟环境python包文件夹
sys.path.insert(0, virtualenv_dir)

from django.core.wsgi import get_wsgi_application

os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'PythonCloudGIS.settings')
application = get_wsgi_application()



